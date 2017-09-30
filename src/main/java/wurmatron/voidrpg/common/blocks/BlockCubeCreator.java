package wurmatron.voidrpg.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.tile.TileCubeCreator;

public class BlockCubeCreator extends BlockContainer {

	private static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BlockCubeCreator (Material material) {
		super (material);
		setCreativeTab (VoidRPG.tabVoidRPG);
		setDefaultState (blockState.getBaseState ().withProperty (FACING,EnumFacing.EAST));
		setUnlocalizedName ("cubeCreator");
	}

	@Override
	public TileEntity createNewTileEntity (World worldIn,int meta) {
		return new TileCubeCreator ();
	}


	@Override
	public boolean onBlockActivated (World world,BlockPos pos,IBlockState state,EntityPlayer player,EnumHand hand,EnumFacing facing,float hitX,float hitY,float hitZ) {
		if (!world.isRemote)
			player.openGui (VoidRPG.instance,Global.CUBECREATOR_GUI,world,pos.getX (),pos.getY (),pos.getZ ());
		return true;
	}

	@Override
	public void breakBlock (World world,BlockPos pos,IBlockState blockstate) {
		TileCubeCreator te = (TileCubeCreator) world.getTileEntity (pos);
		InventoryHelper.dropInventoryItems (world,pos,te);
		super.breakBlock (world,pos,blockstate);
	}

	@Override
	public EnumBlockRenderType getRenderType (IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	public void onBlockAdded (World world,BlockPos pos,IBlockState state) {
		setDefaultState (state);
	}

	@Override
	public IBlockState getStateForPlacement (World world,BlockPos pos,EnumFacing facing,float hitX,float hitY,float hitZ,int meta,EntityLivingBase placer,EnumHand hand) {
		return this.getDefaultState ().withProperty (FACING,placer.getHorizontalFacing ().getOpposite ());
	}

	@Override
	public IBlockState getStateFromMeta (int meta) {
		EnumFacing enumfacing = EnumFacing.getFront (meta);
		if (enumfacing.getAxis () == EnumFacing.Axis.Y)
			enumfacing = EnumFacing.NORTH;
		return this.getDefaultState ().withProperty (FACING,enumfacing);
	}

	@Override
	public int getMetaFromState (IBlockState state) {
		return state.getValue (FACING).getIndex ();
	}

	@Override
	public IBlockState withRotation (IBlockState state,Rotation rot) {
		return state.withProperty (FACING,rot.rotate (state.getValue (FACING)));
	}

	@Override
	public IBlockState withMirror (IBlockState state,Mirror mirrorIn) {
		return state.withRotation (mirrorIn.toRotation (state.getValue (FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState () {
		return new BlockStateContainer (this,FACING);
	}
}
