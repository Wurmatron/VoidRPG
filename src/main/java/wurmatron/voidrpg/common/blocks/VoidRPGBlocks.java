package wurmatron.voidrpg.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wurmatron.voidrpg.client.proxy.ClientProxy;
import wurmatron.voidrpg.common.tile.TileCubeCreator;

public class VoidRPGBlocks {

		public static Block bodyBlock;
		// Armor
		public static Block armorLight;
		public static Block armorReinforced;
		public static Block armorHeavy;
		public static Block armorCarbon;
		public static Block armorWood;
		public static Block armorCardboard;
		public static Block armorActivePlating;
		public static Block armorNanoTech;
		public static Block armorEnergyShieldI;
		public static Block armorEnergyShieldII;
		public static Block armorEnergyShieldIII;
		public static Block armorLife;
		// Cube
		public static Block cubeWaterWalking;
		public static Block cubeCreator;
		public static Block cubeShock;
		public static Block cubeFlippers;
		public static Block cubeMuscle;
		public static Block cubeGravity;
		public static Block cubeVision;
		public static Block cubeJetpack;
		public static Block cubeWaterBreahing;
		public static Block cubeMobStealth;
		public static Block cubeWing;
		public static Block cubeTrueStealth;
		public static Block energyStorageI;
		public static Block energyStorageII;
		public static Block energyStorageIII;
		public static Block energyStorageIV;
		public static Block energyStorageV;
		public static Block energyReactorI;
		public static Block energyReactorII;
		public static Block energyReactorIII;
		public static Block cubeDamageConverter;
		public static Block cubeCooldownReduction;
		public static Block cubeTrueVision;
		public static Block energySolarI;
		public static Block energySolarII;
		public static Block energySolarIII;
		public static Block energyKenetic;
		public static Block energyAutoRepairI;
		public static Block energyAutoRepairII;
		public static Block energyAutoRepairIII;
		public static Block cubeStealth;
		// Deco
		public static Block decoDiamond;
		public static Block decoEmerald;
		public static Block decoWoolWhile;
		public static Block decoWoolOrange;
		public static Block decoWoolMagenta;
		public static Block decoWoolLightBlue;
		public static Block decoWoolYellow;
		public static Block decoWoolLime;
		public static Block decoWoolPink;
		public static Block decoWoolGray;
		public static Block decoWoolLightGray;
		public static Block decoWoolCyan;
		public static Block decoWoolPurple;
		public static Block decoWoolBlue;
		public static Block decoWoolBrown;
		public static Block decoWoolGreen;
		public static Block decoWoolRed;
		public static Block decoWoolBlack;

