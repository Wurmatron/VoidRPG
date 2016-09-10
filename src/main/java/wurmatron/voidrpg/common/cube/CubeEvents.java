package wurmatron.voidrpg.common.cube;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wurmatron.voidrpg.api.event.CubeTickEvent;

public class CubeEvents {

		@SubscribeEvent
		public void onCubeUpdate (CubeTickEvent e) {
				if (e.cubeData.cube == CubeRegistry.INSTANCE.getCubesFromName("armorHeavy"))
						e.player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,5,5));
		}
}
