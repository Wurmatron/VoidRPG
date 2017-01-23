package wurmatron.voidrpg;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.config.ConfigHandler;
import wurmatron.voidrpg.common.config.JsonHandler;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.cube.regular.*;
import wurmatron.voidrpg.common.cube.special.LifeSteal;
import wurmatron.voidrpg.common.cube.special.MobStealth;
import wurmatron.voidrpg.common.cube.special.chest.Gravity;
import wurmatron.voidrpg.common.cube.special.feet.FallReduction;
import wurmatron.voidrpg.common.cube.special.feet.WaterWalking;
import wurmatron.voidrpg.common.cube.special.head.NightVision;
import wurmatron.voidrpg.common.cube.special.head.WaterBreathing;
import wurmatron.voidrpg.common.cube.special.legs.CubeMuscle;
import wurmatron.voidrpg.common.event.FallEvent;
import wurmatron.voidrpg.common.event.HurtEvent;
import wurmatron.voidrpg.common.event.LivingTickEvent;
import wurmatron.voidrpg.common.event.SetTargetEvent;
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
            return VoidRPGItems.goggles;
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
        MinecraftForge.EVENT_BUS.register(new LivingTickEvent());
        MinecraftForge.EVENT_BUS.register(new HurtEvent());
        MinecraftForge.EVENT_BUS.register(new SetTargetEvent());
        MinecraftForge.EVENT_BUS.register(new FallEvent());
        CubeRegistry.registerCube(new LightArmor());
        CubeRegistry.registerCube(new HeavyArmor());
        CubeRegistry.registerCube(new CarborArmor());
        CubeRegistry.registerCube(new CardboardArmor());
        CubeRegistry.registerCube(new WoodCube());
        CubeRegistry.registerCube(new WaterWalking());
        CubeRegistry.registerCube(new NightVision());
        CubeRegistry.registerCube(new CardboardArmor());
        CubeRegistry.registerCube(new CubeMuscle());
        CubeRegistry.registerCube(new WaterBreathing());
        CubeRegistry.registerCube(new LifeSteal());
        CubeRegistry.registerCube(new MobStealth());
        CubeRegistry.registerCube(new FallReduction());
        CubeRegistry.registerCube(new Gravity());
        CubeRegistry.registerCube(new BasicCube("decoWhite", VoidRPGBlocks.decoWoolWhile, new ResourceLocation(Global.MODID, "textures/cube/decoWhite.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoOrange", VoidRPGBlocks.decoWoolOrange, new ResourceLocation(Global.MODID, "textures/cube/decoOrange.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoMagenta", VoidRPGBlocks.decoWoolMagenta, new ResourceLocation(Global.MODID, "textures/cube/decoMagenta.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoLightBlue", VoidRPGBlocks.decoWoolLightBlue, new ResourceLocation(Global.MODID, "textures/cube/decoLightBlue.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoYellow", VoidRPGBlocks.decoWoolYellow, new ResourceLocation(Global.MODID, "textures/cube/decoYellow.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoLime", VoidRPGBlocks.decoWoolLime, new ResourceLocation(Global.MODID, "textures/cube/decoLime.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoPink", VoidRPGBlocks.decoWoolPink, new ResourceLocation(Global.MODID, "textures/cube/decoPink.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoGray", VoidRPGBlocks.decoWoolGray, new ResourceLocation(Global.MODID, "textures/cube/decoGray.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoLightGray", VoidRPGBlocks.decoWoolLightGray, new ResourceLocation(Global.MODID, "textures/cube/decoLightGray.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoCyan", VoidRPGBlocks.decoWoolCyan, new ResourceLocation(Global.MODID, "textures/cube/decoCyan.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoPurple", VoidRPGBlocks.decoWoolPurple, new ResourceLocation(Global.MODID, "textures/cube/decoPurple.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoBlue", VoidRPGBlocks.decoWoolBlue, new ResourceLocation(Global.MODID, "textures/cube/decoBlue.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoBrown", VoidRPGBlocks.decoWoolBrown, new ResourceLocation(Global.MODID, "textures/cube/decoBrown.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoGreen", VoidRPGBlocks.decoWoolGreen, new ResourceLocation(Global.MODID, "textures/cube/decoGreen.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoRed", VoidRPGBlocks.decoWoolRed, new ResourceLocation(Global.MODID, "textures/cube/decoRed.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoBlack", VoidRPGBlocks.decoWoolBlack, new ResourceLocation(Global.MODID, "textures/cube/decoBlack.png"), 5.0, 500, 1, 4096, "", 5));
        CubeRegistry.registerCube(new BasicCube("decoDiamond", VoidRPGBlocks.decoDiamond, new ResourceLocation(Global.MODID, "textures/cube/decoDiamond.png"), 5.0, 2000, 1, 4096, "", 20));
        CubeRegistry.registerCube(new BasicCube("decoEmerald", VoidRPGBlocks.decoEmerald, new ResourceLocation(Global.MODID, "textures/cube/decoEmerald.png"), 5.0, 4000, 1, 4096, "", 20));
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent e) {
        JsonHandler.loadJsonCubes();
    }
}
