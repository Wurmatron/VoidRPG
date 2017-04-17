package wurmatron.voidrpg.client.model;

import net.minecraft.client.model.ModelBiped;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.utils.BitHelper;
import wurmatron.voidrpg.common.utils.LogHandler;

import java.util.ArrayList;
import java.util.Collections;

public class ArmorModel extends ModelBiped {

		private ArrayList<CubeData> headCubes       = new ArrayList<>();
		private ArrayList<CubeData> bodyCubes       = new ArrayList<>();
		private ArrayList<CubeData> leftArmCubes    = new ArrayList<>();
		private ArrayList<CubeData> rightArmCubes   = new ArrayList<>();
		private ArrayList<CubeData> leftLegCubes    = new ArrayList<>();
		private ArrayList<CubeData> rightLegCubes   = new ArrayList<>();
		private ArrayList<CubeData> leftBootsCubes  = new ArrayList<>();
		private ArrayList<CubeData> rightBootsCubes = new ArrayList<>();

		public ArmorModel() {
				LogHandler.info("New Instance");
		}

		public void addHeadCubes(CubeData[] head) {
				if (head != null && head.length > 0) headCubes = checkForRendering(head);
				LogHandler.info("Handling Head Cubes " + head.length);
		}

		public void addBodyCubes(CubeData[] body) {
				if (body != null && body.length > 0) bodyCubes = checkForRendering(body);
		}

		public void addLeftArmCubes(CubeData[] leftArm) {
				if (leftArm != null && leftArm.length > 0) leftArmCubes = checkForRendering(leftArm);
		}

		public void addRightArmCubes(CubeData[] rightArm) {
				if (rightArm != null && rightArm.length > 0) rightArmCubes = checkForRendering(rightArm);
		}

		public void addLeftLegCubes(CubeData[] leftLeg) {
				if (leftLeg != null && leftLeg.length > 0) leftLegCubes = checkForRendering(leftLeg);
		}

		public void addRightLegCubes(CubeData[] rightLeg) {
				if (rightLeg != null && rightLeg.length > 0) rightLegCubes = checkForRendering(rightLeg);
		}

		public void addLeftBootCubes(CubeData[] leftBoot) {
				if (leftBoot != null && leftBoot.length > 0) leftBootsCubes = checkForRendering(leftBoot);
		}

		public void addRightBootCubes(CubeData[] rightBoot) {
				if (rightBoot != null && rightBoot.length > 0) rightBootsCubes = checkForRendering(rightBoot);
		}

		private ArrayList<CubeData> checkForRendering(CubeData[] data) {
				ArrayList<CubeData> output = new ArrayList<>(); Collections.addAll(output, data); return output;
		}

		public ArmorModel handleData(ModelBiped base) {
				if (headCubes != null && headCubes.size() > 0) for (CubeData temp : headCubes)
						if (temp != null) bipedHead.addChild(BitHelper.createModelRenderer(base, temp));
				if (bodyCubes != null && bodyCubes.size() > 0) for (CubeData temp : bodyCubes)
						bipedBody.addChild(BitHelper.createModelRenderer(base, temp));
				if (leftArmCubes != null && leftArmCubes.size() > 0) for (CubeData temp : leftArmCubes)
						bipedLeftArm.addChild(BitHelper.createModelRenderer(base, temp));
				if (rightArmCubes != null && rightArmCubes.size() > 0) for (CubeData temp : rightArmCubes)
						bipedRightArm.addChild(BitHelper.createModelRenderer(base, temp));
				if (leftLegCubes != null && leftLegCubes.size() > 0) for (CubeData temp : leftLegCubes)
						bipedLeftLeg.addChild(BitHelper.createModelRenderer(base, temp));
				if (rightLegCubes != null && rightLegCubes.size() > 0) for (CubeData temp : rightLegCubes)
						bipedRightLeg.addChild(BitHelper.createModelRenderer(base, temp));
				if (leftBootsCubes != null && leftBootsCubes.size() > 0) for (CubeData temp : leftBootsCubes)
						bipedLeftLeg.addChild(BitHelper.createModelRenderer(base, temp));
				if (rightBootsCubes != null) for (CubeData temp : rightBootsCubes)
						bipedRightLeg.addChild(BitHelper.createModelRenderer(base, temp)); return this;
		}
}