package wurmatron.voidrpg.common.cube;

import wurmatron.voidrpg.api.cube.Cube;

import java.util.ArrayList;
import java.util.HashMap;

public class CubeRegistry {

		public static ArrayList<Cube> cubes = new ArrayList<Cube>();
		public static HashMap<String, Cube> cacheNames = new HashMap<String, Cube>();

		public static final String DEFENSE = "Defense";
		public static final String DECO = "Deco";

		public static CubeRegistry INSTANCE = new CubeRegistry();

		public ArrayList<Cube> getCubes () {
				return cubes;
		}

		public void registerCube (Cube cube) {
				if (!cubes.contains(cube)) {
						cubes.add(cube);
				}
		}

		public Cube getCubesFromName (String name) {
				if (cacheNames.containsKey(name)) {
						return cacheNames.get(name);
				} else {
						for (Cube c : cubes) {
								if (c.getUnlocalizedName().equalsIgnoreCase(name)) {
										cacheNames.put(name, c);
										return c;
								}
						}
				}
				return null;
		}
}
