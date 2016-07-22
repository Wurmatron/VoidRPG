package wurmatron.voidrpg.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VoidRPGBlocks {

		public static Block armorLight;
		public static Block armorReinforced;

		public static void init () {
				registerBlock(armorLight = new BlockArmor(Material.ANVIL, MapColor.BLUE).setUnlocalizedName("blockLightArmor"));
				registerBlock(armorReinforced = new BlockArmor(Material.ANVIL, MapColor.BLUE).setUnlocalizedName("armorReinforced"));
		}

		private static Block registerBlock (Block block) {
				GameRegistry.registerBlock(block, block.getUnlocalizedName());
				return block;
		}
}
