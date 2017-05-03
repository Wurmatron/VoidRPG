package wurmatron.voidrpg.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.VoidRPG;

import java.util.List;

public class ItemMaterial extends Item {

	public ItemMaterial () {
		setCreativeTab (VoidRPG.tabVoidRPG);
		setHasSubtypes (true);
		setUnlocalizedName ("itemMaterial");
		setRegistryName ("itemMaterial");
	}

	public static ItemStack createMaterial (String name,int size) {
		for (int s = 0; s <= VoidRPGItems.materials.length; s++)
			if (name.equals (VoidRPGItems.materials[s]))
				return new ItemStack (VoidRPGItems.itemMaterial,size,s);
		return new ItemStack (VoidRPGItems.itemMaterial,size,-1);
	}

	public static ItemStack createMaterial (String name) {
		for (int s = 0; s <= VoidRPGItems.materials.length; s++)
			if (name.equals (VoidRPGItems.materials[s]))
				return new ItemStack (VoidRPGItems.itemMaterial,1,s);
		return new ItemStack (VoidRPGItems.itemMaterial,1,-1);
	}

	@Override
	public void getSubItems (Item item,CreativeTabs tab,List <ItemStack> sub) {
		for (String i : VoidRPGItems.materials)
			sub.add (createMaterial (i,1));
	}

	@Override
	public String getUnlocalizedName (ItemStack stack) {
		if (stack.getItemDamage () < VoidRPGItems.materials.length)
			return "item." + VoidRPGItems.materials[stack.getItemDamage ()];
		return "item.material.error.name";
	}
}
