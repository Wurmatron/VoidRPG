package wurmatron.voidrpg.client.model;

import net.minecraft.client.model.ModelBiped;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.utils.BitHelper;
import wurmatron.voidrpg.common.utils.DataHelper;

import java.util.ArrayList;
import java.util.Collections;

public class ArmorModel extends ModelBiped {

    public ArrayList<CubeData> headCubes;
    public ArrayList<CubeData> bodyCubes;
    public ArrayList<CubeData> leftArmCubes;
    public ArrayList<CubeData> rightArmCubes;
    public ArrayList<CubeData> leftLegCubes;
    public ArrayList<CubeData> rightLegCubes;
    public ArrayList<CubeData> leftBootsCubes;
    public ArrayList<CubeData> rightBootsCubes;

    public ArmorModel() {
        leftArmCubes = new ArrayList<>();
        headCubes = new ArrayList<>();
        bodyCubes = new ArrayList<>();
        rightArmCubes = new ArrayList<>();
        leftLegCubes = new ArrayList<>();
        rightLegCubes = new ArrayList<>();
        leftBootsCubes = new ArrayList<>();
        rightBootsCubes = new ArrayList<>();
    }

    public void addHeadCubes(CubeData[] head) {
        if (head != null && head.length > 0)
            headCubes = checkForRendering(head);
    }

    public void addBodyCubes(CubeData[] body) {
        if (body != null && body.length > 0)
            bodyCubes = checkForRendering(body);
    }

    public void addLeftArmCubes(CubeData[] leftArm) {
        if (leftArm != null && leftArm.length > 0)
            leftArmCubes = checkForRendering(leftArm);
    }

    public void addRightArmCubes(CubeData[] rightArm) {
        if (rightArm != null && rightArm.length > 0)
            rightArmCubes = checkForRendering(rightArm);
    }

    public void addLeftLegCubes(CubeData[] leftLeg) {
        if (leftLeg != null && leftLeg.length > 0)
            leftLegCubes = checkForRendering(leftLeg);
    }

    public void addRightLegCubes(CubeData[] rightLeg) {
        if (rightLeg != null && rightLeg.length > 0)
            rightLegCubes = checkForRendering(rightLeg);
    }

    public void addLeftBootCubes(CubeData[] leftBoot) {
        if (leftBoot != null && leftBoot.length > 0)
            leftBootsCubes = checkForRendering(leftBoot);
    }

    public void addRightBootCubes(CubeData[] rightBoot) {
        if (rightBoot != null && rightBoot.length > 0)
            rightBootsCubes = checkForRendering(rightBoot);
    }

    private ArrayList<CubeData> checkForRendering(CubeData[] data) {
        ArrayList<CubeData> output = new ArrayList<>();
        Collections.addAll(output, data);
        return output;
    }

    public ArmorModel handleData(ModelBiped base) {
        if (headCubes != null && headCubes.size() > 0)
            for (CubeData temp : headCubes)
                bipedHead.addChild(BitHelper.createModelRenderer(base, DataHelper.addOffset(temp, 0, 0, 0)));
        if (bodyCubes != null && bodyCubes.size() > 0)
            for (CubeData temp : bodyCubes)
                base.bipedBody.addChild(BitHelper.createModelRenderer(base, DataHelper.addOffset(temp, 0, 0, 0)));
        if (leftArmCubes != null && leftArmCubes.size() > 0)
            for (CubeData temp : leftArmCubes)
                base.bipedLeftArm.addChild(BitHelper.createModelRenderer(base, DataHelper.addOffset(temp, 0, 0, 0)));
        if (rightArmCubes != null && rightArmCubes.size() > 0)
            for (CubeData temp : rightArmCubes)
                base.bipedRightArm.addChild(BitHelper.createModelRenderer(base, DataHelper.addOffset(temp, 0, 0, 0)));
        if (leftLegCubes != null && leftLegCubes.size() > 0)
            for (CubeData temp : leftLegCubes)
                base.bipedLeftLeg.addChild(BitHelper.createModelRenderer(base, DataHelper.addOffset(temp, 0, 0, 0)));
        if (rightLegCubes != null && rightLegCubes.size() > 0)
            for (CubeData temp : rightLegCubes)
                base.bipedRightLeg.addChild(BitHelper.createModelRenderer(base, DataHelper.addOffset(temp, 0, 0, 0)));
        if (leftBootsCubes != null && leftBootsCubes.size() > 0)
            for (CubeData temp : leftBootsCubes)
                base.bipedLeftLeg.addChild(BitHelper.createModelRenderer(base, DataHelper.addOffset(temp, 0, 12, 0)));
        if (rightBootsCubes != null)
            for (CubeData temp : rightBootsCubes)
                bipedRightLeg.addChild(BitHelper.createModelRenderer(base, DataHelper.addOffset(temp, 0, 12, 0)));
        return this;
    }
}