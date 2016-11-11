package wurmatron.voidrpg.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.cube.CubeCreatorRecipe;
import wurmatron.voidrpg.common.cube.CubeCreatorRecipeHandler;
import wurmatron.voidrpg.common.items.ItemMaterial;
import wurmatron.voidrpg.common.items.ItemModelPlacer;
import wurmatron.voidrpg.common.items.ItemStaff;
import wurmatron.voidrpg.common.items.ItemUpgrade;
import wurmatron.voidrpg.common.utils.StackHelper;

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
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemStaff.createStack(8), "XAX", "BSB", " S ", 'X', Blocks.DIAMOND_BLOCK, 'A', ItemMaterial.createMaterial("creationCrystal", 1), 'B', Blocks.EMERALD_BLOCK, 'S', Items.DIAMOND));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemStaff.createStack(8), "BAB", "XSX", " S ", 'X', Blocks.DIAMOND_BLOCK, 'A', ItemMaterial.createMaterial("creationCrystal", 1), 'B', Blocks.EMERALD_BLOCK, 'S', Items.DIAMOND));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("creationCrystal", 1), "XAX", "BXB", "XAX", 'X', Blocks.END_STONE, 'A', Items.BLAZE_ROD, 'B', Items.EMERALD));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("creationCrystal", 1), "XBX", "AXA", "XBX", 'X', Blocks.END_STONE, 'A', Items.BLAZE_ROD, 'B', Items.EMERALD));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("creationCrystal", 1), "XAX", "BXB", "XAX", 'X', Blocks.NETHER_BRICK, 'A', Items.CHORUS_FRUIT, 'B', Items.EMERALD));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("creationCrystal", 1), "XBX", "AXA", "XBX", 'X', Blocks.NETHER_BRICK, 'A', Items.CHORUS_FRUIT, 'B', Items.EMERALD));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemStaff.createStack(9000), "BAB", "XDX", " S ", 'X', Blocks.DIAMOND_BLOCK, 'A', ItemMaterial.createMaterial("creationCrystal", 1), 'B', Blocks.EMERALD_BLOCK, 'S', Items.DIAMOND, 'D', Blocks.DRAGON_EGG));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemStaff.createStack(9000), "XAX", "BDB", " S ", 'X', Blocks.DIAMOND_BLOCK, 'A', ItemMaterial.createMaterial("creationCrystal", 1), 'B', Blocks.EMERALD_BLOCK, 'S', Items.DIAMOND, 'D', Blocks.DRAGON_EGG));
				GameRegistry.addRecipe(new ShapedOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.energyStorageI, 3), "PPP", "BBB", "PPP", 'P', "plankWood", 'B', ItemMaterial.createMaterial("battery", 1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.energyStorageII, 1), "PPP", "BBB", "PPP", 'P', "plankWood", 'B', new StackHelper().createBitFromBlock(VoidRPGBlocks.energyStorageI, 1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.energyStorageIII, 1), "PPP", "BBB", "PPP", 'P', "plankWood", 'B', new StackHelper().createBitFromBlock(VoidRPGBlocks.energyStorageII, 1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.energyStorageIV, 1), "PPP", "BBB", "PPP", 'P', "plankWood", 'B', new StackHelper().createBitFromBlock(VoidRPGBlocks.energyStorageIII, 1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.energyStorageV, 1), "PPP", "BBB", "PPP", 'P', "plankWood", 'B', new StackHelper().createBitFromBlock(VoidRPGBlocks.energyStorageIV, 1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("upgrade",2), "BXB", "XAX", "BCB", 'B', Blocks.OBSIDIAN, 'X', Items.REDSTONE, 'A', Items.EMERALD, 'C', Items.ENDER_PEARL));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("basicArmorPlate",1), "IXI", "XAX", "IXI", 'I', Items.IRON_INGOT, 'X', Items.GOLD_INGOT, 'A', Blocks.IRON_BLOCK));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("heavyArmorPlate",2), "XPX", "PAP", "XPX", 'P', ItemMaterial.createMaterial("basicArmorPlate",1), 'X', Items.IRON_INGOT, 'A', Items.DIAMOND));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("reactiveArmorPlating",3), "PPP", "XXX", "PPP", 'P', ItemMaterial.createMaterial("heavyArmorPlate",1), 'X', Items.DIAMOND));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("regenerativeArmorPlating",6), "PPP", "XXX", "PPP", 'P', ItemMaterial.createMaterial("reactiveArmorPlating",1), 'X', ItemMaterial.createMaterial("repairBot", 1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("energyArmorPlating",1), "PPP", "XXX", "PPP", 'P', ItemMaterial.createMaterial("regenerativeArmorPlating",1), 'X', ItemMaterial.createMaterial("battery", 1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("cardboard",2), " P ", "PLP", " P ", 'P', Items.PAPER, 'L', "plankWood"));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("carbonChunk",6), " P ", "PLP", " P ", 'P', new ItemStack(Items.COAL,1,OreDictionary.WILDCARD_VALUE), 'L', Blocks.COAL_BLOCK));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("flippers",1)," B ", "PPP", 'B', new ItemStack(Items.DIAMOND_BOOTS,1,0), 'P', "plankWood"));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("nanoTech",1), "XAX", "BCB", "XAX", 'X', Blocks.REDSTONE_BLOCK, 'A', Items.EMERALD, 'B', Items.DIAMOND, 'C', Items.ENDER_EYE));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("mechanicalMuscle",2), "XAX", "XAX", "XAX", 'X',new ItemStack(Blocks.WOOL,1,OreDictionary.WILDCARD_VALUE), 'A', ItemMaterial.createMaterial("nanoTech",1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("gravityCore",1), "XAX", "ACA", "XAX", 'X', Items.NETHER_STAR, 'A', ItemMaterial.createMaterial("jetpackParts",1), 'C',Blocks.DRAGON_EGG));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("jetpackParts",4), "BXB", "BAB", "BBB", 'B', Blocks.REDSTONE_BLOCK, 'X', ItemMaterial.createMaterial("creationCrystal",1), 'A', Items.NETHER_STAR));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("thruster",1), " X ", "XAX", "BBB", 'X', Items.EMERALD, 'A', Blocks.DIAMOND_BLOCK, 'B', Items.BLAZE_POWDER));
