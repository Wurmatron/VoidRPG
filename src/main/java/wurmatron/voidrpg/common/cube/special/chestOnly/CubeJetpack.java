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

public class CubeJetpack implements ICube {
		@Override
		public String getUnlocalizedName () {
				return "jetpack";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeJetpack;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/jetpack.png");
		}

		@Override
		public double getWeight () {
				return 200;
		}

		@Override
		public int getComplexity () {
				return 100;
		}

		@Override
		public int getDurability () {
				return 800;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return true;
		}

		@Override
		public void applyEffect(EntityPlayer player, CubeData data, ItemStack stack) {
						// TODO FIX this not working
		}

		@Override
		public int getMaxAmount (Item item) {
				return 5000;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				if (item.equals(VoidRPGItems.armorChestplate) || item.equals(VoidRPGItems.armorBoots))
						return 4;
				return 0;
		}

		@Override
		public boolean getSupportedArmorTypes (EntityEquipmentSlot type) {
			return type.equals(EntityEquipmentSlot.CHEST) || type.equals(EntityEquipmentSlot.FEET);
		}


		@Override
		public String getDescription () {
				return "cube.jetpack.description";
		}
}
