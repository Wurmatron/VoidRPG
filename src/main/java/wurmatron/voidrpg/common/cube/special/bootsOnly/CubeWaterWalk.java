package wurmatron.voidrpg.common.cube.special.bootsOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.Global;

public class CubeWaterWalk implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "waterWalk";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeWaterWalking;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/waterWalking.png");
		}

		@Override
		public double getWeight () {
				return 10;
		}

		@Override
		public int getComplexity () {
				return 20;
		}

		@Override
		public int getDurability () {
				return 400;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return !player.isSneaking() && player.worldObj.getBlockState(player.getPosition().add(0, -1, 0)).getBlock().equals(Blocks.WATER) || !player.isSneaking() && player.isInWater();
		}

		@Override
		public void applyEffect (EntityPlayer player, CubeData data, CubeData[] cubes) {
				player.motionY = 0;
				player.fallDistance = 0;
				player.onGround = true;
		}

		@Override
		public int getMaxAmount (Item item) {
				return 0;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				if (item.equals(VoidRPGItems.armorBoots))
						return 8;
				return 0;
		}

		@Override
		public boolean getSupportedArmorTypes (EntityEquipmentSlot type) {
				return type.equals(EntityEquipmentSlot.FEET);
		}


		@Override
		public String getDescription () {
				return "cube.waterWalk.description";
		}
}
