package wurmatron.voidrpg.common.event;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wurmatron.voidrpg.common.items.ItemModelArmor;
import wurmatron.voidrpg.common.reference.NBT;

public class FallEvent {

	@SubscribeEvent
	public void onFall (LivingFallEvent e) {
		if (e.getDistance () <= 3 && e.getEntityLiving ().getArmorInventoryList ().iterator ().hasNext ())
			for (ItemStack stack : e.getEntityLiving ().getArmorInventoryList ()) {
				if (stack != null && stack.getItem () instanceof ItemModelArmor && stack.hasTagCompound () && !stack.getTagCompound ().hasNoTags () && stack.getTagCompound ().hasKey (NBT.CAPABILITIES)) {
					NBTTagCompound capabilities = stack.getTagCompound ().getCompoundTag (NBT.CAPABILITIES);
					if (capabilities.getBoolean ("shock")) {
						e.setCanceled (true);
					}
				}
			}
	}
}
