package wurmatron.voidrpg.client.events;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import wurmatron.voidrpg.common.items.CustomArmor;
import wurmatron.voidrpg.common.utils.StackHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerTickHandlerClient {

		public static HashMap<UUID, ArrayList<ItemStack>> storage = new HashMap<>();
		public static ArrayList<UUID> updateRequirment = new ArrayList<>();

		@SubscribeEvent
		public void onPlayerTick (TickEvent.PlayerTickEvent e) {
				if (e.phase == TickEvent.Phase.START && e.side == Side.CLIENT) {
						if (e.player.inventory != null && e.player.inventory.armorInventory != null) {
								if (storage.containsKey(e.player.getGameProfile().getId())) {
										for (int s = 0; s <= 3; s++)
												if (!StackHelper.areItemsEqual(e.player.inventory.armorItemInSlot(s), storage.get(e.player.getGameProfile().getId()).get(s)) && e.player.inventory.armorItemInSlot(s) != null && e.player.inventory.armorItemInSlot(s).getItem() instanceof CustomArmor) {
														if (!updateRequirment.contains(e.player.getGameProfile().getId()))
																updateRequirment.add(e.player.getGameProfile().getId());
														storage.remove(e.player.getGameProfile().getId());
														ArrayList<ItemStack> temp = new ArrayList<>();
														temp.add(0, e.player.inventory.armorItemInSlot(0));
														temp.add(1, e.player.inventory.armorItemInSlot(1));
														temp.add(2, e.player.inventory.armorItemInSlot(2));
														temp.add(3, e.player.inventory.armorItemInSlot(3));
														storage.put(e.player.getGameProfile().getId(), temp);
												}
								} else {
										ArrayList<ItemStack> temp = new ArrayList<>();
										temp.add(0, e.player.inventory.armorItemInSlot(0));
										temp.add(1, e.player.inventory.armorItemInSlot(1));
										temp.add(2, e.player.inventory.armorItemInSlot(2));
										temp.add(3, e.player.inventory.armorItemInSlot(3));
										storage.put(e.player.getGameProfile().getId(), temp);
										if (!updateRequirment.contains(e.player.getGameProfile().getId()))
												updateRequirment.add(e.player.getGameProfile().getId());
								}
						}
				}
		}

		@SubscribeEvent
		public void onPlayerLogoutEvent (PlayerEvent.PlayerLoggedOutEvent e) {
				if (storage.containsKey(e.player.getGameProfile().getId()))
						storage.remove(e.player.getGameProfile().getId());
				if (updateRequirment.contains(e.player.getGameProfile().getId()))
						updateRequirment.remove(e.player.getGameProfile().getId());
		}

//		// Client-Side Only (Because Rendering)
//		@SubscribeEvent
//		public void onPlayerTick (TickEvent.PlayerTickEvent e) {
//				if (e.phase == TickEvent.Phase.START && e.side == Side.CLIENT) {
//						if (e.player.inventory != null && e.player.inventory.armorInventory != null) {
//								if (e.player.inventory.armorItemInSlot(3) != null && e.player.inventory.armorItemInSlot(3).getItem().equals(VoidRPGItems.armorHelmet) || e.player.inventory.armorItemInSlot(2) != null && e.player.inventory.armorItemInSlot(2).getItem().equals(VoidRPGItems.armorChestplate) || e.player.inventory.armorItemInSlot(1) != null && e.player.inventory.armorItemInSlot(1).getItem().equals(VoidRPGItems.armorLeggings) || e.player.inventory.armorItemInSlot(0) != null && e.player.inventory.armorItemInSlot(0).getItem().equals(VoidRPGItems.armorBoots)) {
//										if (armorData.containsKey(e.player.getGameProfile().getId())) {
//												if (armorData.get(e.player.getGameProfile().getId())) {
//														armorData.remove(e.player.getGameProfile().getId());
//														armorData.put(e.player.getGameProfile().getId(), false);
//												}
//										} else
//												armorData.put(e.player.getGameProfile().getId(), true);
//								} else {
//										if (armorData.containsKey(e.player.getGameProfile().getId()))
//												armorData.remove(e.player.getGameProfile().getId());
//								}
//						} else {
//								if (armorData.containsKey(e.player.getGameProfile().getId()))
//										armorData.remove(e.player.getGameProfile().getId());
//						}
//				}
//		}
}
