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
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.client.events.PlayerTickHandlerClient;
import wurmatron.voidrpg.client.model.ArmorModel;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.ArmorHelper;
import wurmatron.voidrpg.common.utils.CustomArmorHelper;

import java.util.HashMap;
import java.util.List;

public class CustomArmor extends ItemArmor implements ISpecialArmor {

		private ArmorModel modelPlayer;
		private boolean requiresUpdate;
		private boolean update;
		private static final ArmorHelper helper = new ArmorHelper();
		private static final CustomArmorHelper armorHelper = new CustomArmorHelper();

		public CustomArmor (ArmorMaterial material, int index, EntityEquipmentSlot slot) {
				super(material, index, slot);
				setUnlocalizedName("armor_" + slot.name().toLowerCase());
		}

		@Override
		public void onArmorTick (World world, EntityPlayer player, ItemStack stack) {
				armorHelper.onArmorTick(world, player, stack);
		}

		// Created by Wurmatron
		@Override
		public ModelBiped getArmorModel (EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot slot, ModelBiped _default) {
				if (!entity.isPotionActive(Potion.getPotionById(14)))
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

		@Override
		public void addInformation (ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
				tip.add(TextFormatting.GRAY + I18n.translateToLocal("stat.reactor.name") + ": " + getActiveReator(stack));
				tip.add(TextFormatting.GRAY + I18n.translateToLocal("stat.complexity.name") + ": " + helper.getComplexityColor(helper.getArmorComplexity(stack), stack.getItem()) + helper.getArmorComplexity(stack));
				tip.add(TextFormatting.GRAY + I18n.translateToLocal("stat.weight.name") + ": " + helper.getArmorWeightColor(helper.getWeight(stack), stack.getItem()) + helper.getWeight(stack));
				if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
						HashMap<ICube, Integer> data = new HashMap();
						for (ICube f : helper.getCubes(stack)) {
								if (data.containsKey(f)) {
										int count = (Integer) data.get(f);
										data.remove(f);
										count++;
										data.put(f, count);
								} else
										data.put(f, 1);
						}
						for (ICube g : data.keySet())
								tip.add(data.get(g) + "x " + I18n.translateToLocal(g.getUnlocalizedName()));
				} else
						tip.add(TextFormatting.AQUA + "Hold ctrl for cubes");
				TeslaUtils.createTooltip(stack, tip);
		}

		@Optional.Method (modid = "tesla")
		@Override
		public ICapabilityProvider initCapabilities (ItemStack stack, NBTTagCompound nbt) {
				return new BaseTeslaContainerProvider(new BaseTeslaContainer(0, 0, 50, 50));
		}

		private String getActiveReator (ItemStack stack) {
				String reactor = helper.hasActiveReactor(helper.getCubesFromStack(stack));
				if (reactor.equalsIgnoreCase("operational"))
						return TextFormatting.GREEN + reactor;
				else if (reactor.equalsIgnoreCase("overloaded"))
						return TextFormatting.YELLOW + reactor;
				else
						return TextFormatting.RED + reactor;
		}
}
