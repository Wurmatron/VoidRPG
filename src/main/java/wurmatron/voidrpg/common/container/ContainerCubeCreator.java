package wurmatron.voidrpg.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCubeCreator extends Container {

    private IInventory inv;

    public ContainerCubeCreator(EntityPlayer player, InventoryPlayer playerInv, IInventory blockInv) {
        this.inv = blockInv;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 9 + j * 18, 98 + (i * 18)));
        for (int i = 0; i < 9; ++i)
            addSlotToContainer(new Slot(playerInv, i, 9 + i * 18, 156));
        addSlotToContainer(new SlotOutput(blockInv, 0, 81, 36));
        addSlotToContainer(new SlotInput(blockInv, 1, 63, 72));
        addSlotToContainer(new SlotInput(blockInv, 2, 99, 72));
        addSlotToContainer(new SlotInput(blockInv, 3, 117, 54));
        addSlotToContainer(new SlotInput(blockInv, 4, 45, 54));
        addSlotToContainer(new SlotInput(blockInv, 5, 45, 18));
        addSlotToContainer(new SlotInput(blockInv, 6, 63, 0));
        addSlotToContainer(new SlotInput(blockInv, 7, 99, 0));
        addSlotToContainer(new SlotInput(blockInv, 8, 117, 18));
        for (int x = 0; x < 5; x++)
            addSlotToContainer(new SlotUpgrade(blockInv, 9 + x, 159, -10 + (x * 18)));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack stack = null;
        Slot slot = inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack temp = slot.getStack();
            stack = temp.copy();
            if (index < 0) {
                if (!this.mergeItemStack(temp, 0, 9, true))
                    return null;
                slot.onSlotChange(temp, stack);
            }
            if (temp.stackSize == 0)
                slot.putStack(null);
            else
                slot.onSlotChanged();
            if (temp.stackSize == stack.stackSize)
                return null;
            slot.onPickupFromSlot(player, temp);
        }
        return stack;
    }


    @Override
    protected boolean mergeItemStack(ItemStack stack, int start, int end, boolean backwards) {
        boolean flag1 = false;
        int k = (backwards ? end - 1 : start);
        Slot slot;
        ItemStack itemstack1;
        if (stack.isStackable()) {
            while (stack.stackSize > 0 && (!backwards && k < end || backwards && k >= start)) {
                slot = inventorySlots.get(k);
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
                slot = inventorySlots.get(k);
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
