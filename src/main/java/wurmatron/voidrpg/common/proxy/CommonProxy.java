package wurmatron.voidrpg.common.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy {

	public void register () {
	}

	public EntityPlayer getPlayerEntity (MessageContext ctx) {
		return ctx.getServerHandler ().player;
	}

	public IThreadListener getThreadFromContext (MessageContext ctx) {
		return ctx.getServerHandler ().player.getServer ();
	}
}
