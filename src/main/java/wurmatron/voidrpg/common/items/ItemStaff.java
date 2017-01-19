package wurmatron.voidrpg.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.utils.BitHelper;
import wurmatron.voidrpg.common.utils.DataHelper;

public class ItemStaff extends Item {

    public ItemStaff() {
        setCreativeTab(VoidRPG.tabVoidRPG);
        setMaxStackSize(1);
        setHasSubtypes(true);
        setUnlocalizedName("creationStaff");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        RayTraceResult ray = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(5, 1);
        if (ray != null && world.getBlockState(ray.getBlockPos()).getBlock() != Blocks.AIR) {
            if (BitHelper.hasValidModel(world, ray.getBlockPos(), BitHelper.modelHead.toArray(new Vec3i[0]))) {
                CubeData[] data = BitHelper.getDataFromModel(world, ray.getBlockPos(), BitHelper.modelHead.toArray(new Vec3i[0]), 16, 16, 16, new Vec3i(0, 0, 0));
                ItemStack item = DataHelper.addDataToStack(new ItemStack(VoidRPGItems.armorHelmet, 1, 0), data);
                player.inventory.addItemStackToInventory(item);
            }
        }
        return new ActionResult(EnumActionResult.PASS, stack);
    }

}
