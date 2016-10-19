package wurmatron.voidrpg.common.cube;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.IProtectionCube;

public class CubeArmor extends Cube implements IProtectionCube {

		private int protection;

		public CubeArmor (String unlocalizedName, Block block, ResourceLocation texture, double weight, int complexity, int durability, int maxAmount, EntityEquipmentSlot[] armorType, String descriptionKey, int protection) {
				super(unlocalizedName, block, texture, weight, complexity, durability, maxAmount, armorType, descriptionKey);
				this.protection = protection;
		}

		@Override
		public int getOverallProtection (EntityPlayer player, DamageSource source) {
				return protection;
		}
}
