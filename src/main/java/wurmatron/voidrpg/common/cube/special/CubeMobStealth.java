package wurmatron.voidrpg.common.cube.special;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.ArmorHelper;

public class CubeMobStealth implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "mobStealth";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeMobStealth;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/mobStealth.png");
		}

		@Override
		public double getWeight () {
				return 50;
		}

		@Override
		public int getComplexity () {
				return 200;
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
		public void applyEffect(EntityPlayer player, CubeData data, ItemStack stack) {
		}

		@Override
		public int getMaxAmount (Item item) {
				return 0;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				return 12;
		}

		@Override
		public boolean getSupportedArmorTypes (EntityEquipmentSlot type) {
				return true;
		}

		@SubscribeEvent
		public void onEntitySetTarget (LivingSetAttackTargetEvent e) {
				if (e.getTarget() != null && e.getTarget() instanceof EntityPlayer && e.getEntityLiving().getLastAttacker() != e.getTarget()) {
						EntityPlayer player = (EntityPlayer) e.getTarget();
						if (player.inventory != null && player.inventory.armorInventory != null) {
								for (ItemStack inv : player.inventory.armorInventory) {
										if (ArmorHelper.instance.isCubeActive(this, inv))
												((EntityLiving) e.getEntity()).setAttackTarget(null);
								}
						}
				}
		}


		@Override
		public String getDescription () {
				return "cube.mobStealth.description";
		}
}
