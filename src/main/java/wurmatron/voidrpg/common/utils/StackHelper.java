package wurmatron.voidrpg.common.utils;

import net.minecraft.item.ItemStack;

public class StackHelper {

		public static boolean areItemsEqual (ItemStack a, ItemStack b) {
				if (a != null && b != null) {
						if (a.isItemEqual(b)) {
								if (a.getTagCompound() == null && b.getTagCompound() == null)
										return true;
								else if (a.getTagCompound() != null && b.getTagCompound() != null && a.getTagCompound().equals(b.getTagCompound()))
										return true;
						}
				}
				if(a == null && b == null)
						return true;
				return false;
		}
}
