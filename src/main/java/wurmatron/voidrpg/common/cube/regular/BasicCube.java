package wurmatron.voidrpg.common.cube.regular;

import net.minecraft.block.Block;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.ICube;

public class BasicCube implements ICube {

    private String name;
    private Block block;
    private ResourceLocation texture;
    private double weight;
    private int maxDurability;
    private int complexity;
    private int maxAmount;
    private EntityEquipmentSlot[] supported;
    private String description;
    private double protection;

    public BasicCube(String name, Block block, ResourceLocation texture, double weight, int maxDurability, int complexity, int maxAmount, EntityEquipmentSlot supported, String description, double protection) {
        this.name = name;
        this.block = block;
        this.texture = texture;
        this.weight = weight;
        this.maxAmount = maxAmount;
        this.complexity = complexity;
        this.maxDurability = maxDurability;
        this.supported[0] = supported;
        this.description = description;
        this.protection = protection;
    }

    public BasicCube(String name, Block block, ResourceLocation texture, double weight, int maxDurability, int complexity, int maxAmount, EntityEquipmentSlot[] supported, String description, double protection) {
        this.name = name;
        this.block = block;
        this.texture = texture;
        this.weight = weight;
        this.maxAmount = maxAmount;
        this.complexity = complexity;
        this.maxDurability = maxDurability;
        this.supported = supported;
        this.description = description;
        this.protection = protection;
    }

    public BasicCube(String name, Block block, ResourceLocation texture, double weight, int maxDurability, int complexity, int maxAmount, String description, double protection) {
        this.name = name;
        this.block = block;
        this.texture = texture;
        this.weight = weight;
        this.maxAmount = maxAmount;
        this.complexity = complexity;
        this.maxDurability = maxDurability;
        this.supported = new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD,EntityEquipmentSlot.CHEST,EntityEquipmentSlot.LEGS,EntityEquipmentSlot.FEET};
        this.description = description;
        this.protection = protection;
    }



    @Override
    public String getName() {
        return name;
    }

    @Override
    public Block getBlock() {
        return block;
    }

    @Override
    public ResourceLocation getTexture() {
        return texture;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getMaxDurability() {
        return maxDurability;
    }

    @Override
    public int getComplexity() {
        return complexity;
    }

    @Override
    public int getMaxAmount(Item item) {
        return maxAmount;
    }

    @Override
    public boolean getSupportedItem(EntityEquipmentSlot slot, Item item) {
        return slot.equals(supported);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean hasEffects() {
        return false;
    }

    @Override
    public double getProtectionPercentage(DamageSource source, double amount) {
        return protection;
    }
}
