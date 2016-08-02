package wurmatron.voidrpg.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class ArmorModel extends ModelBiped {

		public static ArmorModel instance;

		public static void createInstance (int timeTillUpdate) {
				if (instance == null)
						instance = new ArmorModel();
				if (timeTillUpdate % 10 == 0)
						instance = new ArmorModel();
		}

		public ModelBiped init () {
				clearAndReset(bipedHead, 0.0F, 0.0F, 0.0F);
				clearAndReset(bipedBody, 0.0F, 0.0F, 0.0F);
				clearAndReset(bipedRightArm, 5, 2.0F, 0.0F);
				clearAndReset(bipedLeftArm, -5, 2.0F, 0.0F);
				clearAndReset(bipedRightLeg, 2, 12.0F, 0.0F);
				clearAndReset(bipedLeftLeg, -2, 12.0F, 0.0F);
				bipedHeadwear.cubeList.clear();
				return this;
		}

		public void clearAndReset (ModelRenderer mr, float x, float y, float z) {
				mr.cubeList.clear();
				mr.setRotationPoint(x, y, z);
		}
}
