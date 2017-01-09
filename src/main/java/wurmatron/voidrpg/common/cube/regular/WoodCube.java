package wurmatron.voidrpg.common.cube.regular;

import net.minecraft.block.Block;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.reference.Global;

public class WoodCube implements ICube {

    @Override
    public String getName() {
        return "wood";
    }

    @Override
    public Block getBlock() {
        return VoidRPGBlocks.armorWood;
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(Global.MODID, "textures/cube/wood.png");
    }

    @Override
    public double getWeight() {
        return 5;
    }

    @Override
    public int getMaxDurability() {
        return 200;
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
            return (amount - (amount / 10)) > 0 ? (amount - (amount / 10)) : 0;
        return amount;
    }
}
