package wurmatron.voidrpg.common.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.LogHandler;

import java.io.File;

public class ConfigHandler {

		public static File location;
		public static Configuration mainConfig;

		// Global.cfg
		public static Property debug;
		public static Property jsonCubes;
		public static Property cubeEffects;
		public static Property updateCheck;
		public static Property helmetMaxComplexity;
		public static Property chestplateMaxComplexity;
		public static Property leggingsMaxComplexity;
		public static Property bootsMaxComplexity;
		public static Property cubeCreatorUpdatePeriod;
		public static Property modelPlacerBlock;

		public static void preInit (FMLPreInitializationEvent e) {
				location = new File(e.getSuggestedConfigurationFile().getParent() + File.separator + Global.NAME);
				mainConfig = new Configuration(new File(location + File.separator + "Global.cfg"));
		}

		// Global.cfg
		public static void loadMainConfig () {
				if (mainConfig != null) {
						LogHandler.info("Loading main config");
						debug = mainConfig.get(Configuration.CATEGORY_GENERAL, "debug", Defaults.DEBUG, "Enable Debug Mode");
						Settings.debug = debug.getBoolean();
						jsonCubes = mainConfig.get(Configuration.CATEGORY_GENERAL, "jsonCubes", Defaults.JSONCUBES, "Enable Json Based Cubes");
						Settings.jsonCubes = jsonCubes.getBoolean();
						cubeEffects = mainConfig.get(Configuration.CATEGORY_GENERAL, "cubeEffects", Defaults.CUBEEFFECTS, "Enable Cube Specials");
						Settings.cubeEffects = cubeEffects.getBoolean();
						updateCheck = mainConfig.get(Configuration.CATEGORY_GENERAL, "updateCheck", Defaults.UPDATECHECK, "Enables Checking for mod updates");
						Settings.updateCheck = updateCheck.getBoolean();
						helmetMaxComplexity = mainConfig.get(Configuration.CATEGORY_GENERAL, "helmetMaxComplexity", Defaults.HELMETMAXCOMPLEXITY, "Helmet Max Complexity");
						Settings.helmetMaxComplexity = helmetMaxComplexity.getInt();
						chestplateMaxComplexity = mainConfig.get(Configuration.CATEGORY_GENERAL, "chestplateMaxComplexity", Defaults.CHESTPLATEMAXCOMPLEXITY, "Chestplate Max Complexity");
						Settings.chestplateMaxComplexity = chestplateMaxComplexity.getInt();
						leggingsMaxComplexity = mainConfig.get(Configuration.CATEGORY_GENERAL, "leggingsMaxComplexity", Defaults.LEGGINGSMAXCOMPLEXITY, "Leggings Max Complexity");
						Settings.leggingsMaxComplexity = leggingsMaxComplexity.getInt();
						bootsMaxComplexity = mainConfig.get(Configuration.CATEGORY_GENERAL, "bootsMaxComplexity", Defaults.BOOTSMAXCOMPLEXITY, "Boots Max Complexity");
						Settings.bootsMaxComplexity = bootsMaxComplexity.getInt();
						cubeCreatorUpdatePeriod = mainConfig.get(Configuration.CATEGORY_GENERAL, "cubeCreatorUpdatePeriod", Defaults.CUBECREATORUPDATEPERIOD, "Cube Creator Update Period");
						Settings.cubeCreatorUpdatePeriod = cubeCreatorUpdatePeriod.getInt();
						modelPlacerBlock = mainConfig.get(Configuration.CATEGORY_GENERAL, "modelPlacerBlock", Defaults.MODELPLACERBLOCK, "Block that can model can be placed on");
						Settings.MODELPLACERBLOCK = modelPlacerBlock.getString();

						if (mainConfig.hasChanged()) {
								LogHandler.info("Config saved");
								mainConfig.save();
						}
				} else
						LogHandler.error("Unable to load main config (Global.cfg)");
		}

		public static void loadJsonCubes () {
				for (File json : JsonHandler.dir.listFiles()) {
						if (json != null && json.isFile()) {
								ICube temp = JsonHandler.loadCubeFromFile(json);
								if (!CubeRegistry.cubes.contains(temp)) {
										CubeRegistry.INSTANCE.registerCube(temp);
										LogHandler.info("Loaded cube '" + temp.getUnlocalizedName() + "' from json");
								}
						}
				}
		}
}
