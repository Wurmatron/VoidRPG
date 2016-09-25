package wurmatron.voidrpg.common.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.cube.StringCube;

import java.io.*;
import java.util.ArrayList;

public class JsonHandler {

		private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		public static final File dir = new File(ConfigHandler.location + File.separator + "Cubes");

		public static String convertCubeToJson (StringCube cube) {
				if (cube != null)
						return gson.toJson(cube);
				return null;
		}

		public static ICube convertJsonToCube (String json) {
				if (json != null)
						return gson.fromJson(json, StringCube.class);
				return null;
		}

		public static void writeCubeToFile (StringCube cube) {
				File location = new File(dir + File.separator + cube.getUnlocalizedName() + ".json");
				try {
						if (!dir.exists())
								dir.mkdirs();
						if (!location.exists())
								location.createNewFile();
						BufferedWriter bw = new BufferedWriter(new FileWriter(location));
						bw.write(convertCubeToJson(cube));
						bw.close();
				} catch (IOException e) {
						e.printStackTrace();
				}
		}

		public static ICube loadCubeFromFile (File location) {
				ArrayList<String> lines = new ArrayList<String>();
				if (location.exists()) {
						try {
								BufferedReader reader = new BufferedReader(new FileReader(location));
								String line;
								while ((line = reader.readLine()) != null)
										lines.add(line);
								reader.close();
						} catch (FileNotFoundException e) {
								e.printStackTrace();
						} catch (IOException e) {
								e.printStackTrace();
						}
				}
				String temp = "";
				for (int s = 0; s <= lines.size() - 1; s++)
						temp = temp + lines.get(s);
				return convertJsonToCube(temp);
		}
}
