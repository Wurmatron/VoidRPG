package wurmatron.voidrpg.common.cube.special.energy;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.api.cube.IReactor;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.reference.Global;

public class CubeReactorIII implements ICube,IReactor {

		@Override
		public String getUnlocalizedName () {
				return "reactorIII";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.energyReactorIII;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/reactorIII.png");
		}

		@Override
		public double getWeight () {
				return 5000;
		}

		@Override
		public int getComplexity () {
				return 800;
		}

		@Override
		public int getDurability () {
				return 12000;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return false;
		}

		@Override
		public void applyEffect(EntityPlayer player, CubeData data, ItemStack stack) {

		}

		@Override
		public int getMaxAmount (Item item) {
				return 32;
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
				return "cube.reactorIII.description";
		}

		@Override
		public int getMaxPower () {
				return 100;
		}
}
