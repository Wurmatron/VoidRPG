package wurmatron.voidrpg.api.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import wurmatron.voidrpg.api.cube.CubeData;

@Cancelable
public class CubeBreakEvent extends Event {

		public static CubeData cubeData;
		public static EntityLivingBase entity;
		public static ItemStack stack;

		/**
		 * Called when an cubes durability hits its max
		 *
		 * @param data   Cube Data of the cube that is broken
		 * @param entity 　Entity for the broken cube
		 * @param stack  ItemStack that had the cube before it broke
		 */
		public CubeBreakEvent (CubeData data, EntityLivingBase entity, ItemStack stack) {
				this.cubeData = data;
				this.entity = entity;
				this.stack = stack;
		}
}
