package wurmatron.voidrpg.common.utils;

import mod.chiselsandbits.api.APIExceptions;
import mod.chiselsandbits.api.IBitAccess;
import mod.chiselsandbits.api.IBitBrush;
import mod.chiselsandbits.core.api.ChiselAndBitsAPI;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.cube.CubeRegistry;

import java.util.ArrayList;

public class BitsHelper {

		private static final IBitBrush airBrush = new IBitBrush() {
				@Override
				public boolean isAir () {
						return true;
				}

				@Override
				public IBlockState getState () {
						return null;
				}

				@Override
				public ItemStack getItemStack (int i) {
						return new ItemStack(Blocks.AIR, i, 0);
				}

				@Override
				public int getStateID () {
						return 0;
				}
		};

		private static final IBitBrush bodyBrush = new IBitBrush() {
				@Override
				public boolean isAir () {
						return false;
				}

				@Override
				public IBlockState getState () {
						return VoidRPGBlocks.bodyBlock.getDefaultState();
				}

				@Override
				public ItemStack getItemStack (int i) {
						return null;
				}

				@Override
				public int getStateID () {
						return 0;
				}
		};

		private static final ChiselAndBitsAPI api = new ChiselAndBitsAPI();
		private static final ArrayList<Vec3i> bootsModel = new ArrayList<Vec3i>() {{
				add(new Vec3i(2, 0, 2));
				add(new Vec3i(2, 0, 3));
				add(new Vec3i(2, 0, 4));
				add(new Vec3i(2, 0, 5));
				add(new Vec3i(2, 0, 6));
				add(new Vec3i(2, 0, 7));
				add(new Vec3i(2, 0, 8));
				add(new Vec3i(2, 0, 9));
				add(new Vec3i(2, 0, 10));
				add(new Vec3i(2, 0, 11));
				add(new Vec3i(2, 1, 2));
				add(new Vec3i(2, 2, 2));
				add(new Vec3i(2, 3, 2));
				add(new Vec3i(2, 4, 2));
				add(new Vec3i(2, 5, 2));
				add(new Vec3i(3, 0, 2));
				add(new Vec3i(4, 0, 2));
				add(new Vec3i(5, 0, 2));
				add(new Vec3i(6, 0, 2));
				add(new Vec3i(7, 0, 2));
				add(new Vec3i(8, 0, 2));
				add(new Vec3i(9, 0, 2));
				add(new Vec3i(10, 0, 2));
				add(new Vec3i(11, 0, 2));
		}};

		private static final ArrayList<Vec3i> leggingsModel = new ArrayList<Vec3i>();


		public static boolean isValidBoots (World world, BlockPos pos) {
				if (!world.isRemote && api.isBlockChiseled(world, pos)) {
						ArrayList<Boolean> temp = new ArrayList<Boolean>();
						try {
								IBitAccess bit = api.getBitAccess(world, pos);
								for (int x = 0; x <= 15; x++)
										for (int y = 0; y <= 15; y++)
												for (int z = 0; z <= 15; z++)
														for (Vec3i vec : bootsModel)
																if (vec.equals(new Vec3i(x, y, z))) {
																		if (!bit.getBitAt(x, y, z).isAir() && bit.getBitAt(x, y, z).equals(bodyBrush))
																				temp.add(true);
																		else if (areValidBits(bit.getBitAt(x, y, z))) {
																				temp.add(false);
																		}
																}
						} catch (APIExceptions.CannotBeChiseled e) {
								LogHandler.debug(e.getLocalizedMessage());
						}
						return !temp.contains(false);
				}
				return false;
		}

		public static boolean isValidLeggings (World world, BlockPos pos) {
				if (!world.isRemote && api.isBlockChiseled(world, pos)) {
						ArrayList<Boolean> temp = new ArrayList<Boolean>();
						try {
								IBitAccess bit = api.getBitAccess(world, pos);
								createLeggingsModel();
								for (Vec3i model : leggingsModel) {
										if (!bit.getBitAt(model.getX(), model.getY(), model.getZ()).isAir() && bit.getBitAt(model.getX(), model.getY(), model.getZ()).getState().getBlock().equals(VoidRPGBlocks.bodyBlock))
												temp.add(true);
										else
												temp.add(false);
								}
								return !temp.contains(false);
						} catch (APIExceptions.CannotBeChiseled e) {
								LogHandler.debug(e.getLocalizedMessage());
						}
				}
				return false;
		}

		public static ArrayList<Vec3i> createLeggingsModel () {
				for (int x = 0; x <= 15; x++)
						for (int y = 0; y <= 15; y++)
								for (int z = 0; z <= 15; z++)
										if (x >= 5 && x <= 8 && y <= 12 && z >= 3 && z <= 10)
												leggingsModel.add(new Vec3i(x, y, z));
				return leggingsModel;
		}

