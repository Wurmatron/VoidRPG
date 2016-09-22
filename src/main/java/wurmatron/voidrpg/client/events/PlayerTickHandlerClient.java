package wurmatron.voidrpg.client.events;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import wurmatron.voidrpg.common.items.VoidRPGItems;

import java.util.HashMap;
import java.util.UUID;

public class PlayerTickHandlerClient {

		public static HashMap<UUID, Boolean> armorData = new HashMap<UUID, Boolean>();

		// Client-Side Only (Because Rendering)
		@SubscribeEvent
		public void onPlayerTick (TickEvent.PlayerTickEvent e) {
				if (e.phase == TickEvent.Phase.START && e.side == Side.CLIENT) {
						if (e.player.inventory != null && e.player.inventory.armorInventory != null) {
								if (e.player.inventory.armorItemInSlot(3) != null && e.player.inventory.armorItemInSlot(3).getItem().equals(VoidRPGItems.armorHelmet) || e.player.inventory.armorItemInSlot(2) != null && e.player.inventory.armorItemInSlot(2).getItem().equals(VoidRPGItems.armorChestplate)  || e.player.inventory.armorItemInSlot(1) != null && e.player.inventory.armorItemInSlot(1).getItem().equals(VoidRPGItems.armorLeggings)  || e.player.inventory.armorItemInSlot(0) != null && e.player.inventory.armorItemInSlot(0).getItem().equals(VoidRPGItems.armorBoots)) {
										if (armorData.containsKey(e.player.getGameProfile().getId())) {
												if (armorData.get(e.player.getGameProfile().getId())) {
														armorData.remove(e.player.getGameProfile().getId());
														armorData.put(e.player.getGameProfile().getId(), false);
												}
										} else
												armorData.put(e.player.getGameProfile().getId(), true);
								} else {
										if (armorData.containsKey(e.player.getGameProfile().getId()))
												armorData.remove(e.player.getGameProfile().getId());
								}
						} else {
								if (armorData.containsKey(e.player.getGameProfile().getId()))
										armorData.remove(e.player.getGameProfile().getId());
						}
				}
		}
}
