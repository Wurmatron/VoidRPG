package wurmatron.voidrpg.common.cube.special.armor;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.api.cube.IProtectionCube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.energy.TeslaHelper;

public class CubeEnergyShieldIII implements ICube,IProtectionCube {

		@Override
		public String getUnlocalizedName () {
				return "energyShieldIII";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.armorEnergyShieldIII;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/energyShieldIII.png");
		}

		@Override
		public double getWeight () {
				return 20;
		}

		@Override
		public int getComplexity () {
				return 200;
		}

		@Override
		public int getDurability () {
				return 150000;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return true;
		}

		@Override
		public void applyEffect (EntityPlayer player, CubeData data, CubeData[] cubes, ItemStack stack) {
				if (data.damage > 0 && Loader.isModLoaded("tesla")) {
						data.damage -= 20;
						TeslaHelper.consumePower(stack, 50);
				}
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
				return "cube.energyShieldIII.description";
		}

		@Override
		public int getOverallProtection (EntityPlayer player, DamageSource source) {
				return 0;
		}
}
