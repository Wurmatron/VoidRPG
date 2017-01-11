package wurmatron.voidrpg.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.common.utils.BitHelper;

import java.util.ArrayList;

public class ItemModelPlacer extends Item {
    public ItemModelPlacer() {
        setCreativeTab(VoidRPG.tabVoidRPG);
        setUnlocalizedName("modelPlacer");
    }

    @Override
    public EnumActionResult onItemUse (ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            ArrayList<Vec3i> modelHead = new ArrayList<>();
            for (int x = 0; x < 15; x++)
                for (int y = 0; y < 15; y++)
                    for (int z = 0; z < 15; z++)
                        if (x <= 12 && x > 4 && y <= 8 && z <= 12 && z > 4)
                            modelHead.add(new Vec3i(x, y, z));
            BitHelper.createBaseArmorBlock(modelHead.toArray(new Vec3i[0]), world,pos);
        }
        return EnumActionResult.FAIL;
    }
}
