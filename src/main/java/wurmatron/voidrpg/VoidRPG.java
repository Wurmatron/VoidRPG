package wurmatron.voidrpg;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.command.VoidRPGCommand;
import wurmatron.voidrpg.common.config.ConfigHandler;
import wurmatron.voidrpg.common.config.JsonHandler;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.cube.*;
import wurmatron.voidrpg.common.cube.special.CubeAutoRepair;
import wurmatron.voidrpg.common.cube.special.CubeMobStealth;
import wurmatron.voidrpg.common.cube.special.armor.CubeLife;
import wurmatron.voidrpg.common.cube.special.armor.CubeNanoTech;
import wurmatron.voidrpg.common.cube.special.bootsOnly.CubeFlippers;
import wurmatron.voidrpg.common.cube.special.bootsOnly.CubeShock;
import wurmatron.voidrpg.common.cube.special.bootsOnly.CubeWaterWalk;
import wurmatron.voidrpg.common.cube.special.chestOnly.CubeGravity;
import wurmatron.voidrpg.common.cube.special.chestOnly.CubeJetpack;
import wurmatron.voidrpg.common.cube.special.helmetOnly.CubeVision;
import wurmatron.voidrpg.common.cube.special.helmetOnly.CubeWaterBreathing;
import wurmatron.voidrpg.common.cube.special.leggingsOnly.CubeMuscle;
import wurmatron.voidrpg.common.events.PlayerJoinEvent;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.network.GuiHandler;
import wurmatron.voidrpg.common.proxy.CommonProxy;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.LogHandler;

@Mod (modid = Global.MODID, name = Global.NAME, version = Global.VERSION, guiFactory = Global.GUIFACTORY, dependencies = Global.DEPENDENCIES)
public class VoidRPG {

		@Mod.Instance (Global.MODID)
		public static VoidRPG instance;

		@SidedProxy (serverSide = Global.COMMONPROXY, clientSide = Global.CLIENTPROXY)
		public static CommonProxy proxy;

		public static final CreativeTabs tabVoidRPG = new CreativeTabs("tabVoidRPG") {
				@Override
				public Item getTabIconItem () {
						return VoidRPGItems.itemStaff;
				}
		};

		@Mod.EventHandler
		public void onPreInit (FMLPreInitializationEvent e) {
				LogHandler.info("Pre-Init");
				ConfigHandler.preInit(e);
				VoidRPGBlocks.init();
				VoidRPGItems.init();
				proxy.register();
		}

		@Mod.EventHandler
		public void onInit (FMLInitializationEvent e) {
				LogHandler.info("Init");
				ConfigHandler.loadMainConfig();
				NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
				MinecraftForge.EVENT_BUS.register(new PlayerJoinEvent());
				CubeRegistry.INSTANCE.registerCube(new Cube("test", Blocks.IRON_BLOCK, new ResourceLocation("minecraft", "textures/blocks/iron_block.png"), 0.1, 1, 5, 2000, new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET}, "cube.test.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("armorLight", VoidRPGBlocks.armorLight, new ResourceLocation("minecraft", "textures/blocks/gold_block.png"), 0.1, 1, 50, 4096, new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.lightArmor.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("armorHeavy", VoidRPGBlocks.armorReinforced, new ResourceLocation("minecraft", "textures/blocks/diamond_block.png"), 0.5, 5, 200, 4096, new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.heavyArmor.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("armorReinforced", VoidRPGBlocks.armorReinforced, new ResourceLocation("minecraft", "textures/blocks/diamond_block.png"), 0.5, 5, 200, 4096, new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.reinforcedArmor.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("armorCarbon", VoidRPGBlocks.armorCarbon, new ResourceLocation(Global.MODID, "textures/cube/carbon.png"), 0.5, 5, 200, 4096, new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.carbonArmor.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("armorCardboard", VoidRPGBlocks.armorCardboard, new ResourceLocation(Global.MODID, "textures/cube/cardboard.png"), 0.5, 5, 200, 4096, new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.carbonArmor.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("armorWood", VoidRPGBlocks.armorWood, new ResourceLocation(Global.MODID, "textures/cube/wood.png"), 0.5, 5, 200, 4096, new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.woodArmor.description"));

