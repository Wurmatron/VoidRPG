package wurmatron.voidrpg.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wurmatron.voidrpg.client.proxy.ClientProxy;
import wurmatron.voidrpg.common.tiles.TileCubeCreator;

public class VoidRPGBlocks {

		public static Block bodyBlock;
		public static Block armorLight;
		public static Block armorReinforced;
		public static Block cubeWaterWalking;
		public static Block cubeCreator;
		public static Block cubeShock;
		public static Block cubeFlippers;
		public static Block cubeMuscle;
		public static Block cubeGravity;
		public static Block cubeVision;
		public static Block cubeJetpack;

		public static void init () {
				registerBlock(bodyBlock = new BlockBody());
				registerBlock(armorLight = new BlockArmor(Material.ANVIL).setUnlocalizedName("blockLightArmor"));
				registerBlock(armorReinforced = new BlockArmor(Material.ANVIL).setUnlocalizedName("armorReinforced"));
				registerBlock(cubeCreator = new BlockCubeCreator(Material.ANVIL));
				registerBlock(cubeWaterWalking = new BlockArmor(Material.IRON).setUnlocalizedName("cubeWaterWalking"));
				registerBlock(cubeShock = new BlockArmor(Material.IRON).setUnlocalizedName("cubeShock"));
				registerBlock(cubeFlippers = new BlockArmor(Material.IRON).setUnlocalizedName("cubeFlippers"));
				registerBlock(cubeMuscle = new BlockArmor(Material.IRON).setUnlocalizedName("cubeMuscle"));
				registerBlock(cubeGravity = new BlockArmor(Material.IRON).setUnlocalizedName("cubeGravity"));
				registerBlock(cubeVision = new BlockArmor(Material.IRON).setUnlocalizedName("cubeVision"));
				registerBlock(cubeJetpack = new BlockArmor(Material.IRON).setUnlocalizedName("cubeJetpack"));

				GameRegistry.registerTileEntity(TileCubeCreator.class, "cubeCreator");
		}

		private static Block registerBlock (Block block) {
				GameRegistry.registerBlock(block, block.getUnlocalizedName());
				ClientProxy.blocks.add(block);
				return block;
		}
}
