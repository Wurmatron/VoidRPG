package wurmatron.voidrpg.common.utils;

import mod.chiselsandbits.api.APIExceptions;
import mod.chiselsandbits.api.IBitAccess;
import mod.chiselsandbits.api.IBitBrush;
import mod.chiselsandbits.core.api.ChiselAndBitsAPI;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.cube.CubeRegistry;

import java.util.ArrayList;

public class BitsHelper {

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
								return true;
						} catch (APIExceptions.CannotBeChiseled e) {
						}
				}
				return false;
		}

		public static boolean areValidBits (IBitBrush bit) {
				if (bit.getItemStack(1) != null && isValid(bit.getState().getBlock()))
						return true;
				return false;
		}

		public static CubeData[] convertBitsToCubes (World world, BlockPos pos) {
				ArrayList<CubeData> data = new ArrayList<CubeData>();
				if (!world.isRemote && new ChiselAndBitsAPI().isBlockChiseled(world, pos))
						try {
								IBitAccess bit = new ChiselAndBitsAPI().getBitAccess(world, pos);
								for (int x = 16; x >= 0; x--)
										for (int y = 16; y >= 0; y--)
												for (int z = 16; z >= 0; z--) {
														if (!bit.getBitAt(x, y, z).isAir()) {
																for (ICube cube : CubeRegistry.cubes)
																		if (cube.getBlock().equals(bit.getBitAt(x, y, z).getState().getBlock()))
																				data.add(new CubeData(x, y, z, cube,0));
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

		public static CubeData[] rotateClockwise (CubeData[] cubes) {
				CubeData[] data = cubes;
				for (int s = 0; s <= cubes.length - 1; s++)
						data[s] = new CubeData((cubes[s].offZ * -1) + 15, cubes[s].offY, cubes[s].offX, cubes[s].cube,cubes[s].damage);
				return data;
		}

		public static CubeData[] rotateUp (CubeData[] cubes) {
				CubeData[] data = cubes;
				for (int s = 0; s <= cubes.length - 1; s++)
						data[s] = new CubeData(cubes[s].offX, (cubes[s].offY * -1) + 15, cubes[s].offZ, cubes[s].cube,cubes[s].damage);
				return data;
		}

		public static boolean isValid (Block block) {
				for (ICube cube : CubeRegistry.INSTANCE.getCubes()) {
						if (cube.getBlock().equals(block))
								return true;
				}
				return false;
		}
}