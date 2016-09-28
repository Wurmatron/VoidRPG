package wurmatron.voidrpg.common.items;


import mod.chiselsandbits.core.api.ChiselAndBitsAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EnumFaceDirection;
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
import net.minecraft.util.math.BlockPos;
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

import java.util.ArrayList;
import java.util.List;

public class ItemStaff extends Item {

		private static final int MAX_DURABIITY = 8;
		private static final ChiselAndBitsAPI api = new ChiselAndBitsAPI();
		private static final ArmorHelper helper = new ArmorHelper();

		public ItemStaff () {
				setUnlocalizedName("creationStaff");
				setCreativeTab(VoidRPG.tabVoidRPG);
				setMaxStackSize(1);
				setMaxDamage(MAX_DURABIITY * 80);
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick (ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
				if (!world.isRemote) {
						if (stack.getItemDamage() == 0 && !player.isSneaking()) {
								RayTraceResult ray = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(5, 1);
								if (api.isBlockChiseled(world, ray.getBlockPos())) {
										ItemStack helmet = checkAndHandleHelmet(world, ray.getBlockPos(), ray);
										ItemStack chestplate = checkAndHandleChestplate(world, ray.getBlockPos(), ray.getBlockPos().add(1, 0, 0), ray.getBlockPos().add(-1, 0, 0), ray);
										ItemStack leggings = checkAndHandleLeggings(world, ray.getBlockPos(), ray);
										ItemStack boots = checkAndHandleBoots(world, ray.getBlockPos(), ray);
										if (chestplate != null) {
												player.inventory.addItemStackToInventory(chestplate);
												player.addChatComponentMessage(new TextComponentTranslation(Local.STAFF_CHESTPLATE).setStyle(new Style().setColor(TextFormatting.AQUA)));
										} else if (leggings != null) {
												player.inventory.addItemStackToInventory(leggings);
												player.addChatComponentMessage(new TextComponentTranslation(Local.STAFF_LEGS).setStyle(new Style().setColor(TextFormatting.AQUA)));
										} else if (helmet != null) {
												player.inventory.addItemStackToInventory(helmet);
												player.addChatComponentMessage(new TextComponentTranslation(Local.STAFF_HELMET).setStyle(new Style().setColor(TextFormatting.AQUA)));
										} else if (boots != null) {
												player.inventory.addItemStackToInventory(boots);
												player.addChatComponentMessage(new TextComponentTranslation(Local.STAFF_BOOTS).setStyle(new Style().setColor(TextFormatting.AQUA)));
										}
								}
						}
				}
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
		}

		private ItemStack checkAndHandleHelmet (World world, BlockPos pos, RayTraceResult ray) {
				if (BitsHelper.isValidHelmet(world, pos)) {
						ArrayList<CubeData> data = BitsHelper.createHelmetFromBits(world, pos);
						CubeData[] temp = new CubeData[data.size()];
						if (data.size() > 0) {
								for (int c = 0; c < data.size(); c++)
										temp[c] = data.get(c);
								temp = correctForFace(EnumFaceDirection.getFacing(ray.sideHit), temp);
								temp = BitsHelper.rotateUp(temp);
								temp = BitsHelper.rotateUp(temp);
								temp = BitsHelper.rotateUp(temp);
								temp = BitsHelper.rotateUp(temp);
								if (temp != null && data.size() > 0)
										return helper.createArmorStack(VoidRPGItems.armorHelmet, temp);
						}
				}
				return null;
		}

		private ItemStack checkAndHandleChestplate (World world, BlockPos body, BlockPos leftArm, BlockPos rightArm, RayTraceResult ray) {
				if (BitsHelper.isValidChestplate(world, body, leftArm, rightArm)) {
						ArrayList<ArrayList<CubeData>> data = BitsHelper.createChestplateFromBit(world, body, leftArm, rightArm);
						CubeData[] chest = new CubeData[data.get(0).size()];
						CubeData[] left = new CubeData[data.get(1).size()];
						CubeData[] right = new CubeData[data.get(2).size()];
						for (int l = 0; l < chest.length; l++)
								chest[l] = data.get(0).get(l);
						for (int l = 0; l < left.length; l++)
								left[l] = data.get(1).get(l);
						for (int l = 0; l < right.length; l++)
								right[l] = data.get(2).get(l);
						chest = correctForFace(EnumFaceDirection.getFacing(ray.sideHit), chest);
						left = correctForFace(EnumFaceDirection.getFacing(ray.sideHit), left);
						right = correctForFace(EnumFaceDirection.getFacing(ray.sideHit), right);
						left = BitsHelper.rotateUp(left);
						right = BitsHelper.rotateUp(right);
						if (chest != null && left != null && right != null && chest.length > 0 && left.length > 0 && right.length > 0)
								return helper.createArmorStack(VoidRPGItems.armorChestplate, chest, left, right);
				}
				return null;
		}

		private ItemStack checkAndHandleLeggings (World world, BlockPos pos, RayTraceResult ray) {
				if (BitsHelper.isValidLeggings(world, pos)) {
						ArrayList<ArrayList<CubeData>> data = BitsHelper.createLeggingsFromBits(world, pos);
						CubeData[] left = new CubeData[data.get(0).size()];
						CubeData[] right = new CubeData[data.get(1).size()];
						for (int l = 0; l < data.get(0).size(); l++)
								left[l] = data.get(0).get(l);
						for (int l = 0; l < data.get(1).size(); l++)
								right[l] = data.get(1).get(l);
						left = correctForFace(EnumFaceDirection.getFacing(ray.sideHit), left);
						right = correctForFace(EnumFaceDirection.getFacing(ray.sideHit), right);
						if (data.size() > 0 && left.length > 0 && right.length > 0)
								return helper.createArmorStack(VoidRPGItems.armorLeggings, right, left);
				}
				return null;
		}

		private ItemStack checkAndHandleBoots (World world, BlockPos pos, RayTraceResult ray) {
				if (BitsHelper.isValidBoots(world, pos)) {
						ArrayList<ArrayList<CubeData>> data = BitsHelper.createBootsFromBits(world, pos);
						CubeData[] left = new CubeData[data.get(0).size()];
						CubeData[] right = new CubeData[data.get(1).size()];
						for (int l = 0; l < data.get(0).size(); l++)
								left[l] = data.get(0).get(l);
						for (int l = 0; l < data.get(1).size(); l++)
								right[l] = data.get(1).get(l);
						left = correctForFace(EnumFaceDirection.getFacing(ray.sideHit), left);
						right = correctForFace(EnumFaceDirection.getFacing(ray.sideHit), right);
						if (data.size() > 0 && left.length > 0 && right.length > 0)
								return helper.createArmorStack(VoidRPGItems.armorBoots, left, right);
				}
				return null;
		}

		private CubeData[] correctForFace (EnumFaceDirection face, CubeData[] data) {
				if (face != EnumFaceDirection.DOWN || face != EnumFaceDirection.UP) {
						if (face.equals(EnumFaceDirection.EAST)) {
								data = BitsHelper.rotateClockwise(data);
								data = BitsHelper.rotateClockwise(data);
								data = BitsHelper.rotateClockwise(data);
						} else if (face.equals(EnumFaceDirection.WEST))
								data = BitsHelper.rotateClockwise(data);
						else if (face.equals(EnumFaceDirection.SOUTH)) {
								data = BitsHelper.rotateClockwise(data);
								data = BitsHelper.rotateClockwise(data);
						}
						return data;
				}
				return null;
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
				nbt.setInteger(NBT.DURABILITY, MAX_DURABIITY);
				stack.setTagCompound(nbt);
		}

		@Override
		public void addInformation (ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
				if (stack.getTagCompound() != null)
						tip.add(TextFormatting.GOLD + "Durability" + ": " + getColorBasedOnMaxDurability(stack.getTagCompound().getInteger(NBT.DURABILITY), MAX_DURABIITY) + stack.getTagCompound().getInteger(NBT.DURABILITY));
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
				nbt.setInteger(NBT.DURABILITY, MAX_DURABIITY);
				stack.setTagCompound(nbt);
				sub.add(stack);
		}

		@Override
		public EnumRarity getRarity (ItemStack stack) {
				return EnumRarity.UNCOMMON;
		}
}
