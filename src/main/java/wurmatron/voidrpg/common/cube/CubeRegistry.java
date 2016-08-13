package wurmatron.voidrpg.common.cube;

import wurmatron.voidrpg.api.cube.ICube;

import java.util.ArrayList;
import java.util.HashMap;

public class CubeRegistry {

		public static ArrayList<ICube> cubes = new ArrayList<ICube>();
		public static HashMap<String, ICube> cacheNames = new HashMap<String, ICube>();

		public static final String DEFENSE = "Defense";
		public static final String DECO = "Deco";

		public static CubeRegistry INSTANCE = new CubeRegistry();

		public ArrayList<ICube> getCubes () {
				return cubes;
		}

		public void registerCube (ICube cube) {
				if (!cubes.contains(cube)) {
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
