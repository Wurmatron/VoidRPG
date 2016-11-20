package wurmatron.voidrpg.common.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.api.event.CubeTickEvent;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.energy.TeslaHelper;

public class CustomArmorHelper {

		private static final ArmorHelper helper = new ArmorHelper();

		public void onArmorTick (World world, EntityPlayer player, ItemStack stack) {
				if (stack != null && stack.getTagCompound() != null && !stack.getTagCompound().hasNoTags() && Settings.cubeEffects && !world.isRemote) {
						switch (stack.getItem().getUnlocalizedName().substring(11)) {
								case ("head"):
										proccessCubeData(helper.getHelmetCubes(stack), player, stack);
										break;
								case ("chest"):
										proccessCubeData(helper.getChestplateCubes(stack), player, stack);
										break;
								case ("legs"):
										proccessCubeData(helper.getLeggingsCubes(stack), player, stack);
										break;
								case ("feet"):
										proccessCubeData(helper.getBootsCubes(stack), player, stack);
										break;
						}
						if (!stack.getTagCompound().hasKey(NBT.ENERGY) && Loader.isModLoaded("tesla"))
								fixEnergy(stack);
				}
		}

		private void proccessCubeData (CubeData[] data, EntityPlayer player, ItemStack stack) {
				if (data != null && data.length > 0)
						for (CubeData cube : data) {
								if (helper.isCubeActive(cube.cube, stack, data) && cube.cube.hasEffects(player, stack)) {
										cube.cube.applyEffect(player, cube, data, stack);
										MinecraftForge.EVENT_BUS.post(new CubeTickEvent(cube, player, stack));
								}
						}
		}

		@Optional.Method (modid = "tesla")
		private void fixEnergy (ItemStack stack) {
				TeslaHelper.setMaxCapacity(stack, ArmorHelper.instance.getMaxEnergyStorage(stack));
		}

		public void onCubeTick (EntityPlayer player, ItemStack stack) {
				for (int slot = 0; slot < 4; slot++)
						if (player.inventory.armorInventory[slot].isItemEqual(stack) && player.inventory.armorInventory[slot].getTagCompound().equals(stack.getTagCompound())) {
								switch (stack.getItem().getUnlocalizedName().substring(11)) {
										case ("head"): {
												CubeData[] head = ArmorHelper.instance.getHelmetCubes(stack);
												if(head != null && head.length > 0) {
														int killedCubes = 0;
														for(int c =0; c < head.length; c++) {
																if(head[c].damage >= head[c].cube.getDurability()) {
																		head[c] = null;
																		killedCubes++;
																}
														}
														if(killedCubes > 0) {

														}
												}
										}
										case ("chest"):
												proccessCubeData(helper.getChestplateCubes(stack), player, stack);
												break;
										case ("legs"):
												proccessCubeData(helper.getLeggingsCubes(stack), player, stack);
												break;
										case ("feet"):
												proccessCubeData(helper.getBootsCubes(stack), player, stack);
												break;
								}
						}
		}
}
