package wurmatron.voidrpg.common.cube.special.bootsOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.Global;

public class CubeFlippers implements ICube {
		@Override
		public String getUnlocalizedName () {
				return "flippers";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeFlippers;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cubes/flippers.png");
		}

		@Override
		public double getWeight () {
				return 20;
		}

		@Override
		public int getComplexity () {
				return 20;
		}

		@Override
		public int getDurability () {
				return 200;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return player.isInWater();
		}

		@Override
		public void applyEffect (EntityPlayer player, CubeData data, CubeData[] cubes) {
				player.motionX *= 1.1D;
				player.motionZ *= 1.1D;
				player.motionY *= 1.05D;
		}

		@Override
		public int getMaxAmount (Item item) {
				return 0;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				if (item.equals(VoidRPGItems.armorBoots))
						return 2;
				return 0;
		}

		@Override
		public boolean getSupportedArmorTypes (EntityEquipmentSlot type) {
				return type.equals(EntityEquipmentSlot.FEET);
		}
}
