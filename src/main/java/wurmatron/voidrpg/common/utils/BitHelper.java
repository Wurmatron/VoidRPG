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
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.NBT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BitHelper {

	public static final List <Vec3i> modelHead = new ArrayList <> ();
	public static final List <Vec3i> modelLeggings = new ArrayList <> ();
	public static final List <Vec3i> modelChest = new ArrayList <> ();
	public static final List <Vec3i> modelArm = new ArrayList <> ();
	public static final List <Vec3i> modelBoots = new ArrayList <> ();
	private static final ChiselAndBitsAPI api = new ChiselAndBitsAPI ();
	private static final IBitBrush airBrush = new IBitBrush () {
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
			return new ItemStack (Blocks.AIR,i,0);
		}

		@Override
		public int getStateID () {
			return 0;
		}
	};

	/**
	 Checks too see if the model is valid along with no invalid cubes.
	 Note: This does not check for the border size
	 */
	public static boolean hasValidModel (World world,BlockPos pos,Vec3i[] model) {
		if (modelHead.size () <= 0) {
			for (int x = 0; x < 15; x++)
				for (int y = 0; y < 15; y++)
					for (int z = 0; z < 15; z++)
						if (x <= 12 && x > 4 && y <= 8 && z <= 12 && z > 4)
							modelHead.add (new Vec3i (x,y,z));
		} else if (modelLeggings.size () <= 0) {
			for (int x = 4; x < 12; x++)
				for (int y = 0; y < 9; y++)
					for (int z = 6; z < 10; z++)
						modelLeggings.add (new Vec3i (x,y,z));
		} else if (modelChest.size () <= 0) {
			for (int x = 4; x < 12; x++)
				for (int y = 0; y < 12; y++)
					for (int z = 6; z < 10; z++)
						modelChest.add (new Vec3i (x,y,z));
		} else if (modelArm.size () <= 0) {
			for (int x = 6; x < 10; x++)
				for (int y = 0; y < 12; y++)
					for (int z = 6; z < 10; z++)
						modelArm.add (new Vec3i (x,y,z));
		} else if (modelBoots.size () <= 0) {
			for (int x = 4; x < 12; x++)
				for (int y = 0; y < 5; y++)
					for (int z = 6; z < 10; z++)
						modelBoots.add (new Vec3i (x,y,z));
		}
		if (world != null && pos != null && model != null && model.length > 0 && api.isBlockChiseled (world,pos) && !world.isRemote) {
			try {
				IBitAccess bit = api.getBitAccess (world,pos);
				for (Vec3i vec : model) {
					if (bit.getBitAt (vec.getX (),vec.getY (),vec.getZ ()).isAir () || !bit.getBitAt (vec.getX (),vec.getY (),vec.getZ ()).isAir () && !bit.getBitAt (vec.getX (),vec.getY (),vec.getZ ()).getState ().getBlock ().getUnlocalizedName ().equalsIgnoreCase (VoidRPGBlocks.bodyBlock.getUnlocalizedName ()))
						return false;
				}
				return true;
			} catch (APIExceptions.CannotBeChiseled e) {
				e.printStackTrace ();
			}
		}
		return false;
	}

	public static CubeData[] getDataFromModel (World world,BlockPos pos,Vec3i[] model,int maxX,int maxY,int maxZ,Vec3i center) {
		CubeData[] data = createDataFromModel (world,pos);
		ArrayList <CubeData> validCubes = new ArrayList <> ();
		Vec3i[] inverseModel = inverseModel (model);
		for (CubeData c : data)
			for (Vec3i neg : inverseModel) {
				if (c.xPos == neg.getX () && c.yPos == neg.getY () && c.zPos == neg.getZ () && c.xPos >= maxX + center.getX () && c.yPos >= maxY + center.getY () && c.zPos >= maxZ + center.getZ ())
					validCubes.add (c);
			}
		return data;
	}

	public static CubeData[] createDataFromModel (World world,BlockPos pos) {
		ArrayList <CubeData> data = new ArrayList <> ();
		if (!world.isRemote && api.isBlockChiseled (world,pos))
			try {
				IBitAccess bit = api.getBitAccess (world,pos);
				for (int x = 16; x >= 0; x--)
					for (int y = 16; y >= 0; y--)
						for (int z = 16; z >= 0; z--)
							if (!bit.getBitAt (x,y,z).isAir () && areValidBits (bit.getBitAt (x,y,z)))
								for (ICube cube : CubeRegistry.getCubes ())
									if (cube != null && cube.getBlock () != null && cube.getBlock ().getUnlocalizedName ().equals (bit.getBitAt (x,y,z).getState ().getBlock ().getUnlocalizedName ()))
										data.add (new CubeData (cube,x,y,z,0));
			} catch (Exception e) {
				e.printStackTrace ();
			}
		return data.toArray (new CubeData[0]);
	}

	private static boolean areValidBits (IBitBrush bit) {
		LogHandler.info ("Bit: " + bit.getState ().getBlock ());
		for (ICube cube : CubeRegistry.getCubes ())
			if (cube != null && cube.getBlock () != null && bit.getState () != null && bit.getState ().getBlock () != null && cube.getBlock ().equals (bit.getState ().getBlock ()) && cube.getBlock ().equals (bit.getState ().getBlock ()) && bit.getItemStack (1) != null)
				return true;
		return false;
	}

	/**
	 Returns a sorted NBT version of the CubeData[]
	 0 = default cubes
	 1 = special / event cubes
	 2 = energy storage cubes
	 3 = energy production cubes
	 */
	public static NBTTagCompound[] convertCubesToNBT (CubeData[] data) {
		NBTTagCompound defaultCubes = new NBTTagCompound ();
		NBTTagCompound specialCubes = new NBTTagCompound ();
		NBTTagCompound energyStorageCubes = new NBTTagCompound ();
		NBTTagCompound energyProductionCubes = new NBTTagCompound ();
		for (CubeData cube : data) {
			if (cube != null && cube.cube != null)
				if (cube.cube.hasEffects ()) {
					NBTTagCompound nbt = convertCubeDataToNBT (cube);
					if (!nbt.hasNoTags ())
						specialCubes.setTag (Integer.toString (specialCubes.getSize () + 1),nbt);
				} else if (cube.cube instanceof IEnergy) {
					IEnergy energy = (IEnergy) cube.cube;
					if (energy.getStorage () > 0) {
						NBTTagCompound nbt = convertCubeDataToNBT (cube);
						if (!nbt.hasNoTags ())
							energyStorageCubes.setTag (Integer.toString (energyStorageCubes.getSize () + 1),nbt);
					} else if (energy.getProductionAmount () > 0) {
						NBTTagCompound nbt = convertCubeDataToNBT (cube);
						if (!nbt.hasNoTags ())
							energyProductionCubes.setTag (Integer.toString (energyProductionCubes.getSize () + 1),nbt);
					}
				} else {
					NBTTagCompound nbt = convertCubeDataToNBT (cube);
					if (!nbt.hasNoTags ())
						defaultCubes.setTag (Integer.toString (defaultCubes.getSize () + 1),nbt);
				}
		}
		if (defaultCubes.getSize () > 0)
			return new NBTTagCompound[] {defaultCubes,specialCubes,energyStorageCubes,energyProductionCubes};
		return new NBTTagCompound[0];
	}

	public static NBTTagCompound convertCubeDataToNBT (CubeData data) {
		NBTTagCompound nbt = new NBTTagCompound ();
		nbt.setByte (NBT.CUBE_ID,CubeRegistry.getIDForCube (data.cube));
		nbt.setByte (NBT.CUBE_X,(byte) data.xPos);
		nbt.setByte (NBT.CUBE_Y,(byte) data.yPos);
		nbt.setByte (NBT.CUBE_Z,(byte) data.zPos);
		nbt.setInteger (NBT.CUBE_DAMAGE,data.damage);
		if (nbt.getByte (NBT.CUBE_ID) != -1)
			return nbt;
		return new NBTTagCompound ();
	}

	public static CubeData readCubeDataFromNBT (NBTTagCompound nbt) {
		if (nbt != null && !nbt.hasNoTags ())
			return new CubeData (CubeRegistry.getCubeFromID (nbt.getByte (NBT.CUBE_ID)),nbt.getByte (NBT.CUBE_X) - 9,nbt.getByte (NBT.CUBE_Y) + 15,nbt.getByte (NBT.CUBE_Z) - 9,nbt.getInteger (NBT.CUBE_DAMAGE));
		return null;
	}

	@SideOnly (Side.CLIENT)
	public static ModelRenderer createModelRenderer (ModelBiped base,CubeData data) {
		ModelRenderer model = new ModelRenderer (base) {
			@Override
			public void render (float scale) {
				Minecraft.getMinecraft ().renderEngine.bindTexture (data.cube.getTexture ());
				super.render (scale);
				Minecraft.getMinecraft ().renderEngine.bindTexture (new ResourceLocation (Global.MODID,"textures/armor/armor.png"));
			}
		};
		model.addBox (data.xPos,data.yPos,data.zPos,1,1,1,0.00001f);
		return model;
	}

	private static Vec3i[] inverseModel (Vec3i[] model) {
		ArrayList <Vec3i> vec = new ArrayList <> ();
		Collections.addAll (vec,model);
		ArrayList <Vec3i> output = new ArrayList <> ();
		for (int x = 0; x < 15; x++)
			for (int y = 0; y < 15; y++)
				for (int z = 0; z < 15; z++)
					if (!vec.contains (new Vec3i (x,y,z)))
						output.add (new Vec3i (x,y,z));
		return output.toArray (new Vec3i[0]);
	}

	public static void createBaseArmorBlock (Vec3i[] model,World world,BlockPos pos) {
		if (!world.isRemote) {
			world.setBlockState (pos,VoidRPGBlocks.bodyBlock.getDefaultState ());
			try {
				IBitAccess block = api.getBitAccess (world,pos);
				createModelFromData (block,model);
			} catch (APIExceptions.CannotBeChiseled e) {
				LogHandler.info ("Cannot chisel block @ " + pos.toString () + " | " + e.getLocalizedMessage ());
			}
		}
	}

	public static void createModelFromData (IBitAccess block,Vec3i[] data) {
		if (block != null && data != null && data.length > 0) {
			try {
				for (int x = 0; x <= 15; x++) {
					for (int y = 0; y <= 15; y++)
						for (int z = 0; z <= 15; z++) {
							ArrayList <Boolean> temp = new ArrayList <> ();
							for (Vec3i vec : data) {
								if (!vec.equals (new Vec3i (x,y,z)))
									temp.add (false);
								else
									temp.add (true);
							}
							if (!temp.contains (true))
								block.setBitAt (x,y,z,airBrush);
						}
				}
				block.commitChanges (true);
			} catch (APIExceptions.SpaceOccupied e) {
				LogHandler.info (e.getLocalizedMessage ());
			}
		}
	}
}
