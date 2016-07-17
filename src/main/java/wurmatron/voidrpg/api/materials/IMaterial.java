package wurmatron.voidrpg.api.materials;

import net.minecraft.util.ResourceLocation;

public interface IMaterial {

		/**
		 * Unlocalized name of the material
		 * (Used to display its name in-game)
		 */
		String getUnlocalizedName ();

		/**
		 * Default durability without any modifiers
		 */
		double getDefaultDurability ();

		/**
		 * Used to determine the type of armor along with its durability and the player's speed
		 */
		double getWeight (int partCost);

		/**
		 * Texture used for displaying parts out of this material
		 */
		ResourceLocation getTexture ();

		/**
		 * Parts that this material can be built out of
		 */
		MaterialType getMaterialType ();
}
