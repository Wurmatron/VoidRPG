package wurmatron.voidrpg.common.cube.special.armor;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.ArmorHelper;

import java.util.ArrayList;
import java.util.List;

public class CubeLife implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "life";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.armorLife;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/life.png");
		}

		@Override
		public double getWeight () {
				return 100;
		}

		@Override
		public int getComplexity () {
				return 50;
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
		public void applyEffect (EntityPlayer player, CubeData data, CubeData[] cubes) {

		}

		@Override
		public int getMaxAmount (Item item) {
				return 100;
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
				return "cube.life.description";
		}

		@SubscribeEvent
		public void onLivingHurt (LivingHurtEvent e) {
				if (e.getEntityLiving() instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer) e.getEntityLiving();
						if (player.inventory != null && player.inventory.armorInventory != null) {
								int lifeAmount = 0;
								for (ItemStack armor : player.inventory.armorInventory) {
										if (armor != null && armor.getItem().equals(VoidRPGItems.armorHelmet)) {
												ArrayList<ICube> helmet = ArmorHelper.instance.getCubes(armor);
												for (ICube cube : helmet)
														if (cube.getUnlocalizedName().equals(this.getUnlocalizedName()))
																lifeAmount++;
										}
								}
								if (lifeAmount > getMaxAmount(null))
										lifeAmount = getMaxAmount(null);
								if (lifeAmount > 0) {
										List<Entity> entities = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(10, 10, 10));
										for (Entity ent : entities) {
												if (ent != null)
														if (ent instanceof EntityLivingBase) {
																EntityLivingBase entity = (EntityLivingBase) ent;
																if (!(entity instanceof EntityPlayer)) {
																		if (player.getHealth() != player.getMaxHealth()) {
																				float amountToHeal = player.getMaxHealth() - player.getHealth();
																				if (amountToHeal <= entity.getHealth()) {
																						entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(player, entity), amountToHeal);
																						player.heal(amountToHeal);
																				}
																				else {
																						player.heal(entity.getHealth());
																						entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(player, entity), entity.getHealth());
																				}
																		}
																}
														}
										}
								}
						}
				}
		}
}
