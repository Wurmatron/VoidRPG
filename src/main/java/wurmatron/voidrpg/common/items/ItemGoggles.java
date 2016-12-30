package wurmatron.voidrpg.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.utils.BitHelper;
import wurmatron.voidrpg.common.utils.DataHelper;

import java.util.ArrayList;

public class ItemGoggles extends ItemArmor {

    public ItemGoggles() {
        super(ArmorMaterial.CHAIN, 0, EntityEquipmentSlot.HEAD);
        setCreativeTab(VoidRPG.tabVoidRPG);
        setUnlocalizedName("goggles");
        setMaxStackSize(1);
        setMaxDamage(1200);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "voidrpg:textures/models/goggles.png";
    }

    // TODO Create its own item
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        Vec3i[] vec = new Vec3i[]{new Vec3i(1, 1, 1), new Vec3i(2, 1, 1)};
        RayTraceResult ray = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(5, 1);
        if (ray != null && world.getBlockState(ray.getBlockPos()).getBlock() != Blocks.AIR) {
            ArrayList<CubeData> data = BitHelper.createDataFromModel(world, ray.getBlockPos(), vec, 4);
            ItemStack item = DataHelper.addDataToStack(new ItemStack(VoidRPGItems.armorHelmet,1,0),DataHelper.addOffset(data.toArray(new CubeData[0]), 0 ,20,0));
            player.addChatMessage(new TextComponentString("Size: " + data.size()));
            player.inventory.addItemStackToInventory(item);
        }
        return new ActionResult(EnumActionResult.PASS, stack);
    }
}
