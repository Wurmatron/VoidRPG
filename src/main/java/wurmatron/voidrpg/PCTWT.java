package wurmatron.voidrpg;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.api.cube.CubeData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by matthew on 21/11/16.
 */
public final class PCTWT implements Runnable {

    public static final LinkedHashMap<ItemStack, List<PCTWT>> pctwThreads = new LinkedHashMap<>();

    protected final ItemStack stackApplicant;

    protected final EntityPlayer playerApplicant;

    private final ArrayList<CubeData> cubesToProcess = new ArrayList<>();

    private final ArrayList<CubeData> processedCubes = new ArrayList<>();

    public PCTWT(ItemStack stackApplicant, EntityPlayer playerApplicant) {
        this.stackApplicant = stackApplicant;
        this.playerApplicant = playerApplicant;
    }

    /**
     *
     * @return Will return the next PCTWT thread with empty spaces in the process queue
     */
    public static PCTWT getIfNotExists(ItemStack stack) {

    }

    public synchronized void queueCubes(CubeData... cubeApplicants) {

    }
}
