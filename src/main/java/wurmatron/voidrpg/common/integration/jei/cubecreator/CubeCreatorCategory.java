package wurmatron.voidrpg.common.integration.jei.cubecreator;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.common.reference.Global;

import javax.annotation.Nonnull;

public class CubeCreatorCategory extends BlankRecipeCategory <CubeCreatorRecipeWrapper> {

	private final IDrawable background;

	public CubeCreatorCategory (IGuiHelper helper) {
		background = helper.createDrawable (new ResourceLocation (Global.MODID + ":textures/gui/jei.cubeCreator.png"),0,0,89,89);
	}

	@Nonnull
	@Override
	public String getUid () {
		return "voidrpg.cubecreator";
	}

	@Nonnull
	@Override
	public String getTitle () {
		return "Cube Creator";
	}

	@Nonnull
	@Override
	public IDrawable getBackground () {
		return background;
	}

	@Override
	public void setRecipe (@Nonnull IRecipeLayout recipeLayout,@Nonnull CubeCreatorRecipeWrapper recipe) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks ();
		if (recipe.getInputs ().size () > 0) {
			stacks.init (0,true,18,0);
			stacks.setFromRecipe (0,recipe.getInputs ().get (0));
		}
		if (recipe.getInputs ().size () > 1) {
			stacks.init (1,true,54,0);
			stacks.setFromRecipe (1,recipe.getInputs ().get (1));
		}
		if (recipe.getInputs ().size () > 2) {
			stacks.init (2,true,0,18);
			stacks.setFromRecipe (2,recipe.getInputs ().get (2));
		}
		if (recipe.getInputs ().size () > 3) {
			stacks.init (3,true,72,18);
			stacks.setFromRecipe (3,recipe.getInputs ().get (3));
		}
		if (recipe.getInputs ().size () > 4) {
			stacks.init (4,true,0,54);
			stacks.setFromRecipe (4,recipe.getInputs ().get (4));
		}
		if (recipe.getInputs ().size () > 5) {
			stacks.init (5,true,72,54);
			stacks.setFromRecipe (5,recipe.getInputs ().get (5));
		}
		if (recipe.getInputs ().size () > 6) {
			stacks.init (6,true,18,72);
			stacks.setFromRecipe (6,recipe.getInputs ().get (6));
		}
		if (recipe.getInputs ().size () > 7) {
			stacks.init (7,true,54,72);
			stacks.setFromRecipe (7,recipe.getInputs ().get (7));
		}
		stacks.init (9,true,36,36);
		stacks.setFromRecipe (9,recipe.getOutputs ().get (0));
	}
}
