package wurmatron.voidrpg.common.items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import wurmatron.voidrpg.client.proxy.ClientProxy;

public class VoidRPGItems {

	public static final String[] materials = new String[] {"creationCrystal","upgrade","basicArmorPlate","heavyArmorPlate","reactiveArmorPlating","regenerativeArmorPlating","energyArmorPlating","cardboard","carbonChunk","flippers","nanoTech","mechanicalMuscle","gravityCore","jetpackParts","thruster","waterElectrolysisModule","mindControl","wings","battery","solarPanel","smallReactor","largeReactor","repairBot"};
	public static final String[] upgrades = new String[] {"speedI","speedII","speedIII"};
	public static Item itemStaff;
	public static Item itemMaterial;
	public static Item itemUpgrade;
	public static Item goggles;
	public static Item armorHelmet;
	public static Item armorChestplate;
	public static Item armorLeggings;
	public static Item armorBoots;
	public static Item modelPlacer;

	public static void init () {
		registerItem (goggles = new ItemGoggles ());
		GameRegistry.registerItem (itemMaterial = new ItemMaterial ());
		GameRegistry.registerItem (itemUpgrade = new ItemUpgrade ());
		registerItem (itemStaff = new ItemStaff ());
		registerItem (armorHelmet = new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,0,EntityEquipmentSlot.HEAD));
		registerItem (armorChestplate = new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,1,EntityEquipmentSlot.CHEST));
		registerItem (armorLeggings = new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,2,EntityEquipmentSlot.LEGS));
		registerItem (armorBoots = new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,3,EntityEquipmentSlot.FEET));
		GameRegistry.registerItem (modelPlacer = new ItemModelPlacer ());
	}

	private static void registerItem (Item item) {
		GameRegistry.registerItem (item,item.getUnlocalizedName ());
		if (FMLCommonHandler.instance ().getEffectiveSide () == Side.CLIENT)
			ClientProxy.items.add (item);
	}
}

