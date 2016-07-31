package wurmatron.voidrpg.common.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.model.ModelBiped;
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
import wurmatron.voidrpg.api.cube.Cube;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.event.CubeTickEvent;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.ArmorHelper;

import java.util.List;

public class CustomArmor extends ItemArmor implements ISpecialArmor {

		private static ModelBiped modelPlayer;

		public CustomArmor (ArmorMaterial mat, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
				super(mat, renderIndexIn, equipmentSlotIn);
				setUnlocalizedName("armor_" + equipmentSlotIn.name());
				setCreativeTab(VoidRPG.tabVoidRPG);
		}

		@SideOnly (Side.CLIENT)
		@Override
		public ModelBiped getArmorModel (EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot slot, ModelBiped model) {
				modelPlayer = model;
				modelPlayer.bipedHead.cubeList.clear();
				if (stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags()) {
						if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
								NBTTagCompound data = stack.getTagCompound();
								int amt = data.getInteger(NBT.AMOUNT);
								if (amt != 0 && modelPlayer.bipedHead.childModels == null) {
										for (int a = 0; a < amt; a++) {
												NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(a));
												Cube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
												if (cube != null)
														modelPlayer.bipedHead.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getInteger(NBT.OFFSETX) - 8, temp.getInteger(NBT.OFFSETY) - 14, temp.getInteger(NBT.OFFSETZ) - 8, cube)));
										}
								}
						}
				} else {
						return model;
				}
