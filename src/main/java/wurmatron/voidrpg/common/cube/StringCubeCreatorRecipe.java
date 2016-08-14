package wurmatron.voidrpg.common.cube;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.recipe.ICubeCreatorRecipe;

@Deprecated
/**
 * Needs to be tested + nbt support
 */
public class StringCubeCreatorRecipe implements ICubeCreatorRecipe {

		private String output;
		private String[] inputs;
		private int timeInTicks;

		// @ = meta % = stack size
		public StringCubeCreatorRecipe (String output, String[] inputs, int timeInTicks) {
				this.output = output;
				this.inputs = inputs;
				this.timeInTicks = timeInTicks;
		}

		@Override
		public ItemStack getOutputCube () {
				return getStackFromString(output);
		}

		@Override
		public ItemStack[] getInputs () {
				ItemStack[] temp = new ItemStack[inputs.length];
				for (int s = 0; s <= inputs.length; s++)
						temp[s] = getStackFromString(inputs[s]);
				return temp;
		}

		@Override
		public int getTimeInTicks () {
				return 0;
		}

		private ItemStack getStackFromString (String stack) {
				String modid = output.substring(0, output.indexOf(":"));
				String unlocalizedName;
				int meta = 0;
				int stackSize = 1;
				if (output.contains("@")) {
						if (output.contains("%")) {
								stackSize = Integer.getInteger(output.substring(output.indexOf("@"), output.length()));
								meta = Integer.getInteger(output.substring(output.indexOf("@") + 1, output.indexOf("%")));
								unlocalizedName = output.substring(output.indexOf(":") + 1, output.indexOf("@"));
						} else {
								meta = Integer.getInteger(output.substring(output.indexOf("@") + 1, output.length()));
								unlocalizedName = output.substring(output.indexOf(":") + 1, output.indexOf("@"));
						}
				} else {
						unlocalizedName = output.substring(output.indexOf(":") + 1, output.length());
						if (output.contains("%"))
								stackSize = Integer.getInteger(output.substring(output.indexOf("%"), output.length()));
				}
				return new ItemStack(Item.REGISTRY.getObject(new ResourceLocation(modid, unlocalizedName)), stackSize, meta);
		}
}
