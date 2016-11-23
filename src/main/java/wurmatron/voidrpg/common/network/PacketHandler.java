package wurmatron.voidrpg.common.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import wurmatron.voidrpg.common.network.client.SyncConfigMessage;
import wurmatron.voidrpg.common.network.server.SendFileMessage;
import wurmatron.voidrpg.common.reference.Global;

public class PacketHandler {

	private static byte packetId = 0;

	private static final SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Global.MODID);

	public static final void registerPackets () {
		registerMessage(SyncConfigMessage.class);
		registerMessage(SendFileMessage.class);
	}

	private static final <T extends CustomMessage <T> & IMessageHandler <T, IMessage>> void registerMessage (Class <T> clazz) {
		if (CustomMessage.AbstractClientMessage.class.isAssignableFrom(clazz)) {
			PacketHandler.wrapper.registerMessage(clazz, clazz, packetId++, Side.CLIENT);
		} else if (CustomMessage.AbstractServerMessage.class.isAssignableFrom(clazz)) {
			PacketHandler.wrapper.registerMessage(clazz, clazz, packetId++, Side.SERVER);
		} else {
			PacketHandler.wrapper.registerMessage(clazz, clazz, packetId, Side.CLIENT);
			PacketHandler.wrapper.registerMessage(clazz, clazz, packetId++, Side.SERVER);
		}
	}

	public static final void sendTo (IMessage message, EntityPlayerMP player) {
		PacketHandler.wrapper.sendTo(message, player);
	}

	public static void sendToAll (IMessage message) {
		PacketHandler.wrapper.sendToAll(message);
	}

	public static final void sendToAllAround (IMessage message, NetworkRegistry.TargetPoint point) {
		PacketHandler.wrapper.sendToAllAround(message, point);
	}

	public static final void sendToAllAround (IMessage message, int dimension, double x, double y, double z, double range) {
		PacketHandler.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
	}

	public static final void sendToDimension (IMessage message, int dimensionId) {
		PacketHandler.wrapper.sendToDimension(message, dimensionId);
	}

	public static final void sendToServer (IMessage message) {
		PacketHandler.wrapper.sendToServer(message);
	}
}
