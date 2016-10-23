package wurmatron.voidrpg.common.items;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.common.config.Defaults;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.reference.Local;
import wurmatron.voidrpg.common.utils.BitsHelper;

import java.util.List;

public class ItemModelPlacer extends Item {

		private static Block defaultBlock = GameRegistry.findBlock(Defaults.MODELPLACERBLOCK.substring(0, Defaults.MODELPLACERBLOCK.indexOf(":")), Defaults.MODELPLACERBLOCK.substring(Defaults.MODELPLACERBLOCK.indexOf(":") + 1, Defaults.MODELPLACERBLOCK.length()));

		public ItemModelPlacer () {
				setMaxStackSize(1);
				setCreativeTab(VoidRPG.tabVoidRPG);
				setHasSubtypes(true);
				setUnlocalizedName("modelPlacer");
		}

		@Override
		public EnumActionResult onItemUse (ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
				if (!world.isRemote) {
						Block temp = GameRegistry.findBlock(Settings.MODELPLACERBLOCK.substring(0, Settings.MODELPLACERBLOCK.indexOf(":")), Settings.MODELPLACERBLOCK.substring(Settings.MODELPLACERBLOCK.indexOf(":") + 1, Settings.MODELPLACERBLOCK.length()));
						if (temp != null && world.getBlockState(pos).getBlock().equals(temp)) {
								player.inventory.deleteStack(stack);
								BitsHelper.createBaseArmorBlock(stack.getItemDamage(), world, pos);
								return EnumActionResult.SUCCESS;
						} else if (temp == null && world.getBlockState(pos).getBlock().equals(defaultBlock)) {
								BitsHelper.createBaseArmorBlock(stack.getItemDamage(), world, pos);
								player.inventory.deleteStack(stack);
								return EnumActionResult.SUCCESS;
						} else {
								if (temp != null)
										player.addChatComponentMessage(new TextComponentString(I18n.format(Local.PLACER_BLOCK) + " '" + I18n.format(new ItemStack(temp).getDisplayName()) + "'").setStyle(new Style().setColor(TextFormatting.AQUA)));
								else
										player.addChatComponentMessage(new TextComponentString(I18n.format(Local.PLACER_BLOCK) + " '" + I18n.format(new ItemStack(defaultBlock).getDisplayName()) + "'").setStyle(new Style().setColor(TextFormatting.AQUA)));
						}
				}
				return EnumActionResult.FAIL;
		}

		@Override
		public void addInformation (ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
				if (stack.getTagCompound() != null)
						tip.add(TextFormatting.AQUA + I18n.format(Local.PLACMENT_TYPE) + ": " + I18n.format(convertTypeToString(stack.getItemDamage())));
		}

		@Override
		public void getSubItems (Item item, CreativeTabs tab, List<ItemStack> sub) {
				sub.add(createModelPlacer(0));
				sub.add(createModelPlacer(1));
				sub.add(createModelPlacer(2));
				sub.add(createModelPlacer(3));
		}

		public static ItemStack createModelPlacer (int type) {
				return new ItemStack(VoidRPGItems.itemModelPlacer, 1, type);
		}

		private String convertTypeToString (int type) {
				switch (type) {
						case (0):
								return "type.boots";
						case (1):
								return "type.leggings";
						case (2):
								return "type.chestplate";
						case (3):
								return "type.helmet";
						default:
								return "type.unknown";
				}
		}

		@Override
		public String getUnlocalizedName (ItemStack stack) {
				return super.getUnlocalizedName() + "_" + stack.getItemDamage();
		}
}
