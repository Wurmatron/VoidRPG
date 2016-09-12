package wurmatron.voidrpg.client.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import wurmatron.voidrpg.client.events.PlayerTickHandlerClient;
import wurmatron.voidrpg.common.proxy.CommonProxy;
import wurmatron.voidrpg.common.reference.Global;

import java.util.ArrayList;

public class ClientProxy extends CommonProxy {

		public static ArrayList<Item> items = new ArrayList();

		@Override
		public void register () {
				super.register();
				MinecraftForge.EVENT_BUS.register(new PlayerTickHandlerClient());
				for (Item item : items)
						Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Global.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		}
}
