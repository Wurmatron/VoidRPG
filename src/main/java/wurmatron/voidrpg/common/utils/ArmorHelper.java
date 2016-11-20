package wurmatron.voidrpg.common.utils;


import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.*;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.NBT;

import java.util.ArrayList;

public class ArmorHelper {

		private final CubeRegistry registry = new CubeRegistry();
		public static final ArmorHelper instance = new ArmorHelper();

		public static ModelRenderer createModelRenderer (ModelBase base, final CubeData data) {
				ModelRenderer model = new ModelRenderer(base) {
						@Override
						public void render (float scale) {
								Minecraft.getMinecraft().renderEngine.bindTexture(data.cube.getTexture());
								super.render(scale);
								Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Global.MODID, "textures/test/armor.png"));
						}
				};
				model.addBox(data.offX, data.offY, data.offZ, 1, 1, 1);
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
						temp.setInteger(NBT.DAMAGE, c.damage);
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
						temp.setInteger(NBT.DAMAGE, c.damage);
						nbt.setTag(Integer.toString(a), temp);
						a++;
				}
				nbt.setInteger(NBT.AMOUNT, a);
				stack.setTagInfo(NBT.BODY, nbt);
				NBTTagCompound nbt2 = new NBTTagCompound();
				int b = 0;
				if (armLeft != null)
						for (CubeData c : armLeft) {
								NBTTagCompound temp = new NBTTagCompound();
								temp.setInteger(NBT.OFFSETX, c.offX);
								temp.setInteger(NBT.OFFSETY, c.offY);
								temp.setInteger(NBT.OFFSETZ, c.offZ);
								temp.setString(NBT.CUBE, c.cube.getUnlocalizedName());
								temp.setInteger(NBT.DAMAGE, c.damage);
								nbt2.setTag(Integer.toString(b), temp);
								b++;
						}
				nbt2.setInteger(NBT.AMOUNT, b);
				stack.setTagInfo(NBT.LEFTARM, nbt2);
				NBTTagCompound nbt3 = new NBTTagCompound();
				int d = 0;
				if (armRight != null)
						for (CubeData c : armRight) {
								NBTTagCompound temp = new NBTTagCompound();
								temp.setInteger(NBT.OFFSETX, c.offX);
								temp.setInteger(NBT.OFFSETY, c.offY);
								temp.setInteger(NBT.OFFSETZ, c.offZ);
								temp.setString(NBT.CUBE, c.cube.getUnlocalizedName());
								temp.setInteger(NBT.DAMAGE, c.damage);
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
				if (leftLeg != null)
						for (CubeData c : leftLeg) {
								NBTTagCompound temp = new NBTTagCompound();
								temp.setInteger(NBT.OFFSETX, c.offX);
								temp.setInteger(NBT.OFFSETY, c.offY);
								temp.setInteger(NBT.OFFSETZ, c.offZ);
								temp.setString(NBT.CUBE, c.cube.getUnlocalizedName());
								temp.setInteger(NBT.DAMAGE, c.damage);
								nbt.setTag(Integer.toString(a), temp);
								a++;
						}
				nbt.setInteger(NBT.AMOUNT, a);
				stack.setTagInfo(NBT.LEFTLEG, nbt);
				NBTTagCompound nbt2 = new NBTTagCompound();
				int b = 0;
				if (rightLeg != null)
						for (CubeData c : rightLeg) {
								NBTTagCompound temp = new NBTTagCompound();
								temp.setInteger(NBT.OFFSETX, c.offX);
								temp.setInteger(NBT.OFFSETY, c.offY);
								temp.setInteger(NBT.OFFSETZ, c.offZ);
								temp.setString(NBT.CUBE, c.cube.getUnlocalizedName());
								temp.setInteger(NBT.DAMAGE, c.damage);
								nbt2.setTag(Integer.toString(b), temp);
								b++;
						}
				nbt2.setInteger(NBT.AMOUNT, b);
				stack.setTagInfo(NBT.RIGHTLEG, nbt2);
				return stack;
		}

		public double getWeight (ItemStack stack) {
				int weight = 0;
				ArrayList<ICube> cubes = getCubes(stack);
				if (cubes != null)
						for (ICube c : cubes) {
								weight += c.getWeight();
						}
				return weight;
		}

		@Deprecated
		public ArrayList<ICube> getCubes (ItemStack stack) {
				ArrayList<ICube> cubes = new ArrayList<>();
				if (stack != null && stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags()) {
						if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
								NBTTagCompound data = stack.getTagCompound();
								if (data != null && !data.hasNoTags()) {
										int amt = data.getInteger(NBT.AMOUNT);
										for (int i = 0; i < amt; i++) {
												ICube temp = registry.getCubesFromName(data.getCompoundTag(Integer.toString(i)).getString(NBT.CUBE));
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
												ICube temp = registry.getCubesFromName(body.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
												if (temp != null) {
														cubes.add(temp);
												}
										}
								}
								NBTTagCompound leftArm = stack.getTagCompound().getCompoundTag(NBT.LEFTARM);
								if (leftArm != null && !leftArm.hasNoTags()) {
										for (int a = 0; a < leftArm.getInteger(NBT.AMOUNT); a++) {
												ICube temp = registry.getCubesFromName(leftArm.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
												if (temp != null) {
														cubes.add(temp);
												}
										}
								}
								NBTTagCompound rightArm = stack.getTagCompound().getCompoundTag(NBT.RIGHTARM);
								if (rightArm != null && !rightArm.hasNoTags()) {
										for (int a = 0; a < rightArm.getInteger(NBT.AMOUNT); a++) {
												ICube temp = registry.getCubesFromName(rightArm.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
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
												ICube temp = registry.getCubesFromName(leftLeg.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
												if (temp != null) {
														cubes.add(temp);
												}
										}
								}
								NBTTagCompound rightLeg = stack.getTagCompound().getCompoundTag(NBT.RIGHTLEG);
								if (rightLeg != null && !rightLeg.hasNoTags()) {
										for (int a = 0; a < rightLeg.getInteger(NBT.AMOUNT); a++) {
												ICube temp = registry.getCubesFromName(rightLeg.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
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
												ICube temp = registry.getCubesFromName(leftLeg.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
												if (temp != null) {
														cubes.add(temp);
												}
										}
								}
								NBTTagCompound rightLeg = stack.getTagCompound().getCompoundTag(NBT.RIGHTLEG);
								if (rightLeg != null && !rightLeg.hasNoTags()) {
										for (int a = 0; a < rightLeg.getInteger(NBT.AMOUNT); a++) {
												ICube temp = registry.getCubesFromName(rightLeg.getCompoundTag(Integer.toString(a)).getString(NBT.CUBE));
												if (temp != null) {
														cubes.add(temp);
												}
										}
								}
						}
				}
				return cubes;
		}

		public static double getArmorWeight (ItemStack stack) {
				double weight = 0;
				if (stack != null && stack.getTagCompound() != null) {
						if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
								int amountOfCubes = stack.getTagCompound().getInteger(NBT.AMOUNT);
								for (int a = 0; a <= amountOfCubes; a++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(a));
										ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
										if (cube != null)
												weight += cube.getWeight();
								}
						}
				}
				return weight;
		}

		public static ChatFormatting getArmorWeightColor (double weight, Item item) {
				if (item.equals(VoidRPGItems.armorHelmet)) {
						if (weight <= 50)
								return ChatFormatting.GREEN;
						if (weight > 50 && weight <= 75)
								return ChatFormatting.YELLOW;
						if (weight > 75)
								return ChatFormatting.RED;
				}
				return ChatFormatting.BLUE;
		}

		public static int getArmorComplexity (ItemStack stack) {
				int complexity = 0;
				if (stack != null && stack.getTagCompound() != null) {
						if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
								int amountOfCubes = stack.getTagCompound().getInteger(NBT.AMOUNT);
								for (int a = 0; a <= amountOfCubes; a++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(a));
										ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
										if (cube != null)
												complexity += cube.getComplexity();
								}
						}
				}
				return complexity;
		}

		public static ChatFormatting getComplexityColor (int complexity, Item item) {
				if (item.equals(VoidRPGItems.armorHelmet)) {
						if (complexity == -1)
								return ChatFormatting.LIGHT_PURPLE;
						if (complexity <= 1024)
								return ChatFormatting.GREEN;
						if (complexity > 1024 && complexity <= 2047)
								return ChatFormatting.YELLOW;
						if (complexity > 2048)
								return ChatFormatting.RED;
				}
				return ChatFormatting.BLUE;
		}

		public static double calculateArmorDamage (ItemStack stack) {
				if (stack != null && stack.getTagCompound() != null) {
						if (stack.getItem().equals(VoidRPGItems.armorHelmet)) {
								int durability = 0;
								int amountOfCubes = stack.getTagCompound().getInteger(NBT.AMOUNT);
								for (int a = 0; a <= amountOfCubes; a++) {
										NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(a));
										ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
										if (cube != null)
												durability += temp.getInteger(NBT.DAMAGE) / cube.getDurability();
								}
								return Math.abs((durability / amountOfCubes) - 100);
						}
				}
				return 0;
		}

		public static ChatFormatting getDamageColor (double damage) {
				if (damage >= 85)
						return ChatFormatting.GREEN;
				else if (damage >= 50)
						return ChatFormatting.YELLOW;
				else if (damage >= 25)
						return ChatFormatting.RED;
				else if (damage >= 10)
						return ChatFormatting.DARK_RED;
				return ChatFormatting.BLUE;
		}

		public final CubeData[] getHelmetCubes (ItemStack stack) {
				if (stack != null && stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags())
						if (stack.getItem().equals(VoidRPGItems.armorHelmet))
								return getCubesFromNBT(stack.getTagCompound());
				return null;
		}

		public CubeData[] getChestplateCubes (ItemStack stack, String type) {
				if (stack != null && stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags()) {
						if (stack.getItem().equals(VoidRPGItems.armorChestplate))
								return getCubesFromNBT(stack.getTagCompound().getCompoundTag(type));
				}
				return null;
		}

		public final CubeData[] getChestplateCubes (ItemStack stack) {
				if (stack != null && stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags() && stack.getItem().equals(VoidRPGItems.armorChestplate)) {
						CubeData[] body = getCubesFromNBT(stack.getTagCompound().getCompoundTag(NBT.BODY));
						CubeData[] left = getCubesFromNBT(stack.getTagCompound().getCompoundTag(NBT.LEFTARM));
						CubeData[] right = getCubesFromNBT(stack.getTagCompound().getCompoundTag(NBT.RIGHTARM));
						CubeData[] data = new CubeData[body.length + left.length + right.length];
						for (int b = 0; b < body.length; b++)
								data[b] = body[b];
						for (int l = 0; l < left.length; l++)
								data[body.length + l] = left[l];
						for (int r = 0; r < right.length; r++)
								data[body.length + left.length + r] = right[r];
						return data;
				}
				return null;
		}

		public CubeData[] getLeggingsCubes (ItemStack stack, String type) {
				if (stack != null && stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags()) {
						if (stack.getItem().equals(VoidRPGItems.armorLeggings) || stack.getItem().equals(VoidRPGItems.armorBoots))
								return getCubesFromNBT(stack.getTagCompound().getCompoundTag(type));
				}
				return null;
		}

		public final CubeData[] getLeggingsCubes (ItemStack stack) {
				if (stack != null && stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags()) {
						CubeData[] left = getCubesFromNBT(stack.getTagCompound().getCompoundTag(NBT.LEFTLEG));
						CubeData[] right = getCubesFromNBT(stack.getTagCompound().getCompoundTag(NBT.RIGHTLEG));
						CubeData[] data = new CubeData[left.length + right.length];
						for (int l = 0; l < left.length; l++)
								data[l] = left[l];
						for (int r = 0; r < right.length; r++)
								data[left.length + r] = right[r];
						return data;
				}
				return null;
		}

		public CubeData[] getBootsCubes (ItemStack stack, String type) {
				return getLeggingsCubes(stack, type);
		}

		public final CubeData[] getBootsCubes (ItemStack stack) {
				return getLeggingsCubes(stack);
		}

		public CubeData[] getCubesFromNBT (NBTTagCompound nbt) {
				if (nbt != null && !nbt.hasNoTags()) {
						ArrayList<CubeData> cubes = new ArrayList<>();
						NBTTagCompound data = nbt;
						int amount = data.getInteger(NBT.AMOUNT);
						for (int amt = 0; amt < amount; amt++) {
								NBTTagCompound temp = data.getCompoundTag(Integer.toString(amt));
								ICube cube = CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE));
								if (cube != null)
										cubes.add(new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETZ), cube, temp.getInteger(NBT.DAMAGE)));
						}
						CubeData[] temp = new CubeData[cubes.size()];
						for (int c = 0; c < cubes.size(); c++)
								temp[c] = cubes.get(c);
						return temp;
				}
				return null;
		}

		public int getCubeAmount (ItemStack item, ICube cube) {
				if (item != null) {
						if (item.getItem().equals(VoidRPGItems.armorHelmet)) {
								CubeData[] cubes = getHelmetCubes(item);
								int amount = 0;
								for (CubeData data : cubes)
										if (data.cube.getUnlocalizedName().equals(cube.getUnlocalizedName()))
												amount++;
								return amount;
						}
						if (item.getItem().equals(VoidRPGItems.armorChestplate)) {
								CubeData[] body = getChestplateCubes(item, NBT.BODY);
								int amount = 0;
								for (CubeData data : body)
										if (data.cube.getUnlocalizedName().equals(cube.getUnlocalizedName()))
												amount++;
								CubeData[] left = getChestplateCubes(item, NBT.LEFTARM);
								for (CubeData data : left)
										if (data.cube.getUnlocalizedName().equals(cube.getUnlocalizedName()))
												amount++;
								CubeData[] right = getChestplateCubes(item, NBT.RIGHTARM);
								for (CubeData data : right)
										if (data.cube.getUnlocalizedName().equals(cube.getUnlocalizedName()))
												amount++;
								return amount;
						}
						if (item.getItem().equals(VoidRPGItems.armorLeggings)) {
								CubeData[] left = getLeggingsCubes(item, NBT.LEFTLEG);
								CubeData[] right = getLeggingsCubes(item, NBT.RIGHTLEG);
								int amount = 0;
								for (CubeData data : left)
										if (data.cube.getUnlocalizedName().equals(cube.getUnlocalizedName()))
												amount++;
								for (CubeData data : right)
										if (data.cube.getUnlocalizedName().equals(cube.getUnlocalizedName()))
												amount++;
								return amount;
						}
						if (item.getItem().equals(VoidRPGItems.armorBoots)) {
								CubeData[] left = getBootsCubes(item, NBT.LEFTLEG);
								CubeData[] right = getBootsCubes(item, NBT.RIGHTLEG);
								int amount = 0;
								for (CubeData data : left) {
										if (data.cube.getUnlocalizedName().equals(cube.getUnlocalizedName()))
												amount++;
								}
								for (CubeData data : right)
										if (data.cube.getUnlocalizedName().equals(cube.getUnlocalizedName()))
												amount++;
								return amount;
						}
				}
				return 0;
		}

		public boolean isCubeActive (ICube cube, ItemStack stack) {
				if (cube != null && stack != null)
						return getCubeAmount(stack, cube) >= cube.getMinAmount(stack.getItem(), getArmorWeight(stack)) && hasActiveReactor(getCubesFromStack(stack)).equalsIgnoreCase("operational");
				return false;
		}

		public boolean isCubeActive (ICube cube, ItemStack stack, CubeData[] cubes) {
				return cube != null && stack != null && getCubeAmount(stack, cube) >= cube.getMinAmount(stack.getItem(), getArmorWeight(stack)) && hasActiveReactor(cubes).equalsIgnoreCase("operational");
		}

		public String hasActiveReactor (CubeData[] data) {
				if (!Settings.requiresReactor) return "Operational";
				if (data != null && data.length > 0) {
						ArrayList<CubeData> validReactors = new ArrayList<>();
						int passiveDrain = 0;
						for (CubeData cube : data) {
								if (cube.cube instanceof IReactor)
										validReactors.add(cube);
								if (cube.cube instanceof IEnergyConsumer) {
										IEnergyConsumer energy = (IEnergyConsumer) cube.cube;
										passiveDrain += energy.getPassiveDrain();
								}
						}
						int reactorMaxPower = 0;
						for (CubeData reactor : validReactors) {
								IReactor r = (IReactor) reactor.cube;
								reactorMaxPower += r.getMaxPower();
						}
						if (reactorMaxPower == 0)
								return "No Reactor";
						if (reactorMaxPower > passiveDrain)
								return "Operational";
						else
								return "Overloaded";
				}
				return "No Data";
		}

		public ArrayList<ICube> getEnergyCubes (ItemStack stack) {
				if (stack != null && stack.getTagCompound() != null) {
						ArrayList<ICube> energyCubes = new ArrayList<>();
						for (ICube cube : getCubes(stack)) {
								if (cube instanceof IEnergyCube)
										energyCubes.add(cube);
						}
						return energyCubes;
				}
				return null;
		}

		public long getMaxEnergyStorage (ItemStack stack) {
				if (stack != null && stack.getTagCompound() != null) {
						long maxEnergy = 0;
						for (ICube cube : getEnergyCubes(stack)) {
								if (cube instanceof IEnergyCube) {
										IEnergyCube energy = (IEnergyCube) cube;
										maxEnergy += energy.getStorage();
								}
						}
						return maxEnergy;
				}
				return 0;
		}

		public CubeData[] getCubesFromStack (ItemStack stack) {
				if (stack != null && stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags())
						switch (stack.getItem().getUnlocalizedName().substring(11)) {
								case ("head"):
										return getHelmetCubes(stack);
								case ("chest"):
										return getChestplateCubes(stack);
								case ("legs"):
										return getLeggingsCubes(stack);
								case ("feet"):
										return getBootsCubes(stack);
						}
				return null;
		}

		private CubeData[] overrideCubeData (CubeData[] data, CubeData find, CubeData replacment) {
				if (data != null && data.length > 0) {
						CubeData[] output = data;
						for (int slot = 0; slot < data.length; slot++) {
								if (data[slot] == find)
										output[slot] = replacment;
						}
						return output;
				}
				return data;
		}

		public ItemStack overrideData (ItemStack stack, CubeData find, CubeData replacment) {
				if (stack != null && stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags()) {
						switch (stack.getItem().getUnlocalizedName().substring(11)) {
								case ("head"): {
										CubeData[] data = getHelmetCubes(stack);
										CubeData[] output = data;
										for (int slot = 0; slot < data.length; slot++) {
												if (data[slot] == find)
														output[slot] = replacment;
										}
										return createArmorStack(stack.getItem(), output);
								}
								case ("chest"): {
										CubeData[] body = getChestplateCubes(stack, NBT.BODY);
										CubeData[] left = getChestplateCubes(stack, NBT.LEFTARM);
										CubeData[] right = getChestplateCubes(stack, NBT.RIGHTARM);
										CubeData[] bodyOutput = body;
										for (int slot = 0; slot < body.length; slot++)
												if (body[slot] == find)
														bodyOutput[slot] = replacment;
										CubeData[] leftOutput = left;
										for (int slot = 0; slot < left.length; slot++)
												if (left[slot] == find)
														leftOutput[slot] = replacment;
										CubeData[] rightOutput = right;
										for (int slot = 0; slot < right.length; slot++)
												if (right[slot] == find)
														rightOutput[slot] = replacment;
										return createArmorStack(stack.getItem(), body, left, right);
								}
								case ("legs"): {
										CubeData[] left = getLeggingsCubes(stack, NBT.LEFTLEG);
										CubeData[] right = getLeggingsCubes(stack, NBT.RIGHTARM);
										CubeData[] leftOutput = left;
										for (int slot = 0; slot < left.length; slot++)
												if (left[slot] == find)
														leftOutput[slot] = replacment;
										CubeData[] rightOutput = right;
										for (int slot = 0; slot < right.length; slot++)
												if (right[slot] == find)
														rightOutput[slot] = replacment;
										return createArmorStack(stack.getItem(), left, right);
								}
								case ("feet"):
										CubeData[] left = getBootsCubes(stack, NBT.LEFTLEG);
										CubeData[] right = getBootsCubes(stack, NBT.RIGHTARM);
										CubeData[] leftOutput = left;
										if (left != null && left.length > 0)
												for (int slot = 0; slot < left.length; slot++)
														if (left[slot] == find)
																leftOutput[slot] = replacment;
										CubeData[] rightOutput = right;
										if (right != null && right.length > 0)
												for (int slot = 0; slot < right.length; slot++)
														if (right[slot] == find)
																rightOutput[slot] = replacment;
										return createArmorStack(stack.getItem(), left, right);
						}
				}
				return stack;
		}


}
