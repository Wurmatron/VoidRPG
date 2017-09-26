package wurmatron.voidrpg.common.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.NBT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataHelper {

	public static CubeData addOffset (CubeData cubeData,int x,int y,int z) {
		if (cubeData != null && Math.abs (x) > 0 || Math.abs (y) > 0 || Math.abs (z) > 0)
			return new CubeData (cubeData.cube,cubeData.xPos + x,cubeData.yPos + y,cubeData.zPos + z,cubeData.damage);
		return cubeData;
	}

	public static CubeData[] addOffset (CubeData[] cubeData,int x,int y,int z) {
		if (Math.abs (x) > 0 || Math.abs (y) > 0 || Math.abs (z) > 0) {
			CubeData[] temp = new CubeData[cubeData.length];
			for (int index = 0; index < cubeData.length; index++)
				temp[index] = addOffset (cubeData[index],x,y,z);
			return temp;
		}
		return cubeData;
	}

	public static CubeData damageCube (CubeData data,int damage) {
		if (Math.abs (damage) > 0)
			return new CubeData (data.cube,data.xPos,data.yPos,data.zPos,data.damage + damage);
		return data;
	}

	public static CubeData damageCube (CubeData data) {
		return damageCube (data,1);
	}

	public static CubeData[] rotateClockwise (CubeData[] cubes) {
		CubeData[] data = new CubeData[cubes.length];
		for (int s = 0; s < cubes.length; s++)
			if (cubes[s] != null && cubes[s].cube != null)
				data[s] = new CubeData (cubes[s].cube,cubes[s].xPos,cubes[s].yPos,(cubes[s].zPos * -1) + 15,cubes[s].damage);
		return data;
	}

	public static CubeData[] rotateUp (CubeData[] cubes) {
		CubeData[] data = new CubeData[cubes.length];
		for (int s = 0; s < cubes.length; s++)
			if (cubes[s] != null && cubes[s].cube != null)
				data[s] = new CubeData (cubes[s].cube,cubes[s].xPos,(cubes[s].yPos * -1) + 15,cubes[s].zPos,cubes[s].damage);
		return data;
	}

	public static final ItemStack createHelmetFromData (CubeData[] helmetData) {
		ItemStack stack = new ItemStack (VoidRPGItems.armorHelmet,1,0);
		NBTTagCompound tag = new NBTTagCompound ();
		if (helmetData != null && helmetData.length > 0) {
			NBTTagCompound[] nbt = BitHelper.convertCubesToNBT (helmetData);
			for (int i = 0; i < nbt.length; i++)
				tag.setTag (Integer.toString (i),nbt[i]);
		}
		stack.setTagCompound (tag);
		return stack;
	}

	public static final ItemStack createChestplateFromData (CubeData[] chest,CubeData[] leftArm,CubeData[] rightArm) {
		ItemStack stack = new ItemStack (VoidRPGItems.armorChestplate,1,0);
		NBTTagCompound tag = new NBTTagCompound ();
		NBTTagCompound body = new NBTTagCompound ();
		NBTTagCompound leftarm = new NBTTagCompound ();
		NBTTagCompound rightarm = new NBTTagCompound ();
		if (chest != null || leftArm != null || rightArm != null) {
			NBTTagCompound[] nbtChest = BitHelper.convertCubesToNBT (chest);
			NBTTagCompound[] nbtLeftArm = BitHelper.convertCubesToNBT (leftArm);
			NBTTagCompound[] nbtRightArm = BitHelper.convertCubesToNBT (rightArm);
			for (int i = 0; i < nbtChest.length; i++)
				body.setTag (Integer.toString (i),nbtChest[i]);
			tag.setTag (NBT.BODY,body);
			for (int i = 0; i < nbtRightArm.length; i++)
				leftarm.setTag (Integer.toString (i),nbtLeftArm[i]);
			tag.setTag (NBT.LEFT_ARM,leftarm);
			for (int i = 0; i < nbtRightArm.length; i++)
				rightarm.setTag (Integer.toString (i),nbtRightArm[i]);
			tag.setTag (NBT.RIGHT_ARM,rightarm);
		}
		stack.setTagCompound (tag);
		return stack;
	}

	public static final ItemStack createLegsFromData (CubeData[] leftLeg,CubeData[] rightLeg) {
		ItemStack stack = new ItemStack (VoidRPGItems.armorLeggings,1,0);
		NBTTagCompound tag = new NBTTagCompound ();
		NBTTagCompound leftNBT = new NBTTagCompound ();
		NBTTagCompound rightNBT = new NBTTagCompound ();
		if (leftLeg != null || rightLeg != null) {
			NBTTagCompound[] nbtLeftLeg = BitHelper.convertCubesToNBT (leftLeg);
			NBTTagCompound[] nbtRightLeg = BitHelper.convertCubesToNBT (rightLeg);
			for (int i = 0; i < nbtLeftLeg.length; i++)
				leftNBT.setTag (Integer.toString (i),nbtLeftLeg[i]);
			tag.setTag (NBT.LEFT_LEG,leftNBT);
			for (int i = 0; i < nbtRightLeg.length; i++)
				rightNBT.setTag (Integer.toString (i),nbtRightLeg[i]);
			tag.setTag (NBT.RIGHT_LEG,rightNBT);
		}
		stack.setTagCompound (tag);
		return stack;
	}

	public static final ItemStack createBootsFromData (CubeData[] leftBoot,CubeData[] rightBoot) {
		ItemStack stack = new ItemStack (VoidRPGItems.armorBoots,1,0);
		NBTTagCompound tag = new NBTTagCompound ();
		NBTTagCompound leftNBT = new NBTTagCompound ();
		NBTTagCompound rightNBT = new NBTTagCompound ();
		if (leftBoot != null || rightBoot != null) {
			NBTTagCompound[] nbtLeftBoot = BitHelper.convertCubesToNBT (leftBoot);
			NBTTagCompound[] nbtRightBoot = BitHelper.convertCubesToNBT (rightBoot);
			for (int i = 0; i < nbtLeftBoot.length; i++)
				leftNBT.setTag (Integer.toString (i),nbtLeftBoot[i]);
			tag.setTag (NBT.LEFT_BOOT,leftNBT);
			for (int i = 0; i < nbtRightBoot.length; i++)
				rightNBT.setTag (Integer.toString (i),nbtRightBoot[i]);
			tag.setTag (NBT.RIGHT_BOOT,rightNBT);
		}
		stack.setTagCompound (tag);
		return stack;
	}

	private static final NBTTagCompound[] getDataFromNBTData (NBTTagCompound nbt) {
		NBTTagCompound[] data = new NBTTagCompound[nbt.getSize ()];
		for (int i = 0; i < nbt.getSize (); i++)
			data[i] = nbt.getCompoundTag (Integer.toString (i));
		return data;
	}

	public static CubeData[] getDataFromStack (ItemStack stack) {
		if (stack.getItem ().equals (VoidRPGItems.armorChestplate)) {
			List <CubeData> chestCubes = new ArrayList <> ();
			Collections.addAll (chestCubes,getDataFromStack (stack,NBT.BODY));
			Collections.addAll (chestCubes,getDataFromStack (stack,NBT.LEFT_ARM));
			Collections.addAll (chestCubes,getDataFromStack (stack,NBT.RIGHT_ARM));
			return chestCubes.toArray (new CubeData[0]);
		} else if (stack.getItem ().equals (VoidRPGItems.armorLeggings)) {
			List <CubeData> legCubes = new ArrayList <> ();
			Collections.addAll (legCubes,getDataFromStack (stack,NBT.LEFT_LEG));
			Collections.addAll (legCubes,getDataFromStack (stack,NBT.RIGHT_LEG));
			return legCubes.toArray (new CubeData[0]);
		} else if (stack.getItem ().equals (VoidRPGItems.armorBoots)) {
			List <CubeData> bootCubes = new ArrayList <> ();
			Collections.addAll (bootCubes,getDataFromStack (stack,NBT.LEFT_BOOT));
			Collections.addAll (bootCubes,getDataFromStack (stack,NBT.RIGHT_BOOT));
			return bootCubes.toArray (new CubeData[0]);
		}
		return getDataFromStack (stack,"");
	}

	// TODO Correctly Find Special Cubes
	public static CubeData[] getDataFromStack (ItemStack stack,String type) {
		ArrayList <CubeData> data = new ArrayList <> ();
		if (type.length () <= 0) {
			if (stack != null && stack.hasTagCompound () && !stack.getTagCompound ().hasNoTags ()) {
				for (int i = 0; i < 4; i++) {
					NBTTagCompound temp = stack.getTagCompound ().getCompoundTag (Integer.toString (i));
					for (int s = 0; s <= temp.getSize (); s++)
						if (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))) != null)
							data.add (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))));
				}
				return data.toArray (new CubeData[0]);
			}
		} else {
			if (stack.getItem ().equals (VoidRPGItems.armorChestplate)) {
				if (type.equalsIgnoreCase (NBT.BODY)) {
					NBTTagCompound bodyNBT = stack.getTagCompound ().getCompoundTag (NBT.BODY);
					for (int i = 0; i < bodyNBT.getSize (); i++) {
						NBTTagCompound temp = bodyNBT.getCompoundTag (Integer.toString (i));
						LogHandler.info ("Chest NBT (" + i + "): "   + temp);
						if (temp.getSize () > 0) {
							for (int s = 0; s <= temp.getSize (); s++)
								if (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))) != null)
									data.add (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))));
						}
						return data.toArray (new CubeData[0]);
					}
				} else if (type.equalsIgnoreCase (NBT.LEFT_ARM)) {
					NBTTagCompound leftArmNBT = stack.getTagCompound ().getCompoundTag (NBT.LEFT_ARM);
					for (int i = 0; i < leftArmNBT.getSize (); i++) {
						NBTTagCompound temp = leftArmNBT.getCompoundTag (Integer.toString (i));
						LogHandler.info ("Left NBT (" + i + "): "   + temp);
						if (temp.getSize () > 0) {
							for (int s = 0; s <= temp.getSize (); s++)
								if (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))) != null)
									data.add (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))));
						}
						return data.toArray (new CubeData[0]);
					}
				} else if (type.equalsIgnoreCase (NBT.RIGHT_ARM)) {
					NBTTagCompound rightArmNBT = stack.getTagCompound ().getCompoundTag (NBT.RIGHT_ARM);
					for (int i = 0; i < 3; i++) {
						NBTTagCompound temp = rightArmNBT.getCompoundTag (Integer.toString (i));
						LogHandler.info ("Right NBT (" + i + "): "   + temp);
						if (temp.getSize () > 0) {
							for (int s = 0; s <= temp.getSize (); s++)
								if (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))) != null)
									data.add (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))));
						}
						return data.toArray (new CubeData[0]);
					}
				}
			} else if (stack.getItem ().equals (VoidRPGItems.armorLeggings)) {
				if (type.equalsIgnoreCase (NBT.LEFT_LEG)) {
					NBTTagCompound leftLegNBT = stack.getTagCompound ().getCompoundTag (NBT.LEFT_LEG);
					for (int i = 0; i < leftLegNBT.getSize (); i++) {
						NBTTagCompound temp = leftLegNBT.getCompoundTag (Integer.toString (i));
						if (temp.getSize () > 0) {
							for (int s = 0; s <= temp.getSize (); s++)
								if (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))) != null)
									data.add (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))));
						}
						return data.toArray (new CubeData[0]);
					}
				} else if (type.equalsIgnoreCase (NBT.RIGHT_LEG)) {
					NBTTagCompound rightLegNBT = stack.getTagCompound ().getCompoundTag (NBT.RIGHT_LEG);
					for (int i = 0; i < rightLegNBT.getSize (); i++) {
						NBTTagCompound temp = rightLegNBT.getCompoundTag (Integer.toString (i));
						if (temp.getSize () > 0) {
							for (int s = 0; s <= temp.getSize (); s++)
								if (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))) != null)
									data.add (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))));
						}
					}
					return data.toArray (new CubeData[0]);
				}
			} else if (stack.getItem ().equals (VoidRPGItems.armorBoots)) {
				if (type.equalsIgnoreCase (NBT.LEFT_BOOT)) {
					NBTTagCompound leftBootNBT = stack.getTagCompound ().getCompoundTag (NBT.LEFT_BOOT);
					for (int i = 0; i < leftBootNBT.getSize (); i++) {
						NBTTagCompound temp = leftBootNBT.getCompoundTag (Integer.toString (i));
						if (temp.getSize () > 0) {
							for (int s = 0; s <= temp.getSize (); s++)
								if (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))) != null)
									data.add (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))));
						}
						return data.toArray (new CubeData[0]);
					}
				} else if (type.equalsIgnoreCase (NBT.RIGHT_BOOT)) {
					NBTTagCompound rightBootNBT = stack.getTagCompound ().getCompoundTag (NBT.RIGHT_BOOT);
					for (int i = 0; i < rightBootNBT.getSize (); i++) {
						NBTTagCompound temp = rightBootNBT.getCompoundTag (Integer.toString (i));
						if (temp.getSize () > 0) {
							for (int s = 0; s <= temp.getSize (); s++)
								if (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))) != null)
									data.add (BitHelper.readCubeDataFromNBT (temp.getCompoundTag (Integer.toString (s))));
						}
					}
					return data.toArray (new CubeData[0]);
				}
			}
		}
		return new CubeData[0];
	}

	public static CubeData[] getEffectCubes (ItemStack stack) {
		ArrayList <CubeData> data = new ArrayList <> ();
		if (stack != null && stack.hasTagCompound () && !stack.getTagCompound ().hasNoTags ()) {
			CubeData[] stackData = getDataFromStack (stack);
			for (CubeData d : stackData)
				if (d != null && d.cube != null && d.cube.hasEffects ())
					data.add (d);
			return data.toArray (new CubeData[0]);
		}
		return new CubeData[0];
	}

	public static double getWeight (ItemStack stack,boolean update) {
		boolean temp = update;
		if (stack != null && stack.hasTagCompound ()) {
			if (!stack.getTagCompound ().hasKey (NBT.WEIGHT))
				temp = true;
			if (temp) {
				CubeData[] cubes = getDataFromStack (stack);
				double total = 0;
				for (CubeData cube : cubes)
					if (cube != null && cube.cube != null)
						total += cube.cube.getWeight ();
				stack.getTagCompound ().setDouble (NBT.WEIGHT,total);
			}
			return Math.round (stack.getTagCompound ().getDouble (NBT.WEIGHT));
		}
		return -1;
	}

	public static int getDurability (ItemStack stack,boolean update) {
		boolean temp = update;
		if (stack != null && stack.hasTagCompound ()) {
			if (!stack.getTagCompound ().hasKey (NBT.DURABILITY))
				temp = true;
			if (temp) {
				CubeData[] cubes = getDataFromStack (stack,NBT.BODY);
				int damage = 0;
				for (CubeData cube : cubes)
					if (cube != null && cube.cube != null)
						damage += cube.damage;
				stack.getTagCompound ().setInteger (NBT.DURABILITY,(getMaxDurability (stack,true) - damage));
			}
			return stack.getTagCompound ().getInteger (NBT.DURABILITY);
		}
		return -1;
	}

	public static int getMaxDurability (ItemStack stack,boolean update) {
		boolean temp = update;
		if (stack != null && stack.hasTagCompound ()) {
			if (!stack.getTagCompound ().hasKey (NBT.MAX_DURABILITY))
				temp = true;
			if (temp) {
				CubeData[] cubes = getDataFromStack (stack);
				int total = 0;
				for (CubeData cube : cubes)
					if (cube != null && cube.cube != null)
						total += cube.cube.getMaxDurability ();
				stack.getTagCompound ().setInteger (NBT.MAX_DURABILITY,total);
			}
			return stack.getTagCompound ().getInteger (NBT.MAX_DURABILITY);
		}
		return -1;
	}

	public static int getComplexity (ItemStack stack,boolean update) {
		boolean temp = update;
		if (stack != null && stack.hasTagCompound ()) {
			if (!stack.getTagCompound ().hasKey (NBT.COMPLEXITY))
				temp = true;
			if (temp) {
				CubeData[] cubes = getDataFromStack (stack);
				int complexity = 0;
				for (CubeData cube : cubes)
					if (cube != null && cube.cube != null)
						complexity += cube.cube.getComplexity ();
				stack.getTagCompound ().setInteger (NBT.COMPLEXITY,complexity);
			}
			return stack.getTagCompound ().getInteger (NBT.COMPLEXITY);
		}
		return -1;
	}

	public static CubeData[][] splitInHalf (CubeData[] data) {
		List <CubeData> left = new ArrayList <> ();
		List <CubeData> right = new ArrayList <> ();
		for (CubeData c : data)
			if (c != null && c.xPos <= 7)
				right.add (c);
			else if (c != null)
				left.add (c);
		CubeData[][] output = new CubeData[][] {left.toArray (new CubeData[0]),right.toArray (new CubeData[0])};
		return output;
	}
}
