package wurmatron.voidrpg.common.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.LogHandler;

import java.io.File;

public class ConfigHandler {

    public static File location;
    public static Configuration mainConfig;

    // Global.cfg
    public static Property debug;
    public static Property cubeCreatorUpdatePeriod;

    public static void preInit(FMLPreInitializationEvent e) {
        location = new File(e.getSuggestedConfigurationFile().getParent() + File.separator + Global.NAME);
        mainConfig = new Configuration(new File(location + File.separator + "Global.cfg"));
    }

    // Global.cfg
    public static void loadMainConfig() {
        if (mainConfig != null) {
            LogHandler.info("Loading main config");
            debug = mainConfig.get(Configuration.CATEGORY_GENERAL, "debug", Defaults.DEBUG, "Enable Debug Mode");
            Settings.debug = debug.getBoolean();
            cubeCreatorUpdatePeriod = mainConfig.get(Configuration.CATEGORY_GENERAL, "cubeCreatorUpdatePeriod", Defaults.CUBECREATORUPDATEPERIOD, "Cube Creator Update Time");
            Settings.cubeCreatorUpdatePeriod = cubeCreatorUpdatePeriod.getInt();

            if (mainConfig.hasChanged()) {
                LogHandler.info("Config saved");
                mainConfig.save();
            }
        } else
            LogHandler.error("Unable to load main config (Global.cfg)");
    }
}
