package wurmatron.voidrpg.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.VoidRPG;

import java.util.List;

public class ItemMaterial extends Item {

    private static String[] materials;

    public ItemMaterial(String[] materials) {
        ItemMaterial.materials = materials;
        setCreativeTab(VoidRPG.tabVoidRPG);
        setHasSubtypes(true);
        setUnlocalizedName("itemMaterial");
        setRegistryName("itemMaterial");
    }

    public static ItemStack createMaterial(String name, int size) {
        for (int s = 0; s <= materials.length; s++)
            if (name.equals(materials[s]))
                return new ItemStack(VoidRPGItems.itemMaterial, size, s);
        return new ItemStack(VoidRPGItems.itemMaterial, size, -1);
    }

    public static ItemStack createMaterial(String name) {
        for (int s = 0; s <= materials.length; s++)
            if (name.equals(materials[s]))
                return new ItemStack(VoidRPGItems.itemMaterial, 1, s);
        return new ItemStack(VoidRPGItems.itemMaterial, 1, -1);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> sub) {
        for (String i : materials)
            sub.add(createMaterial(i, 1));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.getItemDamage() < materials.length)
            return "item." + materials[stack.getItemDamage()];
        return "item.material.error.name";
    }
}
