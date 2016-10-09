package wurmatron.voidrpg.common.cube.special.bootsOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.ArmorHelper;

public class CubeWaterWalk implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "waterWalk";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeWaterWalking;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/waterWalking.png");
		}

		@Override
		public double getWeight () {
				return 10;
		}

		@Override
		public int getComplexity () {
				return 20;
		}

		@Override
		public int getDurability () {
				return 400;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return false;
		}

		@Override
		public void applyEffect (CubeData data, CubeData[] cubes) {
		}

		@Override
		public int getMaxAmount (Item item) {
				return 0;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				if (item.equals(VoidRPGItems.armorBoots))
						return 8;
				return 0;
		}

		@SubscribeEvent
		public void onPlayerTick (TickEvent.PlayerTickEvent e) {
				if (e.player.inventory != null && e.player.inventory.armorInventory[0] != null && e.player.inventory.armorInventory[0].getItem().equals(VoidRPGItems.armorBoots)) {
						if (!e.player.isSneaking() && e.player.motionY < 0 && e.player.worldObj.getBlockState(e.player.getPosition().add(0, -1, 0)).getBlock().equals(Blocks.WATER) && new ArmorHelper().isCubeActive(this,e.player.inventory.armorInventory[0])) {
								e.player.motionY = 0;
								e.player.fallDistance = 0;
								e.player.onGround = true;
						}
				}
		}
}
