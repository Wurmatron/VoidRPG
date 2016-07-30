package wurmatron.voidrpg.common.utils;


import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.Cube;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.NBT;

import java.util.ArrayList;

public class ArmorHelper {

		private CubeRegistry registry = new CubeRegistry();

		public static ModelRenderer createModelRenderer (ModelBase base, final CubeData data) {
				ModelRenderer model = new ModelRenderer(base) {
						@Override
						public void render (float scale) {
								Minecraft.getMinecraft().renderEngine.bindTexture(data.cube.getTexture());
								super.render(scale);
								Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Global.MODID, "textures/test/armor.png"));
						}
				};
				model.addBox(data.offX, data.offY, data.offZ, 1, data.cube.getSize()[1], data.cube.getSize()[2]);
				return model;
		}

		/**
		 * Used to create custom helmet
		 */
		public ItemStack createArmorStack (Item item, CubeData[] data) {
				ItemStack stack = new ItemStack(item, 1, 0);
				NBTTagCompound nbt = new NBTTagCompound();
				int a = 0;
				for (CubeData c : data) {
						NBTTagCompound temp = new NBTTagCompound();
						temp.setInteger(NBT.OFFSETX, c.offX);
						temp.setInteger(NBT.OFFSETY, c.offY);
						temp.setInteger(NBT.OFFSETZ, c.offZ);
						temp.setString(NBT.CUBE, c.cube.getUnlocalizedName());
						nbt.setTag(Integer.toString(a), temp);
						a++;
				}
				nbt.setInteger(NBT.AMOUNT, a);
				stack.setTagCompound(nbt);
				return stack;
		}

		/**
		 * Used to create custom chestplate
		 */
		public ItemStack createArmorStack (Item item, CubeData[] base, CubeData[] armLeft, CubeData[] armRight) {
				ItemStack stack = new ItemStack(item, 1, 0);
				NBTTagCompound nbt = new NBTTagCompound();
				int a = 0;
				for (CubeData c : base) {
						NBTTagCompound temp = new NBTTagCompound();
						temp.setInteger(NBT.OFFSETX, c.offX);
						temp.setInteger(NBT.OFFSETY, c.offY);
						temp.setInteger(NBT.OFFSETZ, c.offZ);
						temp.setString(NBT.CUBE, c.cube.getUnlocalizedName());
						nbt.setTag(Integer.toString(a), temp);
						a++;
				}
				nbt.setInteger(NBT.AMOUNT, a);
				stack.setTagInfo(NBT.BODY, nbt);
				NBTTagCompound nbt2 = new NBTTagCompound();
				int b = 0;
				for (CubeData c : armLeft) {
						NBTTagCompound temp = new NBTTagCompound();
						temp.setInteger(NBT.OFFSETX, c.offX);
						temp.setInteger(NBT.OFFSETY, c.offY);
						temp.setInteger(NBT.OFFSETZ, c.offZ);
						temp.setString(NBT.CUBE, c.cube.getUnlocalizedName());
						nbt2.setTag(Integer.toString(b), temp);
						b++;
				}
				nbt2.setInteger(NBT.AMOUNT, b);
				stack.setTagInfo(NBT.LEFTARM, nbt2);
				NBTTagCompound nbt3 = new NBTTagCompound();
				int d = 0;
				for (CubeData c : armRight) {
						NBTTagCompound temp = new NBTTagCompound();
						temp.setInteger(NBT.OFFSETX, c.offX);
						temp.setInteger(NBT.OFFSETY, c.offY);
						temp.setInteger(NBT.OFFSETZ, c.offZ);
						temp.setString(NBT.CUBE, c.cube.getUnlocalizedName());
						nbt3.setTag(Integer.toString(d), temp);
						d++;
				}
				nbt3.setInteger(NBT.AMOUNT, d);
				stack.setTagInfo(NBT.RIGHTARM, nbt3);
				return stack;
		}

		/**
		 * Used to create custom leggings and boots
		 */
		public ItemStack createArmorStack (Item item, CubeData[] leftLeg, CubeData[] rightLeg) {
				ItemStack stack = new ItemStack(item, 1, 0);
				NBTTagCompound nbt = new NBTTagCompound();
				int a = 0;
				for (CubeData c : leftLeg) {
						NBTTagCompound temp = new NBTTagCompound();
						temp.setInteger(NBT.OFFSETX, c.offX);
						temp.setInteger(NBT.OFFSETY, c.offY);
						temp.setInteger(NBT.OFFSETZ, c.offZ);
						temp.setString(NBT.CUBE, c.cube.getUnlocalizedName());
						nbt.setTag(Integer.toString(a), temp);
						a++;
				}
				nbt.setInteger(NBT.AMOUNT, a);
				stack.setTagInfo(NBT.LEFTLEG, nbt);
				NBTTagCompound nbt2 = new NBTTagCompound();
				int b = 0;
				for (CubeData c : rightLeg) {
						NBTTagCompound temp = new NBTTagCompound();
						temp.setInteger(NBT.OFFSETX, c.offX);
						temp.setInteger(NBT.OFFSETY, c.offY);
						temp.setInteger(NBT.OFFSETZ, c.offZ);
						temp.setString(NBT.CUBE, c.cube.getUnlocalizedName());
						nbt2.setTag(Integer.toString(b), temp);
						b++;
				}
				nbt2.setInteger(NBT.AMOUNT, b);
				stack.setTagInfo(NBT.RIGHTLEG, nbt2);
				return stack;
		}

		public float getWeight (ItemStack stack) {
				int weight = 0;
				ArrayList<Cube> cubes = getCubes(stack);
				for (Cube c : cubes) {
						weight += c.getWeight();
				}
				return weight;
		}

		public ArrayList<Cube> getCubes (ItemStack stack) {
				ArrayList<Cube> cubes = new ArrayList<Cube>();
				if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
						NBTTagCompound data = stack.getTagCompound();
						if (data != null && !data.hasNoTags()) {
								int amt = data.getInteger(NBT.AMOUNT);
								for (int i = 0; i < amt; i++) {
										Cube temp = registry.getCubesFromName(data.getCompoundTag(Integer.toString(i)).getString(NBT.CUBE));
										if (temp != null) {
												cubes.add(temp);
										}
								}
						}
				}
				if (stack.getItem().equals(VoidRPGItems.armorChestplate)) {
						NBTTagCompound body = stack.getTagCompound().getCompoundTag(NBT.BODY);
						if (body != null && !body.hasNoTags()) {
								for (int a = 0; a < body.getInteger(NBT.AMOUNT); a++) {
										Cube temp = registry.getCubesFromName(body.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
										if (temp != null) {
												cubes.add(temp);
										}
								}
						}
						NBTTagCompound leftArm = stack.getTagCompound().getCompoundTag(NBT.LEFTARM);
						if (leftArm != null && !leftArm.hasNoTags()) {
								for (int a = 0; a < leftArm.getInteger(NBT.AMOUNT); a++) {
										Cube temp = registry.getCubesFromName(leftArm.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
										if (temp != null) {
												cubes.add(temp);
										}
								}
						}
						NBTTagCompound rightArm = stack.getTagCompound().getCompoundTag(NBT.RIGHTARM);
						if (rightArm != null && !rightArm.hasNoTags()) {
								for (int a = 0; a < rightArm.getInteger(NBT.AMOUNT); a++) {
										Cube temp = registry.getCubesFromName(rightArm.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
										if (temp != null) {
												cubes.add(temp);
										}
								}
						}
				}
				if (stack.getItem().equals(VoidRPGItems.armorLeggings)) {
						NBTTagCompound leftLeg = stack.getTagCompound().getCompoundTag(NBT.LEFTLEG);
						if (leftLeg != null && !leftLeg.hasNoTags()) {
								for (int a = 0; a < leftLeg.getInteger(NBT.AMOUNT); a++) {
										Cube temp = registry.getCubesFromName(leftLeg.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
										if (temp != null) {
												cubes.add(temp);
										}
								}
						}
						NBTTagCompound rightLeg = stack.getTagCompound().getCompoundTag(NBT.RIGHTLEG);
						if (rightLeg != null && !rightLeg.hasNoTags()) {
								for (int a = 0; a < rightLeg.getInteger(NBT.AMOUNT); a++) {
										Cube temp = registry.getCubesFromName(rightLeg.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
										if (temp != null) {
												cubes.add(temp);
										}
								}
						}
				}
				if (stack.getItem().equals(VoidRPGItems.armorBoots)) {
						NBTTagCompound leftLeg = stack.getTagCompound().getCompoundTag(NBT.LEFTLEG);
						if (leftLeg != null && !leftLeg.hasNoTags()) {
								for (int a = 0; a < leftLeg.getInteger(NBT.AMOUNT); a++) {
										Cube temp = registry.getCubesFromName(leftLeg.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
										if (temp != null) {
												cubes.add(temp);
										}
								}
						}
						NBTTagCompound rightLeg = stack.getTagCompound().getCompoundTag(NBT.RIGHTLEG);
						if (rightLeg != null && !rightLeg.hasNoTags()) {
								for (int a = 0; a < rightLeg.getInteger(NBT.AMOUNT); a++) {
										Cube temp = registry.getCubesFromName(rightLeg.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
										if (temp != null) {
												cubes.add(temp);
										}
								}
						}
				}
				return cubes;
		}
}
