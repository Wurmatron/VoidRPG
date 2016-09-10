package wurmatron.voidrpg.api.cube;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
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
		 *
		 * @param player Player that this cube is on
		 * @param stack  Stack that this cube is on
		 * @return if the cube has special effects (Note: has to be true for applyEffect to work
		 *
		 * @see ICube#applyEffect(CubeTickEvent)
		 */
		boolean hasEffects (EntityPlayer player, ItemStack stack);

		/**
		 * Called every time the cube is ticked on an players armor
		 *
		 * @see CubeTickEvent
		 */
		void applyEffect (CubeTickEvent e);
}
