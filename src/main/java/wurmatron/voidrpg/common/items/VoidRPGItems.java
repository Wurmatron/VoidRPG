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

public class VoidRPGItems {

	public static final String[] materials = new String[] {"creationCrystal","upgrade","basicArmorPlate","heavyArmorPlate","reactiveArmorPlating","regenerativeArmorPlating","energyArmorPlating","cardboard","carbonChunk","flippers","nanoTech","mechanicalMuscle","gravityCore","jetpackParts","thruster","waterElectrolysisModule","mindControl","wings","battery","solarPanel","smallReactor","largeReactor","repairBot"};
	public static final String[] upgrades = new String[] {"speedI","speedII","speedIII"};

	@GameRegistry.ObjectHolder ("voidrpg:staff")
	public static Item itemStaff = new ItemStaff ();
	@GameRegistry.ObjectHolder ("voidrpg:material")
	public static Item itemMaterial = new ItemMaterial ();
	@GameRegistry.ObjectHolder ("voidrpg:upgrade")
	public static Item itemUpgrade = new ItemUpgrade ();
	@GameRegistry.ObjectHolder ("voidrpg:googles")
	public static Item goggles = new ItemGoggles ();
	@GameRegistry.ObjectHolder ("voidrpg:armorHead")
	public static Item armorHelmet = new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,0,EntityEquipmentSlot.HEAD);
	@GameRegistry.ObjectHolder ("voidrpg:armorChestplate")
	public static Item armorChestplate = new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,1,EntityEquipmentSlot.CHEST);
	@GameRegistry.ObjectHolder ("voidrpg:armorLeggings")
	public static Item armorLeggings = new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,2,EntityEquipmentSlot.LEGS);
	@GameRegistry.ObjectHolder ("voidrpg:armorFeet")
	public static Item armorBoots = new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,3,EntityEquipmentSlot.FEET);
	@GameRegistry.ObjectHolder ("voidrpg:modelPlacer")
	public static Item modelPlacer = new ItemModelPlacer ();

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> e) {
		e.getRegistry().register (itemStaff);
		e.getRegistry().register (itemMaterial);
		e.getRegistry().register (itemUpgrade);
		e.getRegistry().register (goggles);
		e.getRegistry().register (armorHelmet);
		e.getRegistry().register (armorChestplate);
		e.getRegistry().register (armorLeggings);
		e.getRegistry().register (armorBoots);
		e.getRegistry().register (modelPlacer);
	}

	public static void postInit () {
		ClientProxy.items.add (goggles = new ItemGoggles ());
		ClientProxy.items.add (itemStaff = new ItemStaff ());
		ClientProxy.items.add (new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,0,EntityEquipmentSlot.HEAD));
		ClientProxy.items.add (new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,1,EntityEquipmentSlot.CHEST));
		ClientProxy.items.add (new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,2,EntityEquipmentSlot.LEGS));
		ClientProxy.items.add (new ItemModelArmor (ItemArmor.ArmorMaterial.CHAIN,3,EntityEquipmentSlot.FEET));
	}
}

