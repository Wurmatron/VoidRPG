package wurmatron.voidrpg;


import net.minecraft.block.Block;
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
import wurmatron.voidrpg.api.cube.Cube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.config.ConfigHandler;
import wurmatron.voidrpg.common.cube.CubeRegistry;
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
				// Iron block (Test)
				CubeRegistry.INSTANCE.registerCube(new Cube() {
						@Override
						public String getUnlocalizedName () {
								return "test";
						}

						@Override
						public Block getBlock () {
								return Blocks.IRON_BLOCK;
						}

						@Override
						public ResourceLocation getTexture () {
								return new ResourceLocation("minecraft", "textures/blocks/iron_block.png");
						}
				});

				// Light Armor
				CubeRegistry.INSTANCE.registerCube(new Cube() {
						@Override
						public String getUnlocalizedName () {
								return "armorLight";
						}

						@Override
						public ResourceLocation getTexture () {
								return new ResourceLocation("minecraft", "textures/blocks/gold_block.png");
						}

						@Override
						public Block getBlock () {
								return VoidRPGBlocks.armorLight;
						}
				});

				// Reinforced Armor
				CubeRegistry.INSTANCE.registerCube(new Cube() {
						@Override
						public String getUnlocalizedName () {
								return "armorHeavy";
						}

						@Override
						public ResourceLocation getTexture () {
								return new ResourceLocation("minecraft", "textures/blocks/diamond_block.png");
						}

						@Override
						public Block getBlock () {
								return VoidRPGBlocks.armorReinforced;
						}
				});
		}

		@Mod.EventHandler
		public void onPostInit (FMLPostInitializationEvent e) {
				LogHandler.info("Post-Init");
		}
}
