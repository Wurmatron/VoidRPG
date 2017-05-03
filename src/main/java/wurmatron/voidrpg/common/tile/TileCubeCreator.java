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
import wurmatron.voidrpg.common.items.ItemUpgrade;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.LogHandler;
import wurmatron.voidrpg.common.utils.StackHelper;

import javax.annotation.Nullable;

// TODO Create way of telling timer via GUI
public class TileCubeCreator extends TileEntity implements IInventory, ITickable {

	private ItemStack[] inventory = new ItemStack[14];
	private ICubeCreatorRecipe activeRecipe;
	private int timer;
	private int UPDATE_TIMER = Settings.cubeCreatorUpdatePeriod * 20;
	private Boolean upgrades = null;

	@Override
	public void update () {
		if (!worldObj.isRemote) {
			if (worldObj.getTotalWorldTime () % UPDATE_TIMER == 0) {
				hasUpgrades (true);
				if (activeRecipe == null)
					lookForValidRecipe ();
				else if(activeRecipe.getOutputCube ().getTagCompound () != null)
					LogHandler.info (activeRecipe.getOutputCube ().getTagCompound ().toString ());
			}
			if (activeRecipe != null && timer <= 0) {
				handleFinishedRecipe ();
			} else if (activeRecipe != null && timer > 0)
				decreesTimer ();
		}
	}

	@Override
	public int getSizeInventory () {
		return inventory.length;
	}

	@Nullable
	@Override
	public ItemStack getStackInSlot (int index) {
		if (index < getSizeInventory ())
			return inventory[index];
		return null;
	}

	@Override
	public NBTTagCompound writeToNBT (NBTTagCompound nbt) {
		super.writeToNBT (nbt);
		NBTTagList list = new NBTTagList ();
		for (int i = 0; i < this.getSizeInventory (); ++i) {
			if (this.getStackInSlot (i) != null) {
				NBTTagCompound stackTag = new NBTTagCompound ();
				stackTag.setByte (NBT.SLOT,(byte) i);
				this.getStackInSlot (i).writeToNBT (stackTag);
				list.appendTag (stackTag);
			}
		}
		nbt.setInteger (NBT.TIMER,timer);
		nbt.setTag (NBT.INVENTORY,list);
		return nbt;
	}


	@Override
	public void readFromNBT (NBTTagCompound nbt) {
		super.readFromNBT (nbt);
		NBTTagList list = nbt.getTagList (NBT.INVENTORY,10);
		for (int i = 0; i < list.tagCount (); ++i) {
			NBTTagCompound stackTag = list.getCompoundTagAt (i);
			int slot = stackTag.getByte (NBT.SLOT) & 255;
			setInventorySlotContents (slot,ItemStack.loadItemStackFromNBT (stackTag));
		}
		timer = nbt.getInteger (NBT.TIMER);
	}

	@Override
	public ItemStack decrStackSize (int index,int count) {
		if (getStackInSlot (index) != null) {
			ItemStack stack;
			if (getStackInSlot (index).stackSize <= count) {
				stack = getStackInSlot (index);
				setInventorySlotContents (index,null);
				markDirty ();
				return stack;
			} else {
				stack = getStackInSlot (index).splitStack (count);
				if (getStackInSlot (index).stackSize <= 0)
					setInventorySlotContents (index,null);
				else
					setInventorySlotContents (index,getStackInSlot (index));
				markDirty ();
				return stack;
			}
		}
		return null;
	}

	@Nullable
	@Override
	public ItemStack removeStackFromSlot (int index) {
		if (index < getSizeInventory ())
			setInventorySlotContents (index,null);
		return null;
	}

