package wurmatron.voidrpg.common.utils;

import mod.chiselsandbits.api.APIExceptions;
import mod.chiselsandbits.api.IBitAccess;
import mod.chiselsandbits.api.IBitBrush;
import mod.chiselsandbits.core.api.ChiselAndBitsAPI;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.api.cube.IEnergy;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.NBT;

import java.util.ArrayList;
import java.util.Collections;

public class BitHelper {

    private static final ChiselAndBitsAPI api = new ChiselAndBitsAPI();

    private static final IBitBrush bodyBrush = new IBitBrush() {
        @Override
        public boolean isAir() {
            return true;
        }

        @Override
        public IBlockState getState() {
            return Blocks.PLANKS.getDefaultState();
        }

        @Override
        public ItemStack getItemStack(int i) {
            return new ItemStack(Blocks.PLANKS, i, 0);
        }

        @Override
        public int getStateID() {
            return 0;
        }
    };

    /**
     * Checks too see if the model is valid along with no invalid cubes.
     * Note: This does not check for the border size
     */
    public static boolean hasValidModel(World world, BlockPos pos, Vec3i[] model) {
        if (world != null && pos != null && model != null && model.length > 0 && api.isBlockChiseled(world, pos)) {
            ArrayList<Boolean> temp = new ArrayList<>();
            ArrayList<Boolean> modelTest = new ArrayList<>();
            for (int x = 0; x <= 15; x++)
                for (int y = 0; y <= 15; y++)
                    for (int z = 0; z <= 15; z++)
                        try {
                            IBitAccess bit = api.getBitAccess(world, pos);
                            if (!bit.getBitAt(x, y, z).isAir()) {
                                if (areValidBits(bit.getBitAt(x, y, z)))
                                    temp.add(true);
                                else if (!bit.getBitAt(x, y, z).getState().getBlock().equals(bodyBrush.getState().getBlock()))
                                    temp.add(false);
                            }
                        } catch (APIExceptions.CannotBeChiseled e) {
                            e.printStackTrace();
                        }
            try {
                IBitAccess bit = api.getBitAccess(world, pos);
                for (Vec3i vec : model) {
                    if (!bit.getBitAt(vec.getX(), vec.getY(), vec.getZ()).isAir() && bit.getBitAt(vec.getX(), vec.getY(), vec.getZ()).getState().getBlock().equals(bodyBrush.getState().getBlock()))
                        modelTest.add(true);
                    else
                        modelTest.add(false);
                }
            } catch (APIExceptions.CannotBeChiseled e) {
                e.printStackTrace();
            }
            int modelValues = 0;
            for (Boolean t : modelTest) {
                LogHandler.info("Model Test: " + t);
                if (t)
                    modelValues++;
            }
            if (!temp.contains(false) && modelValues == model.length)
                return true;
        }
        return false;
    }

    public static CubeData[] getDataFromModel(World world, BlockPos pos, Vec3i[] model, int maxX, int maxY, int maxZ, Vec3i center) {
        CubeData[] data = createDataFromModel(world, pos);
        ArrayList<CubeData> validCubes = new ArrayList<>();
        Vec3i[] inverseModel = inverseModel(model);
        for (CubeData c : data)
            for (Vec3i neg : inverseModel)
                if (c.xPos == neg.getX() && c.yPos == neg.getY() && c.zPos == neg.getZ() && c.xPos >= maxX + center.getX() && c.yPos >= maxY + center.getY() && c.zPos >= maxZ + center.getZ())
                    validCubes.add(c);
        return data;
    }

