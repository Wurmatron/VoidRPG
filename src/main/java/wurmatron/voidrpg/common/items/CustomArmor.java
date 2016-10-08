package wurmatron.voidrpg.common.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.api.event.CubeTickEvent;
import wurmatron.voidrpg.client.events.PlayerTickHandlerClient;
import wurmatron.voidrpg.client.model.ArmorModel;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.ArmorHelper;

public class CustomArmor extends ItemArmor implements ISpecialArmor {

		private ArmorModel modelPlayer;
		private boolean requiresUpdate;
		private boolean update;
		private static final ArmorHelper helper = new ArmorHelper();

		public CustomArmor (ArmorMaterial material, int index, EntityEquipmentSlot slot) {
				super(material, index, slot);
				setUnlocalizedName("armor_" + slot.name().toLowerCase());
		}

		@Override
		public void onArmorTick (World world, EntityPlayer player, ItemStack stack) {
				if (stack != null && stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags()) {
						if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
								for (CubeData cube : helper.getHelmetCubes(stack))
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(cube, player, stack));
								handleCubeUpdates(player, stack, helper.getHelmetCubes(stack));
						} else if (stack.getItem().equals(VoidRPGItems.armorChestplate)) {
								for (CubeData cube : helper.getChestplateCubes(stack, NBT.BODY))
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(cube, player, stack));
								handleCubeUpdates(player, stack, helper.getChestplateCubes(stack, NBT.BODY));
								for (CubeData cube : helper.getChestplateCubes(stack, NBT.LEFTARM))
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(cube, player, stack));
								handleCubeUpdates(player, stack, helper.getChestplateCubes(stack, NBT.LEFTARM));
								for (CubeData cube : helper.getChestplateCubes(stack, NBT.RIGHTARM))
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(cube, player, stack));
								handleCubeUpdates(player, stack, helper.getChestplateCubes(stack, NBT.RIGHTARM));
						} else if (stack.getItem().equals(VoidRPGItems.armorLeggings)) {
								for (CubeData cube : helper.getLeggingsCubes(stack, NBT.LEFTLEG))
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(cube, player, stack));
								handleCubeUpdates(player, stack, helper.getLeggingsCubes(stack, NBT.LEFTLEG));
								for (CubeData cube : helper.getLeggingsCubes(stack, NBT.RIGHTLEG))
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(cube, player, stack));
								handleCubeUpdates(player, stack, helper.getLeggingsCubes(stack, NBT.RIGHTLEG));
						} else if (stack.getItem().equals(VoidRPGItems.armorBoots)) {
								for (CubeData cube : helper.getBootsCubes(stack, NBT.LEFTLEG))
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(cube, player, stack));
								handleCubeUpdates(player, stack, helper.getBootsCubes(stack, NBT.LEFTLEG));
								for (CubeData cube : helper.getBootsCubes(stack, NBT.RIGHTLEG))
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(cube, player, stack));
								handleCubeUpdates(player, stack, helper.getBootsCubes(stack, NBT.RIGHTLEG));
						}
				}
		}

		@Override
		public ModelBiped getArmorModel (EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot slot, ModelBiped _default) {
				if (entity instanceof EntityPlayer && stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags()) {
						EntityPlayer player = (EntityPlayer) entity;
						if (modelPlayer == null || requiresUpdate) {
								if (modelPlayer == null)
										modelPlayer = new ArmorModel();
								else
										modelPlayer.clear();
								requiresUpdate = false;
						}
						if (PlayerTickHandlerClient.armorData.get(player.getGameProfile().getId()))
								modelPlayer.clear();
						if (stack.getItem().equals(VoidRPGItems.armorHelmet) && modelPlayer.bipedHead.childModels == null) {
								modelPlayer.addHeadCubes(helper.getHelmetCubes(stack));
								update = true;
						} else if (stack.getItem().equals(VoidRPGItems.armorChestplate) && modelPlayer.bipedBody.childModels == null && modelPlayer.bipedLeftArm.childModels == null && modelPlayer.bipedRightArm.childModels == null) {
								modelPlayer.addBodyCubes(helper.getChestplateCubes(stack, NBT.BODY));
								modelPlayer.addLeftArmCubes(helper.getChestplateCubes(stack, NBT.LEFTARM));
								modelPlayer.addRightArmCubes(helper.getChestplateCubes(stack, NBT.RIGHTARM));
								update = true;
						} else if (stack.getItem().equals(VoidRPGItems.armorLeggings) && modelPlayer.leftLegCubes.size() == 0 && modelPlayer.rightLegCubes.size() == 0 || stack.getItem().equals(VoidRPGItems.armorLeggings) && modelPlayer.bipedLeftLeg.childModels == null && modelPlayer.bipedRightLeg.childModels == null) {
								modelPlayer.addLeftLegCubes(helper.getLeggingsCubes(stack, NBT.LEFTLEG));
								modelPlayer.addRightLegCubes(helper.getLeggingsCubes(stack, NBT.RIGHTLEG));
								update = true;
						} else if (stack.getItem().equals(VoidRPGItems.armorBoots) && modelPlayer.leftBootsCubes.size() == 0 && modelPlayer.rightBootsCubes.size() == 0 || stack.getItem().equals(VoidRPGItems.armorBoots) && modelPlayer.bipedLeftLeg.childModels == null && modelPlayer.bipedRightLeg.childModels == null) {
								modelPlayer.addLeftBootsCubes(helper.getBootsCubes(stack, NBT.LEFTLEG));
								modelPlayer.addRightBootsCubes(helper.getBootsCubes(stack, NBT.RIGHTLEG));
								update = true;
						}
						if (update) {
								update = false;
								modelPlayer.covertDataToModel(_default);
						}
						return modelPlayer;
				}
				return null;
		}

		@Override
		public ArmorProperties getProperties (EntityLivingBase player, ItemStack stack, DamageSource source, double damage, int slot) {
				return new ArmorProperties(1, 0.2, 1);
		}

		@Override
		public int getArmorDisplay (EntityPlayer player, ItemStack armor, int slot) {
				return 20;
		}

		@Override
		public void damageArmor (EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {}

		@SideOnly (Side.CLIENT)
		@Override
		public String getArmorTexture (ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
				return Global.MODID + ":" + "textures/models/armor.png";
		}

		private void handleCubeUpdates (EntityPlayer player, ItemStack stack, CubeData[] data) {
				if (Settings.cubeEffects)
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
