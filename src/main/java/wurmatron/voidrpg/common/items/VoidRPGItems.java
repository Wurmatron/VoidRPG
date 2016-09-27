package wurmatron.voidrpg.common.items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wurmatron.voidrpg.client.proxy.ClientProxy;

public class VoidRPGItems {

		public static Item itemStaff;
		public static Item armorHelmet;
		public static Item armorChestplate;
		public static Item armorLeggings;
		public static Item armorBoots;
		public static Item itemModelPlacer;

		public static void init () {
				registerItem(itemStaff = new ItemStaff());
				registerItem(armorHelmet = new CustomArmor(ItemArmor.ArmorMaterial.DIAMOND, 0, EntityEquipmentSlot.HEAD));
				registerItem(armorChestplate = new CustomArmor(ItemArmor.ArmorMaterial.DIAMOND, 1, EntityEquipmentSlot.CHEST));
				registerItem(armorLeggings = new CustomArmor(ItemArmor.ArmorMaterial.DIAMOND, 2, EntityEquipmentSlot.LEGS));
				registerItem(armorBoots = new CustomArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, EntityEquipmentSlot.FEET));
				registerItem(itemModelPlacer = new ItemModelPlacer());
		}

		private static void registerItem (Item item) {
				GameRegistry.registerItem(item, item.getUnlocalizedName());
				ClientProxy.items.add(item);
		}
}

