package wurmatron.voidrpg.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.common.reference.Local;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.BitsHelper;

import java.util.List;

public class ItemModelPlacer extends Item {

		public ItemModelPlacer () {
				setMaxDamage(1);
				setMaxStackSize(1);
				setCreativeTab(VoidRPG.tabVoidRPG);
				setUnlocalizedName("modelPlacer");
		}

		@Override
		public EnumActionResult onItemUse (ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
				if (!world.isRemote && stack.getTagCompound() != null && stack.getTagCompound().hasKey(NBT.TYPE)) {
						BitsHelper.createBaseArmorBlock(stack.getTagCompound().getInteger(NBT.TYPE),world,pos);
						return EnumActionResult.SUCCESS;
				}
				return EnumActionResult.FAIL;
		}

		@Override
		public void addInformation (ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
				if (stack.getTagCompound() != null)
						tip.add(TextFormatting.AQUA + Local.PLACMENT_TYPE + ":" + convertTypeToString(stack.getTagCompound().getInteger(NBT.TYPE)));
		}

		@Override
		public void getSubItems (Item item, CreativeTabs tab, List<ItemStack> sub) {
				sub.add(createModelPlacer(0));
				sub.add(createModelPlacer(1));
				sub.add(createModelPlacer(2));
				sub.add(createModelPlacer(3));
		}

		public static ItemStack createModelPlacer (int type) {
				ItemStack stack = new ItemStack(VoidRPGItems.itemModelPlacer, 1, 0);
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger(NBT.TYPE, type);
				stack.setTagCompound(nbt);
				return stack;
		}

		private String convertTypeToString (int type) {
				switch (type) {
						case (0):
								return "Boots";
						case (1):
								return "Leggings";
						case (2):
								return "Chestplate";
						case (3):
								return "Helmet";
						default:
								return "Unknown";
				}
		}
}
