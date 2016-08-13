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
import wurmatron.voidrpg.common.utils.LogHandler;

public class GuiHandler implements IGuiHandler {

		@Override
		public Object getServerGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
				switch (ID) {
						case (Global.CUBECREATOR_GUI): {
								LogHandler.info("Container GUI handler");
								return new ContainerCubeCreator(player, (TileCubeCreator) world.getTileEntity(new BlockPos(x, y, z)));
						}
				}
				return new ContainerPlayer(player.inventory, true, player);
		}

		@Override
		public Object getClientGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
				switch (ID) {
						case (Global.CUBECREATOR_GUI):
								return new GuiCubeCreator(player, (TileCubeCreator) world.getTileEntity(new BlockPos(x, y, z)));
				}
				return null;
		}
}