				// Deco Cubes
				CubeRegistry.INSTANCE.registerCube(new Cube("decoDiamond", VoidRPGBlocks.decoDiamond, new ResourceLocation(Global.MODID,"textures/cube/decoDiamond.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoDiamond.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoEmerald", VoidRPGBlocks.decoEmerald, new ResourceLocation(Global.MODID,"textures/cube/decoEmerald.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoEmerald.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolWhite", VoidRPGBlocks.decoWoolWhile, new ResourceLocation(Global.MODID,"textures/cube/decoWoolWhite.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolWhite.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolOrange", VoidRPGBlocks.decoWoolOrange, new ResourceLocation(Global.MODID,"textures/cube/decoWoolOrange.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolOrange.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolMagenta", VoidRPGBlocks.decoWoolMagenta, new ResourceLocation(Global.MODID,"textures/cube/decoWoolManenta.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolMagenta.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolLightBlue", VoidRPGBlocks.decoWoolLightBlue, new ResourceLocation(Global.MODID,"textures/cube/decoWoolLightBlue.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolLightBlue.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolYellow", VoidRPGBlocks.decoWoolYellow, new ResourceLocation(Global.MODID,"textures/cube/decoWoolYellow.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolYellow.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolLime", VoidRPGBlocks.decoWoolLime, new ResourceLocation(Global.MODID,"textures/cube/decoWoolLime.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolLime.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolPink", VoidRPGBlocks.decoWoolPink, new ResourceLocation(Global.MODID,"textures/cube/decoWoolPink.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolPink.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolGray", VoidRPGBlocks.decoWoolGray, new ResourceLocation(Global.MODID,"textures/cube/decoWoolGray.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolGray.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolLightGray", VoidRPGBlocks.decoWoolLightGray, new ResourceLocation(Global.MODID,"textures/cube/decoWoolLightGray.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolLightGray.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolCyan", VoidRPGBlocks.decoWoolCyan, new ResourceLocation(Global.MODID,"textures/cube/decoWoolCyan.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolCyan.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolPurple", VoidRPGBlocks.decoWoolPurple, new ResourceLocation(Global.MODID,"textures/cube/decoWoolPurple.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolPurple.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolBlue", VoidRPGBlocks.decoWoolBlue, new ResourceLocation(Global.MODID,"textures/cube/decoWoolBlue.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolBlue.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolBrown", VoidRPGBlocks.decoWoolBrown, new ResourceLocation(Global.MODID,"textures/cube/decoWoolBrown.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolBrown.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolGreen", VoidRPGBlocks.decoWoolGreen, new ResourceLocation(Global.MODID,"textures/cube/decoWoolGreen.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolGreen.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolRed", VoidRPGBlocks.decoWoolRed, new ResourceLocation(Global.MODID,"textures/cube/decoWoolRed.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolRed.description"));
				CubeRegistry.INSTANCE.registerCube(new Cube("decoWoolBlack", VoidRPGBlocks.decoWoolBlack, new ResourceLocation(Global.MODID,"textures/cube/decoWoolBlack.png"), 4,0,9001,0,new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET},"cube.decoWoolBlack.description"));

				CubeRegistry.INSTANCE.registerCube(new CubeAutoRepair());
				CubeRegistry.INSTANCE.registerCube(new CubeWaterWalk());
				CubeRegistry.INSTANCE.registerCube(new CubeShock());
				MinecraftForge.EVENT_BUS.register(new CubeShock());
				CubeRegistry.INSTANCE.registerCube(new CubeFlippers());
				CubeRegistry.INSTANCE.registerCube(new CubeMuscle());
				CubeRegistry.INSTANCE.registerCube(new CubeGravity());
				CubeRegistry.INSTANCE.registerCube(new CubeVision());
				CubeRegistry.INSTANCE.registerCube(new CubeJetpack());
				CubeRegistry.INSTANCE.registerCube(new CubeWaterBreathing());
				CubeRegistry.INSTANCE.registerCube(new CubeMobStealth());
				MinecraftForge.EVENT_BUS.register(new CubeMobStealth());
				CubeRegistry.INSTANCE.registerCube(new CubeLife());
				MinecraftForge.EVENT_BUS.register(new CubeLife());
				CubeRegistry.INSTANCE.registerCube(new CubeNanoTech());
				StringCube testJson = new StringCube("jsonTest", "minecraft", "dirt", new ResourceLocation("minecraft", "textures/blocks/dirt.png"), 0.2, 1, 2, 2500, "head,chest,legs,boots", "cube.jsonTest.description");
				JsonHandler.writeCubeToFile(testJson);
		}

		@Mod.EventHandler
		public void onPostInit (FMLPostInitializationEvent e) {
				LogHandler.info("Post-Init");
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(new ItemStack(Blocks.IRON_BLOCK, 4), new ItemStack[] {new ItemStack(Blocks.BEACON, 2), new ItemStack(Blocks.IRON_BLOCK, 2)}, 700));
		}

		@Mod.EventHandler
		public void onServerStarting (FMLServerStartingEvent e) {
				if (Settings.jsonCubes)
						ConfigHandler.loadJsonCubes();
				e.registerServerCommand(new VoidRPGCommand());
		}
}
