package wurmatron.voidrpg.api.event;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import wurmatron.voidrpg.common.network.PacketHandler;
import wurmatron.voidrpg.common.network.client.SyncConfigMessage;
import wurmatron.voidrpg.common.utils.LogHandler;

public class JoinServerEvent {

	@SubscribeEvent
	public void onPlayerJoin (PlayerEvent.PlayerLoggedInEvent e) {
		LogHandler.info("Loading server config");
		PacketHandler.sendTo(new SyncConfigMessage(), (EntityPlayerMP) e.player);
	}
}
