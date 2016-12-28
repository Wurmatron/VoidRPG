package wurmatron.voidrpg.common.cube;

import wurmatron.voidrpg.api.cube.ICube;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CubeRegistry {

    private static ICube[] cubes = new ICube[256];

    public static List<ICube> getCubes() {
        return Collections.unmodifiableList(Arrays.asList(cubes));
    }

    public static void registerCube(ICube cube) {
        if (!getCubes().contains(cube))
            for (int index = 0; index < cubes.length; index++)
                if (cubes[index] == null)
                    cubes[index] = cube;
    }

    public static ICube getCubeFromName(String name) {
        for (ICube cube : cubes)
            if (cube != null && cube.getName().equalsIgnoreCase(name))
                return cube;
        return null;
    }

    public static ICube getCubeFromID(int ID) {
        if (cubes.length >= ID && cubes[ID] != null)
            return cubes[ID];
        return null;
    }
}
