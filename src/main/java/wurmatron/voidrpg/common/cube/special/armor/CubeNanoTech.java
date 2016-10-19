package wurmatron.voidrpg.common.cube.special.armor;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.api.cube.IProtectionCube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.reference.Global;

public class CubeNanoTech implements ICube, IProtectionCube {

		private int timer;

		@Override
		public String getUnlocalizedName () {
				return "nanoTech";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.armorNanoTech;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/nanoTech.png");
		}

		@Override
		public double getWeight () {
				return 50;
		}

		@Override
		public int getComplexity () {
				return 500;
		}

		@Override
		public int getDurability () {
				return 12000;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return true;
		}

		@Override
		public void applyEffect (EntityPlayer player, CubeData data, CubeData[] cubes, ItemStack stack) {
				if (timer <= 0) {
						data.damage--;
						timer = 100;
				} else
						timer--;
		}

		@Override
		public int getMaxAmount (Item item) {
				return 4096;
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
				return "cube.nanoTech.descrption";
		}

		@Override
		public int getOverallProtection (EntityPlayer player, DamageSource source) {
				return 0;
		}
}
