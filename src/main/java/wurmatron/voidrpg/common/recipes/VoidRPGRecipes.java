package wurmatron.voidrpg.common.recipes;

import mod.chiselsandbits.helpers.ModUtil;
import mod.chiselsandbits.items.ItemChiseledBit;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.cube.CubeCreatorRecipe;
import wurmatron.voidrpg.common.cube.CubeCreatorRecipeHandler;
import wurmatron.voidrpg.common.items.ItemMaterial;

public class VoidRPGRecipes {

	public static void init () {
		addRecipes ();
		addMachineRecipes ();
	}

	public static void addMachineRecipes () {
		addCubeCreatorRecipes ();
	}

	public static void addRecipes () {
		addShapedRecipes ();
		addShapelessRecipes ();
	}

	private static void addShapedRecipes () {
		IForgeRegistry <IRecipe> registry = GameRegistry.findRegistry (IRecipe.class);
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("creationCrystal",1),"XAX","BXB","XAX",'X',Blocks.END_STONE,'A',Items.BLAZE_ROD,'B',Items.EMERALD));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("creationCrystal",1),"XBX","AXA","XBX",'X',Blocks.END_STONE,'A',Items.BLAZE_ROD,'B',Items.EMERALD));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("creationCrystal",1),"XAX","BXB","XAX",'X',Blocks.NETHER_BRICK,'A',Items.CHORUS_FRUIT,'B',Items.EMERALD));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("creationCrystal",1),"XBX","AXA","XBX",'X',Blocks.NETHER_BRICK,'A',Items.CHORUS_FRUIT,'B',Items.EMERALD));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("upgrade",2),"BXB","XAX","BCB",'B',Blocks.OBSIDIAN,'X',Items.REDSTONE,'A',Items.EMERALD,'C',Items.ENDER_PEARL));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("basicArmorPlate",1),"IXI","XAX","IXI",'I',Items.IRON_INGOT,'X',Items.GOLD_INGOT,'A',Blocks.IRON_BLOCK));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("heavyArmorPlate",2),"XPX","PAP","XPX",'P',ItemMaterial.createMaterial ("basicArmorPlate",1),'X',Items.IRON_INGOT,'A',Items.DIAMOND));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("reactiveArmorPlating",3),"PPP","XXX","PPP",'P',ItemMaterial.createMaterial ("heavyArmorPlate",1),'X',Items.DIAMOND));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("regenerativeArmorPlating",6),"PPP","XXX","PPP",'P',ItemMaterial.createMaterial ("reactiveArmorPlating",1),'X',ItemMaterial.createMaterial ("repairBot",1)));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("energyArmorPlating",1),"PPP","XXX","PPP",'P',ItemMaterial.createMaterial ("regenerativeArmorPlating",1),'X',ItemMaterial.createMaterial ("battery",1)));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("cardboard",2)," P ","PLP"," P ",'P',Items.PAPER,'L',"plankWood"));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("carbonChunk",6)," P ","PLP"," P ",'P',new ItemStack (Items.COAL,1,OreDictionary.WILDCARD_VALUE),'L',Blocks.COAL_BLOCK));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("flippers",1)," B ","PPP",'B',new ItemStack (Items.DIAMOND_BOOTS,1,0),'P',"plankWood"));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("nanoTech",1),"XAX","BCB","XAX",'X',Blocks.REDSTONE_BLOCK,'A',Items.EMERALD,'B',Items.DIAMOND,'C',Items.ENDER_EYE));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("mechanicalMuscle",2),"XAX","XAX","XAX",'X',new ItemStack (Blocks.WOOL,1,OreDictionary.WILDCARD_VALUE),'A',ItemMaterial.createMaterial ("nanoTech",1)));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("gravityCore",1),"XAX","ACA","XAX",'X',Items.NETHER_STAR,'A',ItemMaterial.createMaterial ("jetpackParts",1),'C',Blocks.DRAGON_EGG));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("jetpackParts",4),"BXB","BAB","BBB",'B',Blocks.REDSTONE_BLOCK,'X',ItemMaterial.createMaterial ("creationCrystal",1),'A',Items.NETHER_STAR));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("thruster",1)," X ","XAX","BBB",'X',Items.EMERALD,'A',Blocks.DIAMOND_BLOCK,'B',Items.BLAZE_POWDER));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("waterElectrolysisModule",1),"XPX","PAP","XPX",'X',Items.PRISMARINE_SHARD,'P',PotionUtils.addPotionToItemStack (new ItemStack (Items.POTIONITEM),PotionType.getPotionTypeForName ("long_water_breathing")),'A',Items.PRISMARINE_CRYSTALS));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("mindControl",1),"XAX","BCB","XAX",'X',Blocks.END_STONE,'A',ItemMaterial.createMaterial ("reactiveArmorPlating",1),'B',Items.EMERALD,'C',new ItemStack (Items.GOLDEN_APPLE,1,0)));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("wings",1),"L L","LSL","L L",'L',Items.LEATHER,'S',Items.BONE));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("wings",1),"L L","LSL","L L",'L',Items.LEATHER,'S',Items.STICK));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("battery",1)," C ","IRI","IRI",'C',Items.GOLD_INGOT,'I',Items.IRON_INGOT,'R',Items.REDSTONE));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("solarPanel",1),"GGG","IWI","IRI",'G',Blocks.GLASS,'I',Items.GOLD_INGOT,'W',new ItemStack (Blocks.WOOL,1,OreDictionary.WILDCARD_VALUE),'R',Items.REDSTONE));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("largeReactor",1),"CCC","CRC","CCC",'C',ItemMaterial.createMaterial ("smallReactor",1),'R',ItemMaterial.createMaterial ("nanoTech",1)));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemUpgrade.createMaterial ("speedI"),"BRB","RAR","BCB",'B',Blocks.REDSTONE_BLOCK,'R',Blocks.LAPIS_BLOCK,'A',ItemMaterial.createMaterial ("upgrade"),'C',Items.DIAMOND));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),VoidRPGBlocks.cubeCreator,"BFB","XAX","BFB",'F',Blocks.FURNACE,'B',Blocks.IRON_BLOCK,'X',ItemMaterial.createMaterial ("creationCrystal",1),'A',Blocks.CHEST));
		//		registry.register (new ShapedOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemStaff.createStaff (ItemStaff.MAX_DURABILITY),"XDX","JSJ"," S ",'X',Blocks.END_STONE,'J',"gemDiamond",'D',ItemMaterial.createMaterial ("nanoTech",1),'S',Items.BLAZE_ROD));
	}

	private static void addShapelessRecipes () {
		IForgeRegistry <IRecipe> registry = GameRegistry.findRegistry (IRecipe.class);
		//		registry.register (new ShapelessOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),VoidRPGItems.goggles,Items.DIAMOND_HELMET,ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("creationCrystal",1)));
		//		registry.register (new ShapelessOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("smallReactor",1),Blocks.EMERALD_BLOCK,Blocks.DIAMOND_BLOCK,ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("nanoTech",1)));
		//		registry.register (new ShapelessOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemMaterial.createMaterial ("repairBot",1),Blocks.DIAMOND_BLOCK,ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("nanoTech",1),new ItemStack (Blocks.WOOL,1,OreDictionary.WILDCARD_VALUE)));
		//		registry.register (new ShapelessOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemUpgrade.createMaterial ("speedII"),ItemUpgrade.createMaterial ("speedI"),ItemUpgrade.createMaterial ("speedI"),ItemUpgrade.createMaterial ("speedI"),ItemUpgrade.createMaterial ("speedI")));
		//		registry.register (new ShapelessOreRecipe (new ResourceLocation (Global.MODID,"Recipes"),ItemUpgrade.createMaterial ("speedIII"),ItemUpgrade.createMaterial ("speedII"),ItemUpgrade.createMaterial ("speedII"),ItemUpgrade.createMaterial ("speedII"),ItemUpgrade.createMaterial ("speedII")));
	}

	private static void addCubeCreatorRecipes () {
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (new ItemStack (Blocks.OBSIDIAN,4,0),new ItemStack[] {new ItemStack (Items.LAVA_BUCKET),new ItemStack (Items.LAVA_BUCKET),new ItemStack (Items.WATER_BUCKET),new ItemStack (Items.WATER_BUCKET)},600));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.armorLight,16),new ItemStack[] {new ItemStack (Items.IRON_INGOT),ItemMaterial.createMaterial ("basicArmorPlate",1),ItemMaterial.createMaterial ("basicArmorPlate",1),new ItemStack (Items.IRON_INGOT),new ItemStack (Items.IRON_INGOT),ItemMaterial.createMaterial ("basicArmorPlate",1),ItemMaterial.createMaterial ("basicArmorPlate",1),new ItemStack (Items.IRON_INGOT)},600));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.armorReinforced,16),new ItemStack[] {ItemMaterial.createMaterial ("basicArmorPlate",1),ItemMaterial.createMaterial ("heavyArmorPlate",1),ItemMaterial.createMaterial ("heavyArmorPlate",1),ItemMaterial.createMaterial ("basicArmorPlate",1),ItemMaterial.createMaterial ("heavyArmorPlate",1),ItemMaterial.createMaterial ("basicArmorPlate",1),ItemMaterial.createMaterial ("heavyArmorPlate",1),ItemMaterial.createMaterial ("basicArmorPlate",1)},1200));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.armorHeavy,32),new ItemStack[] {ItemMaterial.createMaterial ("heavyArmorPlate",1),new ItemStack (Items.DIAMOND),new ItemStack (Blocks.OBSIDIAN),ItemMaterial.createMaterial ("heavyArmorPlate",1),ItemMaterial.createMaterial ("heavyArmorPlate",1),new ItemStack (Blocks.OBSIDIAN),new ItemStack (Items.DIAMOND),ItemMaterial.createMaterial ("heavyArmorPlate",1)},4000));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.armorNanoTech,8),new ItemStack[] {ItemMaterial.createMaterial ("carbonChunk",6),ItemMaterial.createMaterial ("carbonChunk",6),ItemMaterial.createMaterial ("basicArmorPlate",1),ItemMaterial.createMaterial ("basicArmorPlate",1),ItemMaterial.createMaterial ("basicArmorPlate",1),ItemMaterial.createMaterial ("basicArmorPlate",1),ItemMaterial.createMaterial ("carbonChunk",6),ItemMaterial.createMaterial ("carbonChunk",6)},400));
		for (ItemStack logs : OreDictionary.getOres ("logWood"))
			CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.armorWood,16),new ItemStack[] {logs,logs,ItemMaterial.createMaterial ("basicArmorPlate",1),logs,logs,ItemMaterial.createMaterial ("basicArmorPlate",1),logs,logs},800));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.armorCardboard,4),new ItemStack[] {ItemMaterial.createMaterial ("cardboard",1),ItemMaterial.createMaterial ("cardboard",1),ItemMaterial.createMaterial ("cardboard",1),new ItemStack (Items.STICK),new ItemStack (Items.STICK),ItemMaterial.createMaterial ("cardboard",1),ItemMaterial.createMaterial ("cardboard",1),ItemMaterial.createMaterial ("cardboard",1)},400));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.armorNanoTech,24),new ItemStack[] {ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("carbonChunk",1),new ItemStack (Blocks.COAL_BLOCK),new ItemStack (Blocks.COAL_BLOCK),ItemMaterial.createMaterial ("carbonChunk",1),ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("nanoTech",1)},8000));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.armorLife,2),new ItemStack[] {ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("nanoTech",1),new ItemStack (Items.GOLDEN_APPLE,1,1),new ItemStack (Blocks.DIAMOND_BLOCK),new ItemStack (Blocks.DIAMOND_BLOCK),new ItemStack (Items.GOLDEN_APPLE,1,1),ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("nanoTech",1)},1200));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.cubeWaterWalking,4),new ItemStack[] {new ItemStack (Blocks.SPONGE,4),new ItemStack (Blocks.SPONGE,4),ItemMaterial.createMaterial ("cardboard",1),new ItemStack (Items.DIAMOND),new ItemStack (Items.DIAMOND),ItemMaterial.createMaterial ("cardboard",1),new ItemStack (Blocks.SPONGE,4),new ItemStack (Blocks.SPONGE,4)},800));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.cubeShock,8),new ItemStack[] {new ItemStack (Blocks.WOOL,8,OreDictionary.WILDCARD_VALUE),new ItemStack (Blocks.WOOL,8,OreDictionary.WILDCARD_VALUE),new ItemStack (Items.ENDER_PEARL,2),new ItemStack (Items.BLAZE_ROD,2),new ItemStack (Items.BLAZE_ROD,2),new ItemStack (Items.ENDER_PEARL,2),new ItemStack (Blocks.WOOL,8,OreDictionary.WILDCARD_VALUE),new ItemStack (Blocks.WOOL,8,OreDictionary.WILDCARD_VALUE)},2000));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.cubeFlippers,2),new ItemStack[] {ItemMaterial.createMaterial ("flippers",1),new ItemStack (Blocks.LOG,1,OreDictionary.WILDCARD_VALUE),new ItemStack (Items.DIAMOND),new ItemStack (Items.GOLDEN_CARROT),new ItemStack (Items.GOLDEN_CARROT),new ItemStack (Items.DIAMOND),new ItemStack (Blocks.LOG,1,OreDictionary.WILDCARD_VALUE),ItemMaterial.createMaterial ("flippers",1)},800));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.cubeMuscle,4),new ItemStack[] {ItemMaterial.createMaterial ("mechanicalMuscle",1),ItemMaterial.createMaterial ("mechanicalMuscle",1),new ItemStack (Items.DIAMOND_SWORD),new ItemStack (Items.GOLDEN_APPLE,1,0),new ItemStack (Items.GOLDEN_APPLE,1,0),new ItemStack (Items.DIAMOND_SWORD),ItemMaterial.createMaterial ("mechanicalMuscle",1),ItemMaterial.createMaterial ("mechanicalMuscle",1)},1200));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.cubeGravity,1),new ItemStack[] {ItemMaterial.createMaterial ("gravityCore",2),ItemMaterial.createMaterial ("nanoTech",8),ItemMaterial.createMaterial ("nanoTech",8),ItemMaterial.createMaterial ("carbonChunk",64),ItemMaterial.createMaterial ("carbonChunk",64),ItemMaterial.createMaterial ("nanoTech",8),ItemMaterial.createMaterial ("nanoTech",8),ItemMaterial.createMaterial ("gravityCore",2)},8000));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.cubeJetpack,2),new ItemStack[] {ItemMaterial.createMaterial ("thruster",2),ItemMaterial.createMaterial ("jetpackParts",2),new ItemStack (Blocks.REDSTONE_BLOCK,2),new ItemStack (Blocks.REDSTONE_BLOCK,2),new ItemStack (Blocks.REDSTONE_BLOCK,2),new ItemStack (Blocks.REDSTONE_BLOCK,2),ItemMaterial.createMaterial ("jetpackParts",2),ItemMaterial.createMaterial ("thruster",2)},6000));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.cubeMobStealth,2),new ItemStack[] {ItemMaterial.createMaterial ("mindControl",2),ItemMaterial.createMaterial ("nanoTech",2),new ItemStack (Blocks.DIAMOND_BLOCK,2),new ItemStack (Blocks.DIAMOND_BLOCK,2),new ItemStack (Blocks.DIAMOND_BLOCK,2),new ItemStack (Blocks.DIAMOND_BLOCK,2),ItemMaterial.createMaterial ("nanoTech",2),ItemMaterial.createMaterial ("mindControl",2)},6000));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.cubeVision,1),new ItemStack[] {PotionUtils.addPotionToItemStack (new ItemStack (Items.POTIONITEM),PotionType.getPotionTypeForName ("long_night_vision")),PotionUtils.addPotionToItemStack (new ItemStack (Items.POTIONITEM),PotionType.getPotionTypeForName ("long_night_vision")),ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("nanoTech",1),PotionUtils.addPotionToItemStack (new ItemStack (Items.POTIONITEM),PotionType.getPotionTypeForName ("long_night_vision")),PotionUtils.addPotionToItemStack (new ItemStack (Items.POTIONITEM),PotionType.getPotionTypeForName ("long_night_vision"))},4000));
		CubeCreatorRecipeHandler.registerRecipe (new CubeCreatorRecipe (createBitFromBlock (VoidRPGBlocks.cubeStealth,1),new ItemStack[] {PotionUtils.addPotionToItemStack (new ItemStack (Items.POTIONITEM),PotionType.getPotionTypeForName ("long_invisibility")),PotionUtils.addPotionToItemStack (new ItemStack (Items.POTIONITEM),PotionType.getPotionTypeForName ("long_invisibility")),ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("nanoTech",1),ItemMaterial.createMaterial ("nanoTech",1),PotionUtils.addPotionToItemStack (new ItemStack (Items.POTIONITEM),PotionType.getPotionTypeForName ("long_invisibility")),PotionUtils.addPotionToItemStack (new ItemStack (Items.POTIONITEM),PotionType.getPotionTypeForName ("long_invisibility"))},4000));

	}

	private static ItemStack createBitFromBlock (Block block,int count) {
		return ItemChiseledBit.createStack (ModUtil.getStateId (block.getDefaultState ()),count,true);
	}

}
