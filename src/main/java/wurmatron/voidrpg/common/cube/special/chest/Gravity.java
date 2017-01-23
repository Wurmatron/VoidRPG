package wurmatron.voidrpg.common.cube.special.chest;

import net.minecraft.block.Block;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.reference.Global;

public class Gravity implements ICube {

    @Override
    public String getName() {
        return "gravity";
    }

    @Override
    public Block getBlock() {
        return VoidRPGBlocks.cubeGravity;
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(Global.MODID, "textures/cube/gravity.png");
    }

    @Override
    public double getWeight() {
        return 200;
    }

    @Override
    public int getMaxDurability() {
        return 5000;
    }

    @Override
    public int getComplexity() {
        return 500;
    }

    @Override
    public int getMaxAmount(Item item) {
        return 8;
    }

    @Override
    public boolean getSupportedItem(EntityEquipmentSlot slot, Item item) {
        return slot.equals(EntityEquipmentSlot.CHEST);
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
