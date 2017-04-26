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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.client.model.ArmorModel;
import wurmatron.voidrpg.common.reference.Local;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.DataHelper;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ItemModelArmor extends ItemArmor {

		@SideOnly(Side.CLIENT)
		private ArmorModel armorModel;

		public boolean requiresUpdate = true;

		public ItemModelArmor(ArmorMaterial material, int index, EntityEquipmentSlot slot) {
				super(material, index, slot); setCreativeTab(VoidRPG.tabVoidRPG);
				setUnlocalizedName("armor" + slot.name().toLowerCase());
		}

		@SideOnly(Side.CLIENT)
		@Override
		public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot slot, ModelBiped _default) {
				if (requiresUpdate) if (armorModel != null) {
						if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
								armorModel.addHeadCubes(DataHelper.rotateUp(DataHelper.getDataFromStack(stack)));
								armorModel.handleData(_default); requiresUpdate = false;
						} if (stack.getItem().equals(VoidRPGItems.armorLeggings)) {
								armorModel.addLeftLegCubes(DataHelper.rotateUp(DataHelper.getDataFromStack(stack)));
								armorModel.handleData(_default); requiresUpdate = false;
						} if (stack.getItem().equals(VoidRPGItems.armorChestplate)) {
								armorModel.addBodyCubes(DataHelper.addOffset(DataHelper.rotateUp(DataHelper.getDataFromStack(stack, NBT.BODY)), 1, 11, 1));
								armorModel.addLeftArmCubes(DataHelper.addOffset(DataHelper.rotateUp(DataHelper.getDataFromStack(stack, NBT.LEFT_ARM)), 2, 9, 1));
								armorModel.addRightArmCubes(DataHelper.addOffset(DataHelper.rotateUp(DataHelper.getDataFromStack(stack, NBT.RIGHT_ARM)), 0, 9, 1));
								armorModel.handleData(_default); requiresUpdate = false;
						} if (stack.getItem().equals(VoidRPGItems.armorLeggings)) {
								armorModel.addLeftLegCubes(DataHelper.addOffset(DataHelper.rotateUp(DataHelper.getDataFromStack(stack, NBT.LEFT_LEG)), 0, 11, 0));
								armorModel.addRightLegCubes(DataHelper.addOffset(DataHelper.rotateUp(DataHelper.getDataFromStack(stack, NBT.RIGHT_LEG)), 0, 11, 0));
								armorModel.handleData(_default); requiresUpdate = false;
						} if (stack.getItem().equals(VoidRPGItems.armorBoots)) {
								armorModel.addLeftBootCubes(DataHelper.addOffset(DataHelper.rotateUp(DataHelper.getDataFromStack(stack, NBT.LEFT_BOOT)), 0, 0, 0));
								armorModel.addRightBootCubes(DataHelper.addOffset(DataHelper.rotateUp(DataHelper.getDataFromStack(stack, NBT.RIGHT_BOOT)), 0, 0, 0));
								armorModel.handleData(_default); requiresUpdate = false;
						}
				} else {
						armorModel = new ArmorModel(); requiresUpdate = true;
				} if (entity.getActivePotionEffect(Potion.getPotionFromResourceLocation("invisibility")) == null) return armorModel;
				return null;
		}

		@Override
		public void addInformation(ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
				tip.add(TextFormatting.GRAY + I18n.translateToLocal(Local.STAT_WEIGHT) + ": " + TextFormatting.AQUA + DataHelper.getWeight(stack, false));
				int maxDurability = DataHelper.getMaxDurability(stack, false); if (maxDurability <= 0) maxDurability = 1;
				tip.add(TextFormatting.GRAY + I18n.translateToLocal(Local.STAT_DURABILITY) + ": " + TextFormatting.AQUA + (DataHelper.getDurability(stack, false) / maxDurability) * 100 + "%");
				tip.add(TextFormatting.GRAY + I18n.translateToLocal(Local.STAT_COMPLEXITY) + ": " + TextFormatting.AQUA + (DataHelper.getComplexity(stack, false)));
				if (Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
						HashMap<ICube, Integer> data = new HashMap<>(); for (CubeData f : DataHelper.getDataFromStack(stack)) {
								if (f != null && data.containsKey(f.cube)) {
										int count = data.get(f.cube); data.remove(f.cube); count++; data.put(f.cube, count);
								} else if (f != null) data.put(f.cube, 1);
						}
						tip.addAll(data.keySet().stream().map(g -> data.get(g) + "x " + I18n.translateToLocal(g.getName())).collect(Collectors.toList()));
						tip.add("");
				} else tip.add(TextFormatting.AQUA + I18n.translateToLocal(Local.HOLD_CTRL));
		}
}
