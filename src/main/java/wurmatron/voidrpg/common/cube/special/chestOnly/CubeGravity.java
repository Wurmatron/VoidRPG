package wurmatron.voidrpg.common.cube.special.chestOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
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

public class CubeGravity implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "gravity";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeGravity;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/gravity.png");
		}

		@Override
		public double getWeight () {
				return 2000;
		}

		@Override
		public int getComplexity () {
				return 9000;
		}

		@Override
		public int getDurability () {
				return 800;
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
				if (item.equals(VoidRPGItems.armorChestplate))
						return 8;
				return 0;
		}

		@SubscribeEvent
		public void onPlayerTick (TickEvent.PlayerTickEvent e) {
				if (e.player.inventory != null && e.player.inventory.armorInventory[2] != null && e.player.inventory.armorInventory[2].getItem().equals(VoidRPGItems.armorChestplate)) {
						if (new ArmorHelper().isCubeActive(this, e.player.inventory.armorInventory[2]))
								e.player.capabilities.allowFlying = true;
				} else {
						// TODO Fix this so it supports other mods
						if (e.player.inventory != null && e.player.inventory.armorInventory[2] == null && !e.player.capabilities.isCreativeMode)
								e.player.capabilities.allowFlying = false;
				}
		}
}
