package wurmatron.voidrpg.common.integration.jei.cubecreator;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;

public class CubeCreatorRecipeHandler implements IRecipeHandler<CubeCreatorRecipeWrapper> {

		@Nonnull
		@Override
		public Class<CubeCreatorRecipeWrapper> getRecipeClass() {
				return CubeCreatorRecipeWrapper.class;
		}

		@Nonnull
		@Override
		public String getRecipeCategoryUid() {
				return "voidrpg.cubecreator";
		}

		@Nonnull
		@Override
		public String getRecipeCategoryUid(@Nonnull CubeCreatorRecipeWrapper cubeCreatorRecipeWrapper) {
				return "voidrpg.cubecreator";
		}

		@Nonnull
		@Override
		public IRecipeWrapper getRecipeWrapper(@Nonnull CubeCreatorRecipeWrapper recipe) {
				return recipe;
		}

		@Override
		public boolean isRecipeValid(@Nonnull CubeCreatorRecipeWrapper recipe) {
				return recipe.getOutputs().size() > 0 && recipe.getInputs().size() > 1 && recipe.time > 0;
		}
}
