package wurmatron.voidrpg.common.cube;

import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.api.recipe.ICubeCreatorRecipe;

public class CubeCreatorRecipe implements ICubeCreatorRecipe {

    private ItemStack output;
    private ItemStack[] inputs;
    private int timeInTicks;

    public CubeCreatorRecipe (ItemStack output, ItemStack[] inputs, int timeInTicks) {
        this.output = output;
        this.inputs = inputs;
        this.timeInTicks = timeInTicks;
    }

    @Override
    public ItemStack getOutputCube () {
        return output;
    }

    @Override
    public ItemStack[] getInputs () {
        return inputs;
    }

    @Override
    public int getTimeInTicks () {
        return timeInTicks;
    }
}