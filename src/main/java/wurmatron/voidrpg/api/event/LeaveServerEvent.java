package wurmatron.voidrpg.api.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import wurmatron.voidrpg.common.config.ConfigHandler;
import wurmatron.voidrpg.common.utils.LogHandler;

public class LeaveServerEvent {

	@SubscribeEvent
	public void playerLogoutEvent (PlayerEvent.PlayerLoggedOutEvent e) {
		LogHandler.info("Restoring Local Config Settings");
		ConfigHandler.loadMainConfig();
	}
}
