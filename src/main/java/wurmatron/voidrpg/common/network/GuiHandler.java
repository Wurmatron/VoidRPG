package wurmatron.voidrpg.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

		@Override
		public Object getServerGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
				switch (ID) {}
				return new ContainerPlayer(player.inventory, true, player);
		}

		@Override
		public Object getClientGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
				switch (ID) {}
				return null;
		}
}
