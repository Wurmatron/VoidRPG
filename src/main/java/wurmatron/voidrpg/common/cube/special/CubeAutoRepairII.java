package wurmatron.voidrpg.common.cube.special;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.reference.Global;

public class CubeAutoRepairII implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "autoRepairII";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.energyAutoRepairII;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/auto-repairII.png");
		}

		@Override
		public double getWeight () {
				return 10;
		}

		@Override
		public int getComplexity () {
				return 25;
		}

		@Override
		public int getDurability () {
				return 20;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return true;
		}

		@Override
		public void applyEffect (EntityPlayer player, CubeData cube, CubeData[] data, ItemStack stack) {
				for (CubeData c : data)
						if (c.damage > 0)
								c.damage -= 2;
		}

		@Override
		public int getMaxAmount (Item item) {
				return 200;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				return 0;
		}

		@Override
		public boolean getSupportedArmorTypes (EntityEquipmentSlot type) {
				return true;
		}


		@Override
		public String getDescription () {
				return "cube.autoRepairII.description";
		}
}