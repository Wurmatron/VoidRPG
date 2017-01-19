package wurmatron.voidrpg.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.common.reference.Local;
import wurmatron.voidrpg.common.utils.BitHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemModelPlacer extends Item {

    public ItemModelPlacer() {
        setCreativeTab(VoidRPG.tabVoidRPG);
        setUnlocalizedName("modelPlacer");
        setHasSubtypes(true);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            switch (stack.getItemDamage()) {
                case (0): {
                    ArrayList<Vec3i> modelHead = new ArrayList<>();
                    for (int x = 0; x < 15; x++)
                        for (int y = 0; y < 15; y++)
                            for (int z = 0; z < 15; z++)
                                if (x <= 12 && x > 4 && y <= 8 && z <= 12 && z > 4)
                                    modelHead.add(new Vec3i(x, y, z));
                    BitHelper.createBaseArmorBlock(modelHead.toArray(new Vec3i[0]), world, pos);
                    return EnumActionResult.SUCCESS;
                }
                case (2): {
                    ArrayList<Vec3i> modelLegs = new ArrayList<>();
                    for (int x = 4; x < 12; x++)
                        for (int y = 0; y < 9; y++)
                            for (int z = 6; z < 10; z++)
                                modelLegs.add(new Vec3i(x, y, z));
                    BitHelper.createBaseArmorBlock(modelLegs.toArray(new Vec3i[0]), world, pos);
                    return EnumActionResult.SUCCESS;
                }
                case(3): {
                    ArrayList<Vec3i> modelBoots = new ArrayList<>();
                    for (int x = 4; x < 12; x++)
                        for (int y = 0; y < 5; y++)
                            for (int z = 6; z < 10; z++)
                                modelBoots.add(new Vec3i(x, y, z));
                    BitHelper.createBaseArmorBlock(modelBoots.toArray(new Vec3i[0]), world, pos);
                    return EnumActionResult.SUCCESS;
                }
                default: return EnumActionResult.PASS;
            }
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
        tip.add(I18n.translateToLocal(Local.MODEL_TYPE) + ": " + getNameFromMeta(stack.getItemDamage()));
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> sub) {
        for (int i = 0; i <= 3; i++)
            sub.add(new ItemStack(item, 1, i));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.translateToLocal(stack.getUnlocalizedName() + "_" + stack.getItemDamage() + ".name");
    }

    public static String getNameFromMeta(int meta) {
        switch (meta) {
            case (0):
                return "Helmet";
            case (1):
                return "ChestPlate";
            case (2):
                return "Leggings";
            case (3):
                return "Boots";
            default:
                return "Invaid";
        }
    }
}
