package wurmatron.voidrpg;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.config.ConfigHandler;
import wurmatron.voidrpg.common.config.JsonHandler;
import wurmatron.voidrpg.common.cube.Cube;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.cube.StringCube;
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
						return Items.ENDER_EYE;
				}
		};

		@Mod.EventHandler
		public void onPreInit (FMLPreInitializationEvent e) {
				LogHandler.info("Pre-Init");
				ConfigHandler.preInit(e);
		}

		@Mod.EventHandler
		public void onInit (FMLInitializationEvent e) {
				LogHandler.info("Init");
				ConfigHandler.loadMainConfig();
				NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
				VoidRPGItems.init();
				VoidRPGBlocks.init();
				proxy.register();
				CubeRegistry.INSTANCE.registerCube(new Cube("test", Blocks.IRON_BLOCK, new ResourceLocation("minecraft", "textures/blocks/iron_block.png"), 0.1));
				CubeRegistry.INSTANCE.registerCube(new Cube("armorLight", VoidRPGBlocks.armorLight, new ResourceLocation("minecraft", "textures/blocks/gold_block.png"), 0.1));
				CubeRegistry.INSTANCE.registerCube(new Cube("armorHeavy", VoidRPGBlocks.armorReinforced, new ResourceLocation("minecraft", "textures/blocks/diamond_block.png"), 0.5));

				StringCube testJson = new StringCube("jsonTest","minecraft", "dirt", new ResourceLocation("minecraft", "textures/blocks.dirt.png"),0.2);
				JsonHandler.writeCubeToFile(testJson);
		}

		@Mod.EventHandler
		public void onPostInit (FMLPostInitializationEvent e) {
				LogHandler.info("Post-Init");
				ConfigHandler.loadJsonCubes();
		}
}