//						if (stack.getItem().equals(VoidRPGItems.armorChestplate)) {
//								NBTTagCompound body = stack.getTagCompound().getCompoundTag(NBT.BODY);
//								int amtBody = body.getInteger(NBT.AMOUNT);
//								if (amtBody != 0 && modelPlayer.bipedBody.childModels == null) {
//										for (int b = 0; b < amtBody; b++) {
//												NBTTagCompound temp = body.getCompoundTag(Integer.toString(b));
//												Cube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
//												if (cube != null)
//														modelPlayer.bipedBody.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getFloat(NBT.OFFSETX), temp.getFloat(NBT.OFFSETY), temp.getFloat(NBT.OFFSETZ), cube)));
//										}
//								}
//								NBTTagCompound leftArm = stack.getTagCompound().getCompoundTag(NBT.LEFTARM);
//								int amtLeft = leftArm.getInteger(NBT.AMOUNT);
//								if (amtLeft != 0 && modelPlayer.bipedLeftArm.childModels == null) {
//										for (int b = 0; b < amtLeft; b++) {
//												NBTTagCompound temp = leftArm.getCompoundTag(Integer.toString(b));
//												Cube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
//												if (cube != null)
//														modelPlayer.bipedLeftArm.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getFloat(NBT.OFFSETX), temp.getFloat(NBT.OFFSETY), temp.getFloat(NBT.OFFSETZ), cube)));
//										}
//								}
//								NBTTagCompound rightArm = stack.getTagCompound().getCompoundTag(NBT.RIGHTARM);
//								int amtRight = rightArm.getInteger(NBT.AMOUNT);
//								if (amtRight != 0 && modelPlayer.bipedRightArm.childModels == null) {
//										for (int b = 0; b < amtRight; b++) {
//												NBTTagCompound temp = rightArm.getCompoundTag(Integer.toString(b));
//												Cube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
//												if (cube != null)
//														modelPlayer.bipedRightArm.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getFloat(NBT.OFFSETX), temp.getFloat(NBT.OFFSETY), temp.getFloat(NBT.OFFSETZ), cube)));
//										}
//								}
//						}
//						if (stack.getItem().equals(VoidRPGItems.armorLeggings)) {
//								NBTTagCompound leftLeg = stack.getTagCompound().getCompoundTag(NBT.LEFTLEG);
//								int amtLeft = leftLeg.getInteger(NBT.AMOUNT);
//								if (amtLeft != 0 && modelPlayer.bipedLeftLeg.childModels == null) {
//										for (int b = 0; b < amtLeft; b++) {
//												NBTTagCompound temp = leftLeg.getCompoundTag(Integer.toString(b));
//												Cube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
//												if (cube != null)
//														modelPlayer.bipedLeftLeg.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getFloat(NBT.OFFSETX), temp.getFloat(NBT.OFFSETY), temp.getFloat(NBT.OFFSETZ), cube)));
//										}
//								}
//								NBTTagCompound rightLeg = stack.getTagCompound().getCompoundTag(NBT.RIGHTLEG);
//								int amtRight = rightLeg.getInteger(NBT.AMOUNT);
//								if (amtRight != 0 && modelPlayer.bipedRightLeg.childModels == null) {
//										for (int b = 0; b < amtRight; b++) {
//												NBTTagCompound temp = rightLeg.getCompoundTag(Integer.toString(b));
//												Cube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
//												if (cube != null)
//														modelPlayer.bipedRightLeg.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getFloat(NBT.OFFSETX), temp.getFloat(NBT.OFFSETY), temp.getFloat(NBT.OFFSETZ), cube)));
//										}
//								}
//						}
//						if (stack.getItem().equals(VoidRPGItems.armorBoots)) {
//								NBTTagCompound leftLeg = stack.getTagCompound().getCompoundTag(NBT.LEFTLEG);
//								int amtLeft = leftLeg.getInteger(NBT.AMOUNT);
//								if (amtLeft != 0 && model.bipedLeftLeg.childModels == null) {
//										for (int b = 0; b < amtLeft; b++) {
//												NBTTagCompound temp = leftLeg.getCompoundTag(Integer.toString(b));
//												Cube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
//												if (cube != null)
//														model.bipedLeftLeg.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getFloat(NBT.OFFSETX), temp.getFloat(NBT.OFFSETY), temp.getFloat(NBT.OFFSETZ), cube)));
//										}
//								}
//								NBTTagCompound rightLeg = stack.getTagCompound().getCompoundTag(NBT.RIGHTLEG);
//								int amtRight = rightLeg.getInteger(NBT.AMOUNT);
//								if (amtRight != 0 && modelPlayer.bipedRightLeg.childModels == null) {
//										for (int b = 0; b < amtRight; b++) {
//												NBTTagCompound temp = rightLeg.getCompoundTag(Integer.toString(b));
//												Cube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
//												if (cube != null)
//														modelPlayer.bipedRightLeg.addChild(ArmorHelper.createModelRenderer(model, new CubeData(temp.getFloat(NBT.OFFSETX), temp.getFloat(NBT.OFFSETY), temp.getFloat(NBT.OFFSETZ), cube)));
//										}
//								}
//						}
//				}
//				model = modelPlayer;
				return modelPlayer;
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
				if (item.equals(VoidRPGItems.armorHelmet)) {
						CubeData[] data = new CubeData[6];

						data[0] = new CubeData(0, -10, 0, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[1] = new CubeData(5, -10, 5, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[2] = new CubeData(10, 20, 10, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[3] = new CubeData(15, -20, 15, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[4] = new CubeData(20, 0, 20, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[5] = new CubeData(25, 0, 25, CubeRegistry.INSTANCE.getCubesFromName("test"));

						sub.add(new ArmorHelper().createArmorStack(item, data));
				}

				if (item.equals(VoidRPGItems.armorChestplate)) {
						CubeData[] data = new CubeData[20];

						data[0] = new CubeData(0, 0, 0, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[1] = new CubeData(0, 0, 5, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[2] = new CubeData(0, 0, 10, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[3] = new CubeData(0, 0, 15, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[4] = new CubeData(0, 0, 20, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[5] = new CubeData(0, 0, 25, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[6] = new CubeData(0, 0, 30, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[7] = new CubeData(0, 0, 35, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[8] = new CubeData(0, 0, 40, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[9] = new CubeData(0, 0, 45, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[10] = new CubeData(5, 0, 5, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[11] = new CubeData(5, 0, 10, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[12] = new CubeData(5, 0, 15, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[13] = new CubeData(5, 0, 20, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[14] = new CubeData(5, 0, 25, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[15] = new CubeData(5, 0, 30, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[16] = new CubeData(5, 0, 35, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[17] = new CubeData(5, 0, 40, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[18] = new CubeData(5, 0, 45, CubeRegistry.INSTANCE.getCubesFromName("test"));
						data[19] = new CubeData(5, 0, 50, CubeRegistry.INSTANCE.getCubesFromName("test"));

						sub.add(new ArmorHelper().createArmorStack(item, data, data, data));

				}
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
				// Used to damage the cubes instead of the whole armor
		}

		@Override
		public void onArmorTick (World world, EntityPlayer player, ItemStack stack) {
				if (stack.getTagCompound() != null && stack.getTagCompound().hasKey(NBT.AMOUNT)) {
						if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
								int amount = stack.getTagCompound().getInteger(NBT.AMOUNT);
								for (int a = 0; a <= amount; a++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(a));
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETZ), CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE))), player, stack));
								}
						}
				}
		}

		@Override
		public void addInformation (ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
				double weight = ArmorHelper.getArmorWeight(stack);
				tip.add(ChatFormatting.AQUA + "stat.weight.name" + ": " + ArmorHelper.getArmorWeightColor(weight,stack.getItem()) + weight);
		}
}
