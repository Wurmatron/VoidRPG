package wurmatron.voidrpg.client.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import wurmatron.voidrpg.client.events.PlayerTickHandlerClient;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.proxy.CommonProxy;
import wurmatron.voidrpg.common.reference.Global;

import java.util.ArrayList;

public class ClientProxy extends CommonProxy {

		public static ArrayList<Item> items = new ArrayList();
		public static ArrayList<Block> blocks = new ArrayList();

		@Override
		public void register () {
				super.register();
				MinecraftForge.EVENT_BUS.register(new PlayerTickHandlerClient());
				for (Item item : items)
						createModel(item);
				for (Block block : blocks)
						createModel(block);
				registerItemModels();
		}

		public static void createModel (Item item) {
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Global.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		}

		public static void createModel (Block item) {
				ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(item), 0, new ModelResourceLocation(Global.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		}

		public void registerItemModels () {
				ModelLoader.setCustomModelResourceLocation(VoidRPGItems.itemModelPlacer, 0, new ModelResourceLocation(Global.MODID + ":boots", "inventory"));
				ModelLoader.setCustomModelResourceLocation(VoidRPGItems.itemModelPlacer, 1, new ModelResourceLocation(Global.MODID + ":leggings", "inventory"));
				ModelLoader.setCustomModelResourceLocation(VoidRPGItems.itemModelPlacer, 2, new ModelResourceLocation(Global.MODID + ":chestplate", "inventory"));
				ModelLoader.setCustomModelResourceLocation(VoidRPGItems.itemModelPlacer, 3, new ModelResourceLocation(Global.MODID + ":helmet", "inventory"));
				for (int s = 0; s < VoidRPGItems.materials.length; s++)
						ModelLoader.setCustomModelResourceLocation(VoidRPGItems.itemMaterial, s, new ModelResourceLocation(Global.MODID + ":" + VoidRPGItems.materials[s], "inventory"));
				for (int s = 0; s < VoidRPGItems.upgrades.length; s++)
						ModelLoader.setCustomModelResourceLocation(VoidRPGItems.itemUpgrade, s, new ModelResourceLocation(Global.MODID + ":" + VoidRPGItems.upgrades[s], "inventory"));

		}
}
