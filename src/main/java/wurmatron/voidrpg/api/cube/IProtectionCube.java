package wurmatron.voidrpg.api.cube;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

public interface IProtectionCube {

		/**
		 * Overall protection of this cube
		 *
		 * @see DamageSource
		 */
		int getOverallProtection(EntityPlayer player, DamageSource source);

}
