package wurmatron.voidrpg.common.items;

import net.minecraft.block.Block;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import wurmatron.voidrpg.client.proxy.ClientProxy;
import wurmatron.voidrpg.common.reference.Registry;

public class VoidRPGItems {

	public static final String[] materials = new String[] {"creationCrystal","upgrade","basicArmorPlate","heavyArmorPlate","reactiveArmorPlating","regenerativeArmorPlating","energyArmorPlating","cardboard","carbonChunk","flippers","nanoTech","mechanicalMuscle","gravityCore","jetpackParts","thruster","waterElectrolysisModule","mindControl","wings","battery","solarPanel","smallReactor","largeReactor","repairBot"};
	public static final String[] upgrades = new String[] {"speedI","speedII","speedIII"};

	public static Item itemStaff = new ItemStaff ();
	public static Item itemMaterial = new ItemMaterial ();
	public static Item itemUpgrade = new ItemUpgrade ();
	public static Item goggles = new ItemGoggles ();
	public static Item armorHelmet = new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,0,EntityEquipmentSlot.HEAD);
	public static Item armorChestplate = new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,1,EntityEquipmentSlot.CHEST);
	public static Item armorLeggings = new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,2,EntityEquipmentSlot.LEGS);
	public static Item armorBoots = new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,3,EntityEquipmentSlot.FEET);
	public static Item modelPlacer = new ItemModelPlacer ();

	public static void init () {
		Registry.registerItem(itemStaff,"creationStaff" );
		Registry.registerItem(itemMaterial, "itemMaterial");
		Registry.registerItem(itemUpgrade, "upgrade");
		Registry.registerItem(goggles, "goggles");
		Registry.registerItem(armorHelmet, "armorHelmet");
		Registry.registerItem(armorChestplate, "armorChestplate");
		Registry.registerItem(armorLeggings, "armorLeggings");
		Registry.registerItem(armorBoots, "armorBoots");
		Registry.registerItem(modelPlacer, "modelPlacer");
	}
}

