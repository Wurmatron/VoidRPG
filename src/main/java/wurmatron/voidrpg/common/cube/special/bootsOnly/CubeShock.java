package wurmatron.voidrpg.common.cube.special.bootsOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.ArmorHelper;
import wurmatron.voidrpg.common.utils.ArmorHelper2;

public class CubeShock implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "shock";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeShock;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/shock");
		}

		@Override
		public double getWeight () {
				return 20;
		}

		@Override
		public int getComplexity () {
				return 10;
		}

		@Override
		public int getDurability () {
				return 100;
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
				if (item.equals(VoidRPGItems.armorBoots))
						return 4;
				return 0;
		}

		@Override
		public boolean getSupportedArmorTypes (EntityEquipmentSlot type) {
				return type.equals(EntityEquipmentSlot.FEET);
		}


		@Override
		public String getDescription () {
				return "cube.shock.description";
		}

		@SubscribeEvent
		public void onPlayerFall (LivingFallEvent e) {
				if (e.getEntityLiving() instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer) e.getEntityLiving();
						if (player.inventory != null && player.inventory.armorInventory[0] != null && player.inventory.armorInventory[0].getItem().equals(VoidRPGItems.armorBoots)) {
								if (new ArmorHelper2().isActive(this, player.inventory.armorInventory[0])) {
										e.setCanceled(true);
								}
						}
				}
		}


}
