package wurmatron.voidrpg.common.network.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.network.CustomMessage;

import java.io.IOException;

public class SyncConfigMessage extends CustomMessage.AbstractClientMessage <SyncConfigMessage> {

	private NBTTagCompound data;

	public SyncConfigMessage () {
		data = new NBTTagCompound();
		data.setBoolean("debug", Settings.debug);
		data.setBoolean("cubeEffects", Settings.cubeEffects);
	}

	@Override
	protected void read (PacketBuffer buffer) throws IOException {
		data = buffer.readNBTTagCompoundFromBuffer();
	}

	@Override
	protected void write (PacketBuffer buffer) throws IOException {
		buffer.writeNBTTagCompoundToBuffer(data);
	}

	@Override
	public void process (EntityPlayer player, Side side) {
		Settings.debug = data.getBoolean("debug");
		Settings.cubeEffects = data.getBoolean("cubeEffects");
	}
}
