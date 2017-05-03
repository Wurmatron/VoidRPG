package wurmatron.voidrpg.api.recipe;

import net.minecraft.item.ItemStack;

public interface ICubeCreatorRecipe {

	/**
	 Get the output for the recipe
	 */
	ItemStack getOutputCube ();

	/**
	 Required Items to create the output
	 */
	ItemStack[] getInputs ();

	/**
	 How long it takes for the recipe to finish (in-ticks)
	 */
	int getTimeInTicks ();
}
