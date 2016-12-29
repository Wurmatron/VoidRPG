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
            for (int x = 0; x <= 15; x++)
                for (int y = 0; y <= 15; y++)
                    for (int z = 0; z <= 15; z++)
                        try {
                            IBitAccess bit = api.getBitAccess(world, pos);
                            if (!bit.getBitAt(x, y, z).isAir() && !bit.getBitAt(x, y, z).getState().getBlock().equals(bodyBrush.getState().getBlock()) || !bit.getBitAt(x, y, z).isAir() && areValidBits(bit.getBitAt(x, y, z)) || !bit.getBitAt(x, y, z).getState().getBlock().equals(bodyBrush.getState().getBlock()))
                                temp.add(true);
                            else if (!bit.getBitAt(x, y, z).isAir() && !bit.getBitAt(x, y, z).getState().getBlock().equals(bodyBrush.getState().getBlock()))
                                temp.add(false);
                        } catch (APIExceptions.CannotBeChiseled e) {
                            e.printStackTrace();
                        }
            if (!temp.contains(false))
                return true;
        }
        return false;
    }

    public static ArrayList<CubeData> createDataFromModel(World world, BlockPos pos, Vec3i[] model, int border) {
        if (model.length > 0 && border > 0) {
            ArrayList<CubeData> data = new ArrayList<>();
            for (Vec3i vec : model)
                try {
                    IBitAccess bit = api.getBitAccess(world, pos);
                    LogHandler.info("Model " + areValidBits(bit.getBitAt(vec.getX(), vec.getY(), vec.getZ())));
                    if (areValidBits(bit.getBitAt(vec.getX(), vec.getY(), vec.getZ())) || bit.getBitAt(vec.getX(), vec.getY(), vec.getZ()).equals(bodyBrush))
                        for (int size = 0; size <= border; size++)
                            Collections.addAll(data, createFillData(getCubeFromBit(bit.getBitAt(vec.getX(), vec.getY(), vec.getZ())), vec.getX(), vec.getY(), vec.getZ(), size));
                    return data;
                } catch (APIExceptions.CannotBeChiseled e) {
                    e.printStackTrace();
                }
        }
        LogHandler.info("Null data");
        return new ArrayList<>();
    }

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
        for (ICube cube : CubeRegistry.getCubes())
            if (cube.getBlock().equals(bit.getState().getBlock()) && bit.getItemStack(1) != null)
                return cube;
        return null;
    }

    private static CubeData[] createFillData(ICube cube, int x, int y, int z, int s) {
        CubeData[] data = new CubeData[25];
        data[0] = new CubeData(cube, x, y, z, 0);
        data[1] = new CubeData(cube, x + s, y, z, 0);
        data[2] = new CubeData(cube, x - s, y, z, 0);
        data[3] = new CubeData(cube, x, y + s, z, 0);
        data[4] = new CubeData(cube, x, y - s, z, 0);
        data[5] = new CubeData(cube, x + s, y + s, z, 0);
        data[6] = new CubeData(cube, x + s, y - s, z, 0);
        data[7] = new CubeData(cube, x - s, y + s, z, 0);
        data[8] = new CubeData(cube, x, y, z + s, 0);
        data[9] = new CubeData(cube, x, y, z - s, 0);
        data[10] = new CubeData(cube, x + s, y, z + s, 0);
        data[11] = new CubeData(cube, x - s, y, z + s, 0);
        data[12] = new CubeData(cube, x, y + s, z + s, 0);
        data[13] = new CubeData(cube, x, y - s, z + s, 0);
        data[14] = new CubeData(cube, x + s, y, z - s, 0);
        data[15] = new CubeData(cube, x - s, y, z - s, 0);
        data[16] = new CubeData(cube, x, y + s, z - s, 0);
        data[17] = new CubeData(cube, x, y - s, z - s, 0);
        data[19] = new CubeData(cube, x + s, y + s, z + s, 0);
        data[20] = new CubeData(cube, x - s, y + s, z + s, 0);
        data[21] = new CubeData(cube, x - s, y - s, z + s, 0);
        data[22] = new CubeData(cube, x + s, y + 1, z - s, 0);
        data[23] = new CubeData(cube, x - s, y + s, z - s, 0);
        data[24] = new CubeData(cube, x - s, y - s, z - s, 0);
        return data;
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
        for (CubeData cube : data)
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
                if (!nbt.hasNoTags())
                    defaultCubes.setTag(Integer.toString(defaultCubes.getSize() + 1), nbt);
            }
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
}
