package wurmatron.voidrpg.common.items;

import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.api.implementation.BaseTeslaContainerProvider;
import net.darkhax.tesla.lib.TeslaUtils;
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
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.event.CubeTickEvent;
import wurmatron.voidrpg.client.events.PlayerTickHandlerClient;
import wurmatron.voidrpg.client.model.ArmorModel;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.ArmorHelper;
import wurmatron.voidrpg.common.utils.energy.TeslaHelper;

import java.util.List;

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
						if (!stack.getTagCompound().hasKey(NBT.ENERGY))
							TeslaHelper.setMaxCapacity(stack, ArmorHelper.instance.getMaxEnergyStorage(stack));
				}
		}

		@Override
		public ModelBiped getArmorModel (EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot slot, ModelBiped _default) {
				if (entity instanceof EntityPlayer && stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags()) {
						EntityPlayer player = (EntityPlayer) entity;
						if (PlayerTickHandlerClient.updateRequirment.contains(player.getGameProfile().getId())) {
								PlayerTickHandlerClient.updateRequirment.remove(player.getGameProfile().getId());
								requiresUpdate = true;
						}
						if (modelPlayer == null || requiresUpdate)
								if (modelPlayer == null)
										modelPlayer = new ArmorModel();
								else
										modelPlayer.clear();
						if (modelPlayer != null)
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
						if (update || requiresUpdate) {
								update = false;
								requiresUpdate = false;
								modelPlayer = modelPlayer.covertDataToModel(_default);
						}
						return modelPlayer;
				}
				return _default;
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
		public void damageArmor (EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		}

		@SideOnly (Side.CLIENT)
		@Override
		public String getArmorTexture (ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
				return Global.MODID + ":" + "textures/models/armor.png";
		}

		private void handleCubeUpdates (EntityPlayer player, ItemStack stack, CubeData[] data) {
				if (Settings.cubeEffects)
						for (CubeData cube : data) {
								if (cube.cube.hasEffects(player, stack) && new ArmorHelper().isCubeActive(cube.cube, stack))
										cube.cube.applyEffect(player, cube, data, stack);
						}
		}

		@Override
		public void addInformation (ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
				TeslaUtils.createTooltip(stack, tip);
		}

		@Optional.Method (modid = "tesla")
		@Override
		public ICapabilityProvider initCapabilities (ItemStack stack, NBTTagCompound nbt) {
				return new BaseTeslaContainerProvider(new BaseTeslaContainer(0, 0, 50, 50));
		}
}
