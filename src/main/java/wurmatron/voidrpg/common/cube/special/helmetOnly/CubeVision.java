package wurmatron.voidrpg.common.cube.special.helmetOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.utils.ArmorHelper;

public class CubeVision implements ICube {

		@Override
		public String getUnlocalizedName () {
				return "vision";
		}

		@Override
		public Block getBlock () {
				return VoidRPGBlocks.cubeVision;
		}

		@Override
		public ResourceLocation getTexture () {
				return new ResourceLocation(Global.MODID, "textures/cube/vision.png");
		}

		@Override
		public double getWeight () {
				return 10;
		}

		@Override
		public int getComplexity () {
				return 50;
		}

		@Override
		public int getDurability () {
				return 200;
		}

		@Override
		public boolean hasEffects (EntityPlayer player, ItemStack stack) {
				return false;
		}

		@Override
		public void applyEffect (CubeData data, CubeData[] cubes) {
		}

		@Override
		public int getMaxAmount (Item item) {
				return 0;
		}

		@Override
		public int getMinAmount (Item item, double weight) {
				if (item.equals(VoidRPGItems.armorHelmet))
						return 8;
				return 0;
		}

		@SubscribeEvent
		public void onEntityUpdate (LivingEvent.LivingUpdateEvent e) {
				if (e.getEntityLiving() instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer) e.getEntityLiving();
						if (player.inventory != null && player.inventory.armorInventory != null && player.inventory.armorInventory[3] != null && player.inventory.armorInventory[3].getItem().equals(VoidRPGItems.armorHelmet)) {
								if (new ArmorHelper().isCubeActive(this, player.inventory.armorInventory[3]))
										if (player.isPotionActive(Potion.getPotionById(16))) {
													if(player.getActivePotionEffect(Potion.getPotionById(16)).getDuration() < 60)
															player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 200));
										} else
												player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 200));
						}
				}
		}
}
