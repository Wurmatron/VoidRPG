package wurmatron.voidrpg.common.cube;

import com.sun.corba.se.impl.io.TypeMismatchException;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
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
		private String armorTypes;
		private String descriptionKey;

		private final Object[] variables;

		public StringCube (String unlocalizedName, String modid, String blockName, ResourceLocation texture, double weight, int complexity, int durability, int maxAmount, String armorTypes, String description) {
				this.unlocalizedName = unlocalizedName;
				this.blockModid = modid;
				this.blockName = blockName;
				this.texture = texture;
				this.weight = weight;
				this.complexity = complexity;
				this.durability = durability;
				this.maxAmount = maxAmount;
				this.armorTypes = armorTypes;
				this.descriptionKey = description;
				this.variables = new Object[] {this.unlocalizedName, this.blockModid, this.texture, this.blockName,
						this.weight, this.complexity, this.durability, this.maxAmount, this.armorTypes, this.descriptionKey};
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
		public void applyEffect(EntityPlayer player, CubeData cube, ItemStack stack) {
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
				if (type != null && armorTypes != null && armorTypes.length() > 4) {
						String[] types = armorTypes.split(",");
						if (types.length > 0)
								if (types.length > 0)
										for (String t : types)
												if (isValid(type, t))
														return true;
				}
				return false;
		}

		private boolean isValid (EntityEquipmentSlot type, String name) {
				if (name.equalsIgnoreCase("head") && type.equals(EntityEquipmentSlot.HEAD) || name.equalsIgnoreCase("helmet") && type.equals(EntityEquipmentSlot.HEAD))
						return true;
				else if (name.equalsIgnoreCase("chestplate") && type.equals(EntityEquipmentSlot.CHEST) || name.equalsIgnoreCase("chest") && type.equals(EntityEquipmentSlot.CHEST))
						return true;
				else if (name.equalsIgnoreCase("leggings") && type.equals(EntityEquipmentSlot.LEGS) || name.equalsIgnoreCase("legs") && type.equals(EntityEquipmentSlot.LEGS))
						return true;
				else if (name.equalsIgnoreCase("boots") && type.equals(EntityEquipmentSlot.FEET) || name.equalsIgnoreCase("feet") && type.equals(EntityEquipmentSlot.FEET))
						return true;
				return false;
		}

		@Override
		public String getDescription () {
				return descriptionKey;
		}

		@Override
		public boolean equals(Object cube) throws TypeMismatchException {
			StringCube sc = (StringCube)cube;
			for (int i = 0; i < variables.length; i++) {
				if (this.variables[i] != sc.variables[i]) {
					break;
				} else if (i == variables.length-1) {
					return true;
				}
			}
			return false;
		}
}
