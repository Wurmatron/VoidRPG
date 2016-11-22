package wurmatron.voidrpg.common.network.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import wurmatron.voidrpg.common.cube.StringCube;
import wurmatron.voidrpg.common.cube.StringCubeCreatorRecipe;
import wurmatron.voidrpg.common.network.CustomMessage;

import java.io.IOException;

public class RequestServerData extends CustomMessage.AbstractClientMessage <RequestServerData> {

	private NBTTagCompound data;

	// Does not have data
	public RequestServerData () {
		NBTTagCompound nbt = new NBTTagCompound();
		data = nbt;
	}

	// Has hashes to send
	public RequestServerData (StringCube[] cubes, StringCubeCreatorRecipe[] recipes) {
	}

	@Override
	protected void read (PacketBuffer buff) throws IOException {
		data = buff.readNBTTagCompoundFromBuffer();
	}

	@Override
	protected void write (PacketBuffer buff) throws IOException {
		buff.writeNBTTagCompoundToBuffer(data);
	}

	@Override
	public void process (EntityPlayer player, Side side) {
		if (data != null) {
			// No Hashes (Needs to be sent
			if (data.hasNoTags()) {

			}
			// Has something check the hashes
			else {}
		} else
			((EntityPlayerMP) player).connection.kickPlayerFromServer("Error reciving client data!");
	}
}
