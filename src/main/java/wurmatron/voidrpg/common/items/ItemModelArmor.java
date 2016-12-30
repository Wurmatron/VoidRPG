package wurmatron.voidrpg.common.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.client.model.ArmorModel;
import wurmatron.voidrpg.common.utils.DataHelper;
import wurmatron.voidrpg.common.utils.LogHandler;

public class ItemModelArmor extends ItemArmor {

    private ArmorModel armorModel;
    public boolean requiresUpdate = true;

    public ItemModelArmor(ArmorMaterial material, int index, EntityEquipmentSlot slot) {
        super(material, index, slot);
        setCreativeTab(VoidRPG.tabVoidRPG);
        setUnlocalizedName("armor" + slot.name().toLowerCase());
    }

    @Override
    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot slot, ModelBiped _default) {
        if (armorModel == null) {
            armorModel = new ArmorModel();
            requiresUpdate = true;
        }
        CubeData[] data = DataHelper.getDataFromStack(stack);
        LogHandler.info("data: " + data.length);
        armorModel.addHeadCubes(data);
//        armorModel.addHeadCubes(new CubeData[]{new CubeData(CubeRegistry.getCubeFromID(0), 2, 2, 2, 0), new CubeData(CubeRegistry.getCubeFromID(0), 8, 8, 8, 0)});
        if (requiresUpdate) {
            armorModel.handleData(_default);
            requiresUpdate = false;
        }
        return armorModel;
    }
}
