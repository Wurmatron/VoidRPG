package wurmatron.voidrpg.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import wurmatron.voidrpg.common.items.ItemMaterial;
import wurmatron.voidrpg.common.items.ItemModelPlacer;
import wurmatron.voidrpg.common.items.ItemStaff;

public class VoidRPGRecipes {

		public static void init () {
				addRecipes();
				addMachineRecipes();
		}

		public static void addMachineRecipes () {
				addCubeCreatorRecipes();
		}

		public static void addRecipes () {
				addShapedRecipes();
				addShapelessRecipes();
		}

		private static void addShapedRecipes () {
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemModelPlacer.createModelPlacer(3), "III", "ICI", "X X", 'I', "gemDiamond", 'C', Items.DIAMOND_HELMET, 'X', Blocks.EMERALD_BLOCK));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemModelPlacer.createModelPlacer(2), "X X", "ICI", "III", 'I', "gemDiamond", 'C', Items.DIAMOND_CHESTPLATE, 'X', Blocks.EMERALD_BLOCK));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemModelPlacer.createModelPlacer(1), "XIX", "ICI", "I I", 'I', "gemDiamond", 'C', Items.DIAMOND_LEGGINGS, 'X', Blocks.EMERALD_BLOCK));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemModelPlacer.createModelPlacer(0), "I I", "XCX", 'I', "gemDiamond", 'C', Items.DIAMOND_BOOTS, 'X', Blocks.EMERALD_BLOCK));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemStaff.createStack(8), "XAX", "BSB", " S ", 'X', Blocks.DIAMOND_BLOCK, 'A', ItemMaterial.createMaterial("creationCrystal"), 'B', Blocks.EMERALD_BLOCK, 'S', Items.DIAMOND));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemStaff.createStack(8), "BAB", "XSX", " S ", 'X', Blocks.DIAMOND_BLOCK, 'A', ItemMaterial.createMaterial("creationCrystal"), 'B', Blocks.EMERALD_BLOCK, 'S', Items.DIAMOND));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("creationCrystal"), "XAX", "BXB", "XAX", 'X', Blocks.END_STONE, 'A', Items.BLAZE_ROD, 'B', Items.EMERALD));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("creationCrystal"), "XBX", "AXA", "XBX", 'X', Blocks.END_STONE, 'A', Items.BLAZE_ROD, 'B', Items.EMERALD));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("creationCrystal"), "XAX", "BXB", "XAX", 'X', Blocks.NETHER_BRICK, 'A', Items.CHORUS_FRUIT, 'B', Items.EMERALD));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("creationCrystal"), "XBX", "AXA", "XBX", 'X', Blocks.NETHER_BRICK, 'A', Items.CHORUS_FRUIT, 'B', Items.EMERALD));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemStaff.createStack(9000), "BAB", "XDX", " S ", 'X', Blocks.DIAMOND_BLOCK, 'A', ItemMaterial.createMaterial("creationCrystal"), 'B', Blocks.EMERALD_BLOCK, 'S', Items.DIAMOND, 'D', Blocks.DRAGON_EGG));
		}

		private static void addShapelessRecipes () {
		}

		private static void addCubeCreatorRecipes () {
		}
}
