package wurmatron.voidrpg.common.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import wurmatron.voidrpg.client.gui.ConfigGUI;

import java.util.Set;

public class GuiFactory implements IModGuiFactory {

	@Override
	public void initialize (Minecraft mi) {
	}

	@Override
	public boolean hasConfigGui () {
		return false;
	}

	@Override
	public GuiScreen createConfigGui (GuiScreen parentScreen) {
		return new ConfigGUI (parentScreen);
	}

	@Override
	public Set <RuntimeOptionCategoryElement> runtimeGuiCategories () {
		return null;
	}
}
