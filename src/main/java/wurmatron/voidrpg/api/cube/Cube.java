package wurmatron.voidrpg.api.cube;


import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Cube implements ICube {

		private String unlocalizedName;
		private ResourceLocation block;
		private ResourceLocation texture;
		private double weight;

		public Cube (String unlocalizedName, ResourceLocation block, ResourceLocation texture, double weight) {
				this.unlocalizedName = unlocalizedName;
				this.block = block;
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
				return GameRegistry.findBlock(block.getResourceDomain(), block.getResourcePath());
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
