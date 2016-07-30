package wurmatron.voidrpg.common.items;

import mod.chiselsandbits.core.api.ChiselAndBitsAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.utils.ArmorHelper;
import wurmatron.voidrpg.common.utils.BitsHelper;
import wurmatron.voidrpg.common.utils.LogHandler;

public class ItemStaff extends Item {

		public ItemStaff () {
				setUnlocalizedName("itemCreationStaff");
				setCreativeTab(VoidRPG.tabVoidRPG);
				setMaxStackSize(1);
				setMaxDamage(12);
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick (ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
				if (!world.isRemote && new ChiselAndBitsAPI().isBlockChiseled(world, new BlockPos(player.posX, player.posY - 1, player.posZ))) {
						if (BitsHelper.isValidHelmet(world, new BlockPos(player.posX, player.posY - 1, player.posZ))) {
								LogHandler.info("Valid Helmet Found");
								CubeData[] data = BitsHelper.convertBitsToCubes(world, new BlockPos(player.posX, player.posY - 1, player.posZ));
								ItemStack helmet = new ArmorHelper().createArmorStack(VoidRPGItems.armorHelmet, BitsHelper.rotateUp(data));
								player.inventory.addItemStackToInventory(helmet);
						}
				}
				return super.onItemRightClick(stack, world, player, hand);
		}
}
