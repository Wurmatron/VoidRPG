package wurmatron.voidrpg.common.utils;

import mod.chiselsandbits.items.ItemChiseledBit;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class StackHelper {

		public static boolean areItemsEqual (ItemStack a, ItemStack b) {
				if (a != null && b != null) {
						if (a.isItemEqual(b)) {
								if (a.getTagCompound() == null && b.getTagCompound() == null)
										return true;
								else if (a.getTagCompound() != null && b.getTagCompound() != null && a.getTagCompound().equals(b.getTagCompound()))
										return true;
						}
				}
				return a == null && b == null;
		}

		public static boolean areStacksEqualIgnoreSize (ItemStack a, ItemStack b) {
				if (a != null && b != null) {
						if (a.getItem().equals(b.getItem()) && a.getItemDamage() == b.getItemDamage()) {
								if (a.getTagCompound() == null && b.getTagCompound() == null)
										return true;
								else if (a.getTagCompound() != null && b.getTagCompound() != null && a.getTagCompound().equals(b.getTagCompound()))
										return true;
						}
				}
				return a == null && b == null;
		}

		public static String convert (ItemStack stack) {
				if (stack != null) {
						String temp = "";
						temp += "[" + Item.REGISTRY.getNameForObject(stack.getItem()).getResourceDomain() + ":" + Item.REGISTRY.getNameForObject(stack.getItem()).getResourcePath() + "]";
						temp += "@" + stack.getItemDamage();
						temp += "#" + stack.stackSize;
						if (stack.getTagCompound() != null)
								temp += "%" + stack.getTagCompound().toString();
						return temp;
				}
				return "";
		}

		public static ItemStack convert (String string) {
				if (string.length() > 0 && string.contains(":") && string.contains("@") && string.contains("#")) {
						Item item = Item.REGISTRY.getObject(new ResourceLocation(string.substring(string.indexOf("[") + 1, string.indexOf(":")), string.substring(string.indexOf(":") + 1, string.indexOf("]"))));
						int meta = Integer.parseInt(string.substring(string.indexOf("@") + 1, string.indexOf("#")));
						NBTTagCompound nbt = null;
						if (string.contains("%")) {
								int stackSize = Integer.parseInt(string.substring(string.indexOf("#") + 1, string.indexOf("%")));
								try {
										nbt = JsonToNBT.getTagFromJson(string.substring(string.indexOf("%") + 1, string.length()));
								} catch (NBTException e) {
										e.printStackTrace();
								}
								if (nbt != null) {
										ItemStack stack = new ItemStack(item, stackSize, meta);
										stack.setTagCompound(nbt);
										return stack;
								}
						} else {
								int stackSize = Integer.parseInt(string.substring(string.indexOf("#") + 1, string.length()));
								ItemStack stack = new ItemStack(item, stackSize, meta);
								return stack;
						}
				}
				return null;
		}

		public static IBlockState getStateFromItem (ItemStack block) {
				if (block != null && block.getItem() instanceof ItemBlock) {
						ItemBlock stack = (ItemBlock) block.getItem();
						return stack.getBlock().getStateFromMeta(stack.getMetadata(block));
				}
				return null;
		}

		public static ItemStack createBitFromBlock (Block block, int count) {
				return ItemChiseledBit.createStack(Block.getStateId(getStateFromItem(new ItemStack(block))), count, false);
		}
}