//				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("waterElectrolysisModule",1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("mindControl",1), "XAX", "BCB", "XAX", 'X', Blocks.END_STONE, 'A', ItemMaterial.createMaterial("reactiveArmorPlating",1), 'B', Items.EMERALD, 'C', new ItemStack(Items.GOLDEN_APPLE,1,0)));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("wings",1), "L L", "LSL", "L L", 'L', Items.LEATHER, 'S', Items.BONE));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("wings",1), "L L", "LSL", "L L", 'L', Items.LEATHER, 'S', Items.STICK));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("battery",1), " C ", "IRI", "IRI", 'C', Items.GOLD_INGOT, 'I', Items.IRON_INGOT, 'R', Items.REDSTONE));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("solarPanel",1), "GGG", "IWI", "IRI", 'G', Blocks.GLASS, 'I', Items.GOLD_INGOT, 'W', new ItemStack(Blocks.WOOL,1,OreDictionary.WILDCARD_VALUE), 'R', Items.REDSTONE));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemMaterial.createMaterial("largeReactor",1), "CCC", "CRC", "CCC", 'C', ItemMaterial.createMaterial("nanoTech",1), 'R', ItemMaterial.createMaterial("smallReactor",1)));
				GameRegistry.addRecipe(new ShapedOreRecipe(ItemUpgrade.createMaterial("speedI"), "BRB", "RAR", "BCB", 'B', Blocks.REDSTONE_BLOCK, 'R', Blocks.LAPIS_BLOCK, 'A', Items.GOLD_INGOT, 'C', Items.DIAMOND));
		}

		private static void addShapelessRecipes () {
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.energyReactorI, 1), ItemMaterial.createMaterial("smallReactor"), new ItemStack(Blocks.DIAMOND_BLOCK), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("nanoTech", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.energyReactorII, 1), ItemMaterial.createMaterial("smallReactor"), new ItemStack(Blocks.DIAMOND_BLOCK), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("nanoTech", 1), new StackHelper().createBitFromBlock(VoidRPGBlocks.energyReactorI, 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.energyReactorII, 1), ItemMaterial.createMaterial("largeReactor"), new ItemStack(Blocks.DIAMOND_BLOCK), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("nanoTech", 1), new StackHelper().createBitFromBlock(VoidRPGBlocks.energyReactorII, 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoDiamond, 18), new ItemStack(Blocks.DIAMOND_BLOCK), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoEmerald, 18), new ItemStack(Blocks.EMERALD_BLOCK), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolWhile, 9), new ItemStack(Blocks.WOOL, 1, 0), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolOrange, 9), new ItemStack(Blocks.WOOL, 1, 1), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolMagenta, 9), new ItemStack(Blocks.WOOL, 1, 2), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolLightBlue, 9), new ItemStack(Blocks.WOOL, 1, 3), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolYellow, 9), new ItemStack(Blocks.WOOL, 1, 4), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolLime, 9), new ItemStack(Blocks.WOOL, 1, 5), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolPink, 9), new ItemStack(Blocks.WOOL, 1, 6), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolGray, 9), new ItemStack(Blocks.WOOL, 1, 7), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolLightGray, 9), new ItemStack(Blocks.WOOL, 1, 8), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolCyan, 9), new ItemStack(Blocks.WOOL, 1, 9), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolPurple, 9), new ItemStack(Blocks.WOOL, 1, 10), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolBlue, 9), new ItemStack(Blocks.WOOL, 1, 11), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolBrown, 9), new ItemStack(Blocks.WOOL, 1, 12), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolGreen, 9), new ItemStack(Blocks.WOOL, 1, 13), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolRed, 9), new ItemStack(Blocks.WOOL, 1, 14), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(new StackHelper().createBitFromBlock(VoidRPGBlocks.decoWoolBlack, 9), new ItemStack(Blocks.WOOL, 1, 15), ItemMaterial.createMaterial("basicArmorPlate", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(ItemMaterial.createMaterial("goggles",1), Items.DIAMOND_HELMET,ItemMaterial.createMaterial("nanoTech",1), ItemMaterial.createMaterial("creationCrystal",1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(ItemMaterial.createMaterial("smallReactor",1), Blocks.EMERALD_BLOCK, Blocks.DIAMOND_BLOCK,ItemMaterial.createMaterial("nanoTech", 1),ItemMaterial.createMaterial("nanoTech", 1)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(ItemMaterial.createMaterial("repairBot",1), Blocks.DIAMOND_BLOCK, ItemMaterial.createMaterial("nanoTech", 1),ItemMaterial.createMaterial("nanoTech", 1),ItemMaterial.createMaterial("nanoTech", 1), new ItemStack(Blocks.WOOL,1,OreDictionary.WILDCARD_VALUE)));
				GameRegistry.addRecipe(new ShapelessOreRecipe(ItemUpgrade.createMaterial("speedII"), ItemUpgrade.createMaterial("speedI"),ItemUpgrade.createMaterial("speedI"),ItemUpgrade.createMaterial("speedI"),ItemUpgrade.createMaterial("speedI")));
				GameRegistry.addRecipe(new ShapelessOreRecipe(ItemUpgrade.createMaterial("speedIII"), ItemUpgrade.createMaterial("speedII"),ItemUpgrade.createMaterial("speedII"),ItemUpgrade.createMaterial("speedII"),ItemUpgrade.createMaterial("speedII")));
		}

		private static void addCubeCreatorRecipes () {
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.armorCardboard, 1, new ItemStack[] {ItemMaterial.createMaterial("cardboard", 1), ItemMaterial.createMaterial("cardboard", 1), new ItemStack(Items.PAPER, 1), new ItemStack(Items.PAPER, 1), new ItemStack(Items.PAPER, 1), new ItemStack(Items.PAPER, 1), ItemMaterial.createMaterial("cardboard", 1), ItemMaterial.createMaterial("cardboard", 1)}, 800));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.armorLight, 4, new ItemStack[] {ItemMaterial.createMaterial("basicArmorPlate", 1), ItemMaterial.createMaterial("basicArmorPlate", 1), new ItemStack(Items.IRON_INGOT, 1), new ItemStack(Items.IRON_INGOT, 1), new ItemStack(Items.IRON_INGOT, 1), new ItemStack(Items.IRON_INGOT, 1), ItemMaterial.createMaterial("basicArmorPlate", 1), ItemMaterial.createMaterial("basicArmorPlate", 1)}, 400));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.armorHeavy, 8, new ItemStack[] {ItemMaterial.createMaterial("heavyArmorPlate", 1), ItemMaterial.createMaterial("heavyArmorPlate", 1), ItemMaterial.createMaterial("heavyArmorPlate", 1), ItemMaterial.createMaterial("heavyArmorPlate", 1), ItemMaterial.createMaterial("heavyArmorPlate", 1), ItemMaterial.createMaterial("heavyArmorPlate", 1), ItemMaterial.createMaterial("heavyArmorPlate", 1), ItemMaterial.createMaterial("heavyArmorPlate", 1)}, 800));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.armorReinforced, 8, new ItemStack[] {ItemMaterial.createMaterial("heavyArmorPlate", 1), ItemMaterial.createMaterial("basicArmorPlate", 1), ItemMaterial.createMaterial("heavyArmorPlate", 1), ItemMaterial.createMaterial("basicArmorPlate", 1), ItemMaterial.createMaterial("heavyArmorPlate", 1), ItemMaterial.createMaterial("basicArmorPlate", 1), ItemMaterial.createMaterial("heavyArmorPlate", 1), ItemMaterial.createMaterial("basicArmorPlate", 1)}, 1200));
				for (ItemStack wood : OreDictionary.getOres("logWood"))
						CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.armorWood, 4, new ItemStack[] {wood, wood, new ItemStack(Items.IRON_INGOT), new ItemStack(Items.IRON_INGOT), new ItemStack(Items.IRON_INGOT), new ItemStack(Items.IRON_INGOT), wood, wood}, 600));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.armorActivePlating, 4, new ItemStack[] {ItemMaterial.createMaterial("reactiveArmorPlating", 1), ItemMaterial.createMaterial("reactiveArmorPlating", 1), new ItemStack(Items.DIAMOND), new ItemStack(Items.ENDER_PEARL), new ItemStack(Items.ENDER_PEARL), new ItemStack(Items.DIAMOND), ItemMaterial.createMaterial("reactiveArmorPlating", 1), ItemMaterial.createMaterial("reactiveArmorPlating", 1)}, 4000));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.armorNanoTech, 2, new ItemStack[] {ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("battery", 1), ItemMaterial.createMaterial("reactiveArmorPlating", 1), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("reactiveArmorPlating", 1), ItemMaterial.createMaterial("battery", 1), ItemMaterial.createMaterial("nanoTech", 1)}, 800));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.armorEnergyShieldI, 4, new ItemStack[] {ItemMaterial.createMaterial("energyArmorPlating", 1), ItemMaterial.createMaterial("energyArmorPlating", 1), ItemMaterial.createMaterial("battery", 1), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("battery", 1), ItemMaterial.createMaterial("energyArmorPlating", 1), ItemMaterial.createMaterial("energyArmorPlating", 1)}, 8000));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.armorEnergyShieldII, 4, new ItemStack[] {new StackHelper().createBitFromBlock(VoidRPGBlocks.armorEnergyShieldI, 1), new StackHelper().createBitFromBlock(VoidRPGBlocks.armorEnergyShieldI, 1), ItemMaterial.createMaterial("battery", 1), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("battery", 1), new StackHelper().createBitFromBlock(VoidRPGBlocks.armorEnergyShieldI, 1), new StackHelper().createBitFromBlock(VoidRPGBlocks.armorEnergyShieldI, 1)}, 8000));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.armorEnergyShieldIII, 4, new ItemStack[] {new StackHelper().createBitFromBlock(VoidRPGBlocks.armorEnergyShieldII, 1), new StackHelper().createBitFromBlock(VoidRPGBlocks.armorEnergyShieldII, 1), ItemMaterial.createMaterial("battery", 1), new StackHelper().createBitFromBlock(VoidRPGBlocks.armorEnergyShieldII, 1), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("battery", 1), new StackHelper().createBitFromBlock(VoidRPGBlocks.armorEnergyShieldII, 1), new StackHelper().createBitFromBlock(VoidRPGBlocks.armorEnergyShieldII, 1)}, 8000));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.armorCarbon, 4, new ItemStack[] {ItemMaterial.createMaterial("carbonChunk", 8), ItemMaterial.createMaterial("carbonChunk", 8), new ItemStack(Items.DIAMOND), new ItemStack(Blocks.COAL_BLOCK), new ItemStack(Blocks.COAL_BLOCK), new ItemStack(Items.DIAMOND), ItemMaterial.createMaterial("carbonChunk", 8), ItemMaterial.createMaterial("carbonChunk", 8)}, 800));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.armorLife, 2, new ItemStack[] {ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("nanoTech", 1), new ItemStack(Items.GOLDEN_APPLE, 1, 1), new ItemStack(Blocks.DIAMOND_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), new ItemStack(Items.GOLDEN_APPLE, 1, 1), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("nanoTech", 1)}, 1200));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.cubeWaterWalking, 4, new ItemStack[] {new ItemStack(Blocks.SPONGE, 4), new ItemStack(Blocks.SPONGE, 4), ItemMaterial.createMaterial("cardboard", 1), new ItemStack(Items.DIAMOND), new ItemStack(Items.DIAMOND), ItemMaterial.createMaterial("cardboard", 1), new ItemStack(Blocks.SPONGE, 4), new ItemStack(Blocks.SPONGE, 4)}, 800));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.cubeShock, 8, new ItemStack[] {new ItemStack(Blocks.WOOL, 8, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.WOOL, 8, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.ENDER_PEARL, 2), new ItemStack(Items.BLAZE_ROD, 2), new ItemStack(Items.BLAZE_ROD, 2), new ItemStack(Items.ENDER_PEARL, 2), new ItemStack(Blocks.WOOL, 8, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.WOOL, 8, OreDictionary.WILDCARD_VALUE)}, 2000));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.cubeFlippers, 2, new ItemStack[] {ItemMaterial.createMaterial("flippers", 1), new ItemStack(Blocks.LOG, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.DIAMOND), new ItemStack(Items.GOLDEN_CARROT), new ItemStack(Items.GOLDEN_CARROT), new ItemStack(Items.DIAMOND), new ItemStack(Blocks.LOG, 1, OreDictionary.WILDCARD_VALUE), ItemMaterial.createMaterial("flippers", 1)}, 800));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.cubeMuscle, 4, new ItemStack[] {ItemMaterial.createMaterial("mechanicalMuscle", 1), ItemMaterial.createMaterial("mechanicalMuscle", 1), new ItemStack(Items.DIAMOND_SWORD), new ItemStack(Items.GOLDEN_APPLE, 1, 0), new ItemStack(Items.GOLDEN_APPLE, 1, 0), new ItemStack(Items.DIAMOND_SWORD), ItemMaterial.createMaterial("mechanicalMuscle", 1), ItemMaterial.createMaterial("mechanicalMuscle", 1)}, 1200));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.cubeGravity, 1, new ItemStack[] {ItemMaterial.createMaterial("gravityCore", 2), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("carbonChunk", 64), ItemMaterial.createMaterial("carbonChunk", 64), ItemMaterial.createMaterial("nanoTech", 1), ItemMaterial.createMaterial("nanoTech", 2), ItemMaterial.createMaterial("gravityCore", 2), ItemMaterial.createMaterial("gravityCore", 2)}, 8000));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.cubeJetpack, 2, new ItemStack[] {ItemMaterial.createMaterial("thruster", 2), ItemMaterial.createMaterial("jetpackParts", 2), new ItemStack(Blocks.REDSTONE_BLOCK, 2), new ItemStack(Blocks.REDSTONE_BLOCK, 2), new ItemStack(Blocks.REDSTONE_BLOCK, 2), new ItemStack(Blocks.REDSTONE_BLOCK, 2), ItemMaterial.createMaterial("jetpackParts", 2), ItemMaterial.createMaterial("thruster", 2)}, 6000));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.cubeMobStealth, 2, new ItemStack[] {ItemMaterial.createMaterial("mindControl", 2), ItemMaterial.createMaterial("nanoTech", 2), new ItemStack(Blocks.DIAMOND_BLOCK, 2), new ItemStack(Blocks.DIAMOND_BLOCK, 2), new ItemStack(Blocks.DIAMOND_BLOCK, 2), new ItemStack(Blocks.DIAMOND_BLOCK, 2), ItemMaterial.createMaterial("nanoTech", 2), ItemMaterial.createMaterial("mindControl", 2)}, 6000));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.cubeWing, 1, new ItemStack[] {ItemMaterial.createMaterial("wings", 1), new ItemStack(Items.LEATHER, 8), new ItemStack(Items.DIAMOND, 4), new ItemStack(Items.DIAMOND, 4), new ItemStack(Items.DIAMOND, 4), new ItemStack(Items.DIAMOND, 4), new ItemStack(Items.LEATHER, 8), ItemMaterial.createMaterial("wings", 1)}, 800));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.energySolarI, 1, new ItemStack[] {ItemMaterial.createMaterial("solarPanel", 2), ItemMaterial.createMaterial("solarPanel", 2), new ItemStack(Items.GOLD_INGOT, 4), new ItemStack(Items.REDSTONE, 8), new ItemStack(Items.REDSTONE, 8), new ItemStack(Items.GOLD_INGOT, 4), ItemMaterial.createMaterial("solarPanel", 2), ItemMaterial.createMaterial("solarPanel", 2)}, 1250));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.energySolarII, 1, new ItemStack[] {new StackHelper().createBitFromBlock(VoidRPGBlocks.energySolarI, 1), new StackHelper().createBitFromBlock(VoidRPGBlocks.energySolarI, 1), new ItemStack(Items.GOLD_INGOT, 4), new ItemStack(Items.REDSTONE, 8), new ItemStack(Items.REDSTONE, 8), new ItemStack(Items.GOLD_INGOT, 4), new StackHelper().createBitFromBlock(VoidRPGBlocks.energySolarI, 1), new StackHelper().createBitFromBlock(VoidRPGBlocks.energySolarI, 1)}, 1250));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.energySolarIII, 1, new ItemStack[] {new StackHelper().createBitFromBlock(VoidRPGBlocks.energySolarII, 1), new StackHelper().createBitFromBlock(VoidRPGBlocks.energySolarII, 1), new ItemStack(Items.GOLD_INGOT, 4), new ItemStack(Items.REDSTONE, 8), new ItemStack(Items.REDSTONE, 8), new ItemStack(Items.GOLD_INGOT, 4), new StackHelper().createBitFromBlock(VoidRPGBlocks.energySolarII, 1), new StackHelper().createBitFromBlock(VoidRPGBlocks.energySolarII, 1)}, 1250));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.energyKenetic, 2, new ItemStack[] {ItemMaterial.createMaterial("mechanicalMuscle", 2), new ItemStack(Blocks.WOOL, 2, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.DIAMOND, 4), new ItemStack(Blocks.REDSTONE_BLOCK, 2), new ItemStack(Blocks.REDSTONE_BLOCK, 2), new ItemStack(Items.DIAMOND, 4), new ItemStack(Blocks.WOOL, 2, OreDictionary.WILDCARD_VALUE), ItemMaterial.createMaterial("mechanicalMuscle", 2)}, 800));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.energyAutoRepairI, 1, new ItemStack[] {ItemMaterial.createMaterial("repairBot", 2), ItemMaterial.createMaterial("repairBot", 2), ItemMaterial.createMaterial("nanoTech", 4), ItemMaterial.createMaterial("nanoTech", 4), ItemMaterial.createMaterial("nanoTech", 4), ItemMaterial.createMaterial("nanoTech", 4), ItemMaterial.createMaterial("repairBot", 2), ItemMaterial.createMaterial("repairBot", 2)}, 12500));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.energyAutoRepairII, 1, new ItemStack[] {ItemMaterial.createMaterial("repairBot", 2), ItemMaterial.createMaterial("repairBot", 2), new StackHelper().createBitFromBlock(VoidRPGBlocks.energyAutoRepairI), new StackHelper().createBitFromBlock(VoidRPGBlocks.energyAutoRepairI), new StackHelper().createBitFromBlock(VoidRPGBlocks.energyAutoRepairI), new StackHelper().createBitFromBlock(VoidRPGBlocks.energyAutoRepairI), new StackHelper().createBitFromBlock(VoidRPGBlocks.energyAutoRepairI), ItemMaterial.createMaterial("repairBot", 2)}, 12500));
				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.energyAutoRepairIII, 1, new ItemStack[] {ItemMaterial.createMaterial("repairBot", 8), ItemMaterial.createMaterial("repairBot", 2), new StackHelper().createBitFromBlock(VoidRPGBlocks.energyAutoRepairII), new StackHelper().createBitFromBlock(VoidRPGBlocks.energyAutoRepairII), new StackHelper().createBitFromBlock(VoidRPGBlocks.energyAutoRepairII), new StackHelper().createBitFromBlock(VoidRPGBlocks.energyAutoRepairII), new StackHelper().createBitFromBlock(VoidRPGBlocks.energyAutoRepairII), ItemMaterial.createMaterial("repairBot", 8)}, 12500));
//				CubeCreatorRecipeHandler.registerRecipe(new CubeCreatorRecipe(VoidRPGBlocks.cubeStealth));
		}
}
