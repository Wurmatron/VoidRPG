package wurmatron.voidrpg.common.materials;

import wurmatron.voidrpg.api.materials.IMaterial;
import wurmatron.voidrpg.common.utils.LogHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaterialRegistry {

		public static ArrayList<IMaterial> materials = new ArrayList<IMaterial>();
		private static HashMap<String, IMaterial> names = new HashMap<String, IMaterial>();

		public static void registerMaterial (IMaterial material) {
				if (!materials.contains(material)) {
						materials.add(material);
						names.put(material.getUnlocalizedName(), material);
						LogHandler.info("Registering Material " + material.getUnlocalizedName());
				}
		}

		public static List<IMaterial> getMaterials () {
				return materials;
		}

		public static void overrideMaterial (IMaterial material) {
				if (names.containsKey(material.getUnlocalizedName())) {
						materials.remove(names.get(material.getUnlocalizedName()));
						names.remove(names.get(material.getUnlocalizedName()));
						materials.add(material);
						names.put(material.getUnlocalizedName(), material);
						LogHandler.info("Material " + material.getUnlocalizedName() + " overrided");
				} else
						registerMaterial(material);
		}
}
