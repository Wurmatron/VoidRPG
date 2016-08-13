package wurmatron.voidrpg.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wurmatron.voidrpg.common.tiles.TileCubeCreator;

public class VoidRPGBlocks {

		public static Block armorLight;
		public static Block armorReinforced;
		public static Block cubeCreator;

		public static void init () {
				registerBlock(armorLight = new BlockArmor(Material.ANVIL).setUnlocalizedName("blockLightArmor"));
				registerBlock(armorReinforced = new BlockArmor(Material.ANVIL).setUnlocalizedName("armorReinforced"));
				registerBlock(cubeCreator = new BlockCubeCreator(Material.ANVIL));

				GameRegistry.registerTileEntity(TileCubeCreator.class, "cubeCreator");
		}

		private static Block registerBlock (Block block) {
				GameRegistry.registerBlock(block, block.getUnlocalizedName());
				return block;
		}
}