		public static ArrayList<ArrayList<CubeData>> createBootsFromBits (World world, BlockPos pos) {
				if (world != null && pos != null && !world.isRemote && isValidBoots(world, pos) && api.isBlockChiseled(world, pos)) {
						try {
								IBitAccess bit = api.getBitAccess(world, pos);
								ArrayList<ArrayList<CubeData>> data = new ArrayList<ArrayList<CubeData>>();
								ArrayList<CubeData> a = new ArrayList<CubeData>();
								ArrayList<CubeData> b = new ArrayList<CubeData>();
								for (int x = 3; x <= 10; x++)
										for (int z = 3; z <= 10; z++)
												for (int y = 1; y <= 6; y++)
														if (areValidBits(bit.getBitAt(x, y, z))) {
																ICube cube = null;
																for (ICube c : CubeRegistry.INSTANCE.getCubes())
																		if (c.getBlock().equals(bit.getBitAt(x, y, z).getState().getBlock()))
																				cube = c;
																if (x <= 6 && cube != null)
																		b.add(new CubeData(x, y, z, cube, 0));
																else
																		a.add(new CubeData(x, y, z, cube, 0));
														}
								data.add(a);
								data.add(b);
								return data;
						} catch (APIExceptions.CannotBeChiseled e) {
								LogHandler.debug(e.getLocalizedMessage());
						}
				}
				return null;
		}

		public static ArrayList<ArrayList<CubeData>> createLeggingsFromBits (World world, BlockPos pos) {
				if (world != null && pos != null && !world.isRemote && isValidLeggings(world, pos) && api.isBlockChiseled(world, pos)) {
						try {
								IBitAccess bit = api.getBitAccess(world, pos);
								ArrayList<ArrayList<CubeData>> data = new ArrayList<ArrayList<CubeData>>();
								ArrayList<CubeData> a = new ArrayList<CubeData>();
								ArrayList<CubeData> b = new ArrayList<CubeData>();
								for (int x = 3; x <= 15; x++)
										for (int z = 3; z <= 15; z++)
												for (int y = 0; y <= 12; y++) {
														if (!bit.getBitAt(x, y, z).isAir())
																if (areValidBits(bit.getBitAt(x, y, z))) {
																		ICube cube = null;
																		for (ICube c : CubeRegistry.INSTANCE.getCubes())
																				if (c.getBlock().equals(bit.getBitAt(x, y, z).getState().getBlock()))
																						cube = c;
																		if (x <= 6 && cube != null)
																				b.add(new CubeData(x, y, z, cube, 0));
																		else
																				a.add(new CubeData(x, y, z, cube, 0));
																}
												}
								data.add(a);
								data.add(b);
								return data;
						} catch (APIExceptions.CannotBeChiseled e) {
								LogHandler.debug(e.getLocalizedMessage());
						}
				}
				return null;
		}

		public static boolean isValidChest () {
				return false;
		}

		public static boolean isValidHelmet () {
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
														if (!bit.getBitAt(x, y, z).isAir() && areValidBits(bit.getBitAt(x, y, z))) {
																for (ICube cube : CubeRegistry.cubes)
																		if (cube.getBlock().equals(bit.getBitAt(x, y, z).getState().getBlock()))
																				data.add(new CubeData(x, y, z, cube, 0));
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
				for (int s = 0; s < cubes.length; s++)
						data[s] = new CubeData((cubes[s].offZ * -1) + 15, cubes[s].offY, cubes[s].offX, cubes[s].cube, cubes[s].damage);
				return data;
		}

		public static CubeData[] rotateUp (CubeData[] cubes) {
				CubeData[] data = cubes;
				for (int s = 0; s < cubes.length; s++)
						data[s] = new CubeData(cubes[s].offX, (cubes[s].offY * -1) + 15, cubes[s].offZ, cubes[s].cube, cubes[s].damage);
				return data;
		}

		public static boolean isValid (Block block) {
				for (ICube cube : CubeRegistry.INSTANCE.getCubes()) {
						if (cube.getBlock().equals(block))
								return true;
				}
				return false;
		}

		public static void createBaseArmorBlock (int armorType, World world, BlockPos pos) {
				if (!world.isRemote) {
						switch (armorType) {
								// Boots
								case (0): {
										world.setBlockState(pos, VoidRPGBlocks.bodyBlock.getDefaultState());
										try {
												IBitAccess block = api.getBitAccess(world, pos);
												createLeggingsModel();
												createModelFromData(block, bootsModel);
										} catch (APIExceptions.CannotBeChiseled e) {
												LogHandler.debug("Cannot chisel block @ " + pos.toString());
										}
								}
								// Leggings
								case (1): {
										world.setBlockState(pos, VoidRPGBlocks.bodyBlock.getDefaultState());
										try {
												IBitAccess block = api.getBitAccess(world, pos);
												createModelFromData(block, leggingsModel);
										} catch (APIExceptions.CannotBeChiseled e) {
												LogHandler.debug("Cannot chisel block @ " + pos.toString());
										}
								}
								// ChestPlate
								case (2): {

								}
								// Helmet
								case (3): {

								}
						}
				}
		}

		public static void createModelFromData (IBitAccess block, ArrayList<Vec3i> data) {
				if (block != null && data != null && data.size() > 0) {
						try {
								for (int x = 0; x <= 15; x++) {
										for (int y = 0; y <= 15; y++)
												for (int z = 0; z <= 15; z++) {
														ArrayList<Boolean> temp = new ArrayList<Boolean>();
														for (Vec3i vec : data) {
																if (!vec.equals(new Vec3i(x, y, z)))
																		temp.add(false);
																else
																		temp.add(true);
														}
														if (!temp.contains(true))
																block.setBitAt(x, y, z, airBrush);
												}
								}
								block.commitChanges(true);
						} catch (APIExceptions.SpaceOccupied e) {
								LogHandler.debug(e.getLocalizedMessage());
						}
				}
		}
}