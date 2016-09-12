package wurmatron.voidrpg.common.items;

import mod.chiselsandbits.core.api.ChiselAndBitsAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.reference.Local;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.ArmorHelper;
import wurmatron.voidrpg.common.utils.BitsHelper;
import wurmatron.voidrpg.common.utils.LogHandler;

import java.util.List;

public class ItemStaff extends Item {

		private static final int maxDurability = 12;

		public ItemStaff () {
				setUnlocalizedName("itemCreationStaff");
				setCreativeTab(VoidRPG.tabVoidRPG);
				setMaxStackSize(1);
				setMaxDamage(maxDurability * 80);
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick (ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
				if (!world.isRemote) {
						if (stack.getItemDamage() == 0) {
								RayTraceResult ray = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(5, 1.0F);
								if (new ChiselAndBitsAPI().isBlockChiseled(world, ray.getBlockPos())) {
										if (BitsHelper.isValidHelmet(world, ray.getBlockPos())) {
												EnumFaceDirection face = EnumFaceDirection.getFacing(ray.sideHit);
												if (face != EnumFaceDirection.DOWN || face != EnumFaceDirection.UP) {
														player.addChatComponentMessage(new TextComponentTranslation(Local.STAFF_HELMET).setStyle(new Style().setColor(TextFormatting.AQUA)));
														stack.setItemDamage(getMaxDamage());
														if (stack.getTagCompound() != null)
																stack.getTagCompound().setInteger(NBT.DURABILITY, stack.getTagCompound().getInteger(NBT.DURABILITY) - 1);
														else {
																NBTTagCompound nbt = new NBTTagCompound();
																nbt.setInteger(NBT.DURABILITY, maxDurability - 1);
																stack.setTagCompound(nbt);
														}
														CubeData[] data = BitsHelper.convertBitsToCubes(world, ray.getBlockPos());
														data = BitsHelper.rotateUp(data);
														LogHandler.debug("Direction: " + face.name());
														if (face == EnumFaceDirection.WEST) {
																data = BitsHelper.rotateClockwise(data);
																data = BitsHelper.rotateClockwise(data);
																data = BitsHelper.rotateClockwise(data);
														}
														if (face == EnumFaceDirection.EAST)
																data = BitsHelper.rotateClockwise(data);
														if (face == EnumFaceDirection.SOUTH) {
																data = BitsHelper.rotateClockwise(data);
																data = BitsHelper.rotateClockwise(data);
														}
														ItemStack helmet = new ArmorHelper().createArmorStack(VoidRPGItems.armorHelmet, data);
														player.inventory.addItemStackToInventory(helmet);
														return new ActionResult(EnumActionResult.SUCCESS, stack);
												} else {
														player.addChatComponentMessage(new TextComponentTranslation(Local.STAFF_INVALID).setStyle(new Style().setColor(TextFormatting.DARK_RED)));
														return new ActionResult(EnumActionResult.PASS, stack);
												}
										}
								}
						} else
								player.addChatComponentMessage(new TextComponentTranslation(Local.STAFF_CHARGING).setStyle(new Style().setColor(TextFormatting.RED)));
				} else {
						if (stack.getTagCompound() == null) {
								NBTTagCompound nbt = new NBTTagCompound();
								nbt.setInteger(NBT.DURABILITY, maxDurability);
								stack.setTagCompound(nbt);
						}
				}
				return new ActionResult(EnumActionResult.FAIL, stack);
		}

		@Override
		public void onUpdate (ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
				if (stack.getTagCompound() != null && stack.getTagCompound().hasKey(NBT.DURABILITY))
						if (stack.getTagCompound().getInteger(NBT.DURABILITY) <= 0)
								entity.replaceItemInInventory(slot, null);
				if (stack.getItemDamage() > 0)
						stack.setItemDamage(stack.getItemDamage() - 1);
				super.onUpdate(stack, world, entity, slot, isSelected);
		}

		@Override
		public void onCreated (ItemStack stack, World world, EntityPlayer player) {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger(NBT.DURABILITY, maxDurability);
				stack.setTagCompound(nbt);
		}

		@Override
		public void addInformation (ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
				if (stack.getTagCompound() != null)
						tip.add(TextFormatting.GOLD + I18n.format(NBT.DURABILITY) + ": " + getColorBasedOnMaxDurability(stack.getTagCompound().getInteger(NBT.DURABILITY), maxDurability) + stack.getTagCompound().getInteger(NBT.DURABILITY));
		}

		private TextFormatting getColorBasedOnMaxDurability (int current, int max) {
				if (current == max)
						return TextFormatting.GOLD;
				if (current >= max / 2 + max / 4 && current > max / 2)
						return TextFormatting.GREEN;
				if (current >= max / 2 && current <= max / 3)
						return TextFormatting.YELLOW;
				if (current < max / 4 && current >= max / 6)
						return TextFormatting.RED;
				if (current == 1)
						return TextFormatting.DARK_RED;
				return TextFormatting.GRAY;
		}

		@Override
		public void getSubItems (Item item, CreativeTabs tab, List<ItemStack> sub) {
				ItemStack stack = new ItemStack(item, 1, 0);
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger(NBT.DURABILITY, maxDurability);
				stack.setTagCompound(nbt);
				sub.add(stack);
		}

		@Override
		public EnumRarity getRarity (ItemStack stack) {
				return EnumRarity.UNCOMMON;
		}
}
