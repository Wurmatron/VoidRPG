package wurmatron.voidrpg.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCubeCreator extends Container {

		private EntityPlayer player;
		private InventoryPlayer playerInv;
		private IInventory inv;

		public ContainerCubeCreator (EntityPlayer player, InventoryPlayer playerInv, IInventory blockInv) {
				this.player = player;
				this.playerInv = playerInv;
				this.inv = blockInv;
				for (int i = 0; i < 3; ++i)
						for (int j = 0; j < 9; ++j)
								addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 48 + j * 18, 126 + (i * 18)));
				for (int i = 0; i < 9; ++i)
						addSlotToContainer(new Slot(playerInv, i, 48 + i * 18, 184));
				for (int x = 0; x < 3; x++)
						for (int y = 0; y < 6; y++)
								addSlotToContainer(new SlotInput(blockInv, y + x * 6, 195 + x * 18, 10 + (y * 18)));
		}

		@Override
		public boolean canInteractWith (EntityPlayer player) {
				return true;
		}

		@Override
		public ItemStack transferStackInSlot(EntityPlayer player, int index)
		{
				ItemStack itemstack = null;
				Slot slot = (Slot) this.inventorySlots.get(index);

				if (slot != null && slot.getHasStack())
				{
						ItemStack itemstack1 = slot.getStack();
						itemstack = itemstack1.copy();

						if (index < 0)
						{
								if (!this.mergeItemStack(itemstack1, 0, 9, true))
								{
										return null;
								}

								slot.onSlotChange(itemstack1, itemstack);
						}

						if (itemstack1.stackSize == 0) {
								slot.putStack((ItemStack) null);
						} else {
								slot.onSlotChanged();
						}

						if (itemstack1.stackSize == itemstack.stackSize) {
								return null;
						}

						slot.onPickupFromSlot(player, itemstack1);
				}

				return itemstack;
		}


		@Override
		protected boolean mergeItemStack (ItemStack stack, int start, int end, boolean backwards) {
				boolean flag1 = false;
				int k = (backwards ? end - 1 : start);
				Slot slot;
				ItemStack itemstack1;
				if (stack.isStackable()) {
						while (stack.stackSize > 0 && (!backwards && k < end || backwards && k >= start)) {
								slot = (Slot) inventorySlots.get(k);
								itemstack1 = slot.getStack();
								if (!slot.isItemValid(stack)) {
										k += (backwards ? -1 : 1);
										continue;
								}
								if (itemstack1 != null && itemstack1.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getItemDamage() == itemstack1.getItemDamage()) && ItemStack.areItemStackTagsEqual(stack, itemstack1)) {
										int l = itemstack1.stackSize + stack.stackSize;
										if (l <= stack.getMaxStackSize() && l <= slot.getSlotStackLimit()) {
												stack.stackSize = 0;
												itemstack1.stackSize = l;
												inv.markDirty();
												flag1 = true;
										} else if (itemstack1.stackSize < stack.getMaxStackSize() && l < slot.getSlotStackLimit()) {
												stack.stackSize -= stack.getMaxStackSize() - itemstack1.stackSize;
												itemstack1.stackSize = stack.getMaxStackSize();
												inv.markDirty();
												flag1 = true;
										}
								}

								k += (backwards ? -1 : 1);
						}
				}

				if (stack.stackSize > 0) {
						k = (backwards ? end - 1 : start);

						while (!backwards && k < end || backwards && k >= start) {
								slot = (Slot) inventorySlots.get(k);
								itemstack1 = slot.getStack();

								if (!slot.isItemValid(stack)) {
										k += (backwards ? -1 : 1);
										continue;
								}

								if (itemstack1 == null) {
										int l = stack.stackSize;

										if (l <= slot.getSlotStackLimit()) {
												slot.putStack(stack.copy());
												stack.stackSize = 0;
												inv.markDirty();
												flag1 = true;
												break;
										} else {
												putStackInSlot(k, new ItemStack(stack.getItem(), slot.getSlotStackLimit(), stack.getItemDamage()));
												stack.stackSize -= slot.getSlotStackLimit();
												inv.markDirty();
												flag1 = true;
										}
								}

								k += (backwards ? -1 : 1);
						}
				}
				return flag1;
		}
}
