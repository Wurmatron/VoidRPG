package wurmatron.voidrpg.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.common.container.ContainerCubeCreator;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.LogHandler;

public class GuiCubeCreator extends GuiContainer {

    private int time;

    public GuiCubeCreator(InventoryPlayer inventoryPlayer, IInventory tile, int time) {
        super(new ContainerCubeCreator(inventoryPlayer, tile));
        setGuiSize(182, 198);
        this.time = time;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
        super.drawScreen(mouseX, mouseY, f);
        LogHandler.debug("Time: " + time);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
        mc.renderEngine.bindTexture(new ResourceLocation(Global.MODID + ":textures/gui/cubeCreator.png"));
        drawTexturedModalRect((width - 182) / 2, (height - 198) / 2, 0, 0, 182, 198);
    }
}
