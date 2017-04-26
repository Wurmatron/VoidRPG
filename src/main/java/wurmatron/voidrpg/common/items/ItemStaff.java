package wurmatron.voidrpg.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.BitHelper;
import wurmatron.voidrpg.common.utils.DataHelper;

import java.util.List;

public class ItemStaff extends Item {

		public static final int MAX_DURABILITY = 8;

		public ItemStaff() {
				setCreativeTab(VoidRPG.tabVoidRPG); setMaxStackSize(1); setHasSubtypes(true); setUnlocalizedName("creationStaff");
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
				// TODO FIX THIS SO THAT IT ACTUALLY WORKS CORRECTLY
				RayTraceResult ray = world.rayTraceBlocks(player.getLookVec().add(new Vec3d(player.posX, player.posY, player.posZ)), player.getLookVec().add(new Vec3d(player.getLookVec().xCoord, player.getLookVec().yCoord, player.getLookVec().zCoord).add(new Vec3d(player.posX, player.posY, player.posZ))), false,false,true);
				if (stack.getTagCompound() != null) {
						if (ray != null && world.getBlockState(ray.getBlockPos()).getBlock() != Blocks.AIR) {
								if (BitHelper.hasValidModel(world, ray.getBlockPos(), BitHelper.modelHead.toArray(new Vec3i[0]))) {
										CubeData[] data = BitHelper.getDataFromModel(world, ray.getBlockPos(), BitHelper.modelHead.toArray(new Vec3i[0]), 16, 16, 16, new Vec3i(0, 0, 0));
										ItemStack  item = DataHelper.createHelmetFromData(data); player.inventory.addItemStackToInventory(item);
										stack.getTagCompound().setInteger(NBT.CUBE_DAMAGE, stack.getTagCompound().getInteger(NBT.CUBE_DAMAGE) - 1);
										return new ActionResult(EnumActionResult.PASS, stack);
								}
								if (BitHelper.hasValidModel(world, ray.getBlockPos(), BitHelper.modelChest.toArray(new Vec3i[0])) && BitHelper.hasValidModel(world, ray.getBlockPos().add(1, 0, 0), BitHelper.modelArm.toArray(new Vec3i[0])) && BitHelper.hasValidModel(world, ray.getBlockPos().add(-1, 0, 0), BitHelper.modelArm.toArray(new Vec3i[0]))) {
										CubeData[] bodyData     = BitHelper.getDataFromModel(world, ray.getBlockPos(), BitHelper.modelChest.toArray(new Vec3i[0]), 16, 16, 16, new Vec3i(0, 0, 0));
										CubeData[] rightArmData = BitHelper.getDataFromModel(world, ray.getBlockPos().add(1, 0, 0), BitHelper.modelArm.toArray(new Vec3i[0]), 16, 16, 16, new Vec3i(0, 0, 0));
										CubeData[] leftArmData  = BitHelper.getDataFromModel(world, ray.getBlockPos().add(-1, 0, 0), BitHelper.modelArm.toArray(new Vec3i[0]), 16, 16, 16, new Vec3i(0, 0, 0));
										ItemStack  item         = DataHelper.createChestplateFromData(bodyData, leftArmData, rightArmData);
										player.inventory.addItemStackToInventory(item);
										stack.getTagCompound().setInteger(NBT.CUBE_DAMAGE, stack.getTagCompound().getInteger(NBT.CUBE_DAMAGE) - 1);
										return new ActionResult(EnumActionResult.PASS, stack);
								} else if(BitHelper.hasValidModel(world,ray.getBlockPos(), BitHelper.modelLeggings.toArray(new Vec3i[0]))) {
										// TODO Split These into correct sides
										CubeData[] leftLegData = BitHelper.getDataFromModel(world,ray.getBlockPos(),BitHelper.modelLeggings.toArray(new Vec3i[0]), 8,12,8,new Vec3i(0,0,0));
										CubeData[] rightLegData = BitHelper.getDataFromModel(world,ray.getBlockPos(),BitHelper.modelLeggings.toArray(new Vec3i[0]), 8,12,8,new Vec3i(0,0,0));
//										ItemStaff item = DataHelper.
								}
						} if (stack.getTagCompound().getInteger(NBT.CUBE_DAMAGE) < MAX_DURABILITY) player.inventory.deleteStack(stack);
						return new ActionResult(EnumActionResult.PASS, stack);
				} return new ActionResult(EnumActionResult.FAIL, stack);
		}

		public static ItemStack createStaff(int damage) {
				ItemStack stack = new ItemStack(VoidRPGItems.itemStaff, 1, 0); NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger(NBT.CUBE_DAMAGE, damage); stack.setTagCompound(nbt); return stack;
		}

		@Override
		public void addInformation(ItemStack stack, EntityPlayer player, List<String> tip, boolean adv) {
				if (stack.getTagCompound() != null)
						tip.add(TextFormatting.GRAY + I18n.translateToLocal("stat.durability.name") + ": " + TextFormatting.AQUA + stack.getTagCompound().getInteger(NBT.CUBE_DAMAGE));
				else tip.add(TextFormatting.RED + I18n.translateToLocal("stat.invalid.name"));
		}

		@Override
		public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> sub) {
				sub.add(createStaff(MAX_DURABILITY)); sub.add(createStaff(9001));
		}
}
