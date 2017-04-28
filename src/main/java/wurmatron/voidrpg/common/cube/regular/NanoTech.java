package wurmatron.voidrpg.common.cube.regular;

import net.minecraft.block.Block;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.Local;

public class NanoTech implements ICube {

		@Override
		public String getName() {
				return "nanoTech";
		}

		@Override
		public Block getBlock() {
				return VoidRPGBlocks.armorNanoTech;
		}

		@Override
		public ResourceLocation getTexture() {
				return new ResourceLocation(Global.MODID, "textures/cube/nanoTech.png");
		}

		@Override
		public double getWeight() {
				return 5;
		}

		@Override
		public int getMaxDurability() {
				return 20000;
		}

		@Override
		public int getComplexity() {
				return 5;
		}

		@Override
		public int getMaxAmount(Item item) {
				return 4096;
		}

		@Override
		public boolean getSupportedItem(EntityEquipmentSlot slot, Item item) {
				return true;
		}

		@Override
		public String getDescription() {
				return Local.CUBED_NANO;
		}

		@Override
		public boolean hasEffects() {
				return false;
		}

		@Override
		public double getProtectionPercentage(DamageSource source, double amount) {
				return 20;
		}
}
