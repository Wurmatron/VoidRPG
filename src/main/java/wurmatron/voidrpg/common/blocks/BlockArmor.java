package wurmatron.voidrpg.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import wurmatron.voidrpg.VoidRPG;

public class BlockArmor extends Block {

		public BlockArmor (Material material, MapColor color) {
				super(material, color);
				setCreativeTab(VoidRPG.tabVoidRPG);
		}
}
