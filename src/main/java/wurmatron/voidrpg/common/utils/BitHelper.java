package wurmatron.voidrpg.common.utils;

import mod.chiselsandbits.api.APIExceptions;
import mod.chiselsandbits.api.IBitAccess;
import mod.chiselsandbits.api.IBitBrush;
import mod.chiselsandbits.core.api.ChiselAndBitsAPI;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.cube.CubeRegistry;

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
}
