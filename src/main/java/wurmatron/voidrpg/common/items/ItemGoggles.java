package wurmatron.voidrpg.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.common.reference.Global;

public class ItemGoggles extends ItemArmor {

	public ItemGoggles () {
		super (ArmorMaterial.CHAIN,0,EntityEquipmentSlot.HEAD);
		setCreativeTab (VoidRPG.tabVoidRPG);
		setMaxStackSize (1);
		setMaxDamage (1200);
	}

	@Override
	public String getArmorTexture (ItemStack stack,Entity entity,EntityEquipmentSlot slot,String type) {
		return Global.MODID + ":textures/models/goggles.png";
	}
}
