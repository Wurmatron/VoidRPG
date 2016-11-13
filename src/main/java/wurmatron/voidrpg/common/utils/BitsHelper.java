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

		private static final ArrayList<Vec3i> leggingsModel = new ArrayList<>();
		private static ArrayList<Vec3i> chestplateChestModel = new ArrayList<>();
		private static ArrayList<Vec3i> chestplateArmModel = new ArrayList<>();
		private static ArrayList<Vec3i> helmetModel = new ArrayList<>();

		public static boolean isValidBoots (World world, BlockPos pos) {
				if (!world.isRemote && api.isBlockChiseled(world, pos)) {
						ArrayList<Boolean> temp = new ArrayList<>();
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
						ArrayList<Boolean> temp = new ArrayList<>();
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

		public static boolean isValidHelmet (World world, BlockPos pos) {
				if (!world.isRemote && api.isBlockChiseled(world, pos)) {
						ArrayList<Boolean> temp = new ArrayList<>();
						try {
								IBitAccess bit = api.getBitAccess(world, pos);
								createHelmetModel();
								for (Vec3i model : helmetModel) {
										if (!bit.getBitAt(model.getX(), model.getY(), model.getZ()).isAir() && bit.getBitAt(model.getX(), model.getY(), model.getZ()).getState().getBlock().equals(VoidRPGBlocks.bodyBlock))
												temp.add(true);
										else
												temp.add(false);
								}
						} catch (APIExceptions.CannotBeChiseled e) {
								LogHandler.info(e.getLocalizedMessage());
						}
						return !temp.contains(false);
				}
				return false;
		}

		public boolean isValidChestplate (World world, BlockPos chest, BlockPos leftArm, BlockPos rightArm) {
				if (!world.isRemote && new ChiselAndBitsAPI().isBlockChiseled(world, chest) && new ChiselAndBitsAPI().isBlockChiseled(world, leftArm) && new ChiselAndBitsAPI().isBlockChiseled(world, rightArm)) {
						LogHandler.debug("Phase 1 true");
						ArrayList<Boolean> temp = new ArrayList<>();
						try {
								IBitAccess chestBlock = new ChiselAndBitsAPI().getBitAccess(world, chest);
								IBitAccess leftArmBlock = new ChiselAndBitsAPI().getBitAccess(world, chest);
								IBitAccess rightArmBlock = new ChiselAndBitsAPI().getBitAccess(world, rightArm);
								for (Vec3i base : chestplateChestModel)
										if (chestBlock.getBitAt(base.getX(), base.getY(), base.getZ()).getState().getBlock().equals(bodyBrush.getState().getBlock()))
												temp.add(true);
										else
												temp.add(false);
								LogHandler.debug("Phase 2 " + !temp.contains(false));
								for (Vec3i base : chestplateArmModel)
										if (!leftArmBlock.getBitAt(base.getX(), base.getY(), base.getZ()).isAir() && !rightArmBlock.getBitAt(base.getX(), base.getY(), base.getZ()).isAir())
												if (leftArmBlock.getBitAt(base.getX(), base.getY(), base.getZ()).getState().getBlock().equals(bodyBrush.getState().getBlock()) && rightArmBlock.getBitAt(base.getX(), base.getY(), base.getZ()).getState().getBlock().equals(bodyBrush.getState().getBlock()))
														temp.add(true);
												else
														temp.add(false);
								LogHandler.debug("Phase 3 " + !temp.contains(false));
						} catch (APIExceptions.CannotBeChiseled e) {
								LogHandler.info(e.getLocalizedMessage());
						}
						LogHandler.debug("Phase 4 " + !temp.contains(false));
						return !temp.contains(false);
				}
				return false;
		}

		private static ArrayList<Vec3i> createLeggingsModel () {
				for (int x = 0; x <= 15; x++)
						for (int y = 0; y <= 15; y++)
								for (int z = 0; z <= 15; z++)
										if (x >= 5 && x <= 8 && y <= 12 && z >= 3 && z <= 10)
												leggingsModel.add(new Vec3i(x, y, z));
				return leggingsModel;
		}

		private static ArrayList<Vec3i> createHelmetModel () {
				for (int x = 3; x <= 15; x++)
						for (int y = 0; y <= 15; y++)
								for (int z = 3; z <= 15; z++)
										if (x < 12 && y < 8 && z < 12)
												helmetModel.add(new Vec3i(x, y, z));
				return helmetModel;
		}

		public static ArrayList<ArrayList<CubeData>> createBootsFromBits (World world, BlockPos pos) {
				if (world != null && pos != null && !world.isRemote && isValidBoots(world, pos) && api.isBlockChiseled(world, pos)) {
						try {
								IBitAccess bit = api.getBitAccess(world, pos);
								ArrayList<ArrayList<CubeData>> data = new ArrayList<>();
								ArrayList<CubeData> a = new ArrayList<>();
								ArrayList<CubeData> b = new ArrayList<>();
								for (int x = 3; x <= 10; x++)
										for (int z = 3; z <= 10; z++)
												for (int y = 1; y <= 6; y++)
														if (areValidBits(bit.getBitAt(x, y, z))) {
																ICube cube = null;
																for (ICube c : CubeRegistry.INSTANCE.getCubes())
																		if (c.getBlock().equals(bit.getBitAt(x, y, z).getState().getBlock()))
																				cube = c;
																if (x <= 6 && cube != null)
																		a.add(new CubeData(x, y, z, cube, 0));
																else
																		b.add(new CubeData(x, y, z, cube, 0));
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
								ArrayList<ArrayList<CubeData>> data = new ArrayList<>();
								ArrayList<CubeData> a = new ArrayList<>();
								ArrayList<CubeData> b = new ArrayList<>();
								for (int x = 1; x <= 15; x++)
										for (int z = 1; z <= 15; z++)
												for (int y = 0; y <= 12; y++) {
														if (!bit.getBitAt(x, y, z).isAir())
																if (areValidBits(bit.getBitAt(x, y, z))) {
																		ICube cube = null;
																		for (ICube c : CubeRegistry.INSTANCE.getCubes())
																				if (c.getBlock().equals(bit.getBitAt(x, y, z).getState().getBlock()))
																						cube = c;
																		if (z >= 7 && cube != null) {
																				a.add(new CubeData(x, y, z, cube, 0));
																		} else {
																				b.add(new CubeData(x, y, z, cube, 0));
																		}
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

		public static ArrayList<ArrayList<CubeData>> createChestplateFromBit (World world, BlockPos body, BlockPos leftArm, BlockPos rightArm) {
				if (world != null && body != null && leftArm != null && rightArm != null && !world.isRemote && api.isBlockChiseled(world, body) && api.isBlockChiseled(world, leftArm) && api.isBlockChiseled(world, rightArm)) {
						try {
								IBitAccess bodyBit = api.getBitAccess(world, body);
								IBitAccess leftArmBit = api.getBitAccess(world, leftArm);
								IBitAccess rightArmBit = api.getBitAccess(world, rightArm);
								ArrayList<ArrayList<CubeData>> data = new ArrayList<>();
								ArrayList<CubeData> bodyData = new ArrayList<>();
								ArrayList<CubeData> leftArmData = new ArrayList<>();
								ArrayList<CubeData> rightArmData = new ArrayList<>();
								for (int x = 2; x <= 15; x++)
										for (int y = 0; y <= 15; y++)
												for (int z = 2; z < 14; z++)
														if (areValidBits(bodyBit.getBitAt(x, y, z))) {
																ICube cube = null;
																for (ICube c : CubeRegistry.INSTANCE.getCubes())
																		if (c.getBlock().equals(bodyBit.getBitAt(x, y, z).getState().getBlock()))
																				cube = c;
																bodyData.add(new CubeData(x, y, z, cube, 0));
														}
								for (int x = 2; x <= 14; x++)
										for (int y = 0; y <= 15; y++)
												for (int z = 2; z <= 14; z++) {
														if (areValidBits(leftArmBit.getBitAt(x, y, z))) {
																ICube cube = null;
																for (ICube c : CubeRegistry.INSTANCE.getCubes())
																		if (c.getBlock().equals(leftArmBit.getBitAt(x, y, z).getState().getBlock()))
																				cube = c;
																leftArmData.add(new CubeData(x, y, z, cube, 0));
														}
														if (areValidBits(rightArmBit.getBitAt(x, y, z))) {
																ICube cube = null;
																for (ICube c : CubeRegistry.INSTANCE.getCubes())
																		if (c.getBlock().equals(rightArmBit.getBitAt(x, y, z).getState().getBlock()))
																				cube = c;
																rightArmData.add(new CubeData(x, y, z, cube, 0));
														}
												}
								data.add(bodyData);
								data.add(leftArmData);
								data.add(rightArmData);
								LogHandler.info("Data: " + data.toString());
								return data;
						} catch (APIExceptions.CannotBeChiseled e) {
								LogHandler.debug(e.getLocalizedMessage());
						}
				}
				return null;
		}

		private static final ArrayList<Vec3i> createChestplateChestModel () {
				ArrayList<Vec3i> temp = new ArrayList<>();
				for (int x = 0; x <= 15; x++)
						for (int y = 0; y <= 15; y++)
								for (int z = 0; z <= 15; z++) {
										if (x >= 5 && x <= 12 && z >= 6 && z < 10 && y <= 12) {
												temp.add(new Vec3i(x, y, z));
										}
								}
				chestplateChestModel = temp;
				return temp;
		}

		private static final ArrayList<Vec3i> createChestplateArmModel () {
				ArrayList<Vec3i> temp = new ArrayList<>();
				for (int x = 0; x <= 15; x++)
						for (int y = 0; y <= 15; y++)
								for (int z = 0; z <= 15; z++)
										if (x >= 7 && z >= 7 && y <= 12 && x <= 10 && z <= 10)
												temp.add(new Vec3i(x, y, z));
				chestplateArmModel = temp;
				return temp;
		}

		private static boolean areValidBits (IBitBrush bit) {
				return bit.getItemStack(1) != null && isValid(bit.getState().getBlock());
		}

		public static CubeData[] convertBitsToCubes (World world, BlockPos pos) {
				ArrayList<CubeData> data = new ArrayList<>();
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

		public static ArrayList<CubeData> createHelmetFromBits (World world, BlockPos pos) {
				if (world != null && pos != null && !world.isRemote && isValidHelmet(world, pos) && api.isBlockChiseled(world, pos)) {
						ArrayList<CubeData> temp = new ArrayList<>();
						try {
								IBitAccess bit = api.getBitAccess(world, pos);
								for (int x = 0; x <= 15; x++)
										for (int y = 0; y <= 15; y++)
												for (int z = 0; z <= 15; z++)
														if (areValidBits(bit.getBitAt(x, y, z))) {
																ICube cube = null;
																for (ICube c : CubeRegistry.INSTANCE.getCubes())
																		if (c.getBlock().equals(bit.getBitAt(x, y, z).getState().getBlock()))
																				cube = c;
																temp.add(new CubeData(x, y, z, cube, 0));
														}
								return temp;
						} catch (APIExceptions.CannotBeChiseled e) {
								LogHandler.debug(e.getLocalizedMessage());
						}
				}
				return null;
		}

		public static CubeData[] rotateUp (CubeData[] cubes) {
				if (cubes != null) {
						CubeData[] data = cubes;
						for (int s = 0; s < cubes.length; s++)
								data[s] = new CubeData(cubes[s].offX, (cubes[s].offY * -1) + 15, cubes[s].offZ, cubes[s].cube, cubes[s].damage);
						return data;
				}
				return null;
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
												createModelFromData(block, bootsModel);
										} catch (APIExceptions.CannotBeChiseled e) {
												LogHandler.info("Cannot chisel block @ " + pos.toString() + " | " + e.getLocalizedMessage());
										}
										break;
								}
								// Leggings
								case (1): {
										world.setBlockState(pos, VoidRPGBlocks.bodyBlock.getDefaultState());
										try {
												IBitAccess block = api.getBitAccess(world, pos);
												createLeggingsModel();
												createModelFromData(block, leggingsModel);
										} catch (APIExceptions.CannotBeChiseled e) {
												LogHandler.info("Cannot chisel block @ " + pos.toString() + " | " + e.getLocalizedMessage());
										}
										break;
								}
								// ChestPlate
								case (2): {
										world.setBlockState(pos, VoidRPGBlocks.bodyBlock.getDefaultState());
										world.setBlockState(pos.add(1, 0, 0), VoidRPGBlocks.bodyBlock.getDefaultState());
										world.setBlockState(pos.add(-1, 0, 0), VoidRPGBlocks.bodyBlock.getDefaultState());
										try {
												createChestplateArmModel();
												createChestplateChestModel();
												IBitAccess blockBody = api.getBitAccess(world, pos);
												IBitAccess blockArmA = api.getBitAccess(world, pos.add(1, 0, 0));
												IBitAccess blockArmB = api.getBitAccess(world, pos.add(-1, 0, 0));
												createModelFromData(blockBody, chestplateChestModel);
												createModelFromData(blockArmA, chestplateArmModel);
												createModelFromData(blockArmB, chestplateArmModel);
										} catch (APIExceptions.CannotBeChiseled e) {
												LogHandler.info("Cannot chisel block @ " + pos.toString() + " | " + e.getLocalizedMessage());
										}
										break;
								}
								// Helmet
								case (3): {
										world.setBlockState(pos, VoidRPGBlocks.bodyBlock.getDefaultState());
										try {
												IBitAccess block = api.getBitAccess(world, pos);
												createHelmetModel();
												createModelFromData(block, helmetModel);
										} catch (APIExceptions.CannotBeChiseled e) {
												LogHandler.info("Cannot chisel block @ " + pos.toString() + " | " + e.getLocalizedMessage());
										}
										break;
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
														ArrayList<Boolean> temp = new ArrayList<>();
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
								LogHandler.info(e.getLocalizedMessage());
						}
				}
		}

		public static CubeData[] translate (CubeData[] data, Vec3i vec) {
				if (data != null && data.length > 0 && vec != null) {
						ArrayList<CubeData> translated = new ArrayList<>();
						for (CubeData d : data)
								translated.add(new CubeData(d.offX + vec.getX(), d.offY + vec.getY(), d.offZ + vec.getZ(), d.cube, d.damage));
						CubeData[] temp = new CubeData[translated.size()];
						for (int f = 0; f < translated.size(); f++)
								temp[f] = translated.get(f);
						return temp;
				}
				return null;
		}
}