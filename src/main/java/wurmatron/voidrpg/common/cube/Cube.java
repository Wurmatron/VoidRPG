package wurmatron.voidrpg.common.cube;


import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;

public class Cube implements ICube {

		private String unlocalizedName;
		private ResourceLocation texture;
		private double weight;
		private int complexity;
		private int durability;
		private Block realBlock;
		private int maxAmount;
		private EntityEquipmentSlot[] armorType;

		public Cube (String unlocalizedName, Block block, ResourceLocation texture, double weight, int complexity, int durability, int maxAmount, EntityEquipmentSlot[] armorType) {
				this.unlocalizedName = unlocalizedName;
				this.realBlock = block;
				this.texture = texture;
				this.weight = weight;
				this.complexity = complexity;
				this.durability = durability;
				this.maxAmount = maxAmount;
				this.armorType = armorType;
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
		public void applyEffect (EntityPlayer player, CubeData cube, CubeData[] data) {
		}

		@Override
		public int getMaxAmount (Item item) {
				return maxAmount;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				return 0;
		}

		@Override
		public boolean getSupportedArmorTypes (EntityEquipmentSlot type) {
				for (EntityEquipmentSlot t : armorType)
						if (type.equals(t))
								return true;
				return false;
		}
}
