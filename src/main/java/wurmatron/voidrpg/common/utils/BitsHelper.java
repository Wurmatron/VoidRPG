package wurmatron.voidrpg.common.utils;

import mod.chiselsandbits.api.APIExceptions;
import mod.chiselsandbits.api.IBitAccess;
import mod.chiselsandbits.api.IBitBrush;
import mod.chiselsandbits.core.api.ChiselAndBitsAPI;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import wurmatron.voidrpg.api.cube.Cube;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.cube.CubeRegistry;

import java.util.ArrayList;

public class BitsHelper {

		private static ArrayList<Block> validBlocks = new ArrayList<Block>() {{
				for (ICube cube : CubeRegistry.cubes)
						add(cube.getBlock());
		}};

		public static boolean isValidHelmet (World world, BlockPos pos) {
				if (!world.isRemote && new ChiselAndBitsAPI().isBlockChiseled(world, pos)) {
						try {
								IBitAccess bit = new ChiselAndBitsAPI().getBitAccess(world, pos);
								for (int x = 5; x <= 12; x++) {
										for (int y = 5; y <= 12; y++) {
												for (int z = 5; z <= 12; z++) {
														if (!bit.getBitAt(x, y, z).isAir()) {
																if (areValidBits(bit.getBitAt(x, y, z)))
																		LogHandler.debug("Bit at " + x + "," + y + "," + z + " is valid");
																else {
																		LogHandler.debug("Bit at " + x + "," + y + "," + z + " is invalid");
																		return false;
																}
														} else
																return false;
												}
										}
								}
								LogHandler.info("Valid Helmet Found");
								return true;
						} catch (APIExceptions.CannotBeChiseled e) {
						}
				}
				return false;
		}

		public static boolean areValidBits (IBitBrush bit) {
				if (bit.getItemStack(1) != null && validBlocks.contains(bit.getState().getBlock()))
						return true;
				return false;
		}

		public static CubeData[] convertBitsToCubes (World world, BlockPos pos) {
				ArrayList<CubeData> data = new ArrayList<CubeData>();
				if (!world.isRemote && new ChiselAndBitsAPI().isBlockChiseled(world, pos))
						try {
								IBitAccess bit = new ChiselAndBitsAPI().getBitAccess(world, pos);
								for (int x = 0; x <= 16; x++)
										for (int y = 0; y <= 16; y++)
												for (int z = 0; z <= 16; z++) {
														if (!bit.getBitAt(x, y, z).isAir()) {
																int spacer = 10;
																for (Cube cube : CubeRegistry.cubes)
																		if (cube.getBlock().equals(bit.getBitAt(x, y, z).getState().getBlock()))
																				data.add(new CubeData(x - spacer + 3, y - 15, z - spacer, cube));
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
}