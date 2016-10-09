package wurmatron.voidrpg.common.cube.special.leggingsOnly;

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

public class CubeMuscle implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "muscle";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeMuscle;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/muscle.png");
		}

		@Override
		public double getWeight () {
				return 50;
		}

		@Override
		public int getComplexity () {
				return 20;
		}

		@Override
		public int getDurability () {
				return 1000;
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
				if (item.equals(VoidRPGItems.armorLeggings))
						return 4;
				return 0;
		}

		@SubscribeEvent
		public void onPlayerTick (TickEvent.PlayerTickEvent e) {
				if (e.player.inventory != null && e.player.inventory.armorInventory[1] != null && e.player.inventory.armorInventory[1].getItem().equals(VoidRPGItems.armorLeggings)) {
						if (new ArmorHelper().isCubeActive(this, e.player.inventory.armorInventory[1])) {
								e.player.motionX *= 1.05D;
								e.player.motionZ *= 1.05D;
								e.player.motionY *= 1.05D;
						}
				}
		}
}
