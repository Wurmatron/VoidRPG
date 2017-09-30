package wurmatron.voidrpg.common.utils;

import net.minecraft.item.ItemStack;

public class StackHelper {

	public static boolean areItemsEqual (ItemStack a,ItemStack b) {
		if (a != null && b != null && a.getUnlocalizedName ().equals (b.getUnlocalizedName ()) && a.getItemDamage () == b.getItemDamage () && a.getCount () == b.getCount ())
			if (a.getTagCompound () != null && b.getTagCompound () != null && a.getTagCompound ().equals (b.getTagCompound ()))
				return true;
			else if (a.getTagCompound () == null && b.getTagCompound () == null)
				return true;
		return false;
	}

	public static boolean areStacksEqualIgnoreSize (ItemStack a,ItemStack b) {
		if (a != null && b != null && a.getUnlocalizedName ().equals (b.getUnlocalizedName ()) && a.getItemDamage () == b.getItemDamage ())
			if (a.getTagCompound () != null && b.getTagCompound () != null && a.getTagCompound ().equals (b.getTagCompound ()) || a.getTagCompound () == null && b.getTagCompound () == null)
				return true;
		return false;
	}
}
