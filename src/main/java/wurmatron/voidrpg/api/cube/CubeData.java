package wurmatron.voidrpg.api.cube;

public class CubeData {

    public final ICube cube;
    public final int xPos;
    public final int yPos;
    public final int zPos;
    public final int damage;

    /**
     * Used to store cubes along with its data
     *
     * @param cube Cube
     * @param xPos X Location of the cube
     * @param yPos Y Location of the cube
     * @param zPos Z Location of the cube
     * @param damage How much damage this
     */
    public CubeData(ICube cube,int xPos, int yPos, int zPos, int damage) {
        this.cube = cube;
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        this.damage = damage;
    }
}
