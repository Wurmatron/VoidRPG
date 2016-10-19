package wurmatron.voidrpg.common.cube.special.energy;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Loader;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.energy.TeslaHelper;

public class CubeSolarIII implements ICube {

		private int timer;

		@Override
		public String getUnlocalizedName () {
				return "solarIII";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.energySolarIII;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/solarIII.png");
		}

		@Override
		public double getWeight () {
				return 10;
		}

		@Override
		public int getComplexity () {
				return 10;
		}

		@Override
		public int getDurability () {
				return 400;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				if (Loader.isModLoaded("tesla") && player.worldObj.canSeeSky(new BlockPos(player.posX, player.posY, player.posZ)))
						return true;
				return false;
		}

		@Override
		public void applyEffect (EntityPlayer player, CubeData data, CubeData[] cubes, ItemStack stack) {
				if (Loader.isModLoaded("tesla"))
						if (timer == 0) {
								timer = 500;
								TeslaHelper.addPower(stack, 1);
						} else
								timer--;
		}

		@Override
		public int getMaxAmount (Item item) {
				return 0;
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
				return "cube.solarIII.description";
		}
}
