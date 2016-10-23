package wurmatron.voidrpg.common.cube;

import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.api.recipe.ICubeCreatorRecipe;
import wurmatron.voidrpg.common.utils.StackHelper;

public class StringCubeCreatorRecipe implements ICubeCreatorRecipe {

		private String output;
		private String[] inputs;
		private int timeInTicks;

		public StringCubeCreatorRecipe (String output, String[] inputs, int timeInTicks) {
				this.output = output;
				this.inputs = inputs;
				this.timeInTicks = timeInTicks;
		}

		@Override
		public ItemStack getOutputCube () {
				return StackHelper.convert(output);
		}

		@Override
		public ItemStack[] getInputs () {
				ItemStack[] temp = new ItemStack[inputs.length];
				for (int s = 0; s < inputs.length; s++)
						temp[s] = StackHelper.convert(inputs[s]);
				return temp;
		}

		@Override
		public int getTimeInTicks () {
				return timeInTicks;
		}

}
