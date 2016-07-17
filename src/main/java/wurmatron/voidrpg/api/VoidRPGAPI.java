package wurmatron.voidrpg.api;

import wurmatron.voidrpg.api.materials.IMaterial;
import wurmatron.voidrpg.common.materials.MaterialRegistry;

import java.util.Collections;
import java.util.List;

public class VoidRPGAPI {

		/**
		 * Register an material to be used for armor creation
		 *
		 * @see MaterialRegistry
		 */
		public static void registerMaterial (IMaterial material) {
				MaterialRegistry.overrideMaterial(material);
		}

		public static List<IMaterial> getMaterials () {
				return Collections.unmodifiableList(MaterialRegistry.getMaterials());
		}
}
