package wurmatron.voidrpg.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import wurmatron.voidrpg.VoidRPG;

public class BlockBody extends Block {

		public BlockBody (Material material) {
				super(material);
				setCreativeTab(VoidRPG.tabVoidRPG);
		}
}