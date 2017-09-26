package wurmatron.voidrpg.common.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.GameData;
import wurmatron.voidrpg.VoidRPG;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.reference.Local;
import wurmatron.voidrpg.common.utils.BitHelper;

import java.util.ArrayList;

public class ItemModelPlacer extends Item {

	private static Block MODEL_BLOCK = GameRegistry.findRegistry (Block.class).getValue (new ResourceLocation (Settings.modelBlock.substring (0,Settings.modelBlock.indexOf (":")),Settings.modelBlock.substring (Settings.modelBlock.indexOf (":") + 1,Settings.modelBlock.length ())));

	public ItemModelPlacer () {
		setCreativeTab (VoidRPG.tabVoidRPG);
		setUnlocalizedName ("modelPlacer");
		setRegistryName ("modelPlacer");
		setHasSubtypes (true);
		setMaxStackSize (1);
	}

	public static String getNameFromMeta (int meta) {
		switch (meta) {
			case (0):
				return "Helmet";
			case (1):
				return "ChestPlate";
			case (2):
				return "Leggings";
			case (3):
				return "Boots";
			default:
				return "Invaid";
		}
	}

	@Override
	public EnumActionResult onItemUse (EntityPlayer player,World world,BlockPos pos,EnumHand hand,EnumFacing facing,float hitX,float hitY,float hitZ) {
		if (!world.isRemote) {
			ItemStack stack = player.inventory.getCurrentItem ();
			if (!world.isRemote && world.getBlockState (pos).getBlock ().getUnlocalizedName ().equalsIgnoreCase (MODEL_BLOCK.getUnlocalizedName ())) {
				switch (stack.getItemDamage ()) {
					case (0): {
						ArrayList <Vec3i> modelHead = new ArrayList <> ();
						for (int x = 0; x < 15; x++)
							for (int y = 0; y < 15; y++)
								for (int z = 0; z < 15; z++)
									if (x <= 12 && x > 4 && y <= 8 && z <= 12 && z > 4)
										modelHead.add (new Vec3i (x,y,z));
						BitHelper.createBaseArmorBlock (modelHead.toArray (new Vec3i[0]),world,pos);
						stack.setCount (stack.getCount ()-1);
						return EnumActionResult.SUCCESS;
					}
					case (1): {
						ArrayList <Vec3i> modelChest = new ArrayList <> ();
						for (int x = 4; x < 12; x++)
							for (int y = 0; y < 12; y++)
								for (int z = 6; z < 10; z++)
									modelChest.add (new Vec3i (x,y,z));
						BitHelper.createBaseArmorBlock (modelChest.toArray (new Vec3i[0]),world,pos);
						ArrayList <Vec3i> modelArm = new ArrayList <> ();
						for (int x = 6; x < 10; x++)
							for (int y = 0; y < 12; y++)
								for (int z = 6; z < 10; z++)
									modelArm.add (new Vec3i (x,y,z));
						BitHelper.createBaseArmorBlock (modelArm.toArray (new Vec3i[0]),world,pos.add (1,0,0));
						BitHelper.createBaseArmorBlock (modelArm.toArray (new Vec3i[0]),world,pos.add (-1,0,0));
						stack.setCount (stack.getCount ()-1);
						return EnumActionResult.SUCCESS;
					}
					case (2): {
						ArrayList <Vec3i> modelLegs = new ArrayList <> ();
						for (int x = 4; x < 12; x++)
							for (int y = 0; y < 9; y++)
								for (int z = 6; z < 10; z++)
									modelLegs.add (new Vec3i (x,y,z));
						BitHelper.createBaseArmorBlock (modelLegs.toArray (new Vec3i[0]),world,pos);
						stack.setCount (stack.getCount ()-1);
						return EnumActionResult.SUCCESS;
					}
					case (3): {
						ArrayList <Vec3i> modelBoots = new ArrayList <> ();
						for (int x = 4; x < 12; x++)
							for (int y = 0; y < 5; y++)
								for (int z = 6; z < 10; z++)
									modelBoots.add (new Vec3i (x,y,z));
						BitHelper.createBaseArmorBlock (modelBoots.toArray (new Vec3i[0]),world,pos);
						stack.setCount (stack.getCount ()-1);
						return EnumActionResult.SUCCESS;
					}
					default:
						return EnumActionResult.PASS;
				}
			} else
				player.sendMessage (new TextComponentString (net.minecraft.client.resources.I18n.format (Local.BLOCK_MODELPLACER).replaceAll ("#",MODEL_BLOCK.getLocalizedName ())));
		}
		return EnumActionResult.FAIL;
	}

	@Override
	public void getSubItems (CreativeTabs tab,NonNullList <ItemStack> sub) {
		for (int i = 0; i <= 3; i++)
			sub.add (new ItemStack (VoidRPGItems.modelPlacer,1,i));
	}

	@Override
	public String getItemStackDisplayName (ItemStack stack) {
		return I18n.translateToLocal (stack.getUnlocalizedName () + "_" + stack.getItemDamage () + ".name");
	}
}
