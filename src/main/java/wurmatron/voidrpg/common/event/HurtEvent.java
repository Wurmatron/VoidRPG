package wurmatron.voidrpg.common.event;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wurmatron.voidrpg.common.items.ItemModelArmor;
import wurmatron.voidrpg.common.reference.NBT;

import java.util.List;


public class HurtEvent {

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent e) {
        if (e.getEntityLiving().getArmorInventoryList().iterator().hasNext() && !e.getEntityLiving().isSneaking()) {
            for (ItemStack stack : e.getEntityLiving().getArmorInventoryList()) {
                if (stack != null && stack.getItem() instanceof ItemModelArmor && stack.hasTagCompound() && !stack.getTagCompound().hasNoTags() && stack.getTagCompound().hasKey(NBT.CAPABILITIES)) {
                    NBTTagCompound capabilities = stack.getTagCompound().getCompoundTag(NBT.CAPABILITIES);
                    if (capabilities.getBoolean("life")) {
                        List<Entity> entities = e.getEntityLiving().worldObj.getEntitiesWithinAABBExcludingEntity(e.getEntityLiving(), e.getEntityLiving().getEntityBoundingBox().expand(10, 10, 10));
                        for (Entity ent : entities)
                            if (ent != null && ent instanceof EntityLivingBase) {
                                EntityLivingBase entity = (EntityLivingBase) ent;
                                if (!(entity instanceof EntityPlayer) && e.getEntityLiving().getHealth() != e.getEntityLiving().getMaxHealth()) {
                                    float amountToHeal = e.getEntityLiving().getMaxHealth() - e.getEntityLiving().getHealth();
                                    if (amountToHeal <= entity.getHealth()) {
                                        entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(e.getEntityLiving(), entity), amountToHeal);
                                        e.getEntityLiving().heal(amountToHeal);
                                    } else {
                                        e.getEntityLiving().heal(entity.getHealth());
                                        entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(e.getEntityLiving(), entity), entity.getHealth());
                                    }
                                }
                            }
                    }
                }
            }
        }
    }
}
