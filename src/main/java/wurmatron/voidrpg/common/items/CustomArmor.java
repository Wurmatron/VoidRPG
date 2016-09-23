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
import wurmatron.voidrpg.api.event.CubeTickEvent;
import wurmatron.voidrpg.client.events.PlayerTickHandlerClient;
import wurmatron.voidrpg.client.model.ArmorModel;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.Local;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.ArmorHelper;

import java.util.List;

public class CustomArmor extends ItemArmor implements ISpecialArmor {

		private static ArmorModel modelPlayer;
		private boolean requiresUpdate = false;
		private static boolean effects = Settings.cubeEffects;

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
						// TODO Only Render / Add visible cubes
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
								if (stack.getItem().equals(VoidRPGItems.armorChestplate)) {
										NBTTagCompound body = stack.getTagCompound().getCompoundTag(NBT.BODY);
										int bodyAmount = body.getInteger(NBT.AMOUNT);
										if (bodyAmount != 0 && modelPlayer.bipedBody.childModels == null) {
												for (int a = 0; a < bodyAmount; a++) {
														NBTTagCompound temp = body.getCompoundTag(Integer.toString(a));
														ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
														if (cube != null)
																modelPlayer.bipedBody.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETZ), cube, temp.getInteger(NBT.DAMAGE))));
												}
										}
										NBTTagCompound leftArm = stack.getTagCompound().getCompoundTag(NBT.LEFTARM);
										int leftArmAmount = leftArm.getInteger(NBT.AMOUNT);
										if (leftArmAmount != 0 && modelPlayer.bipedLeftArm.childModels == null) {
												for (int a = 0; a < leftArmAmount; a++) {
														NBTTagCompound temp = leftArm.getCompoundTag(Integer.toString(a));
														ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
														if (cube != null)
																modelPlayer.bipedLeftArm.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETZ) - 32, cube, temp.getInteger(NBT.DAMAGE))));
												}
										}
										NBTTagCompound rightArm = stack.getTagCompound().getCompoundTag(NBT.RIGHTARM);
										int rightArmAmount = rightArm.getInteger(NBT.AMOUNT);
										if (rightArmAmount != 0 && modelPlayer.bipedRightArm.childModels == null) {
												for (int a = 0; a < rightArmAmount; a++) {
														NBTTagCompound temp = rightArm.getCompoundTag(Integer.toString(a));
														ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
														if (cube != null)
																modelPlayer.bipedRightArm.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getInteger(NBT.OFFSETX) - 8, temp.getInteger(NBT.OFFSETY) - 14, temp.getInteger(NBT.OFFSETZ) - 8, cube, temp.getInteger(NBT.DAMAGE))));
												}
										}
								} else if (stack.getItem().equals(VoidRPGItems.armorLeggings)) {
										NBTTagCompound rightLeg = stack.getTagCompound().getCompoundTag(NBT.RIGHTLEG);
										int rightLegAmount = rightLeg.getInteger(NBT.AMOUNT);
										if (rightLegAmount != 0 && modelPlayer.bipedRightLeg.childModels == null) {
												for (int a = 0; a < rightLegAmount; a++) {
														NBTTagCompound temp = rightLeg.getCompoundTag(Integer.toString(a));
														ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
														if (cube != null)
																modelPlayer.bipedRightLeg.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getInteger(NBT.OFFSETX) - 7, temp.getInteger(NBT.OFFSETY) + 6, temp.getInteger(NBT.OFFSETZ) - 9, cube, temp.getInteger(NBT.DAMAGE))));
												}
										}
										NBTTagCompound leftLeg = stack.getTagCompound().getCompoundTag(NBT.LEFTLEG);
										int leftLegAmount = leftLeg.getInteger(NBT.AMOUNT);
										if (leftLegAmount != 0 && modelPlayer.bipedLeftLeg.childModels == null) {
												for (int a = 0; a < leftLegAmount; a++) {
														NBTTagCompound temp = leftLeg.getCompoundTag(Integer.toString(a));
														ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
														if (cube != null)
																modelPlayer.bipedLeftLeg.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getInteger(NBT.OFFSETX) - 11, temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETZ) - 9, cube, temp.getInteger(NBT.DAMAGE))));
												}
										}
								} else if (stack.getItem().equals(VoidRPGItems.armorBoots)) {
										NBTTagCompound rightLeg = stack.getTagCompound().getCompoundTag(NBT.RIGHTLEG);
										int rightLegAmount = rightLeg.getInteger(NBT.AMOUNT);
										if (rightLegAmount != 0 && modelPlayer.bipedRightLeg.childModels == null) {
												for (int a = 0; a < rightLegAmount; a++) {
														NBTTagCompound temp = rightLeg.getCompoundTag(Integer.toString(a));
														ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
														if (cube != null)
																modelPlayer.bipedRightLeg.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getInteger(NBT.OFFSETX) - 7, temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETZ) - 9, cube, temp.getInteger(NBT.DAMAGE))));
												}
										}
										NBTTagCompound leftLeg = stack.getTagCompound().getCompoundTag(NBT.LEFTLEG);
										int leftLegAmount = leftLeg.getInteger(NBT.AMOUNT);
										if (leftLegAmount != 0 && modelPlayer.bipedLeftLeg.childModels == null) {
												for (int a = 0; a < leftLegAmount; a++) {
														NBTTagCompound temp = leftLeg.getCompoundTag(Integer.toString(a));
														ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
														if (cube != null)
																modelPlayer.bipedLeftLeg.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getInteger(NBT.OFFSETX) - 11, temp.getInteger(NBT.OFFSETY) + 6, temp.getInteger(NBT.OFFSETZ) - 9, cube, temp.getInteger(NBT.DAMAGE))));
												}
										}
								}
						}
						return modelPlayer;
				}
				return null;
		}

		@SideOnly (Side.CLIENT)
		@Override
		public String getArmorTexture (ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
				if (slot.equals(EntityEquipmentSlot.HEAD) || slot.equals(EntityEquipmentSlot.CHEST) || slot.equals(EntityEquipmentSlot.FEET))
						return Global.MODID + ":" + "textures/armor/armor.png";
				if (slot.equals(EntityEquipmentSlot.LEGS))
						return Global.MODID + ":" + "textures/armor/armor2.png";
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
						// TODO Redo this for all armor types
//						if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
//								int amount = stack.getTagCompound().getInteger(NBT.AMOUNT);
//								for (int a = 0; a <= amount; a++) {
//										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(a));
//										temp.setInteger(NBT.DAMAGE, temp.getInteger(NBT.DAMAGE) + damage);
//										stack.getTagCompound().removeTag(Integer.toString(a));
//										stack.getTagCompound().setTag(Integer.toString(a), temp);
//										ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
//										if (cube != null)
//												if (temp.getInteger(NBT.DAMAGE) >= cube.getDurability()) {
//														CubeBreakEvent event = new CubeBreakEvent(new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETZ), cube, temp.getInteger(NBT.DAMAGE)), entity, stack);
//														MinecraftForge.EVENT_BUS.post(event);
//														if (!event.isCanceled()) {
//																stack.getTagCompound().removeTag(Integer.toString(a));
//																requiresUpdate = true;
//														}
//												}
//								}
//						}
				}
		}

		@Override
		public void onArmorTick (World world, EntityPlayer player, ItemStack stack) {
				if (stack.getTagCompound() != null && stack.getTagCompound().hasKey(NBT.AMOUNT)) {
						if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
								int amount = stack.getTagCompound().getInteger(NBT.AMOUNT);
								CubeData[] data = new CubeData[amount];
								for (int a = 0; a < amount; a++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(a));
										CubeData d = new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETZ), CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE)), temp.getInteger(NBT.DAMAGE));
										final CubeData j = new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETZ), CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE)), temp.getInteger(NBT.DAMAGE));
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(d, player, stack));
										if (d.cube != j.cube)
												temp.setString(NBT.CUBE, d.cube.getUnlocalizedName());
										if (d.offY != j.offY)
												temp.setInteger(NBT.OFFSETY, d.offY);
										if (d.offX != j.offX)
												temp.setInteger(NBT.OFFSETX, d.offX);
										if (d.offZ != j.offZ)
												temp.setInteger(NBT.OFFSETZ, d.offZ);
										if (d.damage != j.damage)
												temp.setInteger(NBT.DAMAGE, d.damage);
										data[a] = d;
								}
								handleCubeUpdates(player, stack, data);
						} else if (stack.getItem().equals(VoidRPGItems.armorChestplate)) {
								int baseAmount = stack.getTagCompound().getCompoundTag(NBT.BODY).getInteger(NBT.AMOUNT);
								int leftArmAmount = stack.getTagCompound().getCompoundTag(NBT.LEFTARM).getInteger(NBT.AMOUNT);
								int rightArmAmount = stack.getTagCompound().getCompoundTag(NBT.RIGHTARM).getInteger(NBT.AMOUNT);
								int counter = 0;
								CubeData[] data = new CubeData[baseAmount + leftArmAmount + rightArmAmount];
								for (int b = 0; b < baseAmount; b++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(NBT.BODY);
										CubeData d = new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETY), CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE)), temp.getInteger(NBT.DAMAGE));
										final CubeData j = d;
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(d, player, stack));
										if (d.cube != j.cube)
												temp.setString(NBT.CUBE, d.cube.getUnlocalizedName());
										if (d.offY != j.offY)
												temp.setInteger(NBT.OFFSETY, d.offY);
										if (d.offX != j.offX)
												temp.setInteger(NBT.OFFSETX, d.offX);
										if (d.offZ != j.offZ)
												temp.setInteger(NBT.OFFSETZ, d.offZ);
										if (d.damage != j.damage)
												temp.setInteger(NBT.DAMAGE, d.damage);
										data[counter] = d;
										counter++;
								}
								for (int b = 0; b < leftArmAmount; b++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(NBT.LEFTARM);
										CubeData d = new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETY), CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE)), temp.getInteger(NBT.DAMAGE));
										final CubeData j = d;
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(d, player, stack));
										if (d.cube != j.cube)
												temp.setString(NBT.CUBE, d.cube.getUnlocalizedName());
										if (d.offY != j.offY)
												temp.setInteger(NBT.OFFSETY, d.offY);
										if (d.offX != j.offX)
												temp.setInteger(NBT.OFFSETX, d.offX);
										if (d.offZ != j.offZ)
												temp.setInteger(NBT.OFFSETZ, d.offZ);
										if (d.damage != j.damage)
												temp.setInteger(NBT.DAMAGE, d.damage);
										data[counter] = d;
										counter++;
								}
								for (int b = 0; b < rightArmAmount; b++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(NBT.RIGHTARM);
										CubeData d = new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETY), CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE)), temp.getInteger(NBT.DAMAGE));
										final CubeData j = d;
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(d, player, stack));
										if (d.cube != j.cube)
												temp.setString(NBT.CUBE, d.cube.getUnlocalizedName());
										if (d.offY != j.offY)
												temp.setInteger(NBT.OFFSETY, d.offY);
										if (d.offX != j.offX)
												temp.setInteger(NBT.OFFSETX, d.offX);
										if (d.offZ != j.offZ)
												temp.setInteger(NBT.OFFSETZ, d.offZ);
										if (d.damage != j.damage)
												temp.setInteger(NBT.DAMAGE, d.damage);
										data[counter] = d;
										counter++;
								}
								handleCubeUpdates(player, stack, data);
						} else if (stack.getItem().equals(VoidRPGItems.armorLeggings)) {
								int leftLegAmount = stack.getTagCompound().getCompoundTag(NBT.LEFTLEG).getInteger(NBT.AMOUNT);
								int rightLegAmount = stack.getTagCompound().getCompoundTag(NBT.RIGHTLEG).getInteger(NBT.AMOUNT);
								int counter = 0;
								CubeData[] data = new CubeData[leftLegAmount + rightLegAmount];
								for (int b = 0; b < leftLegAmount; b++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(NBT.LEFTLEG);
										CubeData d = new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETY), CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE)), temp.getInteger(NBT.DAMAGE));
										final CubeData j = d;
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(d, player, stack));
										if (d.cube != j.cube)
												temp.setString(NBT.CUBE, d.cube.getUnlocalizedName());
										if (d.offY != j.offY)
												temp.setInteger(NBT.OFFSETY, d.offY);
										if (d.offX != j.offX)
												temp.setInteger(NBT.OFFSETX, d.offX);
										if (d.offZ != j.offZ)
												temp.setInteger(NBT.OFFSETZ, d.offZ);
										if (d.damage != j.damage)
												temp.setInteger(NBT.DAMAGE, d.damage);
										data[counter] = d;
										counter++;
								}
								for (int b = 0; b < rightLegAmount; b++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(NBT.RIGHTLEG);
										CubeData d = new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETY), CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE)), temp.getInteger(NBT.DAMAGE));
										final CubeData j = d;
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(d, player, stack));
										if (d.cube != j.cube)
												temp.setString(NBT.CUBE, d.cube.getUnlocalizedName());
										if (d.offY != j.offY)
												temp.setInteger(NBT.OFFSETY, d.offY);
										if (d.offX != j.offX)
												temp.setInteger(NBT.OFFSETX, d.offX);
										if (d.offZ != j.offZ)
												temp.setInteger(NBT.OFFSETZ, d.offZ);
										if (d.damage != j.damage)
												temp.setInteger(NBT.DAMAGE, d.damage);
										data[counter] = d;
										counter++;
								}
								handleCubeUpdates(player, stack, data);
						} else if (stack.getItem().equals(VoidRPGItems.armorBoots)) {
								int leftLegAmount = stack.getTagCompound().getCompoundTag(NBT.LEFTLEG).getInteger(NBT.AMOUNT);
								int rightLegAmount = stack.getTagCompound().getCompoundTag(NBT.RIGHTLEG).getInteger(NBT.AMOUNT);
								int counter = 0;
								CubeData[] data = new CubeData[leftLegAmount + rightLegAmount];
								for (int b = 0; b < leftLegAmount; b++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(NBT.LEFTLEG);
										CubeData d = new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETY), CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE)), temp.getInteger(NBT.DAMAGE));
										final CubeData j = d;
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(d, player, stack));
										if (d.cube != j.cube)
												temp.setString(NBT.CUBE, d.cube.getUnlocalizedName());
										if (d.offY != j.offY)
												temp.setInteger(NBT.OFFSETY, d.offY);
										if (d.offX != j.offX)
												temp.setInteger(NBT.OFFSETX, d.offX);
										if (d.offZ != j.offZ)
												temp.setInteger(NBT.OFFSETZ, d.offZ);
										if (d.damage != j.damage)
												temp.setInteger(NBT.DAMAGE, d.damage);
										data[counter] = d;
										counter++;
								}
								for (int b = 0; b < rightLegAmount; b++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(NBT.RIGHTLEG);
										CubeData d = new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETY), CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE)), temp.getInteger(NBT.DAMAGE));
										final CubeData j = d;
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(d, player, stack));
										if (d.cube != j.cube)
												temp.setString(NBT.CUBE, d.cube.getUnlocalizedName());
										if (d.offY != j.offY)
												temp.setInteger(NBT.OFFSETY, d.offY);
										if (d.offX != j.offX)
												temp.setInteger(NBT.OFFSETX, d.offX);
										if (d.offZ != j.offZ)
												temp.setInteger(NBT.OFFSETZ, d.offZ);
										if (d.damage != j.damage)
												temp.setInteger(NBT.DAMAGE, d.damage);
										data[counter] = d;
										counter++;
								}
								handleCubeUpdates(player, stack, data);
						}
				}
		}

		@Override
		public void addInformation (ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
				double weight = ArmorHelper.getArmorWeight(stack);
				int complexity = ArmorHelper.getArmorComplexity(stack);
				double damage = ArmorHelper.calculateArmorDamage(stack);
				tip.add(ChatFormatting.AQUA + I18n.format(Local.WEIGHT) + ": " + ArmorHelper.getArmorWeightColor(weight, stack.getItem()) + weight);
				tip.add(ChatFormatting.GOLD + I18n.format(Local.COMPLEXITY) + ": " + ArmorHelper.getComplexityColor(complexity, stack.getItem()) + complexity);
				tip.add(ChatFormatting.RED + I18n.format(Local.DURABILITY) + ": " + ArmorHelper.getDamageColor(damage) + damage);
		}

		private void handleCubeUpdates (EntityPlayer player, ItemStack stack, CubeData[] data) {
				if (effects)
						for (int a = 0; a < data.length; a++) {
								NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(a));
								if (!temp.hasNoTags()) {
										CubeData d = new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETZ), CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE)), temp.getInteger(NBT.DAMAGE));
										final CubeData j = d;
										ICube cube = d.cube;
										if (cube != null && cube.hasEffects(player, stack))
												cube.applyEffect(d, data);
										if (d.cube != j.cube)
												temp.setString(NBT.CUBE, d.cube.getUnlocalizedName());
										if (d.offY != j.offY)
												temp.setInteger(NBT.OFFSETY, d.offY);
										if (d.offX != j.offX)
												temp.setInteger(NBT.OFFSETX, d.offX);
										if (d.offZ != j.offZ)
												temp.setInteger(NBT.OFFSETZ, d.offZ);
										if (d.damage != j.damage)
												temp.setInteger(NBT.DAMAGE, d.damage);
								}
						}
		}
}