		public static void init () {
				registerBlock(bodyBlock = new BlockBody());
				// Armor
				registerBlock(armorLight = new BlockArmor(Material.ANVIL).setUnlocalizedName("armorLight"));
				registerBlock(armorReinforced = new BlockArmor(Material.ANVIL).setUnlocalizedName("armorReinforced"));
				registerBlock(armorHeavy = new BlockArmor(Material.ANVIL).setUnlocalizedName("armorHeavy"));
				registerBlock(armorCarbon = new BlockArmor(Material.ANVIL).setUnlocalizedName("armorCarbon"));
				registerBlock(armorWood = new BlockArmor(Material.ANVIL).setUnlocalizedName("armorWood"));
				registerBlock(armorCardboard = new BlockArmor(Material.ANVIL).setUnlocalizedName("armorCardboard"));
				registerBlock(armorActivePlating = new BlockArmor(Material.ANVIL).setUnlocalizedName("armorActivePlating"));
				registerBlock(armorNanoTech = new BlockArmor(Material.ANVIL).setUnlocalizedName("armorNanoTech"));
				registerBlock(armorEnergyShieldI = new BlockArmor(Material.ANVIL).setUnlocalizedName("energyShieldI"));
				registerBlock(armorEnergyShieldII = new BlockArmor(Material.ANVIL).setUnlocalizedName("energyShieldII"));
				registerBlock(armorEnergyShieldIII = new BlockArmor(Material.ANVIL).setUnlocalizedName("energyShieldIII"));
				registerBlock(armorLife = new BlockArmor(Material.ANVIL).setUnlocalizedName("armorLife"));
				// Machines
				registerBlock(cubeCreator = new BlockCubeCreator(Material.ANVIL));
				// Cubes
				registerBlock(cubeWaterWalking = new BlockArmor(Material.IRON).setUnlocalizedName("cubeWaterWalking"));
				registerBlock(cubeShock = new BlockArmor(Material.IRON).setUnlocalizedName("cubeShock"));
				registerBlock(cubeFlippers = new BlockArmor(Material.IRON).setUnlocalizedName("cubeFlippers"));
				registerBlock(cubeMuscle = new BlockArmor(Material.IRON).setUnlocalizedName("cubeMuscle"));
				registerBlock(cubeGravity = new BlockArmor(Material.IRON).setUnlocalizedName("cubeGravity"));
				registerBlock(cubeVision = new BlockArmor(Material.IRON).setUnlocalizedName("cubeVision"));
				registerBlock(cubeJetpack = new BlockArmor(Material.IRON).setUnlocalizedName("cubeJetpack"));
				registerBlock(cubeWaterBreahing = new BlockArmor(Material.IRON).setUnlocalizedName("cubeWaterBreathing"));
				registerBlock(cubeMobStealth = new BlockArmor(Material.IRON).setUnlocalizedName("cubeMobStealth"));
				registerBlock(cubeWing = new BlockArmor(Material.IRON).setUnlocalizedName("cubeWing"));
				registerBlock(cubeTrueStealth = new BlockArmor(Material.IRON).setUnlocalizedName("cubeTrueStealth"));
				registerBlock(energyStorageI = new BlockArmor(Material.IRON).setUnlocalizedName("energyStorageI"));
				registerBlock(energyStorageII = new BlockArmor(Material.IRON).setUnlocalizedName("energyStorageII"));
				registerBlock(energyStorageIII = new BlockArmor(Material.IRON).setUnlocalizedName("energyStorageIII"));
				registerBlock(energyStorageIV = new BlockArmor(Material.IRON).setUnlocalizedName("energyStorageIV"));
				registerBlock(energyStorageV = new BlockArmor(Material.IRON).setUnlocalizedName("energyStorageV"));
				registerBlock(energyReactorI = new BlockArmor(Material.IRON).setUnlocalizedName("energyReactorI"));
				registerBlock(energyReactorII = new BlockArmor(Material.IRON).setUnlocalizedName("energyReactorII"));
				registerBlock(energyReactorIII = new BlockArmor(Material.IRON).setUnlocalizedName("energyReactorIII"));
				registerBlock(cubeDamageConverter = new BlockArmor(Material.IRON).setUnlocalizedName("cubeDamageConverter"));
				registerBlock(cubeCooldownReduction = new BlockArmor(Material.IRON).setUnlocalizedName("cubeCooldownReduction"));
				registerBlock(cubeTrueVision = new BlockArmor(Material.IRON).setUnlocalizedName("cubeTrueVision"));
				registerBlock(energySolarI = new BlockArmor(Material.IRON).setUnlocalizedName("energySolarI"));
				registerBlock(energySolarII = new BlockArmor(Material.IRON).setUnlocalizedName("energySolarII"));
				registerBlock(energySolarIII = new BlockArmor(Material.IRON).setUnlocalizedName("energySolarIII"));
				registerBlock(energyKenetic = new BlockArmor(Material.IRON).setUnlocalizedName("energyKenetic"));
				registerBlock(energyAutoRepairI = new BlockArmor(Material.IRON).setUnlocalizedName("energyAutoRepairI"));
				registerBlock(energyAutoRepairII = new BlockArmor(Material.IRON).setUnlocalizedName("energyAutoRepairII"));
				registerBlock(energyAutoRepairIII = new BlockArmor(Material.IRON).setUnlocalizedName("energyAutoRepairIII"));
				registerBlock(cubeStealth = new BlockArmor(Material.IRON).setUnlocalizedName("cubeStealth"));
				// Deco
				registerBlock(decoDiamond = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoDiamond"));
				registerBlock(decoEmerald = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoEmerald"));
				registerBlock(decoWoolWhile = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolWhite"));
				registerBlock(decoWoolOrange = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolOrange"));
				registerBlock(decoWoolMagenta = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolMagenta"));
				registerBlock(decoWoolLightBlue = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolLightBlue"));
				registerBlock(decoWoolYellow = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolYellow"));
				registerBlock(decoWoolLime = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolLime"));
				registerBlock(decoWoolPink = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolPink"));
				registerBlock(decoWoolGray = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolGray"));
				registerBlock(decoWoolLightGray = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolLightGray"));
				registerBlock(decoWoolCyan = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolCyan"));
				registerBlock(decoWoolPurple = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolPurple"));
				registerBlock(decoWoolBlue = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolBlue"));
				registerBlock(decoWoolBrown = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolBrown"));
				registerBlock(decoWoolGreen = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolGreen"));
				registerBlock(decoWoolRed = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolRed"));
				registerBlock(decoWoolBlack = new BlockArmor(Material.ANVIL).setUnlocalizedName("decoWoolBlack"));
				GameRegistry.registerTileEntity(TileCubeCreator.class, "cubeCreator");
		}

		private static Block registerBlock (Block block) {
				GameRegistry.registerBlock(block, block.getUnlocalizedName());
				ClientProxy.blocks.add(block);
				return block;
		}
}
