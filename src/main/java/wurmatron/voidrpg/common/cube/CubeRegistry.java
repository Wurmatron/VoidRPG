package wurmatron.voidrpg.common.cube;

import wurmatron.voidrpg.api.cube.ICube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CubeRegistry {


	public static CubeRegistry INSTANCE = new CubeRegistry();

	public static Set <ICube> cubes = new HashSet <>();
	public static HashMap <String, ICube> cacheNames = new HashMap <>();

	public ArrayList <ICube> getCubes () {
		return new ArrayList <>(cubes);
	}

	public <T extends ICube> void registerCube (T cube) {
		if (! cubes.contains(cube)) {
			cubes.add(cube);
		}
	}

	public ICube getCubesFromName (String name) {
		if (cacheNames.containsKey(name)) {
			return cacheNames.get(name);
		} else {
			for (ICube c : cubes) {
				if (c.getUnlocalizedName().equalsIgnoreCase(name)) {
					cacheNames.put(name, c);
					return c;
				}
			}
		}
		return null;
	}
}
