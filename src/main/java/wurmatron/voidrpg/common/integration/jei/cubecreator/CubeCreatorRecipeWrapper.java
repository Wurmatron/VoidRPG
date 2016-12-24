package wurmatron.voidrpg.common.integration.jei.cubecreator;

import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CubeCreatorRecipeWrapper extends BlankRecipeWrapper {

    private final List<ItemStack> inputs;
    private final List<ItemStack> output;
    public final int time;

    public CubeCreatorRecipeWrapper(ItemStack[] inputs, ItemStack output, int time) {
        ArrayList<ItemStack> input = new ArrayList<>();
        Collections.addAll(input, inputs);
        this.inputs = input;
        ArrayList<ItemStack> outputs = new ArrayList<>();
        outputs.add(output);
        this.output = outputs;
        this.time = time;
    }

    @Nonnull
    @Override
    public List getInputs() {
        return inputs;
    }

    @Nonnull
    @Override
    public List getOutputs() {
        return output;
    }

    @Override
    public void drawInfo(@Nonnull Minecraft mc, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        mc.fontRendererObj.drawString(time + " ticks", 25, 60, Color.gray.getRGB());
    }
}
