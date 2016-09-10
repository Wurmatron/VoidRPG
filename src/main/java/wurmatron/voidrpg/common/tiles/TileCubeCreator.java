package wurmatron.voidrpg.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.recipe.ICubeCreatorRecipe;
import wurmatron.voidrpg.common.cube.CubeCreatorRecipeHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TileCubeCreator extends TileEntity implements ITickable, IInventory {

		private ItemStack[] inv = new ItemStack[30];
		private ICubeCreatorRecipe activeRecipe;
		public int proccessingTime;

		@Override
		public void update () {
				if (activeRecipe != null) {
						proccessingTime--;
						if (proccessingTime <= 0) {
								consumeRecipeItems(activeRecipe);
								setInventorySlotContents(0, activeRecipe.getOutputCube());
								activeRecipe = null;
						}
				} else
						checkForValidRecipe();
		}

		private void consumeRecipeItems (ICubeCreatorRecipe recipe) {
				for (ItemStack stored : recipe.getInputs()) {
						for (int r = 0; r < inv.length; r++) {
								if (stored.isItemEqual(inv[r])) {
										setInventorySlotContents(r, null);
								}
						}
				}
		}

		private boolean checkForValidRecipe () {
				for (ICubeCreatorRecipe recipe : CubeCreatorRecipeHandler.getRecipes()) {
						HashMap<ItemStack, Boolean> temp = new HashMap<ItemStack, Boolean>();
						for (ItemStack r : recipe.getInputs()) {
								List<String> test = new ArrayList<String>();
								for (ItemStack stored : inv) {
										if (r.isItemEqual(stored))
												test.add("true");
										else
												test.add("false");
										temp.put(r, test.contains("true") ? true : false);
								}
						}
						for (ItemStack r : recipe.getInputs()) {
								if (!temp.get(r)) {
										activeRecipe = null;
										return false;
								}
						}
						activeRecipe = recipe;
						proccessingTime = activeRecipe.getTimeInTicks();
						return true;
				}
				activeRecipe = null;
				return false;
		}

		public void setActiveRecipe (ICubeCreatorRecipe recipe) {
				this.activeRecipe = recipe;
		}

		public ICubeCreatorRecipe getActiveRecipe () {
				return activeRecipe;
		}

		@Override
		public int getSizeInventory () {
				return inv.length;
		}

		@Nullable
		@Override
		public ItemStack getStackInSlot (int index) {
				return inv[index];
		}

		@Override
		public ItemStack decrStackSize (int slot, int amt) {
				ItemStack stack = getStackInSlot(slot);
				if (stack != null) {
						if (stack.stackSize <= amt) {
								setInventorySlotContents(slot, null);
						} else {
								stack = stack.splitStack(amt);
								if (stack.stackSize == 0)
										setInventorySlotContents(slot, null);
						}
				}
				return stack;
		}

		@Nullable
		@Override
		public ItemStack removeStackFromSlot (int index) {
				return null;
		}

		@Override
		public void setInventorySlotContents (int index, ItemStack stack) {
				inv[index] = stack;
				if (stack != null && stack.stackSize > getInventoryStackLimit())
						stack.stackSize = this.getInventoryStackLimit();
				markDirty();
		}

		@Override
		public int getInventoryStackLimit () {
				return 64;
		}

		@Override
		public boolean isUseableByPlayer (EntityPlayer player) {
				return true;
		}

		@Override
		public void openInventory (EntityPlayer player) {

		}

		@Override
		public void closeInventory (EntityPlayer player) {

		}

		@Override
		public boolean isItemValidForSlot (int index, ItemStack stack) {
				return true;
		}

		@Override
		public int getField (int id) {
				return 0;
		}

		@Override
		public void setField (int id, int value) {

		}

		@Override
		public int getFieldCount () {
				return 0;
		}

		@Override
		public void clear () {

		}

		@Override
		public String getName () {
				return "cubeCreator";
		}

		@Override
		public boolean hasCustomName () {
				return false;
		}

		@Override
		public NBTTagCompound writeToNBT (NBTTagCompound compound) {
				super.writeToNBT(compound);
				NBTTagList items = new NBTTagList();
				for (int i = 0; i < getSizeInventory(); ++i) {
						if (getStackInSlot(i) != null) {
								NBTTagCompound item = new NBTTagCompound();
								item.setByte("Slot", (byte) i);
								getStackInSlot(i).writeToNBT(item);
								items.appendTag(item);
						}
				}
				compound.setTag("Inventory", items);
				if (activeRecipe != null) {
						ResourceLocation recipeOutput = Item.REGISTRY.getNameForObject(activeRecipe.getOutputCube().getItem());
						compound.setString("ActiveRecipe", recipeOutput.getResourceDomain() + ":" + recipeOutput.getResourcePath() + "@" + activeRecipe.getOutputCube().getItemDamage() + "%" + activeRecipe.getOutputCube().stackSize);
				} else
						compound.setString("ActiveRecipe", "null");
				compound.setInteger("Time", proccessingTime);
				return compound;
		}

		@Override
		public void readFromNBT (NBTTagCompound compound) {
				super.readFromNBT(compound);
				NBTTagList items = compound.getTagList("Inventory", compound.getId());
				for (int i = 0; i < items.tagCount(); ++i) {
						NBTTagCompound item = items.getCompoundTagAt(i);
						byte slot = item.getByte("Slot");
						if (slot >= 0 && slot < getSizeInventory())
								setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
				}
				if (compound.getString("ActiveRecipe") != "null") {
						ResourceLocation recipeOutput = new ResourceLocation(compound.getString("ActiveRecipe").substring(0, compound.getString("ActiveRecipe").indexOf(":")), compound.getString("ActiveRecipe").substring(compound.getString("ActiveRecipe").indexOf(":"), compound.getString("ActiveRecipe").indexOf("@")));
						int stackSize = Integer.getInteger(compound.getString("ActiveRecipe").substring(compound.getString("ActiveRecipe").indexOf("@") - 1, compound.getString("ActiveRecipe").length()));
						int meta = Integer.getInteger(compound.getString("ActiveRecipe").substring(compound.getString("ActiveRecipe").indexOf("@") + 1, compound.getString("ActiveRecipe").indexOf(":")));
						ItemStack outputStack = new ItemStack(Item.REGISTRY.getObject(recipeOutput), stackSize, meta);
						if (outputStack != null) {
								for (ICubeCreatorRecipe recipe : CubeCreatorRecipeHandler.getRecipes())
										if (recipe.getOutputCube().isItemEqual(outputStack))
												activeRecipe = recipe;
						} else
								compound.setString("ActiveRecipe", "null");
				}
				proccessingTime = compound.getInteger("Time");
		}
}