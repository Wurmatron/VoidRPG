package wurmatron.voidrpg.common.cube;


import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.ICube;

public class Cube implements ICube {

		private String unlocalizedName;
		private ResourceLocation texture;
		private double weight;
		private Block realBlock;


		public Cube (String unlocalizedName, Block block, ResourceLocation texture, double weight) {
				this.unlocalizedName = unlocalizedName;
				this.realBlock = block;
				this.texture = texture;
				this.weight = weight;
		}

		@Override
		public String getUnlocalizedName () {
				if (unlocalizedName != null)
						return unlocalizedName;
				return "error";
		}

		@Override
		public Block getBlock () {
				return realBlock;
		}

		@Override
		public ResourceLocation getTexture () {
				return texture;
		}

		@Override
		public double getWeight () {
				return weight;
		}
}
