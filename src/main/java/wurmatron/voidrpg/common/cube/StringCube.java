package wurmatron.voidrpg.common.cube;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.ICube;

public class StringCube implements ICube {

		private String unlocalizedName;
		private ResourceLocation texture;
		private double weight;
		private String blockModid;
		private String blockName;

		public StringCube (String unlocalizedName, String modid, String blockName, ResourceLocation texture, double weight) {
				this.unlocalizedName = unlocalizedName;
				this.blockModid = modid;
				this.blockName = blockName;
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
				return Block.REGISTRY.getObject(new ResourceLocation(blockModid,blockName));
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
