package wurmatron.voidrpg.api.cube;


import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Cube extends Item {

		public String getName () {
				return "";
		}
		public float getWeight () {
				return -1;
		}
		public ResourceLocation getTexture () {
				return new ResourceLocation("voidrpg", "textures/cubes/notexture.png");
		}
		public int[] getSize () {
				return new int[] {1, 1, 1};
		}
}
