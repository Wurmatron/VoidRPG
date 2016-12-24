package wurmatron.voidrpg.common.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.common.items.ItemUpgrade;

import javax.annotation.Nullable;

public class SlotUpgrade extends Slot {
    public SlotUpgrade(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack stack) {
        return stack.getItem() instanceof ItemUpgrade;
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
}
