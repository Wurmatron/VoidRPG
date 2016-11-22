package wurmatron.voidrpg.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.config.JsonHandler;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.cube.StringCube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class HashManager {

	public static ArrayList <String> hashes = new ArrayList <>();
	public static HashMap <String, String> hashValues = new HashMap <>();

	public static void init () {
		for (ICube cube : CubeRegistry.INSTANCE.getCubes()) {
			if (cube.getClass().equals(StringCube.class)) {
				String cubeJson = JsonHandler.convertCubeToJson((StringCube) cube);
				String hash = new HashManager().createMD5(cubeJson);
				hashes.add(hash);
				hashValues.put(hash, cubeJson);
				LogHandler.info("Hash: " + hash);
			}
		}
	}

	public String createMD5 (String key) {
		return DigestUtils.md5Hex(key);
	}

	public boolean compareHash (String key1, String key2) {
		return Objects.deepEquals(createMD5(key1), createMD5(key2));
	}

	public static void reload () {
		hashes.clear();
		hashValues.clear();
		init();
	}
}
