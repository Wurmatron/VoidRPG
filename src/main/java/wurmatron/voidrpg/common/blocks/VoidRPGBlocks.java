package wurmatron.voidrpg.common.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VoidRPGBlocks {

		public static void init() {}

		private static Block registerBlock(Block block) {
				GameRegistry.registerBlock(block,block.getUnlocalizedName());
				return block;
		}
}
