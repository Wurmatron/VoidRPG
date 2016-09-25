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
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.cube.CubeCreatorRecipeHandler;
import wurmatron.voidrpg.common.reference.NBT;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class TileCubeCreator extends TileEntity implements ITickable, IInventory {

		private ItemStack[] inventory = new ItemStack[18];
		private ICubeCreatorRecipe[] validRecipes;
		private ICubeCreatorRecipe activeRecipe;
		private int checkTimer;
		public int recipeTimer;
		private final int UPDATE_TIMER = Settings.cubeCreatorUpdatePeriod * 20;
		private boolean isFrozen;

		@Override
		public void update () {
				if (checkTimer <= 0 && inventory != null && inventory.length > 0) {
						checkTimer = UPDATE_TIMER;
						validRecipes = checkForValidRecipes();
				} else if (checkTimer > 0)
						checkTimer--;
				if (activeRecipe != null && recipeTimer <= 0) {
						if (hasRecipeItems(activeRecipe)) {
								if (!isFrozen) {
										consumeRecipeItems(activeRecipe);
										addOutput(activeRecipe.getOutputCube(), false);
								} else
										addOutput(activeRecipe.getOutputCube(), true);
								if (hasRecipeItems(activeRecipe)) {
										recipeTimer = activeRecipe.getTimeInTicks();
								} else
										activeRecipe = null;
						}
				} else if (recipeTimer > 0)
						recipeTimer--;
		}

		private void consumeRecipeItems (ICubeCreatorRecipe recipe) {
				if (hasRecipeItems(recipe)) {
						int requiredAmount;
						for (ItemStack r : recipe.getInputs()) {
								requiredAmount = r.stackSize;
								for (int slot = 0; slot <= inventory.length; slot++) {
										ItemStack i = getStackInSlot(slot);
										if (i != null && r.isItemEqual(i)) {
												if (requiredAmount < i.stackSize) {
														ItemStack item = new ItemStack(i.getItem(), i.stackSize - requiredAmount, i.getItemDamage());
														if (i.getTagCompound() != null)
																item.setTagCompound(i.getTagCompound());
														setInventorySlotContents(slot, item);
														requiredAmount = 0;
												} else if (requiredAmount == i.stackSize) {
														setInventorySlotContents(slot, null);
														requiredAmount = 0;
												} else if (requiredAmount > i.stackSize) {
														setInventorySlotContents(slot, null);
														requiredAmount -= i.stackSize;
												}
										}
								}
						}
				} else
						activeRecipe = null;
		}

		public void setActiveRecipe (ICubeCreatorRecipe recipe) {
				this.activeRecipe = recipe;
				if (recipeTimer <= 0)
						this.recipeTimer = activeRecipe.getTimeInTicks();
		}

		public ICubeCreatorRecipe getActiveRecipe () {
				return activeRecipe;
		}

		@Override
		public int getSizeInventory () {
				return inventory.length;
		}

		@Nullable
		@Override
		public ItemStack getStackInSlot (int index) {
				if (index < inventory.length)
						return inventory[index];
				return null;
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
				if (index < inventory.length) {
						inventory[index] = stack;
						if (stack != null && stack.stackSize > getInventoryStackLimit())
								stack.stackSize = getInventoryStackLimit();
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
								item.setByte(NBT.SLOT, (byte) i);
								getStackInSlot(i).writeToNBT(item);
								items.appendTag(item);
						}
				}
				compound.setTag(NBT.INVENTORY, items);
				if (activeRecipe != null) {
						ResourceLocation recipeOutput = Item.REGISTRY.getNameForObject(activeRecipe.getOutputCube().getItem());
						compound.setString(NBT.ACTIVERECIPE, recipeOutput.getResourceDomain() + ":" + recipeOutput.getResourcePath() + "@" + activeRecipe.getOutputCube().getItemDamage() + "%" + activeRecipe.getOutputCube().stackSize);
				} else
						compound.setString(NBT.ACTIVERECIPE, "null");
				compound.setInteger(NBT.TIME, recipeTimer);
				return compound;
		}

		@Override
		public void readFromNBT (NBTTagCompound compound) {
				super.readFromNBT(compound);
				NBTTagList items = compound.getTagList(NBT.INVENTORY, compound.getId());
				for (int i = 0; i < items.tagCount(); ++i) {
						NBTTagCompound item = items.getCompoundTagAt(i);
						byte slot = item.getByte(NBT.SLOT);
						if (slot >= 0 && slot < getSizeInventory())
								setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
				}
				if (!compound.getString(NBT.ACTIVERECIPE).equals("null")) {
						ResourceLocation recipeOutput = new ResourceLocation(compound.getString(NBT.ACTIVERECIPE).substring(0, compound.getString(NBT.ACTIVERECIPE).indexOf(":")), compound.getString(NBT.ACTIVERECIPE).substring(compound.getString(NBT.ACTIVERECIPE).indexOf(":"), compound.getString(NBT.ACTIVERECIPE).indexOf("@")));
						int stackSize = Integer.getInteger(compound.getString(NBT.ACTIVERECIPE).substring(compound.getString(NBT.ACTIVERECIPE).indexOf("@") - 1, compound.getString(NBT.ACTIVERECIPE).length()));
						int meta = Integer.getInteger(compound.getString(NBT.ACTIVERECIPE).substring(compound.getString(NBT.ACTIVERECIPE).indexOf("@") + 1, compound.getString(NBT.ACTIVERECIPE).indexOf(":")));
						ItemStack outputStack = new ItemStack(Item.REGISTRY.getObject(recipeOutput), stackSize, meta);
						if (outputStack != null) {
								for (ICubeCreatorRecipe recipe : CubeCreatorRecipeHandler.getRecipes())
										if (recipe.getOutputCube().isItemEqual(outputStack))
												activeRecipe = recipe;
						} else
								compound.setString(NBT.ACTIVERECIPE, "null");
				}
				recipeTimer = compound.getInteger(NBT.TIME);
		}

		private ICubeCreatorRecipe[] checkForValidRecipes () {
				if (CubeCreatorRecipeHandler.getRecipes() != null && CubeCreatorRecipeHandler.getRecipes().size() > 0) {
						ArrayList<ICubeCreatorRecipe> temp = new ArrayList<ICubeCreatorRecipe>();
						for (ICubeCreatorRecipe r : CubeCreatorRecipeHandler.getRecipes())
								if (hasRecipeItems(r))
										temp.add(r);
						ICubeCreatorRecipe[] recipes = new ICubeCreatorRecipe[temp.size()];
						if (temp.size() > 0)
								for (int n = 0; n < temp.size(); n++)
										recipes[n] = temp.get(n);
						if (recipes != null && recipes.length > 0 && recipes[0] != null)
								setActiveRecipe(recipes[0]);
						return recipes;
				}
				return null;
		}

		private boolean hasRecipeItems (ICubeCreatorRecipe recipe) {
				if (recipe != null) {
						ArrayList<Boolean> temp = new ArrayList<Boolean>();
						for (ItemStack input : recipe.getInputs()) {
								int amountRequired = input.stackSize;
								for (ItemStack stack : inventory) {
										if (stack != null && stack.getItem().equals(input.getItem()) && stack.getItemDamage() == input.getItemDamage())
												if (input.getTagCompound() != null && stack.getTagCompound() != null && input.getTagCompound().equals(stack.getTagCompound()))
														amountRequired -= stack.stackSize;
												else if (input.getTagCompound() == null && stack.getTagCompound() == null)
														amountRequired -= stack.stackSize;
								}
								if (amountRequired <= 0)
										temp.add(true);
								else {
										temp.add(false);
										break;
								}
						}
						if (temp.contains(false))
								return false;
						return true;
				}
				return false;
		}

		private void addOutput (ItemStack stack, boolean checking) {
				boolean added = false;
				int amountLeft = stack.stackSize;
				if (stack != null) {
						for (int slot = 0; slot < inventory.length; slot++) {
								if (getStackInSlot(slot) != null && getStackInSlot(slot).isItemEqual(stack) && getStackInSlot(slot).stackSize < 64) {
										if (getStackInSlot(slot).getTagCompound() == null && stack.getTagCompound() == null || getStackInSlot(slot).getTagCompound() != null && stack.getTagCompound() != null && getStackInSlot(slot).getTagCompound().equals(stack.getTagCompound())) {
												if (getStackInSlot(slot).stackSize + stack.stackSize <= 64) {
														ItemStack temp = getStackInSlot(slot);
														temp.stackSize += stack.stackSize;
														setInventorySlotContents(slot, temp);
														amountLeft -= stack.stackSize;
												} else if (getStackInSlot(slot).stackSize + 1 <= 64) {
														ItemStack temp = getStackInSlot(slot);
														int amountTillFull = temp.stackSize;
														for (int a = 0; a <= 64; a++)
																if (amountTillFull + a <= stack.getMaxStackSize())
																		if (amountTillFull + a == stack.getMaxStackSize()) {
																				amountLeft -= a;
																				amountTillFull += a;
																				temp.stackSize = amountTillFull;
																				if (!checking)
																						setInventorySlotContents(slot, temp);
																		}
														if (!checking)
																setInventorySlotContents(slot, temp);
														amountLeft -= stack.stackSize;
												}
										}
										if (amountLeft == 0) {
												added = true;
												break;
										}
								}
						}
						if (!added) {
								for (int slot = 0; slot < inventory.length; slot++) {
										if (getStackInSlot(slot) == null) {
												if (!checking)
														setInventorySlotContents(slot, stack);
												amountLeft -= stack.stackSize;
										}
										if (amountLeft == 0) {
												added = true;
												break;
										}
								}
								// No open slots detected, freeze machine
								recipeTimer = 1;
								isFrozen = true;
						}
						if (amountLeft == 0)
								isFrozen = false;
				}
		}
}