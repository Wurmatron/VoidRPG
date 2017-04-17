package wurmatron.voidrpg.common.cube.special.feet;


import net.minecraft.block.Block;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.Local;

public class WaterWalking implements ICube {

		@Override
		public String getName() {
				return "waterWalk";
		}

		@Override
		public Block getBlock() {
				return VoidRPGBlocks.cubeWaterWalking;
		}

		@Override
		public ResourceLocation getTexture() {
				return new ResourceLocation(Global.MODID, "textures/cube/waterWalking.png");
		}

		@Override
		public double getWeight() {
				return 5;
		}

		@Override
		public int getMaxDurability() {
				return 50;
		}

		@Override
		public int getComplexity() {
				return 20;
		}

		@Override
		public int getMaxAmount(Item item) {
				return 64;
		}

		@Override
		public boolean getSupportedItem(EntityEquipmentSlot slot, Item item) {
				return slot.equals(EntityEquipmentSlot.FEET);
		}

		@Override
		public String getDescription() {
				return Local.CUBED_WATERWALKING;
		}

		@Override
		public boolean hasEffects() {
				return true;
		}

		@Override
		public double getProtectionPercentage(DamageSource source, double amount) {
				return 0;
		}
}
