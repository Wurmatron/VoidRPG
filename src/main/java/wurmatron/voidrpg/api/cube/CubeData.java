package wurmatron.voidrpg.api.cube;

public class CubeData {

		public int offX;
		public int offY;
		public int offZ;
		public ICube cube;
		public int damage;

		public CubeData (int offX, int offY, int offZ, ICube cube, int damage) {
				this.offX = offX;
				this.offY = offY;
				this.offZ = offZ;
				this.cube = cube;
				this.damage = damage;
		}
}
