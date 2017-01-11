package wurmatron.voidrpg.common.event;


import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.cube.special.legs.CubeMuscle;
import wurmatron.voidrpg.common.items.ItemModelArmor;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.DataHelper;

public class LivingTickEvent {

    @SubscribeEvent
    public void onPlayerTick(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntityLiving().getArmorInventoryList().iterator().hasNext() && !e.getEntityLiving().isSneaking()) {
            for (ItemStack stack : e.getEntityLiving().getArmorInventoryList()) {
                if (stack != null && stack.getItem() instanceof ItemModelArmor && stack.hasTagCompound()) {
                    if (!stack.getTagCompound().hasNoTags() && stack.getTagCompound().hasKey(NBT.CAPABILITIES)) {
                        NBTTagCompound capabilities = stack.getTagCompound().getCompoundTag(NBT.CAPABILITIES);
                        if (e.getEntityLiving().worldObj.getBlockState(e.getEntityLiving().getPosition().add(0, -1, 0)).getBlock().equals(Blocks.WATER) && capabilities.hasKey("waterWalk") && capabilities.getBoolean("waterWalk") || capabilities.hasKey("waterWalk") && capabilities.getBoolean("waterWalk") && e.getEntityLiving().isInWater()) {
                            e.getEntityLiving().motionY = 0;
                            e.getEntityLiving().onGround = true;
                            if (e.getEntityLiving().fallDistance > 3)
                                e.getEntityLiving().fallDistance = 0;
                        }
                        if (capabilities.getBoolean("nightVision") && e.getEntityLiving().getActivePotionEffect(Potion.getPotionFromResourceLocation("night_vision")) != null && e.getEntityLiving().getActivePotionEffect(Potion.getPotionFromResourceLocation("night_vision")).getDuration() <= 200 || capabilities.getBoolean("nightVision") && e.getEntityLiving().getActivePotionEffect(Potion.getPotionFromResourceLocation("night_vision")) == null)
                            e.getEntityLiving().addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("night_vision"), 400));
                        if (capabilities.getBoolean("muscle"))
                            e.getEntityLiving().moveEntityWithHeading(e.getEntityLiving().moveStrafing, e.getEntityLiving().moveForward * CubeMuscle.MOVEMENT_SPEED);
                        if (capabilities.getBoolean("waterBreathing") && e.getEntityLiving().getActivePotionEffect(Potion.getPotionFromResourceLocation("water_breathing")) != null && e.getEntityLiving().getActivePotionEffect(Potion.getPotionFromResourceLocation("water_breathing")).getDuration() <= 200 || capabilities.getBoolean("water_breathing") && e.getEntityLiving().getActivePotionEffect(Potion.getPotionFromResourceLocation("water_breathing")) == null)
                            e.getEntityLiving().addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("water_breathing"), 400));
                    } else if (!stack.getTagCompound().hasNoTags()) {
                        NBTTagCompound capabilities = new NBTTagCompound();
                        CubeData[] specialCubes = DataHelper.getEffectCubes(stack);
                        if (specialCubes.length > 0) {
                            for (CubeData data : specialCubes) {
                                // Check for Valid Prerequisites for cube to work here
                                if (data != null && data.cube != null)
                                    capabilities.setBoolean(data.cube.getName(), true);
                            }
                            stack.getTagCompound().setTag(NBT.CAPABILITIES, capabilities);
                        }
                    }
                }
            }
        }
    }
}
