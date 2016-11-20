package wurmatron.voidrpg.common.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.items.CustomArmor2;
import wurmatron.voidrpg.common.reference.NBT;
import wurmatron.voidrpg.common.utils.ArmorHelper2;

public class PlayerHurtEvent {

	@SubscribeEvent
	public void onPlayerHurt (LivingHurtEvent e) {
		if (e.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.getEntityLiving();
			for (int i = 0; i < 4; i++) {
				if (player.inventory.armorInventory[i] != null && player.inventory.armorInventory[i].getItem() instanceof CustomArmor2) {
					ItemStack stack = player.inventory.armorInventory[i];
					if (stack.getTagCompound() != null && ! stack.getTagCompound().hasNoTags()) {
						switch (stack.getItem().getUnlocalizedName().substring(11)) {
							case ("head"): {
								CubeData[] head = ArmorHelper2.armourInstance.getCubeData(stack);
								CubeData[] output = new CubeData[head.length];
								for (int index = 0; index < head.length; index++)
									output[index] = new CubeData(head[index].offX, head[index].offY, head[index].offZ, head[index].cube, head[index].damage + (int) e.getAmount());
								ItemStack outputStack = ArmorHelper2.armourInstance.createHelmet(output);
								player.inventory.armorInventory[i] = outputStack;
								break;
							}
							case ("chest"): {
								CubeData[] body = ArmorHelper2.armourInstance.getCubeData(stack, NBT.BODY);
								CubeData[] left = ArmorHelper2.armourInstance.getCubeData(stack, NBT.LEFTARM);
								CubeData[] right = ArmorHelper2.armourInstance.getCubeData(stack, NBT.RIGHTARM);
								CubeData[] outputBody = new CubeData[body.length];
								CubeData[] outputLeft = new CubeData[left.length];
								CubeData[] outputRight = new CubeData[right.length];
								for (int index = 0; index < body.length; index++)
									outputBody[index] = new CubeData(body[index].offX, body[index].offY, body[index].offZ, body[index].cube, body[index].damage + (int) e.getAmount());
								for (int index = 0; index < left.length; index++)
									outputLeft[index] = new CubeData(left[index].offX, left[index].offY, left[index].offZ, left[index].cube, left[index].damage + (int) e.getAmount());
								for (int index = 0; index < right.length; index++)
									outputRight[index] = new CubeData(right[index].offX, right[index].offY, right[index].offZ, right[index].cube, right[index].damage + (int) e.getAmount());
								ItemStack outputStack = ArmorHelper2.armourInstance.createChestplate(outputBody, outputLeft, outputRight);
								player.inventory.armorInventory[i] = outputStack;
								break;
							}
							case ("legs"): {
								CubeData[] left = ArmorHelper2.armourInstance.getCubeData(stack, NBT.LEFTLEG);
								CubeData[] right = ArmorHelper2.armourInstance.getCubeData(stack, NBT.RIGHTLEG);
								CubeData[] outputLeft = new CubeData[left.length];
								CubeData[] outputRight = new CubeData[right.length];
								for (int index = 0; index < left.length; index++)
									outputLeft[index] = new CubeData(left[index].offX, left[index].offY, left[index].offZ, left[index].cube, left[index].damage + (int) e.getAmount());
								for (int index = 0; index < right.length; index++)
									outputRight[index] = new CubeData(right[index].offX, right[index].offY, right[index].offZ, right[index].cube, right[index].damage + (int) e.getAmount());
								ItemStack outputStack = ArmorHelper2.armourInstance.createLeggings(outputLeft, outputRight);
								player.inventory.armorInventory[i] = outputStack;
								break;
							}
							case ("feet"): {
								CubeData[] left = ArmorHelper2.armourInstance.getCubeData(stack, NBT.LEFTLEG);
								CubeData[] right = ArmorHelper2.armourInstance.getCubeData(stack, NBT.RIGHTLEG);
								CubeData[] outputLeft = new CubeData[left.length];
								CubeData[] outputRight = new CubeData[right.length];
								for (int index = 0; index < left.length; index++)
									outputLeft[index] = new CubeData(left[index].offX, left[index].offY, left[index].offZ, left[index].cube, left[index].damage + (int) e.getAmount());
								for (int index = 0; index < right.length; index++)
									outputRight[index] = new CubeData(right[index].offX, right[index].offY, right[index].offZ, right[index].cube, right[index].damage + (int) e.getAmount());
								ItemStack outputStack = ArmorHelper2.armourInstance.createBoots(outputLeft, outputRight);
								player.inventory.armorInventory[i] = outputStack;
								break;
							}
						}
					}
				}
			}
		}
	}
}
