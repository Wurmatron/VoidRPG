package wurmatron.voidrpg.common.cube.special.chestOnly;

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

public class CubeGravity implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "gravity";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeGravity;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/gravity.png");
		}

		@Override
		public double getWeight () {
				return 2000;
		}

		@Override
		public int getComplexity () {
				return 9000;
		}

		@Override
		public int getDurability () {
				return 800;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return !player.capabilities.isCreativeMode;
		}

		@Override
		public void applyEffect(EntityPlayer player, CubeData data, ItemStack stack) {
				// TODO Need to disable this somehow
				player.capabilities.allowFlying = true;
		}

		@Override
		public int getMaxAmount (Item item) {
				return 0;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				if (item.equals(VoidRPGItems.armorChestplate))
						return 8;
				return 0;
		}

		@Override
		public boolean getSupportedArmorTypes (EntityEquipmentSlot type) {
				return type.equals(EntityEquipmentSlot.CHEST);
		}


		@Override
		public String getDescription () {
				return "cube.gravity.description";
		}
}
