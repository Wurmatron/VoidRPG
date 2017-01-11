package wurmatron.voidrpg.common.integration.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.gui.IAdvancedGuiHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.api.cube.IEnergy;
import wurmatron.voidrpg.api.recipe.ICubeCreatorRecipe;
import wurmatron.voidrpg.client.gui.GuiCubeCreator;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.integration.jei.cubecreator.CubeCreatorCategory;
import wurmatron.voidrpg.common.integration.jei.cubecreator.CubeCreatorRecipeHandler;
import wurmatron.voidrpg.common.integration.jei.cubecreator.CubeCreatorRecipeWrapper;
import wurmatron.voidrpg.common.reference.Local;

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
        for (ICube cube : CubeRegistry.getCubes())
            if (cube != null && cube.getDescription() != null || cube.getDescription().length() > 0)
                if (cube instanceof IEnergy) {
                    IEnergy energy = (IEnergy) cube;
                    registry.addDescription(new ItemStack(cube.getBlock(), 1, 0), "Name: " + I18n.format(cube.getName()), I18n.format(Local.STAT_DURABILITY) + ": " + cube.getMaxDurability(), I18n.format(Local.STAT_COMPLEXITY) + ": " + cube.getComplexity(), I18n.format(Local.STAT_WEIGHT) + ": " + cube.getWeight(), I18n.format(Local.STAT_ENERGY) + ": " + energy.getStorage(), I18n.format(Local.PLACMENT_TYPE) + ": " + getValidArmorTypes(cube), "", I18n.format(cube.getDescription()));
                } else
                    registry.addDescription(new ItemStack(cube.getBlock(), 1, 0), "Name: " + I18n.format(cube.getName()), I18n.format(Local.STAT_DURABILITY) + ": " + cube.getMaxDurability(), I18n.format(Local.STAT_COMPLEXITY) + ": " + cube.getComplexity(), I18n.format(Local.STAT_WEIGHT) + ": " + cube.getWeight(), I18n.format(Local.PLACMENT_TYPE) + ": " + getValidArmorTypes(cube), "", I18n.format(cube.getDescription()));
            else
                registry.addDescription(new ItemStack(cube.getBlock(), 1, 0), "Name: " + I18n.format(cube.getName()), I18n.format(Local.STAT_DURABILITY) + ": " + cube.getMaxDurability(), I18n.format(Local.STAT_COMPLEXITY) + ": " + cube.getComplexity(), I18n.format(Local.STAT_WEIGHT) + ": " + cube.getWeight(), I18n.format(Local.PLACMENT_TYPE) + ": " + getValidArmorTypes(cube));

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

    private String getValidArmorTypes(ICube cube) {
        if (cube != null) {
            String temp = "";
            if (cube.getSupportedItem(EntityEquipmentSlot.HEAD, null))
                temp += "Helmet, ";
            if (cube.getSupportedItem(EntityEquipmentSlot.LEGS, null))
                temp += "Leggings, ";
            if (cube.getSupportedItem(EntityEquipmentSlot.CHEST, null))
                temp += "Chestplate, ";
            if (cube.getSupportedItem(EntityEquipmentSlot.FEET, null))
                temp += "Boots, ";
            if (temp.endsWith(","))
                return temp.substring(0, temp.lastIndexOf(",") - 1);
            return temp;
        }
        return "none";
    }
}
