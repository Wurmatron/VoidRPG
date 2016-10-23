package wurmatron.voidrpg.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import wurmatron.voidrpg.api.recipe.ICubeCreatorRecipe;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.cube.CubeCreatorRecipeHandler;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.StackHelper;

import javax.annotation.Nullable;

public class TileCubeCreator extends TileEntity implements IInventory, ITickable {

		private ItemStack[] inventory = new ItemStack[14];
		private ICubeCreatorRecipe activeRecipe;
		private int timer;
		private int UPDATE_TIMER = Settings.cubeCreatorUpdatePeriod * 20;

		@Override
		public void update () {
				if (!worldObj.isRemote) {
						if (activeRecipe == null && worldObj.getWorldTime() % UPDATE_TIMER == 0)
								hasValidRecipe();
						if (timer <= 0 && activeRecipe != null) {
								addOutput(activeRecipe.getOutputCube());
								activeRecipe = null;
						} else if (timer > 0)
								timer--;
				}
		}

		@Override
		public int getSizeInventory () {
				return inventory.length;
		}

		@Nullable
		@Override
		public ItemStack getStackInSlot (int index) {
				if (index < getSizeInventory())
						return inventory[index];
				return null;
		}

		@Override
		public NBTTagCompound writeToNBT (NBTTagCompound nbt) {
				super.writeToNBT(nbt);
				NBTTagList list = new NBTTagList();
				for (int i = 0; i < this.getSizeInventory(); ++i) {
						if (this.getStackInSlot(i) != null) {
								NBTTagCompound stackTag = new NBTTagCompound();
								stackTag.setByte(NBT.SLOT, (byte) i);
								this.getStackInSlot(i).writeToNBT(stackTag);
								list.appendTag(stackTag);
						}
				}
				nbt.setTag(NBT.INVENTORY, list);
				return nbt;
		}


		@Override
		public void readFromNBT (NBTTagCompound nbt) {
				super.readFromNBT(nbt);
				NBTTagList list = nbt.getTagList(NBT.INVENTORY, 10);
				for (int i = 0; i < list.tagCount(); ++i) {
						NBTTagCompound stackTag = list.getCompoundTagAt(i);
						int slot = stackTag.getByte(NBT.SLOT) & 255;
						setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
				}
		}

		@Override
		public ItemStack decrStackSize (int index, int count) {
				if (getStackInSlot(index) != null) {
						ItemStack stack;
						if (getStackInSlot(index).stackSize <= count) {
								stack = getStackInSlot(index);
								setInventorySlotContents(index, null);
								markDirty();
								return stack;
						} else {
								stack = getStackInSlot(index).splitStack(count);
								if (getStackInSlot(index).stackSize <= 0)
										setInventorySlotContents(index, null);
								else
										setInventorySlotContents(index, getStackInSlot(index));
								markDirty();
								return stack;
						}
				}
				return null;
		}

		@Nullable
		@Override
		public ItemStack removeStackFromSlot (int index) {
				if (index < getSizeInventory())
						setInventorySlotContents(index, null);
				return null;
		}

		@Override
		public void setInventorySlotContents (int index, @Nullable ItemStack stack) {
				if (index < getSizeInventory()) {
						inventory[index] = stack;
						markDirty();
				}
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
				return false;
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
				return true;
		}

		private void hasValidRecipe () {
				for (ICubeCreatorRecipe recipe : CubeCreatorRecipeHandler.recipes) {
						boolean hasItems = true;
						if (recipe.getInputs() != null && recipe.getOutputCube() != null) {
								for (ItemStack stack : recipe.getInputs()) {
										if (!hasStack(stack))
										hasItems = false;
										if (hasItems && activeRecipe == null)
												setActiveRecipe(recipe);
								}
						}
				}
		}

		private boolean hasStack (ItemStack stack) {
				int amount = 0;
				for (int slot = 1; slot <= 8; slot++) {
						if (getStackInSlot(slot) != null && StackHelper.areItemsEqual(stack, getStackInSlot(slot)))
								return true;
						if (getStackInSlot(slot) != null && StackHelper.areStacksEqualIgnoreSize(stack, getStackInSlot(slot)))
								amount += getStackInSlot(slot).stackSize;
				}
				if (amount >= stack.stackSize)
						return true;
				return false;
		}

		private void addOutput (ItemStack stack) {
				if (stack != null && getStackInSlot(0) == null) {
						setInventorySlotContents(0, stack);
				} else if (stack != null && getStackInSlot(0) != null && StackHelper.areStacksEqualIgnoreSize(getStackInSlot(0), stack)) {
						ItemStack item = new ItemStack(stack.getItem(), getStackInSlot(0).stackSize + stack.stackSize);
						if (stack.getTagCompound() != null)
								item.setTagCompound(stack.getTagCompound());
						setInventorySlotContents(0, item);
				}
		}

		private void consumeRecipeItems (ICubeCreatorRecipe recipe) {
				for (ItemStack stack : recipe.getInputs()) {
						for (int s = 1; s <= 8; s++) {
								if (getStackInSlot(s) != null && StackHelper.areStacksEqualIgnoreSize(stack, getStackInSlot(s))) {
										ItemStack item = new ItemStack(stack.getItem(), getStackInSlot(s).stackSize - stack.stackSize);
										if (stack.getTagCompound() != null)
												item.setTagCompound(stack.getTagCompound());
										setInventorySlotContents(s, item);
								}
						}
				}
		}

		private void setActiveRecipe (ICubeCreatorRecipe recipe) {
				if (recipe != null) {
						timer = recipe.getTimeInTicks();
						consumeRecipeItems(recipe);
						activeRecipe = recipe;
				}
		}
}
