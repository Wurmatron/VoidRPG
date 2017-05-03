package wurmatron.voidrpg.common.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotInput extends Slot {

	public SlotInput (IInventory inv,int index,int x,int y) {
		super (inv,index,x,y);
	}

	@Override
	public boolean isItemValid (ItemStack stack) {
		return true;
	}

	@Override
	public boolean canBeHovered () {
		return super.canBeHovered ();
	}
}
