package wurmatron.voidrpg.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.utils.BitHelper;

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
        Vec3i[] vec = new Vec3i[] {new Vec3i(1,1,1), new Vec3i(2,1,1)};
        ArrayList<CubeData> data = BitHelper.createDataFromModel(world,player.getPosition().add(0,-2,0),vec,4);
        return new ActionResult(EnumActionResult.PASS, stack);
    }
}
