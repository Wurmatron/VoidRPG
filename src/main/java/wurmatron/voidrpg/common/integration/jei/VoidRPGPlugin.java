package wurmatron.voidrpg.common.integration.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.gui.IAdvancedGuiHandler;
import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.api.recipe.ICubeCreatorRecipe;
import wurmatron.voidrpg.client.gui.GuiCubeCreator;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.integration.jei.cubecreator.CubeCreatorCategory;
import wurmatron.voidrpg.common.integration.jei.cubecreator.CubeCreatorRecipeHandler;
import wurmatron.voidrpg.common.integration.jei.cubecreator.CubeCreatorRecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@JEIPlugin
public class VoidRPGPlugin extends BlankModPlugin {

    @Override
    public void register(@Nonnull IModRegistry registry) {
        registry.addAdvancedGuiHandlers(new IAdvancedGuiHandler<GuiCubeCreator>() {
            @Nonnull
            @Override
            public Class<GuiCubeCreator> getGuiContainerClass() {
                return GuiCubeCreator.class;
            }

            @Nullable
            @Override
            public java.util.List<Rectangle> getGuiExtraAreas(GuiCubeCreator gui) {
                List<Rectangle> list = new ArrayList<>();
                list.add(new Rectangle((gui.width - 256) / 2, (gui.height - 212) / 2, 256, 212));
                return list;
            }
        });
        registry.addRecipeCategories(new CubeCreatorCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeHandlers(new CubeCreatorRecipeHandler());
        registry.addRecipes(getCubeCreatorRecipes());
        registry.addRecipeCategoryCraftingItem(new ItemStack(VoidRPGBlocks.cubeCreator), "voidrpg.cubecreator");
    }

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime runtime) {
    }

    private List<CubeCreatorRecipeWrapper> getCubeCreatorRecipes() {
        List<CubeCreatorRecipeWrapper> recipes = new ArrayList<>();
        for (ICubeCreatorRecipe recipe : wurmatron.voidrpg.common.cube.CubeCreatorRecipeHandler.getRecipes())
            recipes.add(new CubeCreatorRecipeWrapper(recipe.getInputs(), recipe.getOutputCube(), recipe.getTimeInTicks()));
        return recipes;
    }
}
