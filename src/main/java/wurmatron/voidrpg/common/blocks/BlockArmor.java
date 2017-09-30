package wurmatron.voidrpg.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.text.translation.I18n;
import wurmatron.voidrpg.VoidRPG;

public class BlockArmor extends Block {

	public BlockArmor (Material material) {
		super (material);
		setCreativeTab (VoidRPG.tabVoidRPG);
		setHardness (5);
		setResistance (5);
	}
}
