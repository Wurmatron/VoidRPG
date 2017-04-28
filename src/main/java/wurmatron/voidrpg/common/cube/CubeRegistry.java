package wurmatron.voidrpg.common.cube;

import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.blocks.VoidRPGBlocks;
import wurmatron.voidrpg.common.cube.regular.*;
import wurmatron.voidrpg.common.cube.special.DamageConverter;
import wurmatron.voidrpg.common.cube.special.LifeSteal;
import wurmatron.voidrpg.common.cube.special.MobStealth;
import wurmatron.voidrpg.common.cube.special.chest.Gravity;
import wurmatron.voidrpg.common.cube.special.feet.FallReduction;
import wurmatron.voidrpg.common.cube.special.feet.Flippers;
import wurmatron.voidrpg.common.cube.special.feet.WaterWalking;
import wurmatron.voidrpg.common.cube.special.head.NightVision;
import wurmatron.voidrpg.common.cube.special.head.WaterBreathing;
import wurmatron.voidrpg.common.cube.special.legs.CubeMuscle;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.Local;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CubeRegistry {

		private static ICube[]              cubes   = new ICube[256];
		private static HashMap<ICube, Byte> idCache = new HashMap<>();

		public static List<ICube> getCubes() {
				return Collections.unmodifiableList(Arrays.asList(cubes));
		}

		public static void registerCube(ICube cube) {
				if (!getCubes().contains(cube)) for (byte index = 0; index < cubes.length; index++)
						if (cubes[index] == null) {
								cubes[index] = cube; idCache.put(cube, index); return;
						}
		}

		public static ICube getCubeFromName(String name) {
				for (ICube cube : cubes)
						if (cube != null && cube.getName().equalsIgnoreCase(name)) return cube; return null;
		}

		public static ICube getCubeFromID(int ID) {
				if (cubes.length >= ID && cubes[ID] != null) return cubes[ID]; return null;
		}

		public static byte getIDForCube(ICube cube) {
				if (cube != null && idCache.containsKey(cube)) return idCache.get(cube); return -1;
		}

		public static void addDefaultCubes() {
				CubeRegistry.registerCube(new LightArmor()); CubeRegistry.registerCube(new HeavyArmor());
				CubeRegistry.registerCube(new ReinforcedArmor()); CubeRegistry.registerCube(new CarbonArmor());
				CubeRegistry.registerCube(new CardboardArmor()); CubeRegistry.registerCube(new WoodCube());
				CubeRegistry.registerCube(new WaterWalking()); CubeRegistry.registerCube(new NightVision());
				CubeRegistry.registerCube(new CardboardArmor()); CubeRegistry.registerCube(new CubeMuscle());
				CubeRegistry.registerCube(new WaterBreathing()); CubeRegistry.registerCube(new LifeSteal());
				CubeRegistry.registerCube(new MobStealth()); CubeRegistry.registerCube(new FallReduction());
				CubeRegistry.registerCube(new Gravity()); CubeRegistry.registerCube(new DamageConverter());
				CubeRegistry.registerCube(new Flippers());
				CubeRegistry.registerCube(new BasicCube("decoWhite", VoidRPGBlocks.decoWoolWhile, new ResourceLocation(Global.MODID, "textures/cube/decoWhite.png"), 5.0, 500, 1, 4096, Local.CUBED_WHITE, 5));
				CubeRegistry.registerCube(new BasicCube("decoOrange", VoidRPGBlocks.decoWoolOrange, new ResourceLocation(Global.MODID, "textures/cube/decoOrange.png"), 5.0, 500, 1, 4096, Local.CUBED_ORANGE, 5));
				CubeRegistry.registerCube(new BasicCube("decoMagenta", VoidRPGBlocks.decoWoolMagenta, new ResourceLocation(Global.MODID, "textures/cube/decoMagenta.png"), 5.0, 500, 1, 4096, Local.CUBED_MEGENTA, 5));
				CubeRegistry.registerCube(new BasicCube("decoLightBlue", VoidRPGBlocks.decoWoolLightBlue, new ResourceLocation(Global.MODID, "textures/cube/decoLightBlue.png"), 5.0, 500, 1, 4096, Local.CUBED_LIGHTBLUE, 5));
				CubeRegistry.registerCube(new BasicCube("decoYellow", VoidRPGBlocks.decoWoolYellow, new ResourceLocation(Global.MODID, "textures/cube/decoYellow.png"), 5.0, 500, 1, 4096, Local.CUBED_YELLOW, 5));
				CubeRegistry.registerCube(new BasicCube("decoLime", VoidRPGBlocks.decoWoolLime, new ResourceLocation(Global.MODID, "textures/cube/decoLime.png"), 5.0, 500, 1, 4096, Local.CUBED_LIME, 5));
				CubeRegistry.registerCube(new BasicCube("decoPink", VoidRPGBlocks.decoWoolPink, new ResourceLocation(Global.MODID, "textures/cube/decoPink.png"), 5.0, 500, 1, 4096, Local.CUBED_PINK, 5));
				CubeRegistry.registerCube(new BasicCube("decoGray", VoidRPGBlocks.decoWoolGray, new ResourceLocation(Global.MODID, "textures/cube/decoGray.png"), 5.0, 500, 1, 4096, Local.CUBED_GRAY, 5));
				CubeRegistry.registerCube(new BasicCube("decoLightGray", VoidRPGBlocks.decoWoolLightGray, new ResourceLocation(Global.MODID, "textures/cube/decoLightGray.png"), 5.0, 500, 1, 4096, Local.CUBED_LIGHTGRAY, 5));
				CubeRegistry.registerCube(new BasicCube("decoCyan", VoidRPGBlocks.decoWoolCyan, new ResourceLocation(Global.MODID, "textures/cube/decoCyan.png"), 5.0, 500, 1, 4096, Local.CUBED_CYAN, 5));
				CubeRegistry.registerCube(new BasicCube("decoPurple", VoidRPGBlocks.decoWoolPurple, new ResourceLocation(Global.MODID, "textures/cube/decoPurple.png"), 5.0, 500, 1, 4096, Local.CUBED_PURPLE, 5));
				CubeRegistry.registerCube(new BasicCube("decoBlue", VoidRPGBlocks.decoWoolBlue, new ResourceLocation(Global.MODID, "textures/cube/decoBlue.png"), 5.0, 500, 1, 4096, Local.CUBED_BLUE, 5));
				CubeRegistry.registerCube(new BasicCube("decoBrown", VoidRPGBlocks.decoWoolBrown, new ResourceLocation(Global.MODID, "textures/cube/decoBrown.png"), 5.0, 500, 1, 4096, Local.CUBED_BROWN, 5));
				CubeRegistry.registerCube(new BasicCube("decoGreen", VoidRPGBlocks.decoWoolGreen, new ResourceLocation(Global.MODID, "textures/cube/decoGreen.png"), 5.0, 500, 1, 4096, Local.CUBED_GREEN, 5));
				CubeRegistry.registerCube(new BasicCube("decoRed", VoidRPGBlocks.decoWoolRed, new ResourceLocation(Global.MODID, "textures/cube/decoRed.png"), 5.0, 500, 1, 4096, Local.CUBED_RED, 5));
				CubeRegistry.registerCube(new BasicCube("decoBlack", VoidRPGBlocks.decoWoolBlack, new ResourceLocation(Global.MODID, "textures/cube/decoBlack.png"), 5.0, 500, 1, 4096, Local.CUBED_BLACK, 5));
				CubeRegistry.registerCube(new BasicCube("decoDiamond", VoidRPGBlocks.decoDiamond, new ResourceLocation(Global.MODID, "textures/cube/decoDiamond.png"), 5.0, 2000, 1, 4096, Local.CUBED_DIAMOND, 20));
				CubeRegistry.registerCube(new BasicCube("decoEmerald", VoidRPGBlocks.decoEmerald, new ResourceLocation(Global.MODID, "textures/cube/decoEmerald.png"), 5.0, 4000, 1, 4096, Local.CUBED_EMERALD, 20));
		}
}
