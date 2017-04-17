package wurmatron.voidrpg.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBody extends Block {

		public BlockBody() {
				super(Material.BARRIER); setUnlocalizedName("body"); setResistance(100); setHardness(100);
		}
}