    public static CubeData[] createDataFromModel(World world, BlockPos pos) {
        ArrayList<CubeData> data = new ArrayList<>();
        if (!world.isRemote && api.isBlockChiseled(world, pos))
            try {
                IBitAccess bit = api.getBitAccess(world, pos);
                for (int x = 16; x >= 0; x--)
                    for (int y = 16; y >= 0; y--)
                        for (int z = 16; z >= 0; z--) {
                            if (!bit.getBitAt(x, y, z).isAir() && areValidBits(bit.getBitAt(x, y, z))) {
                                for (ICube cube : CubeRegistry.getCubes())
                                    if (cube.getBlock().equals(bit.getBitAt(x, y, z).getState().getBlock()))
                                        data.add(new CubeData(cube, x, y, z, 0));
                            }
                        }
            } catch (Exception e) {
                e.printStackTrace();
            }
        CubeData[] cubes = new CubeData[data.size()];
        for (int c = 0; c <= data.size() - 1; c++)
            cubes[c] = data.get(c);
        return cubes;
    }

//    public static ArrayList<CubeData> createDataFromModel(World world, BlockPos pos, Vec3i[] model, int border) {
//        if (model.length > 0 && border > 0 && hasValidModel(world, pos, model)) {
//            ArrayList<CubeData> data = new ArrayList<>();
//            for (Vec3i vec : model) {
//                try {
//                    IBitAccess bit = api.getBitAccess(world, pos);
//                    for (int x = 1; x < border; x += 2)
//                        for (int y = 1; y < border; y += 2)
//                            for (int z = 1; z < border; z += 2) {
//                                LogHandler.info("X " + x + " Y " + y + " Z " + z);
//                                data.add(new CubeData(getCubeFromBit(bit.getBitAt(vec.getX() + x, vec.getY() + y, vec.getZ() + z)), vec.getX() + x, vec.getY() + y, vec.getZ() + z, 0));
////                                data.add(new CubeData(getCubeFromBit(bit.getBitAt(vec.getX() - x, vec.getY() - y, vec.getZ() - z)), vec.getX() - x, vec.getY() - y, vec.getZ() - z, 0));
////                                data.add(new CubeData(getCubeFromBit(bit.getBitAt(vec.getX() - x, vec.getY() + y, vec.getZ() + z)), vec.getX() - x, vec.getY() + y, vec.getZ() + z, 0));
////                                data.add(new CubeData(getCubeFromBit(bit.getBitAt(vec.getX() + x, vec.getY() - y, vec.getZ() + z)), vec.getX() + x, vec.getY() - y, vec.getZ() + z, 0));
////                                data.add(new CubeData(getCubeFromBit(bit.getBitAt(vec.getX() - x, vec.getY() + y, vec.getZ() - z)), vec.getX() + x, vec.getY() + y, vec.getZ() - z, 0));
////                                data.add(new CubeData(getCubeFromBit(bit.getBitAt(vec.getX() - x, vec.getY() - y, vec.getZ() + z)), vec.getX() - x, vec.getY() - y, vec.getZ() + z, 0));
////                                data.add(new CubeData(getCubeFromBit(bit.getBitAt(vec.getX() + x, vec.getY() - y, vec.getZ() + z)), vec.getX() + x, vec.getY() - y, vec.getZ() - z, 0));
////                                data.add(new CubeData(getCubeFromBit(bit.getBitAt(vec.getX() - x, vec.getY() - y, vec.getZ() - z)), vec.getX() - x, vec.getY() - y, vec.getZ() - z, 0));
//                            }
//                } catch (APIExceptions.CannotBeChiseled e) {
//                    e.printStackTrace();
//                }
//            }
//            return data;
//        }
//        return new ArrayList<>();
//    }

//    public static ArrayList<CubeData> createDataFromModel(World world, BlockPos pos, Vec3i[] model, int border) {
//        if (hasValidModel(world,pos,model) && model.length > 0 && border > 0) {
//            ArrayList<CubeData> data = new ArrayList<>();
//            for (Vec3i vec : model)
//                try {
//                    IBitAccess bit = api.getBitAccess(world, pos);
//                    if (areValidBits(bit.getBitAt(vec.getX(), vec.getY(), vec.getZ())) || bit.getBitAt(vec.getX(), vec.getY(), vec.getZ()).equals(bodyBrush))
//                        for (int size = 0; size <= border; size++)
//                            Collections.addAll(data, createFillData(getCubeFromBit(bit.getBitAt(vec.getX(), vec.getY(), vec.getZ())), vec.getX(), vec.getY(), vec.getZ(), size));
//                    return data;
//                } catch (APIExceptions.CannotBeChiseled e) {
//                    e.printStackTrace();
//                }
//        }
//        LogHandler.info("Null data");
//        return new ArrayList<>();
//    }

    private static boolean areValidBits(IBitBrush bit) {
        for (ICube cube : CubeRegistry.getCubes())
            if (cube != null)
                if (cube.getBlock() != null)
                    if (bit.getState() != null && bit.getState().getBlock() != null)
                        if (cube.getBlock().equals(bit.getState().getBlock()))
                            if (bit.getItemStack(1) != null)
                                return true;
        return false;
    }

