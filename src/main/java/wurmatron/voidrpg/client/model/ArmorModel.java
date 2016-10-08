package wurmatron.voidrpg.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.utils.ArmorHelper;

import java.util.ArrayList;

public class ArmorModel extends ModelBiped {

		public ArrayList<CubeData> headCubes = new ArrayList<>();
		public ArrayList<CubeData> bodyCubes = new ArrayList<>();
		public ArrayList<CubeData> leftArmCubes = new ArrayList<>();
		public ArrayList<CubeData> rightArmCubes = new ArrayList<>();
		public ArrayList<CubeData> leftLegCubes = new ArrayList<>();
		public ArrayList<CubeData> rightLegCubes = new ArrayList<>();
		public ArrayList<CubeData> leftBootsCubes = new ArrayList<>();
		public ArrayList<CubeData> rightBootsCubes = new ArrayList<>();

		public void addHeadCubes (CubeData[] head) {
				if (head != null && head.length > 0)
						headCubes = getValidForRendering(head);
		}

		public void addBodyCubes (CubeData[] body) {
				if (body != null && body.length > 0)
						bodyCubes = getValidForRendering(body);
		}

		public void addLeftArmCubes (CubeData[] leftArm) {
				if (leftArm != null && leftArm.length > 0)
						leftArmCubes = getValidForRendering(leftArm);
		}

		public void addRightArmCubes (CubeData[] rightArm) {
				if (rightArm != null && rightArm.length > 0)
						rightArmCubes = getValidForRendering(rightArm);
		}

		public void addLeftLegCubes (CubeData[] leftLeg) {
				if (leftLeg != null && leftLeg.length > 0)
						leftLegCubes = getValidForRendering(leftLeg);
		}

		public void addRightLegCubes (CubeData[] rightLeg) {
				if (rightLeg != null && rightLeg.length > 0)
						rightLegCubes = getValidForRendering(rightLeg);
		}

		public void addLeftBootsCubes (CubeData[] leftLeg) {
				if (leftLeg != null && leftLeg.length > 0)
						leftBootsCubes = getValidForRendering(leftLeg);
		}

		public void addRightBootsCubes (CubeData[] rightLeg) {
				if (rightLeg != null && rightLeg.length > 0)
						rightBootsCubes = getValidForRendering(rightLeg);
		}

		private ArrayList<CubeData> getValidForRendering (CubeData[] data) {
				if (data != null && data.length > 0) {
						ArrayList<CubeData> temp = new ArrayList<CubeData>();
						for (CubeData cube : data) {
								if (isValidToRender(cube, data))
										temp.add(cube);
						}
						return temp;
				}
				return new ArrayList<CubeData>();
		}

		private boolean isValidToRender (CubeData cube, CubeData[] data) {
				for (CubeData c : data)
						if (cube.offZ + 1 == c.offZ && cube.offZ - 1 == c.offZ && cube.offY + 1 == c.offY && cube.offY - 1 == c.offY && cube.offX + 1 == c.offX && cube.offX - 1 == c.offX)
								return false;
				return true;
		}

		public ModelBiped covertDataToModel (ModelBiped base) {
				if (headCubes != null && headCubes.size() > 0)
						for (CubeData temp : headCubes)
								bipedHead.addChild(ArmorHelper.createModelRenderer(base, new CubeData(temp.offX - 8, temp.offY - 16, temp.offZ - 6, temp.cube, temp.damage)));
				if (bodyCubes != null && bodyCubes.size() > 0)
						for (CubeData temp : bodyCubes)
								bipedBody.addChild(ArmorHelper.createModelRenderer(base, new CubeData(temp.offX - 9, temp.offY - 4, temp.offZ - 8, temp.cube, temp.damage)));
				if (leftArmCubes != null && leftArmCubes.size() > 0)
						for (CubeData temp : leftArmCubes)
								bipedLeftArm.addChild(ArmorHelper.createModelRenderer(base, new CubeData(temp.offX - 8, temp.offY - 2, temp.offZ - 9, temp.cube, temp.damage)));
				if (rightArmCubes != null && rightArmCubes.size() > 0)
						for (CubeData temp : rightArmCubes)
								bipedRightArm.addChild(ArmorHelper.createModelRenderer(base, new CubeData(temp.offX - 10, temp.offY - 2, temp.offZ - 9, temp.cube, temp.damage)));
				if (leftLegCubes != null && leftLegCubes.size() > 0)
						for (CubeData temp : leftLegCubes)
								bipedLeftLeg.addChild(ArmorHelper.createModelRenderer(base, new CubeData(temp.offX - 7, temp.offY - 5, temp.offZ - 7, temp.cube, temp.damage)));
				if (leftLegCubes != null && leftLegCubes.size() > 0)
						for (CubeData temp : leftLegCubes)
								bipedLeftLeg.addChild(ArmorHelper.createModelRenderer(base, new CubeData(temp.offX - 11, temp.offY - 5, temp.offZ - 7, temp.cube, temp.damage)));
				if (rightLegCubes != null && rightLegCubes.size() > 0)
						for (CubeData temp : rightLegCubes)
								bipedRightLeg.addChild(ArmorHelper.createModelRenderer(base, new CubeData(temp.offX - 7, temp.offY - 5, temp.offZ - 7, temp.cube, temp.damage)));
				if (leftBootsCubes != null && leftBootsCubes.size() > 0)
						for (CubeData temp : leftBootsCubes)
								bipedLeftLeg.addChild(ArmorHelper.createModelRenderer(base, new CubeData(temp.offX - 11, temp.offY, temp.offZ - 9, temp.cube, temp.damage)));
				if (rightBootsCubes != null && rightBootsCubes.size() > 0)
						for (CubeData temp : rightBootsCubes)
								bipedRightLeg.addChild(ArmorHelper.createModelRenderer(base, new CubeData(temp.offX - 7, temp.offY, temp.offZ - 9, temp.cube, temp.damage)));
				return this;
		}

		public ModelBiped clear () {
				clearAndReset(bipedHead, 0.0F, 0.0F, 0.0F);
				clearAndReset(bipedBody, 0.0F, 0.0F, 0.0F);
				clearAndReset(bipedRightArm, 5, 2.0F, 0.0F);
				clearAndReset(bipedLeftArm, -5, 2.0F, 0.0F);
				clearAndReset(bipedRightLeg, 2, 12.0F, 0.0F);
				clearAndReset(bipedLeftLeg, -2, 12.0F, 0.0F);
				bipedHeadwear.cubeList.clear();
				headCubes = new ArrayList<>();
				bodyCubes = new ArrayList<>();
				leftArmCubes = new ArrayList<>();
				rightArmCubes = new ArrayList<>();
				leftLegCubes = new ArrayList<>();
				rightLegCubes = new ArrayList<>();
				return this;
		}

		public void clearAndReset (ModelRenderer mr, float x, float y, float z) {
				mr.cubeList.clear();
				mr.setRotationPoint(x, y, z);
		}
}
