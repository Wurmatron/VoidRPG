package wurmatron.voidrpg.common.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.cube.CubeJson;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.utils.LogHandler;

import java.io.*;
import java.util.ArrayList;

public class JsonHandler {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static final File dir = ConfigHandler.location;

    public static String convertCubeToJson(CubeJson cube) {
        if (cube != null && cube.getName() != null && cube.getName().length() > 0 && cube.getBlock() != null)
            return gson.toJson(cube);
        return null;
    }

    public static ICube convertJsonToCube(String json) {
        if (json != null && json.length() > 0)
            return gson.fromJson(json, CubeJson.class);
        return null;
    }

    public static void writeCubeToFile(CubeJson cube) {
        File location = new File(dir + File.separator + "Cubes" + File.separator + cube.getName() + ".json");
        try {
            if (!new File(dir + File.separator + "Cubes").exists())
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

    public static ICube loadCubeFromFile(File location) {
        ArrayList<String> lines = new ArrayList<>();
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

    public static void loadJsonCubes() {
        try {
            if (new File(JsonHandler.dir + File.separator + "Cubes" + File.separator).listFiles().length > 0) {
                for (File json : new File(JsonHandler.dir + File.separator + "Cubes" + File.separator).listFiles()) {
                    if (json != null && json.isFile()) {
                        ICube temp = JsonHandler.loadCubeFromFile(json);
                        if (!CubeRegistry.getCubes().contains(temp)) {
                            CubeRegistry.registerCube(temp);
                            LogHandler.info("Loaded cube '" + temp.getName() + "' with id: " + CubeRegistry.getIDForCube(temp) + " from json");
                        }
                    }
                }
            } else
                LogHandler.info("Unable to load and cubes");
        } catch (NullPointerException e) {
            LogHandler.debug(e.getLocalizedMessage());
        }
    }
}
