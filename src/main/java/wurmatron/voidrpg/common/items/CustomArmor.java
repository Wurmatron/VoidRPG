package wurmatron.voidrpg.common.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.api.event.CubeBreakEvent;
import wurmatron.voidrpg.api.event.CubeTickEvent;
import wurmatron.voidrpg.client.events.PlayerTickHandlerClient;
import wurmatron.voidrpg.client.model.ArmorModel;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.ArmorHelper;
import wurmatron.voidrpg.common.utils.LogHandler;

import java.util.List;

public class CustomArmor extends ItemArmor implements ISpecialArmor {

		private static ArmorModel modelPlayer;
		private boolean requiresUpdate = false;

		public CustomArmor (ArmorMaterial mat, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
				super(mat, renderIndexIn, equipmentSlotIn);
				setUnlocalizedName("armor_" + equipmentSlotIn.name());
				setCreativeTab(VoidRPG.tabVoidRPG);
		}

		@SideOnly (Side.CLIENT)
		@Override
		public ModelBiped getArmorModel (EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot slot, ModelBiped model) {
				if (entity instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer) entity;
						if (modelPlayer == null || requiresUpdate) {
								modelPlayer = new ArmorModel();
								requiresUpdate = false;
						}
						if (PlayerTickHandlerClient.armorData.get(player.getGameProfile().getId())) {
								modelPlayer = new ArmorModel();
						}
						if (stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags()) {
								if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
										NBTTagCompound data = stack.getTagCompound();
										int amt = data.getInteger(NBT.AMOUNT);
										if (amt != 0 && modelPlayer.bipedHead.childModels == null) {
												for (int a = 0; a < amt; a++) {
														NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(a));
														ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
														if (cube != null)
																modelPlayer.bipedHead.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getInteger(NBT.OFFSETX) - 8, temp.getInteger(NBT.OFFSETY) - 14, temp.getInteger(NBT.OFFSETZ) - 8, cube, temp.getInteger(NBT.DAMAGE))));
												}
										}
								}
						} else {
								return modelPlayer;
						}
						return modelPlayer;
				}
				return null;
		}

		@SideOnly (Side.CLIENT)
		@Override
		public String getArmorTexture (ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
				if (slot.equals(EntityEquipmentSlot.HEAD) || slot.equals(EntityEquipmentSlot.CHEST) || slot.equals(EntityEquipmentSlot.FEET)) {
						return Global.MODID + ":" + "textures/test/armor.png";
				}
				if (slot.equals(EntityEquipmentSlot.LEGS)) {
						return Global.MODID + ":" + "textures/test/armor2.png";
				}
				return super.getArmorTexture(stack, entity, slot, type);
		}

		@Override
		public void getSubItems (Item item, CreativeTabs tab, List<ItemStack> sub) {
		}

		@Override
		public ArmorProperties getProperties (EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
				return new ArmorProperties(1, 1, 1);
		}

		@Override
		public int getArmorDisplay (EntityPlayer player, ItemStack armor, int slot) {
				return 0;
		}

		@Override
		public void damageArmor (EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
				if (stack.getTagCompound() != null && stack.getTagCompound().hasKey(NBT.AMOUNT)) {
						if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
								int amount = stack.getTagCompound().getInteger(NBT.AMOUNT);
								for (int a = 0; a <= amount; a++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(a));
										LogHandler.info("d: " + (stack.getTagCompound().getCompoundTag(Integer.toString(a))).getInteger(NBT.DAMAGE));
										temp.setInteger(NBT.DAMAGE, temp.getInteger(NBT.DAMAGE) + damage);
										stack.getTagCompound().removeTag(Integer.toString(a));
										stack.getTagCompound().setTag(Integer.toString(a),temp);
										LogHandler.info("d: " + (stack.getTagCompound().getCompoundTag(Integer.toString(a))).getInteger(NBT.DAMAGE));
										ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
										if (cube != null)
												if (temp.getInteger(NBT.DAMAGE) >= cube.getDurability()) {
														CubeBreakEvent event = new CubeBreakEvent(new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETZ), cube, temp.getInteger(NBT.DAMAGE)), entity, stack);
														MinecraftForge.EVENT_BUS.post(event);
														if (!event.isCanceled()) {
																stack.getTagCompound().removeTag(Integer.toString(a));
																requiresUpdate = true;
														}
												}
								}
						}
				}
		}

		@Override
		public void onArmorTick (World world, EntityPlayer player, ItemStack stack) {
				if (stack.getTagCompound() != null && stack.getTagCompound().hasKey(NBT.AMOUNT)) {
						if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
								int amount = stack.getTagCompound().getInteger(NBT.AMOUNT);
								for (int a = 0; a <= amount; a++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(a));
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETZ), CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE)), temp.getInteger(NBT.DAMAGE)), player, stack));
								}
						}
				}
		}

		@Override
		public void addInformation (ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
				double weight = ArmorHelper.getArmorWeight(stack);
				int complexity = ArmorHelper.getArmorComplexity(stack);
				double damage = ArmorHelper.calculateArmorDamage(stack);
				tip.add(ChatFormatting.AQUA + I18n.format("stat.weight.name") + ": " + ArmorHelper.getArmorWeightColor(weight, stack.getItem()) + weight);
				tip.add(ChatFormatting.GOLD + I18n.format("stat.complexity.name") + ": " + ArmorHelper.getComplexityColor(complexity, stack.getItem()) + complexity);
				tip.add(ChatFormatting.RED + I18n.format("stat.durability.name") + ": " + ArmorHelper.getDamageColor(damage) + damage);
		}
}
