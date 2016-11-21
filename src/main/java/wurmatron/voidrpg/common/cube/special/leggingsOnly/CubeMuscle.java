package wurmatron.voidrpg.common.cube.special.leggingsOnly;

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

public class CubeMuscle implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "muscle";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeMuscle;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/muscle.png");
		}

		@Override
		public double getWeight () {
				return 50;
		}

		@Override
		public int getComplexity () {
				return 20;
		}

		@Override
		public int getDurability () {
				return 1000;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return true;
		}

		@Override
		public void applyEffect(EntityPlayer player, CubeData data, ItemStack stack) {
				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
				player.motionY *= 1.05D;
		}

		@Override
		public int getMaxAmount (Item item) {
				return 0;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				if (item.equals(VoidRPGItems.armorLeggings))
						return 4;
				return 0;
		}

		@Override
		public boolean getSupportedArmorTypes (EntityEquipmentSlot type) {
				return type.equals(EntityEquipmentSlot.LEGS);
		}

		@Override
		public String getDescription () {
				return "cube.muscle.description";
		}
}
