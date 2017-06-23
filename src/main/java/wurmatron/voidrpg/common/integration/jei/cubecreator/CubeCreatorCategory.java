package wurmatron.voidrpg.common.integration.jei.cubecreator;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.item.ItemStack;
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
	public String getModName () {
		return Global.NAME;
	}

	@Override
	public void setRecipe (IRecipeLayout recipeLayout,CubeCreatorRecipeWrapper wrapper,IIngredients recipe) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks ();
		if (recipe.getInputs (ItemStack.class).size () > 0) {
			stacks.init (0,true,18,0);
			stacks.set (0,recipe.getInputs (ItemStack.class).get (0));
		}
		if (recipe.getInputs (ItemStack.class).size () > 1) {
			stacks.init (1,true,54,0);
			stacks.set (1,recipe.getInputs (ItemStack.class).get (1));
		}
		if (recipe.getInputs (ItemStack.class).size () > 2) {
			stacks.init (2,true,0,18);
			stacks.set (2,recipe.getInputs (ItemStack.class).get (2));
		}
		if (recipe.getInputs (ItemStack.class).size () > 3) {
			stacks.init (3,true,72,18);
			stacks.set (3,recipe.getInputs (ItemStack.class).get (3));
		}
		if (recipe.getInputs (ItemStack.class).size () > 4) {
			stacks.init (4,true,0,54);
			stacks.set (4,recipe.getInputs (ItemStack.class).get (4));
		}
		if (recipe.getInputs (ItemStack.class).size () > 5) {
			stacks.init (5,true,72,54);
			stacks.set (5,recipe.getInputs (ItemStack.class).get (5));
		}
		if (recipe.getInputs (ItemStack.class).size () > 6) {
			stacks.init (6,true,18,72);
			stacks.set (6,recipe.getInputs (ItemStack.class).get (6));
		}
		if (recipe.getInputs (ItemStack.class).size () > 7) {
			stacks.init (7,true,54,72);
			stacks.set (7,recipe.getInputs (ItemStack.class).get (7));
		}
		stacks.init (9,true,36,36);
		stacks.set (9,recipe.getOutputs (ItemStack.class).get (0));
	}

}
