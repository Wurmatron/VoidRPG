package wurmatron.voidrpg.api;

import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.api.recipe.ICubeCreatorRecipe;
import wurmatron.voidrpg.common.cube.CubeCreatorRecipeHandler;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.utils.ArmorHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Interact with the voidRPG mod easily using this
 */
public class VoidRPGAPI {

		/**
		 * Gets an list of all the registed cubes
		 */
		public static List<ICube> getCubes () {
				return Collections.unmodifiableList(CubeRegistry.INSTANCE.getCubes());
		}

		/**
		 * Adds the cube to the registry
		 *
		 * @param cube cube that you want to register
		 */
		public static void registerCube (ICube cube) {
				CubeRegistry.INSTANCE.registerCube(cube);
		}

		/**
		 *
		 * Gets an ArrayList of all the cubes on the itemstack that are valid
		 *
		 * @param stack ItemStack to get the cubes from
		 */
		public static ArrayList<ICube> getArmorCubes (ItemStack stack) {
				return new ArmorHelper().getCubes(stack);
		}

		/**
		 * Creates an new Cube Creator Recipe for use in the Cube Creator Machine
		 *
		 * @param recipe Cube Creator recipe that you want to add
		 */
		public static void registerRecipe (ICubeCreatorRecipe recipe) {
				CubeCreatorRecipeHandler.registerRecipe(recipe);
		}

		/**
		 * Removes an active Cube creator recipe
		 *
		 * @param recipe recipe that you want to remove from the cube creator
		 */
		public static void removeRecipe (ICubeCreatorRecipe recipe) {
				CubeCreatorRecipeHandler.registerRecipe(recipe);
		}

		/**
		 * List of all the currently known active recipes for the cube creator
		 */
		public static List<ICubeCreatorRecipe> getAllCubeCreatorRecipes() {
				return Collections.unmodifiableList(CubeCreatorRecipeHandler.getRecipes());
		}
}
