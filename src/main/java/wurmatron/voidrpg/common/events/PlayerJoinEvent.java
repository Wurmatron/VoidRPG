package wurmatron.voidrpg.common.events;

import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.VersionChecker;

public class PlayerJoinEvent {

		@SubscribeEvent
		public void onPlayerJoin (PlayerEvent.PlayerLoggedInEvent e) {
				if (Settings.updateCheck && !VersionChecker.isUpdated())
						e.player.addChatComponentMessage(new TextComponentString("Update found for " + Global.NAME + " Current: " + Global.VERSION + " Latest: " + VersionChecker.getVersion(Global.UPDATE_URL)).setStyle(new Style().setColor(TextFormatting.AQUA)));
		}
}
