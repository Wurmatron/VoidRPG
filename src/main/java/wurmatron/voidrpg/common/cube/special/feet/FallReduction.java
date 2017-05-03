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

public class FallReduction implements ICube {

	@Override
	public String getName () {
		return "shock";
	}

	@Override
	public Block getBlock () {
		return VoidRPGBlocks.cubeShock;
	}

	@Override
	public ResourceLocation getTexture () {
		return new ResourceLocation (Global.MODID,"textures/cube/shock.png");
	}

	@Override
	public double getWeight () {
		return 25;
	}

	@Override
	public int getMaxDurability () {
		return 2500;
	}

	@Override
	public int getComplexity () {
		return 5;
	}

	@Override
	public int getMaxAmount (Item item) {
		return 64;
	}

	@Override
	public boolean getSupportedItem (EntityEquipmentSlot slot,Item item) {
		return slot.equals (EntityEquipmentSlot.FEET);
	}

	@Override
	public String getDescription () {
		return Local.CUBED_SHOCK;
	}

	@Override
	public boolean hasEffects () {
		return true;
	}

	@Override
	public double getProtectionPercentage (DamageSource source,double amount) {
		return 0;
	}
}
