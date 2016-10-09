package wurmatron.voidrpg.common.cube.special;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;

public class CubeAutoRepair implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "autoRepair";
		}

		@Override
		public Block getBlock () {
				return Blocks.DIAMOND_BLOCK;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation("minecraft", "textures/blocks/diamond_block.png");
		}

		@Override
		public double getWeight () {
				return 10;
		}

		@Override
		public int getComplexity () {
				return 25;
		}

		@Override
		public int getDurability () {
				return 20;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return true;
		}

		@Override
		public void applyEffect (CubeData cube, CubeData[] data) {
				for (CubeData c : data)
						if (c.damage > 0)
								c.damage = c.damage--;
		}

		@Override
		public int getMaxAmount (Item item) {
				return 200;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				return 0;
		}
}
