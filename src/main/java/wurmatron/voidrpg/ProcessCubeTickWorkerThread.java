package wurmatron.voidrpg;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.utils.ArmorHelper2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static wurmatron.voidrpg.ProcessCubeTickSupervisorThread.Range;

/**
 * Created by matthew on 19/11/16.
 */
public class ProcessCubeTickWorkerThread implements Runnable {

    protected static final LinkedHashMap<ProcessCubeTickSupervisorThread, ProcessCubeTickWorkerThread> workers =
            new LinkedHashMap<>();

    protected static final LinkedHashMap<ProcessCubeTickWorkerThread, Range<Integer>> rangedWorkers =
            new LinkedHashMap<>();

    private static final ArmorHelper2 ahelper = new ArmorHelper2();

    public final ProcessCubeTickSupervisorThread superThread;

    public final Range<Integer> threadDomain;

    private final ItemStack stack;

    private final EntityPlayer player;

    private boolean finished = false;

    private ArrayList<CubeData> cubesToProcess;

    private boolean kill = false;

    public ProcessCubeTickWorkerThread(ProcessCubeTickSupervisorThread superThread, Range<Integer> domain,
                                       ItemStack stack, EntityPlayer player) {
        workers.put(superThread, this);
        rangedWorkers.put(this, domain);
        this.superThread = superThread;
        this.threadDomain = domain;
        this.stack = stack;
        this.player = player;
    }

    public static final ProcessCubeTickWorkerThread getIfNotExists(ProcessCubeTickSupervisorThread superThread, Range<Integer> domain,
                                                                   ItemStack stack, EntityPlayer player) {
        for (ProcessCubeTickWorkerThread pctwt : rangedWorkers.keySet()) {
            if (Range.compareRanges(rangedWorkers.get(pctwt), domain)) {
                return pctwt;
            }
        }
        return new ProcessCubeTickWorkerThread(superThread, domain, stack, player);
    }

    public synchronized boolean kill() {
        if (cubesToProcess == null) {
            this.kill = true;
            return true;
        }
        return false;
    }

    protected synchronized boolean queueCubes() {
        if (cubesToProcess == null) {
            cubesToProcess = new ArrayList<CubeData>() {
                {
                    for (int i = threadDomain.lower - 1; i < superThread.cubes.size() && i < threadDomain.getUpperLimit(); i++) {
                        add(superThread.cubes.get(i));
                    }
                }
            };
            return true;
        } else {
            return false;
        }
    }

    protected synchronized boolean clearQueue() {
        if (finished)  {
            cubesToProcess = null;
            this.finished = false;
        }
        return false;
    }

    protected synchronized void calc() {
        cubesToProcess.forEach(c -> {
            if (ahelper.isActive(c.cube, stack) && c.cube.hasEffects(player, stack)) {
                c.cube.applyEffect(player, c, stack);
            }
        });
        this.finished = true;
    }

    protected synchronized boolean calcsFinished() {
        return this.finished;
    }

    @Override
    public void run() {
        while (!kill) {
            if (this.cubesToProcess == null) queueCubes();
            if (finished) {

                try {
                    wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

}
