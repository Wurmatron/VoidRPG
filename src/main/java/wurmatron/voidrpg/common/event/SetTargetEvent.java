package wurmatron.voidrpg.common.event;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wurmatron.voidrpg.common.items.ItemModelArmor;
import wurmatron.voidrpg.common.reference.NBT;

public class SetTargetEvent {

	@SubscribeEvent
	public void onEntitySetTarget (LivingSetAttackTargetEvent e) {
		if (e.getTarget () != null && e.getTarget () instanceof EntityPlayer && e.getEntityLiving ().getLastAttacker () != e.getTarget ()) {
			EntityPlayer player = (EntityPlayer) e.getTarget ();
			if (player.inventory != null && player.inventory.armorInventory != null)
				for (ItemStack stack : player.inventory.armorInventory)
					if (stack != null && stack.getItem () instanceof ItemModelArmor && stack.hasTagCompound () && !stack.getTagCompound ().hasNoTags () && stack.getTagCompound ().hasKey (NBT.CAPABILITIES)) {
						NBTTagCompound capabilities = stack.getTagCompound ().getCompoundTag (NBT.CAPABILITIES);
						if (capabilities.getBoolean ("mobStealth"))
							((EntityLiving) e.getEntity ()).setAttackTarget (null);
					}
		}
	}
}
