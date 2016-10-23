package wurmatron.voidrpg.common.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotUpgrade extends Slot {
		public SlotUpgrade (IInventory inventoryIn, int index, int xPosition, int yPosition) {
				super(inventoryIn, index, xPosition, yPosition);
		}
}
