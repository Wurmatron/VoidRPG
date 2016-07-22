package wurmatron.voidrpg.api.cube;


import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Cube extends Item implements ICube {

		@Override
		public Block getBlock () {
				return Blocks.IRON_BLOCK;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation("voidrpg", "/textures/cubes/unknownCube.png");
		}

		@Override
		public int[] getSize () {
				return new int[] {1, 1, 1};
		}

		@Override
		public double getWeight () {
				return 0.1;
		}
}
