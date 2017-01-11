package wurmatron.voidrpg.common.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import wurmatron.voidrpg.api.cube.CubeData;
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

    // TODO Needs support for multiple storage types not just one
    // Example: Chestplate needs Body, Left and Right Arm == 3
    public static ItemStack addDataToStack(ItemStack stack, CubeData[] data) {
        NBTTagCompound tag = new NBTTagCompound();
        if (data != null && data.length > 0) {
            NBTTagCompound[] nbt = BitHelper.convertCubesToNBT(data);
            for (int i = 0; i < nbt.length; i++)
                tag.setTag(Integer.toString(i), nbt[i]);
        }
        stack.setTagCompound(tag);
        return stack;
    }

    public static CubeData[] getDataFromStack(ItemStack stack) {
        ArrayList<CubeData> data = new ArrayList<>();
        if (stack != null && stack.hasTagCompound() && !stack.getTagCompound().hasNoTags()) {
            for (int i = 0; i < stack.getTagCompound().getSize(); i++) {
                NBTTagCompound temp = stack.getTagCompound().getCompoundTag(Integer.toString(i));
                for (int s = 0; s < temp.getSize(); s++)
                    data.add(BitHelper.readCubeDataFromNBT(temp.getCompoundTag(Integer.toString(s))));
            }
            return removeNull(data.toArray(new CubeData[0]));
        }
        return new CubeData[0];
    }

    public static CubeData[] getEffectCubes(ItemStack stack) {
        ArrayList<CubeData> data = new ArrayList<>();
        if (stack != null && stack.hasTagCompound() && !stack.getTagCompound().hasNoTags()) {
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
            return stack.getTagCompound().getDouble(NBT.WEIGHT);
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
