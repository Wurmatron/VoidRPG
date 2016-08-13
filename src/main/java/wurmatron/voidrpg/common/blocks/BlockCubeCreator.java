package wurmatron.voidrpg.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.tiles.TileCubeCreator;

import javax.annotation.Nullable;

public class BlockCubeCreator extends BlockContainer {

		public BlockCubeCreator (Material material) {
				super(material);
				setCreativeTab(VoidRPG.tabVoidRPG);
				setUnlocalizedName("cubeCreator");
		}

		@Override
		public TileEntity createNewTileEntity (World worldIn, int meta) {
				return new TileCubeCreator();
		}

		@Override
		public boolean onBlockActivated (World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
				if (!world.isRemote)
						player.openGui(VoidRPG.instance, Global.CUBECREATOR_GUI, world, pos.getX(), pos.getY(), pos.getZ());
				return true;
		}
}
