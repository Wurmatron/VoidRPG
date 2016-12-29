package wurmatron.voidrpg.api.cube;

import net.minecraft.block.Block;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public interface ICube {

    /**
     * The name of this cube
     * Localization name is "cube.<name>.name"
     */
    String getName();

    /**
     * Block used to create this cube / bits
     */
    Block getBlock();

    /**
     * Texture used while displaying this cube
     */
    ResourceLocation getTexture();

    /**
     * Weight of this cube
     */
    double getWeight();

    /**
     * Max amount of this cube that can be on a item
     *
     * @param item Item that has a limit
     */
    int getMaxAmount(Item item);

    /**
     * Used to restrict the cubes from being placed on certain items or armor
     *
     * @param slot slot that the item is in
     * @param item item that the cube is trying to be placed on
     */
    boolean getSupportedItem(EntityEquipmentSlot slot, Item item);

    /**
     * Description of what this cube does.
     * Should be a localization key. (Will be handles automatically)
     */
    String getDescription();

    /**
     * Does this cube has a special effect?
     *
     */
    boolean hasEffects();
}
