package wurmatron.voidrpg.common.cube.regular;

import net.minecraft.block.Block;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.reference.Global;

public class HeavyArmor implements ICube {

    @Override
    public String getName() {
        return "heavyArmor";
    }

    @Override
    public Block getBlock() {
        return VoidRPGBlocks.armorHeavy;
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(Global.MODID, "textures/cube/heavyArmor.png");
    }

    @Override
    public double getWeight() {
        return 50;
    }

    @Override
    public int getMaxDurability() {
        return 1000;
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
        return "";
    }

    @Override
    public boolean hasEffects() {
        return false;
    }

    @Override
    public double getProtectionPercentage(DamageSource source, double amount) {
        if (!source.isDamageAbsolute())
            return (amount - (amount / 5)) > 0 ? (amount - (amount / 5)) : 0;
        return amount;
    }
}
