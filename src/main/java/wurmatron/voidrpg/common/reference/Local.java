package wurmatron.voidrpg.common.reference;

import com.sun.org.apache.xml.internal.security.utils.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Local {
		public static final String STAFF_CHARGING = "chat.message.charging";
		public static final String STAFF_INVALID = "chat.message.invalid";
		public static final String STAFF_HELMET = "chat.message.validHelmet";
		public static final String STAFF_CHESTPLATE = "chat.message.validChestplate";
		public static final String STAFF_LEGS = "chat.message.validLeggings";
		public static final String STAFF_BOOTS = "chat.message.validBoots";
		public static final String DURABILITY = "stat.durability.name";
		public static final String COMPLEXITY = "stat.complexity.name";
		public static final String WEIGHT = "stat.weight.name";
		public static final String MAXAMOUNT_HELMET = "stat.maxHelmet.name";
		public static final String MAXAMOUNT_CHEST = "stat.maxChest.name";
		public static final String MAXAMOUNT_LEGS = "stat.maxLegs.name";
		public static final String MAXAMOUNT_BOOTS = "stat.maxBoots.name";
		public static final String PLACMENT_TYPE = "tooltip.placmenttype.name";

		@SideOnly (Side.CLIENT)
		public static String transtate (String key) {
				return I18n.translate(key);
		}
}
