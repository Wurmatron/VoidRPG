package wurmatron.voidrpg;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import wurmatron.voidrpg.client.proxy.ClientProxy;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.config.ConfigHandler;
import wurmatron.voidrpg.common.config.JsonHandler;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.event.FallEvent;
import wurmatron.voidrpg.common.event.HurtEvent;
import wurmatron.voidrpg.common.event.LivingTickEvent;
import wurmatron.voidrpg.common.event.SetTargetEvent;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.network.GuiHandler;
import wurmatron.voidrpg.common.proxy.CommonProxy;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.Registry;
import wurmatron.voidrpg.common.utils.LogHandler;

@Mod (modid = Global.MODID, name = Global.NAME, version = Global.VERSION, guiFactory = Global.GUIFACTORY, dependencies = Global.DEPENDENCIES)
public class VoidRPG {

	public static final CreativeTabs tabVoidRPG = new CreativeTabs ("tabVoidRPG") {
		@Override
		public ItemStack getTabIconItem () {
			return new ItemStack (VoidRPGItems.goggles);
		}
	};
	@Mod.Instance (Global.MODID)
	public static VoidRPG instance;
	@SidedProxy (serverSide = Global.COMMONPROXY, clientSide = Global.CLIENTPROXY)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void onPreInit (FMLPreInitializationEvent e) {
		LogHandler.info ("Pre-Init");
		ConfigHandler.preInit (e);
		ConfigHandler.loadMainConfig ();
		MinecraftForge.EVENT_BUS.register (new Registry ());
		MinecraftForge.EVENT_BUS.register (new ClientProxy ());
		VoidRPGBlocks.init ();
		VoidRPGItems.init ();
		proxy.register ();
	}

	@Mod.EventHandler
	public void onInit (FMLInitializationEvent e) {
		LogHandler.info ("Init");
		NetworkRegistry.INSTANCE.registerGuiHandler (this,new GuiHandler ());
		MinecraftForge.EVENT_BUS.register (new LivingTickEvent ());
		MinecraftForge.EVENT_BUS.register (new HurtEvent ());
		MinecraftForge.EVENT_BUS.register (new SetTargetEvent ());
		MinecraftForge.EVENT_BUS.register (new FallEvent ());
		CubeRegistry.addDefaultCubes ();
	}

	@Mod.EventHandler
	public void onPostInit (FMLPostInitializationEvent e) {
		//		VoidRPGRecipes.init ();
	}

	@Mod.EventHandler
	public void onServerStarting (FMLServerStartingEvent e) {
		JsonHandler.loadJsonCubes ();
	}
}
