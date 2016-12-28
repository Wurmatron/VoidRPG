package wurmatron.voidrpg;


import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.config.ConfigHandler;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.network.GuiHandler;
import wurmatron.voidrpg.common.proxy.CommonProxy;
import wurmatron.voidrpg.common.recipes.VoidRPGRecipes;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.LogHandler;

@Mod(modid = Global.MODID, name = Global.NAME, version = Global.VERSION, guiFactory = Global.GUIFACTORY, dependencies = Global.DEPENDENCIES)
public class VoidRPG {

    @Mod.Instance(Global.MODID)
    public static VoidRPG instance;

    @SidedProxy(serverSide = Global.COMMONPROXY, clientSide = Global.CLIENTPROXY)
    public static CommonProxy proxy;

    public static final CreativeTabs tabVoidRPG = new CreativeTabs("tabVoidRPG") {
        @Override
        public Item getTabIconItem() {
            return VoidRPGItems.itemStaff;
        }
    };

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent e) {
        LogHandler.info("Pre-Init");
        ConfigHandler.preInit(e);
        VoidRPGBlocks.init();
        VoidRPGItems.init();
        proxy.register();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent e) {
        LogHandler.info("Init");
        ConfigHandler.loadMainConfig();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        VoidRPGRecipes.init();
         class CubeTest implements ICube {
             @Override
             public String getName() {
                 return "name";
             }

             @Override
             public Block getBlock() {
                 return Blocks.IRON_BLOCK;
             }

             @Override
             public ResourceLocation getTexture() {
                 return null;
             }

             @Override
             public double getWeight() {
                 return 10;
             }

             @Override
             public int getMaxAmount(Item item) {
                 return 10000000;
             }

             @Override
             public boolean getSupportedItem(EntityEquipmentSlot slot, Item item) {
                 return true;
             }

             @Override
             public String getDescription() {
                 return "";
             }
         }
        CubeRegistry.registerCube(new CubeTest());
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent e) {
        LogHandler.info("Post-Init");
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent e) {}
}
