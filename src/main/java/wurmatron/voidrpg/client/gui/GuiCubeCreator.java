package wurmatron.voidrpg.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.common.container.ContainerCubeCreator;
import wurmatron.voidrpg.common.reference.Global;

public class GuiCubeCreator extends GuiContainer {

		private EntityPlayer player;
		private int time;

		public GuiCubeCreator (EntityPlayer player, InventoryPlayer inventoryPlayer, IInventory tile, int time) {
				super(new ContainerCubeCreator(player, inventoryPlayer, tile));
				this.player = player;
				setGuiSize(256, 212);
				this.time = time;
		}

		@Override
		public void drawScreen (int mouseX, int mouseY, float f) {
				super.drawScreen(mouseX, mouseY, f);
		}

		@Override
		protected void drawGuiContainerBackgroundLayer (float f, int mouseX, int mouseY) {
				mc.renderEngine.bindTexture(new ResourceLocation(Global.MODID + ":textures/gui/cubeCreator.png"));
				drawTexturedModalRect((width - 256) / 2, (height - 212) / 2, 0, 0, 256, 212);
		}
}
