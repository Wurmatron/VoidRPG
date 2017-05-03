package wurmatron.voidrpg.common.cube;

import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.api.recipe.ICubeCreatorRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CubeCreatorRecipeHandler {

	private static ArrayList <ICubeCreatorRecipe> recipes = new ArrayList <> ();

	public static void registerRecipe (ICubeCreatorRecipe recipe) {
		if (!recipes.contains (recipe) && recipe.getInputs ().length <= 8)
			recipes.add (recipe);
	}

	public static void removeRecipe (ICubeCreatorRecipe recipe) {
		if (recipes.contains (recipe))
			recipes.remove (recipe);
	}

	public static void removeRecipe (ItemStack output) {
		recipes.stream ().filter (recipe -> recipe.getOutputCube ().isItemEqual (output)).forEach (recipe -> recipes.remove (recipe));
	}

	public static List <ICubeCreatorRecipe> getRecipes () {
		return Collections.unmodifiableList (recipes);
	}
}
