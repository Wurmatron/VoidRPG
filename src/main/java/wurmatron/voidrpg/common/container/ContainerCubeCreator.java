package wurmatron.voidrpg.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import wurmatron.voidrpg.common.tiles.TileCubeCreator;

public class ContainerCubeCreator extends Container {

		public EntityPlayer player;
		public TileCubeCreator tile;

		public ContainerCubeCreator (EntityPlayer player, TileCubeCreator tile) {
				this.player = player;
				this.tile = tile;
		}

		@Override
		public boolean canInteractWith (EntityPlayer player) {
				return true;
		}
}
