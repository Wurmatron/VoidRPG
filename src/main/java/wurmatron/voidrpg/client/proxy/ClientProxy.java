package wurmatron.voidrpg.client.proxy;

import net.minecraftforge.common.MinecraftForge;
import wurmatron.voidrpg.client.events.PlayerTickHandlerClient;
import wurmatron.voidrpg.common.proxy.CommonProxy;

public class ClientProxy extends CommonProxy {

		@Override
		public void register () {
				super.register();
				MinecraftForge.EVENT_BUS.register(new PlayerTickHandlerClient());
		}
}
