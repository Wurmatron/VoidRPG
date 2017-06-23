package wurmatron.voidrpg.common.integration.jei.cubecreator;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CubeCreatorRecipeWrapper extends BlankRecipeWrapper {

	public final int time;
	private final List <ItemStack> inputs;
	private final List <ItemStack> output;

	public CubeCreatorRecipeWrapper (ItemStack[] inputs,ItemStack output,int time) {
		ArrayList <ItemStack> input = new ArrayList <> ();
		this.inputs = input;
		ArrayList <ItemStack> outputs = new ArrayList <> ();
		outputs.add (output);
		this.output = outputs;
		this.time = time;
		Collections.addAll (input,inputs);
	}

	@Override
	public void getIngredients (IIngredients ingredients) {
		if(!inputs.isEmpty())
			ingredients.setInputs(ItemStack.class, inputs);
		if(!output.isEmpty())
			ingredients.setOutputs (ItemStack.class, output);
	}

	@Override
	public void drawInfo (@Nonnull Minecraft mc,int recipeWidth,int recipeHeight,int mouseX,int mouseY) {
		mc.fontRendererObj.drawString (time + " ticks",25,60,Color.gray.getRGB ());
	}
}
