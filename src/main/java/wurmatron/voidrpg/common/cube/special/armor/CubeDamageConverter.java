package wurmatron.voidrpg.common.cube.special.armor;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.api.cube.IProtectionCube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.items.CustomArmor;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.ArmorHelper;

public class CubeDamageConverter implements ICube,IProtectionCube {
		@Override
		public String getUnlocalizedName () {
				return "damageConverter";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeDamageConverter;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/damageConverter.png");
		}

		@Override
		public double getWeight () {
				return 100;
		}

		@Override
		public int getComplexity () {
				return 0;
		}

		@Override
		public int getDurability () {
				return 0;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return false;
		}

		@Override
		public void applyEffect (EntityPlayer player, CubeData data, CubeData[] cubes, ItemStack stack) {
		}

		@Override
		public int getMaxAmount (Item item) {
				return 8;
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
				return "cube.damageConverter.description";
		}

		@SubscribeEvent
		public void onLivingHurt (LivingHurtEvent e) {
				if (e.getEntityLiving() instanceof EntityPlayer && e.getAmount() > 2) {
						EntityPlayer player = (EntityPlayer) e.getEntityLiving();
						int amountOfThis = 0;
						if (player.inventory != null && player.inventory.armorInventory != null) {
								for (ItemStack armor : player.inventory.armorInventory) {
										if (armor != null && armor.getItem() instanceof CustomArmor && armor.getTagCompound() != null) {
												for (ICube cube : ArmorHelper.instance.getCubes(armor))
														if (cube.getUnlocalizedName().equals(this.getUnlocalizedName()))
																amountOfThis++;
										}
								}
								if (amountOfThis > 0) {
										float amount = e.getAmount() - amountOfThis;
										if(amount > 0)
												e.setAmount(amount);
										else
												e.setAmount(0);
										// TODO Damage cube
								}
						}
				}
		}

		@Override
		public int getOverallProtection (EntityPlayer player, DamageSource source) {
				return 0;
		}
}
