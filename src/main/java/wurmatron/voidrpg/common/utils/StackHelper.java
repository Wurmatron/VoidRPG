package wurmatron.voidrpg.common.utils;

import net.minecraft.item.ItemStack;

public class StackHelper {

    public static boolean areItemsEqual(ItemStack a, ItemStack b) {
        if (a != null && b != null && a.isItemEqual(b))
            return a.getTagCompound() != null && b.getTagCompound() != null && a.getTagCompound().equals(b.getTagCompound()) && a.getTagCompound() == null && b.getTagCompound() == null;
        return a == null && b == null;
    }

    public static boolean areStacksEqualIgnoreSize(ItemStack a, ItemStack b) {
        if (a != null && b != null && a.getItem().equals(b.getItem()) && a.getItemDamage() == b.getItemDamage())
            return a.getTagCompound() == null && b.getTagCompound() == null || a.getTagCompound() != null && b.getTagCompound() != null && a.getTagCompound().equals(b.getTagCompound());
        return a == null && b == null;
    }
}
