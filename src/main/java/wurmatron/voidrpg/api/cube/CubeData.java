package wurmatron.voidrpg.api.cube;

public class CubeData {

		public final int offX;
		public final int offY;
		public final int offZ;
		public final Cube cube;

		public CubeData (int offX, int offY, int offZ, Cube cube) {
				this.offX = offX;
				this.offY = offY;
				this.offZ = offZ;
				this.cube = cube;
		}
}
