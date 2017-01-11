package wurmatron.voidrpg.common.cube.special.legs;

import net.minecraft.block.Block;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.reference.Global;

public class CubeMuscle implements ICube {

    public static final float MOVEMENT_SPEED = 1.5f;

    @Override
    public String getName() {
        return "muscle";
    }

    @Override
    public Block getBlock() {
        return VoidRPGBlocks.cubeMuscle;
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(Global.MODID,"textures/cube/muscle.png");
    }

    @Override
    public double getWeight() {
        return 20;
    }

    @Override
    public int getMaxDurability() {
        return 8192;
    }

    @Override
    public int getComplexity() {
        return 10;
    }

    @Override
    public int getMaxAmount(Item item) {
        return 24;
    }

    @Override
    public boolean getSupportedItem(EntityEquipmentSlot slot, Item item) {
        return slot.equals(EntityEquipmentSlot.LEGS);
    }

    @Override
    public String getDescription() {
        return "";
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
