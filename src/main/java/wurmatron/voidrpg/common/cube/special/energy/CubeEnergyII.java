package wurmatron.voidrpg.common.cube.special.energy;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.api.cube.IEnergyCube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.reference.Global;

public class CubeEnergyII implements ICube, IEnergyCube {

		@Override
		public String getUnlocalizedName () {
				return "energyII";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.energyStorageII;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "texutes/cube/energyII.png");
		}

		@Override
		public double getWeight () {
				return 100;
		}

		@Override
		public int getComplexity () {
				return 50;
		}

		@Override
		public int getDurability () {
				return 4000;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return false;
		}

		@Override
		public void applyEffect(EntityPlayer player, CubeData data, ItemStack stack) {}

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
				return "cube.energyII.description";
		}

		@Override
		public long getStorage () {
				return 1000;
		}
}
