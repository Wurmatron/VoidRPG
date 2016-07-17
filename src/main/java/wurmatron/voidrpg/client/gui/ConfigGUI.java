package wurmatron.voidrpg.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.IConfigElement;
import wurmatron.voidrpg.common.config.ConfigHandler;
import wurmatron.voidrpg.common.reference.Global;

import java.util.ArrayList;
import java.util.List;

public class ConfigGUI extends GuiConfig {

		public ConfigGUI (GuiScreen ps) {
				super(ps, getConfigElements(), Global.NAME, "", false, false, ConfigHandler.mainConfig.getConfigFile().getName());
		}

		private static List<IConfigElement> getConfigElements () {
				List<IConfigElement> list = new ArrayList<IConfigElement>();
				list.add(new DummyConfigElement.DummyCategoryElement("General", ConfigHandler.mainConfig.getConfigFile().getName(), CategoryEntryGeneral.class));
				return list;
		}

		public static class CategoryEntryGeneral extends GuiConfigEntries.CategoryEntry {

				public CategoryEntryGeneral (GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
						super(owningScreen, owningEntryList, prop);
				}

				@Override
				protected GuiScreen buildChildScreen () {
						Configuration configuration = ConfigHandler.mainConfig;
						ConfigElement cat_general = new ConfigElement(configuration.getCategory(Configuration.CATEGORY_GENERAL));
						List<IConfigElement> propertiesOnThisScreen = cat_general.getChildElements();
						String windowTitle = configuration.toString();
						return new GuiConfig(owningScreen, propertiesOnThisScreen, owningScreen.modID, Configuration.CATEGORY_GENERAL, configElement.requiresWorldRestart() || owningScreen.allRequireWorldRestart, configElement.requiresMcRestart() || owningScreen.allRequireMcRestart, windowTitle);
				}
		}
}
