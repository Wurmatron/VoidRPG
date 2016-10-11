package wurmatron.voidrpg.common.cube.special.chestOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.Global;

public class CubeJetpack implements ICube {
		@Override
		public String getUnlocalizedName () {
				return "jetpack";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeJetpack;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/jetpack.png");
		}

		@Override
		public double getWeight () {
				return 200;
		}

		@Override
		public int getComplexity () {
				return 100;
		}

		@Override
		public int getDurability () {
				return 800;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return true;
		}

		@Override
		public void applyEffect (EntityPlayer player, CubeData data, CubeData[] cubes) {
				if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
						player.motionY += 0.00006;
		}

		@Override
		public int getMaxAmount (Item item) {
				return 0;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				if (item.equals(VoidRPGItems.armorChestplate))
						return 4;
				return 0;
		}

		@Override
		public boolean getSupportedArmorTypes (EntityEquipmentSlot type) {
			return type.equals(EntityEquipmentSlot.CHEST);
		}
}
