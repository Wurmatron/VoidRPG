package wurmatron.voidrpg.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;
import wurmatron.voidrpg.api.cube.CubeData;

//TODO Redundant, remove
public class CubeTickEvent extends Event {

		public final CubeData cubeData;
		public final EntityPlayer player;
		public final ItemStack stack;

		/**
		 * Called Every tick that a cube is on the players armor
		 *
		 * @param data   Location and type of cube
		 * @param player Player that has the armor with this cube
		 * @param stack  ItemStack that this cube is placed on
		 */
		public CubeTickEvent (CubeData data, EntityPlayer player, ItemStack stack) {
				this.cubeData = data;
				this.player = player;
				this.stack = stack;
		}
}
