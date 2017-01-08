package wurmatron.voidrpg.common.event;


import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.items.ItemModelArmor;
import wurmatron.voidrpg.common.utils.DataHelper;

public class LivingTickEvent {

    @SubscribeEvent
    public void onPlayerTick(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntityLiving().worldObj.getBlockState(e.getEntityLiving().getPosition().add(0, -1, 0)).getBlock().equals(Blocks.WATER) && e.getEntityLiving().getArmorInventoryList().iterator().hasNext() && !e.getEntityLiving().isSneaking()) {
            for (ItemStack helmet : e.getEntityLiving().getArmorInventoryList())
                if (helmet != null && helmet.hasTagCompound() && helmet.getItem() instanceof ItemModelArmor) {
                    CubeData[] specialCubes = DataHelper.getEffectCubes(helmet);
                    if (specialCubes.length > 0)
                        for (CubeData cube : specialCubes)
                            if (cube != null && cube.cube != null && cube.cube.getName().equals("waterWalk")) {
                                e.getEntityLiving().motionY = 0;
                                e.getEntityLiving().fallDistance = 0;
                                e.getEntityLiving().onGround = true;
                            }
                }
        }
    }
}
