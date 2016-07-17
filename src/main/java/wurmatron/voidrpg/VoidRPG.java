package wurmatron.voidrpg;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wurmatron.voidrpg.common.config.ConfigHandler;
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
		}

		@Mod.EventHandler
		public void onPostInit (FMLPostInitializationEvent e) {
				LogHandler.info("Post-Init");
		}
}
