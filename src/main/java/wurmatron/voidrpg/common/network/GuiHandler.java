package wurmatron.voidrpg.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import wurmatron.voidrpg.client.gui.GuiCubeCreator;
import wurmatron.voidrpg.common.container.ContainerCubeCreator;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.tiles.TileCubeCreator;

public class GuiHandler implements IGuiHandler {

		@Override
		public Object getServerGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
				switch (ID) {
						case (Global.CUBECREATOR_GUI): {
								TileCubeCreator tile = (TileCubeCreator) world.getTileEntity(new BlockPos(x, y, z));
								return new ContainerCubeCreator(player, player.inventory, tile);
						}
				}
				return new ContainerPlayer(player.inventory, true, player);
		}

		@Override
		public Object getClientGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
				switch (ID) {
						case (Global.CUBECREATOR_GUI):
								TileCubeCreator tile = (TileCubeCreator) world.getTileEntity(new BlockPos(x, y, z));
								return new GuiCubeCreator(player, player.inventory, tile, tile.recipeTimer);
				}
				return null;
		}
}
