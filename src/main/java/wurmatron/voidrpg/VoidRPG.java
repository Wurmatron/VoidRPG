package wurmatron.voidrpg;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
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
				CubeRegistry.INSTANCE.registerCube(new Cube("test", Blocks.IRON_BLOCK, new ResourceLocation("minecraft", "textures/blocks/iron_block.png"), 0.1, 1, 5,2000));
				CubeRegistry.INSTANCE.registerCube(new Cube("armorLight", VoidRPGBlocks.armorLight, new ResourceLocation("minecraft", "textures/blocks/gold_block.png"), 0.1, 1, 50,4096));
				CubeRegistry.INSTANCE.registerCube(new Cube("armorHeavy", VoidRPGBlocks.armorReinforced, new ResourceLocation("minecraft", "textures/blocks/diamond_block.png"), 0.5, 5, 200,4096));
				CubeRegistry.INSTANCE.registerCube(new CubeAutoRepair());
				StringCube testJson = new StringCube("jsonTest", "minecraft", "dirt", new ResourceLocation("minecraft", "textures/blocks/dirt.png"), 0.2, 1, 2,2500);
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
