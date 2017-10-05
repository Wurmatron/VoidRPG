package wurmatron.voidrpg.client.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import wurmatron.voidrpg.common.items.ItemModelPlacer;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.proxy.CommonProxy;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.Registry;
import wurmatron.voidrpg.common.utils.LogHandler;

public class ClientProxy extends CommonProxy {

	@Override
	public void register () {
		super.register ();
	}

	@SubscribeEvent
	public void model (ModelRegistryEvent e) {
		for (Item item : Registry.items)
			createModel (item);
		for (Item blockItem : Registry.blockItems.values ())
			createModel (blockItem);
		for (Block block : Registry.blocks)
			createModel (block);
		registerItemModels ();
	}

	public static void createModel (Item item) {
		ModelLoader.setCustomModelResourceLocation (item,0,new ModelResourceLocation (item.getRegistryName ().toString (),"inventory"));
	}

	public static void createModel (Block block) {
		ModelLoader.setCustomModelResourceLocation (Registry.blockItems.get (block),0,new ModelResourceLocation (block.getRegistryName ().toString (),"inventory"));
	}

	public void registerItemModels () {
		for (int s = 0; s < VoidRPGItems.materials.length; s++)
			ModelLoader.setCustomModelResourceLocation (VoidRPGItems.itemMaterial,s,new ModelResourceLocation (Global.MODID + ":" + VoidRPGItems.materials[s],"inventory"));
		for (int s = 0; s < VoidRPGItems.upgrades.length; s++)
			ModelLoader.setCustomModelResourceLocation (VoidRPGItems.itemUpgrade,s,new ModelResourceLocation (Global.MODID + ":" + VoidRPGItems.upgrades[s],"inventory"));
		for (int s = 0; s < 4; s++)
			ModelLoader.setCustomModelResourceLocation (VoidRPGItems.modelPlacer,s,new ModelResourceLocation (Global.MODID + ":" + ItemModelPlacer.getNameFromMeta (s).toLowerCase (), "inventory"));
		ModelLoader.setCustomModelResourceLocation (VoidRPGItems.itemStaff,0,new ModelResourceLocation (Global.MODID + ":" + "creationstaff","inventory"));
	}

	@Override
	public EntityPlayer getPlayerEntity (MessageContext ctx) {
		return (ctx.side.isClient () ? Minecraft.getMinecraft ().player : super.getPlayerEntity (ctx));
	}

	@Override
	public IThreadListener getThreadFromContext (MessageContext ctx) {
		return (ctx.side.isClient () ? Minecraft.getMinecraft () : super.getThreadFromContext (ctx));
	}
}
