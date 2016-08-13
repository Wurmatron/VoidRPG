package wurmatron.voidrpg.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.common.container.ContainerCubeCreator;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.tiles.TileCubeCreator;

public class GuiCubeCreator extends GuiContainer {

		private EntityPlayer player;
		private TileCubeCreator tile;

		public GuiCubeCreator (EntityPlayer player, TileCubeCreator tile) {
				super(new ContainerCubeCreator(player, tile));
				this.player = player;
				this.tile = tile;
				setGuiSize(256,212);
		}

		@Override
		protected void drawGuiContainerBackgroundLayer (float partialTicks, int mouseX, int mouseY) {}

		@Override
		public void drawScreen (int mouseX, int mouseY, float partialTicks) {
				mc.renderEngine.bindTexture(new ResourceLocation(Global.MODID + ":textures/gui/cubeCreator.png"));
				drawTexturedModalRect(width/2 - 128,height/2 - 106,256,256,256,212);
		}
}
