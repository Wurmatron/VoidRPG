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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.client.events.PlayerTickHandlerClient;
import wurmatron.voidrpg.client.model.ArmorModel;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.Local;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.ArmorHelper2;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CustomArmor2 extends ItemArmor {

		private ArmorModel modelPlayer;
		private boolean requiresUpdate;
		private boolean update;
		private static final ArmorHelper2 helper = new ArmorHelper2();

		public CustomArmor2 (ArmorMaterial material, int index, EntityEquipmentSlot slot) {
				super(material, index, slot);
				setUnlocalizedName("armor_" + slot.name().toLowerCase());
		}

		@Override
		public void onArmorTick (World world, EntityPlayer player, ItemStack stack) {
			if (Settings.cubeEffects) {
				helper.processCubeTick(player, stack);
			}
		}

		@Override
		public ModelBiped getArmorModel (EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot slot, ModelBiped _default) {
				if (!entity.isPotionActive(Potion.getPotionFromResourceLocation("invisibility")))
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
												modelPlayer.addHeadCubes(helper.getCubeData(stack));
												update = true;
										} else if (stack.getItem().equals(VoidRPGItems.armorChestplate) && modelPlayer.bipedBody.childModels == null && modelPlayer.bipedLeftArm.childModels == null && modelPlayer.bipedRightArm.childModels == null) {
												modelPlayer.addBodyCubes(helper.getCubeData(stack, NBT.BODY));
												modelPlayer.addLeftArmCubes(helper.getCubeData(stack, NBT.LEFTARM));
												modelPlayer.addRightArmCubes(helper.getCubeData(stack, NBT.RIGHTARM));
												update = true;
										} else if (stack.getItem().equals(VoidRPGItems.armorLeggings) && modelPlayer.leftLegCubes.isEmpty() && modelPlayer.rightLegCubes.isEmpty() || stack.getItem().equals(VoidRPGItems.armorLeggings) && modelPlayer.bipedLeftLeg.childModels == null && modelPlayer.bipedRightLeg.childModels == null) {
												modelPlayer.addLeftLegCubes(helper.getCubeData(stack, NBT.LEFTLEG));
												modelPlayer.addRightLegCubes(helper.getCubeData(stack, NBT.RIGHTLEG));
												update = true;
										} else if (stack.getItem().equals(VoidRPGItems.armorBoots) && modelPlayer.leftBootsCubes.isEmpty() && modelPlayer.rightBootsCubes.isEmpty() || stack.getItem().equals(VoidRPGItems.armorBoots) && modelPlayer.bipedLeftLeg.childModels == null && modelPlayer.bipedRightLeg.childModels == null) {
												modelPlayer.addLeftBootsCubes(helper.getCubeData(stack, NBT.LEFTLEG));
												modelPlayer.addRightBootsCubes(helper.getCubeData(stack, NBT.RIGHTLEG));
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

		@SideOnly (Side.CLIENT)
		@Override
		public String getArmorTexture (ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
				return Global.MODID + ":" + "textures/models/armor.png";
		}

		@Override
		public void addInformation (ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
				tip.add(TextFormatting.GRAY + I18n.translateToLocal(Local.REACTOR) + ": " + helper.hasValidReactor(stack));
				tip.add(TextFormatting.GRAY + I18n.translateToLocal(Local.COMPLEXITY) + ": " + helper.getComplexity(stack));
				tip.add(TextFormatting.GRAY + I18n.translateToLocal(Local.WEIGHT) + ": " + helper.getWeight(stack));
				if (Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
						HashMap<ICube, Integer> data = new HashMap <> ();
						for (CubeData f : helper.getCubeData(stack)) {
								if (data.containsKey(f.cube)) {
										int count = data.get(f.cube);
										data.remove(f.cube);
										count++;
										data.put(f.cube, count);
								} else
										data.put(f.cube, 1);
						}
						tip.addAll(data.keySet().stream().map(g -> data.get(g) + "x " + I18n.translateToLocal(g.getUnlocalizedName())).collect(Collectors.toList()));
						tip.add("");
				} else
						tip.add(TextFormatting.AQUA + I18n.translateToLocal(Local.HOLD_CTRL));
				TeslaUtils.createTooltip(stack, tip);
		}

		@Optional.Method (modid = "tesla")
		@Override
		public ICapabilityProvider initCapabilities (ItemStack stack, NBTTagCompound nbt) {
				return new BaseTeslaContainerProvider(new BaseTeslaContainer(0, 0, 50, 50));
		}
}
