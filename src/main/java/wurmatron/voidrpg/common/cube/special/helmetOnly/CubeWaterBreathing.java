package wurmatron.voidrpg.common.cube.special.helmetOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.Global;

public class CubeWaterBreathing implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "waterBreathing";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeWaterBreahing;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/waterBreathing.png");
		}

		@Override
		public double getWeight () {
				return 20;
		}

		@Override
		public int getComplexity () {
				return 10;
		}

		@Override
		public int getDurability () {
				return 100;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return true;
		}

		@Override
		public void applyEffect (EntityPlayer player, CubeData data, CubeData[] cubes) {
				if (player.isPotionActive(Potion.getPotionById(13))) {
						if (player.getActivePotionEffect(Potion.getPotionById(13)).getDuration() < 60)
								player.addPotionEffect(new PotionEffect(Potion.getPotionById(13), 200));
				} else
						player.addPotionEffect(new PotionEffect(Potion.getPotionById(13), 200));
		}

		@Override
		public int getMaxAmount (Item item) {
				return 0;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				if (item.equals(VoidRPGItems.armorHelmet))
						return 8;
				return 0;
		}

		@Override
		public boolean getSupportedArmorTypes (EntityEquipmentSlot type) {
				return type.equals(EntityEquipmentSlot.HEAD);
		}


		@Override
		public String getDescription () {
				return "cube.waterBreathing.description";
		}
}
