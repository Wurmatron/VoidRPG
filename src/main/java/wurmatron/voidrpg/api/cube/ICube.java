package wurmatron.voidrpg.api.cube;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.event.CubeTickEvent;

public interface ICube {

		/**
		 * Name for this cube
		 */
		String getUnlocalizedName ();

		/**
		 * Get Block that is used to create this cube
		 */
		Block getBlock ();

		/**
		 * Texture used to display this cube on the armor
		 */
		ResourceLocation getTexture ();

		/**
		 * Used for calculating the weight of the armor
		 */
		double getWeight ();

		/**
		 * Used to limit the amount of cubes placed on an single piece of armor
		 */
		int getComplexity ();

		/**
		 * Durability of the cube while on the armor
		 */
		int getDurability ();

		/**
		 * Makes sure that thee cube meets the requirements to have its effect
		 * Automatically checks for minimum cubes
		 *
		 * @param player Player that this cube is on
		 * @param stack  Stack that this cube is on
		 * @return if the cube has special effects (Note: has to be true for applyEffect to work
		 *
		 * @see ICube#applyEffect(EntityPlayer, CubeData, CubeData[])
		 * @see ICube#getMinAmount(Item, double)
		 */
		boolean hasEffects (EntityPlayer player, ItemStack stack);

		/**
		 * Called every time the cube is ticked on an players armor
		 *
		 * @see CubeTickEvent
		 */
		void applyEffect (EntityPlayer player, CubeData data, CubeData[] cubes);

		/**
		 * Maximum amount of this cube that can be on a single piece of armor
		 *
		 * @param item Item that is trying to be created
		 */
		int getMaxAmount (Item item);

		/**
		 * Minimim Amount of this cube required for its effects to activate
		 *
		 * @param item   item that the cube is placed on
		 * @param weight how much that part of the armor weights
		 */
		int getMinAmount (Item item, double weight);

		/**
		 * Can this cube be placed on this Item
		 *
		 * @param type Type of armor that it is trying to be placed on.
		 */
		boolean getSupportedArmorTypes (EntityEquipmentSlot type);

		/**
		 * Used for displaying an explination on waht the cube does.
		 * This should be an locilization key and is automaticlly localized
		 */
		String getDescription ();
}
