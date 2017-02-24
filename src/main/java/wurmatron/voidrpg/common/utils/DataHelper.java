package wurmatron.voidrpg.common.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.NBT;

import java.util.ArrayList;

public class DataHelper {

    public static CubeData addOffset(CubeData cubeData, int x, int y, int z) {
        if (cubeData != null && Math.abs(x) > 0 || Math.abs(y) > 0 || Math.abs(z) > 0)
            return new CubeData(cubeData.cube, cubeData.xPos + x, cubeData.yPos + y, cubeData.zPos + z, cubeData.damage);
        return cubeData;
    }

    public static CubeData[] addOffset(CubeData[] cubeData, int x, int y, int z) {
        if (Math.abs(x) > 0 || Math.abs(y) > 0 || Math.abs(z) > 0) {
            CubeData[] temp = new CubeData[cubeData.length];
            for (int index = 0; index < cubeData.length; index++)
                temp[index] = addOffset(cubeData[index], x, y, z);
            return temp;
        }
        return removeNull(cubeData);
    }

    public static CubeData damageCube(CubeData data, int damage) {
        if (Math.abs(damage) > 0)
            return new CubeData(data.cube, data.xPos, data.yPos, data.zPos, data.damage + damage);
        return data;
    }

    public static CubeData damageCube(CubeData data) {
        return damageCube(data, 1);
    }

    public static CubeData[] rotateClockwise(CubeData[] cubes) {
        CubeData[] data = new CubeData[cubes.length];
        for (int s = 0; s < cubes.length; s++)
            if (cubes[s] != null && cubes[s].cube != null)
                data[s] = new CubeData(cubes[s].cube, cubes[s].xPos, cubes[s].yPos, (cubes[s].zPos * -1) + 15, cubes[s].damage);
        return data;
    }

    public static CubeData[] rotateUp(CubeData[] cubes) {
        CubeData[] data = new CubeData[cubes.length];
        for (int s = 0; s < cubes.length; s++)
            if (cubes[s] != null && cubes[s].cube != null)
                data[s] = new CubeData(cubes[s].cube, cubes[s].xPos, (cubes[s].yPos * -1) + 15, cubes[s].zPos, cubes[s].damage);
        return data;
    }

    public static final ItemStack createHelmetFromData(CubeData[] helmetData) {
        ItemStack stack = new ItemStack(VoidRPGItems.armorHelmet, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        if (helmetData != null && helmetData.length > 0) {
            NBTTagCompound[] nbt = BitHelper.convertCubesToNBT(helmetData);
            for (int i = 0; i < nbt.length; i++)
                tag.setTag(Integer.toString(i), nbt[i]);
        }
        stack.setTagCompound(tag);
        return stack;
    }

    // TODO Fix CubeData[] Reader
    public static final ItemStack createChestplateFromData(CubeData[] chest, CubeData[] leftArm, CubeData[] rightArm) {
        ItemStack stack = new ItemStack(VoidRPGItems.armorChestplate, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        NBTTagCompound body = new NBTTagCompound();
        if (chest != null || leftArm != null || rightArm != null) {
            NBTTagCompound[] nbtChest = BitHelper.convertCubesToNBT(chest);
            for (int i = 0; i < nbtChest.length; i++)
                body.setTag(Integer.toString(i), nbtChest[i]);
            tag.setTag(NBT.BODY, body);
        }
        stack.setTagCompound(tag);
        return stack;
    }

    private static final NBTTagCompound[] convertDataToNBT(CubeData[]... cubeData) {
        NBTTagCompound[] nbt = new NBTTagCompound[cubeData.length];
        for (int index = 0; index < cubeData.length; index++) {
            NBTTagCompound tag = new NBTTagCompound();
            if (cubeData[index] != null && cubeData[index].length > 0) {
                NBTTagCompound[] d = BitHelper.convertCubesToNBT(cubeData[index]);
                for (int i = 0; i < d.length; i++)
                    tag.setTag(Integer.toString(i), nbt[i]);
                nbt[index] = tag;
            }
        }
        return nbt;
    }

    private static final NBTTagCompound[] getNBTDataFromStack(ItemStack stack) {
        if (stack != null && stack.getTagCompound() != null) {
            ArrayList<CubeData> data = new ArrayList<>();
            NBTTagCompound[] stackData = getDataFromNBTData(stack.getTagCompound());
            for (int i = 0; i < stackData.length; i++) {
                NBTTagCompound temp = stackData[i];
                for (int s = 0; s <= temp.getSize(); s++)
                    data.add(BitHelper.readCubeDataFromNBT(temp.getCompoundTag(Integer.toString(s))));
            }
        }
        return new NBTTagCompound[0];
    }

    private static final NBTTagCompound[] getDataFromNBTData(NBTTagCompound nbt) {
        NBTTagCompound[] data = new NBTTagCompound[nbt.getSize()];
        for (int i = 0; i < nbt.getSize(); i++)
            data[i] = nbt.getCompoundTag(Integer.toString(i));
        return data;
    }

    public static CubeData[] getDataFromStack(ItemStack stack) {
        return getDataFromStack(stack, "");
    }

    public static CubeData[] getDataFromStack(ItemStack stack, String type) {
        ArrayList<CubeData> data = new ArrayList<>();
        if (type.length() <= 0) {
            if (stack != null && stack.hasTagCompound() && !stack.getTagCompound().hasNoTags()) {
                for (int i = 0; i < stack.getTagCompound().getSize(); i++) {
                    NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(i));
                    for (int s = 0; s <= temp.getSize(); s++)
                        data.add(BitHelper.readCubeDataFromNBT(temp.getCompoundTag(Integer.toString(s))));
                }
                return removeNull(data.toArray(new CubeData[0]));
            }
        } else {
            if (stack.getItem().equals(VoidRPGItems.armorChestplate)) {
                if (type.equalsIgnoreCase(NBT.BODY)) {
                    NBTTagCompound bodyNBT = stack.getTagCompound().getCompoundTag(NBT.BODY);
                    for (int i = 0; i < bodyNBT.getSize(); i++) {
                        NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(i));
                        for (int s = 0; s <= temp.getSize(); s++) {
                            data.add(BitHelper.readCubeDataFromNBT(temp.getCompoundTag(Integer.toString(s))));
                        }
                    }
                    return removeNull(data.toArray(new CubeData[0]));
                }
            }
        }
        return new CubeData[0];
    }

