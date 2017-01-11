package wurmatron.voidrpg.common.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.client.model.ArmorModel;
import wurmatron.voidrpg.common.reference.Local;
import wurmatron.voidrpg.common.utils.DataHelper;

import java.util.List;

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
        if (requiresUpdate)
            if (armorModel != null) {
                armorModel.addHeadCubes(DataHelper.rotateUp(DataHelper.getDataFromStack(stack)));
                armorModel.handleData(_default);
                requiresUpdate = false;
            } else {
                armorModel = new ArmorModel();
                requiresUpdate = true;
            }
        if (entity.getActivePotionEffect(Potion.getPotionFromResourceLocation("invisibility")) == null)
            return armorModel;
        return null;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
        tip.add(TextFormatting.GRAY + I18n.translateToLocal(Local.STAT_WEIGHT) + ": " + TextFormatting.AQUA + DataHelper.getWeight(stack, false));
        int maxDurability = DataHelper.getMaxDurability(stack, false);
        if (maxDurability <= 0)
            maxDurability = 1;
        tip.add(TextFormatting.GRAY + I18n.translateToLocal(Local.STAT_DURABILITY) + ": " + TextFormatting.AQUA + (DataHelper.getDurability(stack, false) / maxDurability) * 100 + "%");
        tip.add(TextFormatting.GRAY + I18n.translateToLocal(Local.STAT_COMPLEXITY) + ": " + TextFormatting.AQUA + (DataHelper.getComplexity(stack, false)));
    }
}
