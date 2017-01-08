package wurmatron.voidrpg.common.utils;

import net.minecraft.item.ItemStack;

public class StackHelper {

    public static boolean areItemsEqual(ItemStack a, ItemStack b) {
        if (a != null && b != null && a.isItemEqual(b))
            if (a.getTagCompound() == null && b.getTagCompound() == null)
                return true;
            else if (a.getTagCompound() != null && b.getTagCompound() != null && a.getTagCompound().equals(b.getTagCompound()))
                return true;
        return a == null && b == null;
    }

    public static boolean areStacksEqualIgnoreSize(ItemStack a, ItemStack b) {
        if (a != null && b != null && a.getItem().equals(b.getItem()) && a.getItemDamage() == b.getItemDamage()) {
            if (a.getTagCompound() == null && b.getTagCompound() == null)
                return true;
            else if (a.getTagCompound() != null && b.getTagCompound() != null && a.getTagCompound().equals(b.getTagCompound()))
                return true;
        }
        return a == null && b == null;
    }
}
