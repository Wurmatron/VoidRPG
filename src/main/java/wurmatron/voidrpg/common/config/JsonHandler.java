package wurmatron.voidrpg.common.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.cube.StringCube;
import wurmatron.voidrpg.common.cube.StringCubeCreatorRecipe;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class JsonHandler {

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static final File dir = ConfigHandler.location;
	public static final File serverDir = new File(dir + File.separator + "Servers");

	public static String convertRecipeToJson (StringCubeCreatorRecipe recipe) {
		if (recipe != null)
			return gson.toJson(recipe);
		return null;
	}

	public static StringCubeCreatorRecipe convertJsonToRecipe (String json) {
		if (json != null)
			return gson.fromJson(json, StringCubeCreatorRecipe.class);
		return null;
	}

	public static String convertCubeToJson (StringCube cube) {
		if (cube != null)
			return gson.toJson(cube);
		return null;
	}

	public static StringCube convertJsonToCube (String json) {
		if (json != null)
			return gson.fromJson(json, StringCube.class);
		return null;
	}

	public static void writeCubeToFile (StringCube cube) {
		File location = new File(dir + File.separator + "Cubes" + File.separator + cube.getUnlocalizedName() + ".json");
		try {
			if (! new File(dir + File.separator + "Cubes").exists())
				dir.mkdirs();
			if (! location.exists())
				location.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(location));
			bw.write(convertCubeToJson(cube));
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ICube loadCubeFromFile (File location) {
		ArrayList <String> lines = new ArrayList <>();
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

	public static void writeRecipeToFile (StringCubeCreatorRecipe cube) {
		File location = new File(dir + File.separator + "Recipes" + File.separator + cube.getOutputCube().getUnlocalizedName() + ".json");
		try {
			if (! new File(dir + File.separator + "Recipes").exists())
				new File(dir + File.separator + "Recipes").mkdirs();
			if (! location.exists())
				location.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(location));
			bw.write(convertRecipeToJson(cube));
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static StringCubeCreatorRecipe loadRecipeFromFile (File location) {
		ArrayList <String> lines = new ArrayList <>();
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
		return convertJsonToRecipe(temp);
	}

	public static String compress (String str) {
		String out = "";
		try {
			if (str == null || str.isEmpty())
				return str;
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(outSteam);
			gzip.write(str.getBytes());
			gzip.close();
			out = outSteam.toString("ISO-8859-1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}

	public static String decompress (String str) {
		String out = "";
		try {
			if (str == null || str.isEmpty())
				return str;
			GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(str.getBytes("ISO-8859-1")));
			BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "ISO-8859-1"));
			String line;
			while ((line = bf.readLine()) != null)
				out += line;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}


	public static void writeCubeJsonFromServer (StringCube cube, String ip) {
		File location = new File(serverDir + File.separator + ip + File.separator + "Cubes" + File.separator + cube.getUnlocalizedName() + ".json");
		try {
			if (! new File(serverDir + File.separator + ip + File.separator + "Cubes").exists())
				dir.mkdirs();
			if (! location.exists())
				location.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(location));
			bw.write(convertCubeToJson(cube));
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeRecipeToFile (StringCubeCreatorRecipe cube, String ip) {
		File location = new File(serverDir + File.separator + ip + File.separator + "Recipes" + File.separator + cube.getOutputCube().getUnlocalizedName() + ".json");
		try {
			if (! new File(serverDir + File.separator + ip + File.separator + "Recipes").exists())
				new File(serverDir + File.separator + ip + File.separator + "Recipes").mkdirs();
			if (! location.exists())
				location.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(location));
			bw.write(convertRecipeToJson(cube));
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
