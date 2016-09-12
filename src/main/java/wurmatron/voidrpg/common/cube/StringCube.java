package wurmatron.voidrpg.common.cube;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;

public class StringCube implements ICube {

		private String unlocalizedName;
		private ResourceLocation texture;
		private double weight;
		private int complexity;
		private int durability;
		private String blockModid;
		private String blockName;
		private int maxAmount;

		public StringCube (String unlocalizedName, String modid, String blockName, ResourceLocation texture, double weight, int complexity, int durability, int maxAmount) {
				this.unlocalizedName = unlocalizedName;
				this.blockModid = modid;
				this.blockName = blockName;
				this.texture = texture;
				this.weight = weight;
				this.complexity = complexity;
				this.durability = durability;
				this.maxAmount = maxAmount;
		}

		@Override
		public String getUnlocalizedName () {
				if (unlocalizedName != null)
						return unlocalizedName;
				return "error";
		}

		@Override
		public Block getBlock () {
				return Block.REGISTRY.getObject(new ResourceLocation(blockModid, blockName));
		}

		@Override
		public ResourceLocation getTexture () {
				return texture;
		}

		@Override
		public double getWeight () {
				return weight;
		}

		@Override
		public int getComplexity () {
				return complexity;
		}

		@Override
		public int getDurability () {
				return durability;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return false;
		}

		@Override
		public void applyEffect (CubeData cube, CubeData[] data) {
		}

		@Override
		public int getMaxAmount (Item item) {
				return maxAmount;
		}
}