    public static CubeData[] getEffectCubes(ItemStack stack) {
        ArrayList<CubeData> data = new ArrayList<>();
        if (stack != null && stack.hasTagCompound() && !stack.getTagCompound().hasNoTags() && stack.getTagCompound().getTag("1") != null) {
            NBTTagCompound specialCubes = (NBTTagCompound) stack.getTagCompound().getTag("1");
            if (!specialCubes.hasNoTags())
                for (int i = 0; i < specialCubes.getSize(); i++)
                    data.add(BitHelper.readCubeDataFromNBT((NBTTagCompound) specialCubes.getTag(Integer.toString(i))));
            return data.toArray(new CubeData[0]);
        }
        return new CubeData[0];
    }

    private static <T> T[] removeNull(T[] data) {
        ArrayList<T> temp = new ArrayList();
        for (T f : data)
            if (f != null)
                temp.add(f);
        return temp.toArray(data);
    }

    public static double getWeight(ItemStack stack, boolean update) {
        boolean temp = update;
        if (stack != null && stack.hasTagCompound()) {
            if (!stack.getTagCompound().hasKey(NBT.WEIGHT))
                temp = true;
            if (temp) {
                CubeData[] cubes = getDataFromStack(stack);
                double total = 0;
                for (CubeData cube : cubes)
                    if (cube != null && cube.cube != null)
                        total += cube.cube.getWeight();
                stack.getTagCompound().setDouble(NBT.WEIGHT, total);
            }
            return Math.round(stack.getTagCompound().getDouble(NBT.WEIGHT));
        }
        return -1;
    }

    public static int getDurability(ItemStack stack, boolean update) {
        boolean temp = update;
        if (stack != null && stack.hasTagCompound()) {
            if (!stack.getTagCompound().hasKey(NBT.DURABILITY))
                temp = true;
            if (temp) {
                CubeData[] cubes = getDataFromStack(stack);
                int damage = 0;
                for (CubeData cube : cubes)
                    if (cube != null && cube.cube != null)
                        damage += cube.damage;
                stack.getTagCompound().setInteger(NBT.DURABILITY, (getMaxDurability(stack, true) - damage));
            }
            return stack.getTagCompound().getInteger(NBT.DURABILITY);
        }
        return -1;
    }

    public static int getMaxDurability(ItemStack stack, boolean update) {
        boolean temp = update;
        if (stack != null && stack.hasTagCompound()) {
            if (!stack.getTagCompound().hasKey(NBT.MAX_DURABILITY))
                temp = true;
            if (temp) {
                CubeData[] cubes = getDataFromStack(stack);
                int total = 0;
                for (CubeData cube : cubes)
                    if (cube != null && cube.cube != null)
                        total += cube.cube.getMaxDurability();
                stack.getTagCompound().setInteger(NBT.MAX_DURABILITY, total);
            }
            return stack.getTagCompound().getInteger(NBT.MAX_DURABILITY);
        }
        return -1;
    }

    public static int getComplexity(ItemStack stack, boolean update) {
        boolean temp = update;
        if (stack != null && stack.hasTagCompound()) {
            if (!stack.getTagCompound().hasKey(NBT.COMPLEXITY))
                temp = true;
            if (temp) {
                CubeData[] cubes = getDataFromStack(stack);
                int complexity = 0;
                for (CubeData cube : cubes)
                    if (cube != null && cube.cube != null)
                        complexity += cube.cube.getComplexity();
                stack.getTagCompound().setInteger(NBT.COMPLEXITY, complexity);
            }
            return stack.getTagCompound().getInteger(NBT.COMPLEXITY);
        }
        return -1;
    }
}
