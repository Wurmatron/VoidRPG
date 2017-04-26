package wurmatron.voidrpg.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.VoidRPG;

import java.util.List;

public class ItemUpgrade extends Item {

		public ItemUpgrade() {
				setCreativeTab(VoidRPG.tabVoidRPG); setHasSubtypes(true); setUnlocalizedName("upgrade"); setRegistryName("upgrade");
				setMaxStackSize(4);
		}

		public static ItemStack createMaterial(String name) {
				for (int s = 0; s <= VoidRPGItems.upgrades.length; s++)
						if (name.equals(VoidRPGItems.upgrades[s])) return new ItemStack(VoidRPGItems.itemUpgrade, 1, s);
				return new ItemStack(VoidRPGItems.itemUpgrade, 1, -1);
		}

		@Override
		public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> sub) {
				for (String i : VoidRPGItems.upgrades)
						sub.add(createMaterial(i));
		}

		@Override
		public String getUnlocalizedName(ItemStack stack) {
				if (stack.getItemDamage() < VoidRPGItems.upgrades.length)
						return "item." + VoidRPGItems.upgrades[stack.getItemDamage()]; return "item.upgrade.error.name";
		}
}
