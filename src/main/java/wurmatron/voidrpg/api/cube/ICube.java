package wurmatron.voidrpg.api.cube;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public interface ICube {

		/**
		 * Name for this cube
		 */
		String getUnlocalizedName();

		/**
		 * Get Block that is used to create this cube
		 */
		Block getBlock();

		/**
		 * Texture used to display this cube on the armor
		 */
		 ResourceLocation getTexture();

		/**
		 * Size of this cube on the armor
		 */
		int[] getSize();

		/**
		 * Used for calculating the weight of the armor
		 */
		double getWeight();
}
