package wurmatron.voidrpg.common.network.server;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import wurmatron.voidrpg.common.config.JsonHandler;
import wurmatron.voidrpg.common.network.CustomMessage;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.LogHandler;

import java.io.IOException;

public class SendFileMessage extends CustomMessage.AbstractClientMessage <SendFileMessage> {

	private NBTTagCompound nbt;

	public SendFileMessage () {}

	public SendFileMessage (String jsonData, String type) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("json", JsonHandler.compress(jsonData));
		nbt.setString("type", type);
		this.nbt = nbt;
		LogHandler.info("nbt");
	}

	@Override
	protected void read (PacketBuffer buff) throws IOException {
		nbt = buff.readNBTTagCompoundFromBuffer();
		LogHandler.info("Read");
	}

	@Override
	protected void write (PacketBuffer buff) throws IOException {
		buff.writeNBTTagCompoundToBuffer(nbt);
		LogHandler.info("write");
	}

	@Override
	public void process (EntityPlayer player, Side side) {
		if (nbt != null) {
			String jsonData = JsonHandler.decompress(nbt.getString("json"));
			LogHandler.info("Creating " + nbt.getString("type"));
			try {
				if (nbt.getString("type").equals(NBT.JSON_CUBE) && Minecraft.getMinecraft().getCurrentServerData() != null) {
					LogHandler.info("Before adding");
					JsonHandler.writeCubeJsonFromServer(JsonHandler.convertJsonToCube(jsonData), Minecraft.getMinecraft().getCurrentServerData().serverIP);
				} else if (nbt.getString("type").equals(NBT.JSON_RECIPE) && Minecraft.getMinecraft().getCurrentServerData() != null) {
					JsonHandler.writeRecipeToFile(JsonHandler.convertJsonToRecipe(jsonData), Minecraft.getMinecraft().getCurrentServerData().serverIP);
				}
			} catch (NullPointerException e) {
			}
		}
	}
}