    private static ICube getCubeFromBit(IBitBrush bit) {
        if (!bit.isAir())
            for (ICube cube : CubeRegistry.getCubes())
                if (cube.getBlock().equals(bit.getState().getBlock()) && bit.getItemStack(1) != null)
                    return cube;
        return null;
    }

    /**
     * Returns a sorted NBT version of the CubeData[]
     * 0 = default cubes
     * 1 = special / event cubes
     * 2 = energy storage cubes
     * 3 = energy production cubes
     */
    public static NBTTagCompound[] convertCubesToNBT(CubeData[] data) {
        NBTTagCompound defaultCubes = new NBTTagCompound();
        NBTTagCompound specialCubes = new NBTTagCompound();
        NBTTagCompound energyStorageCubes = new NBTTagCompound();
        NBTTagCompound energyProductionCubes = new NBTTagCompound();
        for (CubeData cube : data) {
            if (cube != null && cube.cube != null)
                if (cube.cube.hasEffects()) {
                    NBTTagCompound nbt = convertCubeDataToNBT(cube);
                    if (!nbt.hasNoTags())
                        specialCubes.setTag(Integer.toString(specialCubes.getSize() + 1), nbt);
                } else if (cube.cube instanceof IEnergy) {
                    IEnergy energy = (IEnergy) cube.cube;
                    if (energy.getStorage() > 0) {
                        NBTTagCompound nbt = convertCubeDataToNBT(cube);
                        if (!nbt.hasNoTags())
                            energyStorageCubes.setTag(Integer.toString(energyStorageCubes.getSize() + 1), nbt);
                    } else if (energy.getProductionAmount() > 0) {
                        NBTTagCompound nbt = convertCubeDataToNBT(cube);
                        if (!nbt.hasNoTags())
                            energyProductionCubes.setTag(Integer.toString(energyProductionCubes.getSize() + 1), nbt);
                    }
                } else {
                    NBTTagCompound nbt = convertCubeDataToNBT(cube);
                    LogHandler.info("NBT Default: " + nbt.toString());
                    if (!nbt.hasNoTags())
                        defaultCubes.setTag(Integer.toString(defaultCubes.getSize() + 1), nbt);
                }
        }
        if (defaultCubes.getSize() > 0)
            return new NBTTagCompound[]{defaultCubes, specialCubes, energyStorageCubes, energyProductionCubes};
        return new NBTTagCompound[0];
    }

    public static NBTTagCompound convertCubeDataToNBT(CubeData data) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger(NBT.CUBE_ID, CubeRegistry.getIDForCube(data.cube));
        nbt.setInteger(NBT.CUBE_X, data.xPos);
        nbt.setInteger(NBT.CUBE_Y, data.yPos);
        nbt.setInteger(NBT.CUBE_Z, data.zPos);
        nbt.setInteger(NBT.CUBE_DAMAGE, data.damage);
        if (nbt.getInteger(NBT.CUBE_ID) != -1)
            return nbt;
        return new NBTTagCompound();
    }

    public static CubeData readCubeDataFromNBT(NBTTagCompound nbt) {
        if (!nbt.hasNoTags())
            return new CubeData(CubeRegistry.getCubeFromID(nbt.getInteger(NBT.CUBE_ID)), nbt.getInteger(NBT.CUBE_X), nbt.getInteger(NBT.CUBE_Y), nbt.getInteger(NBT.CUBE_Z), nbt.getInteger(NBT.CUBE_DAMAGE));
        return null;
    }

    @SideOnly(Side.CLIENT)
    public static ModelRenderer createModelRenderer(ModelBiped base, CubeData data) {
        ModelRenderer model = new ModelRenderer(base) {
            @Override
            public void render(float scale) {
                Minecraft.getMinecraft().renderEngine.bindTexture(data.cube.getTexture());
                super.render(scale);
                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Global.MODID, "textures/armor/armor.png"));
            }
        };
        model.addBox(data.xPos, data.yPos, data.zPos, 1, 1, 1, 0.00001f);
        return model;
    }

    private static Vec3i[] inverseModel(Vec3i[] model) {
        ArrayList<Vec3i> vec = new ArrayList<>();
        Collections.addAll(vec, model);
        ArrayList<Vec3i> output = new ArrayList<>();
        for (int x = 0; x < 15; x++)
            for (int y = 0; y < 15; y++)
                for (int z = 0; z < 15; z++)
                    if (!vec.contains(new Vec3i(x, y, z)))
                        output.add(new Vec3i(x, y, z));
        return output.toArray(new Vec3i[0]);
    }
}
