package wurmatron.voidrpg.common.cube.special;

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
import wurmatron.voidrpg.common.reference.Global;

public class CubeStealth implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "stealth";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeStealth;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/stealth.png");
		}

		@Override
		public double getWeight () {
				return 10;
		}

		@Override
		public int getComplexity () {
				return 100;
		}

		@Override
		public int getDurability () {
				return 500;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return true;
		}

		@Override
		public void applyEffect (EntityPlayer player, CubeData data, CubeData[] cubes, ItemStack stack) {
				if (player.isPotionActive(Potion.getPotionById(14))) {
						if (player.getActivePotionEffect(Potion.getPotionById(14)).getDuration() < 60)
								player.addPotionEffect(new PotionEffect(Potion.getPotionById(14), 200));
				} else
						player.addPotionEffect(new PotionEffect(Potion.getPotionById(14), 200));
		}

		@Override
		public int getMaxAmount (Item item) {
				return 0;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				return 0;
		}

		@Override
		public boolean getSupportedArmorTypes (EntityEquipmentSlot type) {
				return true;
		}

		@Override
		public String getDescription () {
				return "cube.stealth.description";
		}
}
