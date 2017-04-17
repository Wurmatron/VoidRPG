package wurmatron.voidrpg.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.cube.CubeCreatorRecipe;
import wurmatron.voidrpg.common.cube.CubeCreatorRecipeHandler;
import wurmatron.voidrpg.common.items.ItemMaterial;
import wurmatron.voidrpg.common.items.ItemUpgrade;
import wurmatron.voidrpg.common.items.VoidRPGItems;

public class VoidRPGRecipes {

		public static void init() {
				addRecipes(); addMachineRecipes();
		}

		public static void addMachineRecipes() {
				addCubeCreatorRecipes();
		}

		public static void addRecipes() {
				addShapedRecipes(); addShapelessRecipes();
		}

		private static void addShapedRecipes() {
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("creationCrystal", 1), "XAX", "BXB", "XAX", 'X', Blocks.END_STONE, 'A', Items.BLAZE_ROD, 'B', Items.EMERALD));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("creationCrystal", 1), "XBX", "AXA", "XBX", 'X', Blocks.END_STONE, 'A', Items.BLAZE_ROD, 'B', Items.EMERALD));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("creationCrystal", 1), "XAX", "BXB", "XAX", 'X', Blocks.NETHER_BRICK, 'A', Items.CHORUS_FRUIT, 'B', Items.EMERALD));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("creationCrystal", 1), "XBX", "AXA", "XBX", 'X', Blocks.NETHER_BRICK, 'A', Items.CHORUS_FRUIT, 'B', Items.EMERALD));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("upgrade", 2), "BXB", "XAX", "BCB", 'B', Blocks.OBSIDIAN, 'X', Items.REDSTONE, 'A', Items.EMERALD, 'C', Items.ENDER_PEARL));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("basicArmorPlate", 1), "IXI", "XAX", "IXI", 'I', Items.IRON_INGOT, 'X', Items.GOLD_INGOT, 'A', Blocks.IRON_BLOCK));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("heavyArmorPlate", 2), "XPX", "PAP", "XPX", 'P', ItemMaterial.createMaterial("basicArmorPlate", 1), 'X', Items.IRON_INGOT, 'A', Items.DIAMOND));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("reactiveArmorPlating", 3), "PPP", "XXX", "PPP", 'P', ItemMaterial.createMaterial("heavyArmorPlate", 1), 'X', Items.DIAMOND));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("regenerativeArmorPlating", 6), "PPP", "XXX", "PPP", 'P', ItemMaterial.createMaterial("reactiveArmorPlating", 1), 'X', ItemMaterial.createMaterial("repairBot", 1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("energyArmorPlating", 1), "PPP", "XXX", "PPP", 'P', ItemMaterial.createMaterial("regenerativeArmorPlating", 1), 'X', ItemMaterial.createMaterial("battery", 1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("cardboard", 2), " P ", "PLP", " P ", 'P', Items.PAPER, 'L', "plankWood"));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("carbonChunk", 6), " P ", "PLP", " P ", 'P', new ItemStack(Items.COAL, 1, OreDictionary.WILDCARD_VALUE), 'L', Blocks.COAL_BLOCK));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("flippers", 1), " B ", "PPP", 'B', new ItemStack(Items.DIAMOND_BOOTS, 1, 0), 'P', "plankWood"));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("nanoTech", 1), "XAX", "BCB", "XAX", 'X', Blocks.REDSTONE_BLOCK, 'A', Items.EMERALD, 'B', Items.DIAMOND, 'C', Items.ENDER_EYE));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("mechanicalMuscle", 2), "XAX", "XAX", "XAX", 'X', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE), 'A', ItemMaterial.createMaterial("nanoTech", 1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("gravityCore", 1), "XAX", "ACA", "XAX", 'X', Items.NETHER_STAR, 'A', ItemMaterial.createMaterial("jetpackParts", 1), 'C', Blocks.DRAGON_EGG));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("jetpackParts", 4), "BXB", "BAB", "BBB", 'B', Blocks.REDSTONE_BLOCK, 'X', ItemMaterial.createMaterial("creationCrystal", 1), 'A', Items.NETHER_STAR));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("thruster", 1), " X ", "XAX", "BBB", 'X', Items.EMERALD, 'A', Blocks.DIAMOND_BLOCK, 'B', Items.BLAZE_POWDER));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("waterElectrolysisModule", 1), "XPX", "PAP", "XPX", 'X', Items.PRISMARINE_SHARD, 'P', PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionType.getPotionTypeForName("long_water_breathing")), 'A', Items.PRISMARINE_CRYSTALS));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("mindControl", 1), "XAX", "BCB", "XAX", 'X', Blocks.END_STONE, 'A', ItemMaterial.createMaterial("reactiveArmorPlating", 1), 'B', Items.EMERALD, 'C', new ItemStack(Items.GOLDEN_APPLE, 1, 0)));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("wings", 1), "L L", "LSL", "L L", 'L', Items.LEATHER, 'S', Items.BONE));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("wings", 1), "L L", "LSL", "L L", 'L', Items.LEATHER, 'S', Items.STICK));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("battery", 1), " C ", "IRI", "IRI", 'C', Items.GOLD_INGOT, 'I', Items.IRON_INGOT, 'R', Items.REDSTONE));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("solarPanel", 1), "GGG", "IWI", "IRI", 'G', Blocks.GLASS, 'I', Items.GOLD_INGOT, 'W', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE), 'R', Items.REDSTONE));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("largeReactor", 1), "CCC", "CRC", "CCC", 'C', ItemMaterial.createMaterial("smallReactor", 1), 'R', ItemMaterial.createMaterial("nanoTech", 1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemUpgrade.createMaterial("speedI"), "BRB", "RAR", "BCB", 'B', Blocks.REDSTONE_BLOCK, 'R', Blocks.LAPIS_BLOCK, 'A', ItemMaterial.createMaterial("upgrade"), 'C', Items.DIAMOND));
				GameRegistry.addRecipe(new ShapedOreRecipe(VoidRPGBlocks.cubeCreator, "BFB", "XAX", "BFB", 'F', Blocks.FURNACE, 'B', Blocks.IRON_BLOCK, 'X', ItemMaterial.createMaterial("creationCrystal", 1), 'A', Blocks.CHEST));
		}

		private static void addShapelessRecipes() {
				GameRegistry.addRecipe(new ShapelessOreRecipe(VoidRPGItems.goggles, Items.DIAMOND_HELMET, ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("creationCrystal", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(ItemMaterial.createMaterial("smallReactor", 1), Blocks.EMERALD_BLOCK, Blocks.DIAMOND_BLOCK, ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("nanoTech", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(ItemMaterial.createMaterial("repairBot", 1), Blocks.DIAMOND_BLOCK, ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("nanoTech", 1), new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(ItemUpgrade.createMaterial("speedII"), ItemUpgrade.createMaterial("speedI"), ItemUpgrade.createMaterial("speedI"), ItemUpgrade.createMaterial("speedI"), ItemUpgrade.createMaterial("speedI")));
				GameRegistry.addRecipe(new ShapelessOreRecipe(ItemUpgrade.createMaterial("speedIII"), ItemUpgrade.createMaterial("speedII"), ItemUpgrade.createMaterial("speedII"), ItemUpgrade.createMaterial("speedII"), ItemUpgrade.createMaterial("speedII")));
		}

		private static void addCubeCreatorRecipes() {
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(new ItemStack(Blocks.OBSIDIAN, 4, 0), new ItemStack[]{new ItemStack(Items.LAVA_BUCKET), new ItemStack(Items.LAVA_BUCKET), new ItemStack(Items.WATER_BUCKET), new ItemStack(Items.WATER_BUCKET)}, 600));
		}
}