	@Override
	public void setInventorySlotContents (int index,ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory ())
			return;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit ())
			stack.stackSize = this.getInventoryStackLimit ();

		if (stack != null && stack.stackSize == 0)
			stack = null;

		this.inventory[index] = stack;
		this.markDirty ();
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
	public boolean isItemValidForSlot (int index,ItemStack stack) {
		return false;
	}

	@Override
	public int getField (int id) {
		return 0;
	}

	@Override
	public void setField (int id,int value) {

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

	private void lookForValidRecipe () {
		if (activeRecipe == null && CubeCreatorRecipeHandler.getRecipes ().size () > 0) {
			for (ICubeCreatorRecipe recipe : CubeCreatorRecipeHandler.getRecipes ()) {
				boolean hasItems = true;
				if (recipe.getInputs () != null && recipe.getOutputCube () != null && recipe.getTimeInTicks () > 0) {
					for (ItemStack recipeInput : recipe.getInputs ())
						if (!hasItem (recipeInput))
							hasItems = false;
					if (hasItems)
						setActiveRecipe (recipe);
				}
			}
		}
	}

	private boolean hasItem (ItemStack stack) {
		int itemAmount = 0;
		for (int slot = 1; slot <= 8; slot++) {
			ItemStack slotStack = getStackInSlot (slot);
			if (slotStack != null)
				if (StackHelper.areItemsEqual (stack,slotStack))
					return true;
				else if (StackHelper.areStacksEqualIgnoreSize (stack,slotStack))
					itemAmount += slotStack.stackSize;
		}
		return itemAmount >= stack.stackSize;
	}

	private void setActiveRecipe (ICubeCreatorRecipe recipe) {
		activeRecipe = recipe;
		timer = recipe.getTimeInTicks ();
	}

	private void decreesTimer () {
		timer -= getSpeedMod ();
	}

	private int getSpeedMod () {
		if (hasUpgrades (false)) {
			int modAmount = 1;
			for (int slot = 9; slot <= 13; slot++) {
				ItemStack upgradeStack = getStackInSlot (slot);
				if (upgradeStack != null && upgradeStack.getItem () instanceof ItemUpgrade)
					if (upgradeStack.getItemDamage () == 0)
						modAmount += 2 * upgradeStack.stackSize;
					else if (upgradeStack.getItemDamage () == 1)
						modAmount += 4 * upgradeStack.stackSize;
					else if (upgradeStack.getItemDamage () == 2)
						modAmount += 8 * upgradeStack.stackSize;
			}
			if (modAmount <= 0)
				modAmount = 1;
			return modAmount;
		}
		return 1;
	}

	private boolean hasUpgrades (boolean forceUpdate) {
		if (upgrades == null || forceUpdate) {
			for (int slot = 9; slot <= 13; slot++)
				if (getStackInSlot (slot) != null) {
					upgrades = true;
					return true;
				}
			upgrades = false;
			return false;
		} else
			return upgrades;
	}

	private void handleFinishedRecipe () {
		if (addOutput (activeRecipe.getOutputCube ())) {
			consumeRecipe ();
			activeRecipe = null;
			lookForValidRecipe ();
		}
	}

	private boolean addOutput (ItemStack stack) {
		if (stack != null) {
			if (getStackInSlot (0) == null) {
				setInventorySlotContents (0,stack);
				return true;
			} else if (StackHelper.areStacksEqualIgnoreSize (getStackInSlot (0),stack)) {
				if (getStackInSlot (0).stackSize + stack.stackSize <= 64) {
					getStackInSlot (0).stackSize += stack.stackSize;
					return true;
				}
				return false;
			}
		}
		return false;
	}

	private void consumeRecipe () {
		if (activeRecipe != null)
			for (ItemStack input : activeRecipe.getInputs ())
				consumeItem (input);
	}

	private void consumeItem (ItemStack stack) {
		if (stack != null) {
			int amountNeeded = stack.stackSize;
			for (int slot = 1; slot <= 8; slot++) {
				ItemStack slotStack = getStackInSlot (slot);
				if (slotStack != null)
					if (StackHelper.areItemsEqual (stack,slotStack))
						setInventorySlotContents (slot,null);
					else if (StackHelper.areStacksEqualIgnoreSize (stack,slotStack)) {
						if (slotStack.stackSize > amountNeeded)
							slotStack.stackSize -= amountNeeded;
						else {
							amountNeeded -= slotStack.stackSize;
							setInventorySlotContents (slot,null);
						}
					}
			}
		}
	}
}
