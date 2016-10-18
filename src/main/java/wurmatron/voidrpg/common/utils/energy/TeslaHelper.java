package wurmatron.voidrpg.common.utils.energy;

import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.Optional;

public class TeslaHelper {

		@Optional.Method (modid = "tesla")
		public static boolean isValidItem (ItemStack stack) {
				if (stack.hasCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.UP))
						return true;
				return false;
		}

		@Optional.Method (modid = "tesla")
		public static BaseTeslaContainer getContainer (ItemStack stack) {
				if (isValidItem(stack)) {
						BaseTeslaContainer container = (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.UP);
						return container;
				}
				return null;
		}

		@Optional.Method (modid = "tesla")
		public static long getStoredPower (ItemStack stack) {
				if (isValidItem(stack)) {
						BaseTeslaContainer container = getContainer(stack);
						return getContainer(stack).getStoredPower();
				} else return 0;
		}

		@Optional.Method (modid = "tesla")
		public static void addPower (ItemStack stack, long amount) {
				if (isValidItem(stack))
						getContainer(stack).givePower(amount, false);
		}

		@Optional.Method (modid = "tesla")
		public static long getMaxCapacity (ItemStack stack) {
				if (isValidItem(stack))
						return getContainer(stack).getCapacity();
				else
						return 0;
		}

		@Optional.Method (modid = "tesla")
		public static void setMaxCapacity (ItemStack stack, long amount) {
				if (isValidItem(stack))
						getContainer(stack).setCapacity(amount);
		}

		@Optional.Method (modid = "tesla")
		public static void consumePower (ItemStack stack, long amount) {
				if (isValidItem(stack))
						getContainer(stack).takePower(amount, false);
		}
}
