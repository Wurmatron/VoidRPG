package wurmatron.voidrpg.common.network.server;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import wurmatron.voidrpg.common.config.JsonHandler;
import wurmatron.voidrpg.common.network.CustomMessage;

import java.io.IOException;

public class SendFileMessage extends CustomMessage.AbstractClientMessage <SendFileMessage> {

	private NBTTagCompound nbt;

	public SendFileMessage () {
	}

	public SendFileMessage (String jsonData) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("json", JsonHandler.compress(jsonData));
		this.nbt = nbt;
	}

	@Override
	protected void read (PacketBuffer buff) throws IOException {
		nbt = buff.readNBTTagCompoundFromBuffer();
	}

	@Override
	protected void write (PacketBuffer buff) throws IOException {
		buff.writeNBTTagCompoundToBuffer(nbt);
	}

	@Override
	public void process (EntityPlayer player, Side side) {
		if (nbt != null) {
			String jsonData = JsonHandler.decompress(nbt.getString("json"));

		}
	}
}
