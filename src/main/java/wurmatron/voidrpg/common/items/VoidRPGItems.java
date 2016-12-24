package wurmatron.voidrpg.common.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wurmatron.voidrpg.client.proxy.ClientProxy;

public class VoidRPGItems {

    public static Item itemStaff;
    public static Item itemMaterial;
    public static Item itemUpgrade;
    public static Item goggles;
    public static final String[] materials = new String[]{"creationCrystal", "upgrade", "basicArmorPlate", "heavyArmorPlate", "reactiveArmorPlating", "regenerativeArmorPlating", "energyArmorPlating", "cardboard", "carbonChunk", "flippers", "nanoTech", "mechanicalMuscle", "gravityCore", "jetpackParts", "thruster", "waterElectrolysisModule", "mindControl", "wings", "battery", "solarPanel", "smallReactor", "largeReactor", "repairBot"};
    public static final String[] upgrades = new String[]{"speedI", "speedII", "speedIII"};

    public static void init() {
        registerItem(goggles = new ItemGoggles());
        GameRegistry.registerItem(itemMaterial = new ItemMaterial(materials));
        GameRegistry.registerItem(itemUpgrade = new ItemUpgrade(upgrades));
    }

    private static void registerItem(Item item) {
        GameRegistry.registerItem(item, item.getUnlocalizedName());
        ClientProxy.items.add(item);
    }
}

