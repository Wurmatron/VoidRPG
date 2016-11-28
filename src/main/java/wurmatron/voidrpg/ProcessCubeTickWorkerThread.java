package wurmatron.voidrpg;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.utils.ArmorHelper2;
import wurmatron.voidrpg.common.utils.Arrays;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by matthew on 21/11/16.
 */
public final class ProcessCubeTickWorkerThread extends Thread {

    public static final LinkedHashMap<ItemStack, List<ProcessCubeTickWorkerThread>> pctwThreads = new LinkedHashMap<>();

    public final ProcessCubeTickSupervisorThread.Range<Integer> range = ProcessCubeTickSupervisorThread.workerThreadThreshhold;

    protected final ItemStack stackApplicant;

    protected final EntityPlayer playerApplicant;

    protected final CreateArmourSupervisorThread createArmourSupervisorThread;

    protected final Thread initializerThread;

    private final ArmorHelper2 armorHelper2 = new ArmorHelper2();

    private final ArrayList<CubeData> cubesToProcess = new ArrayList<>();

    private final ArrayList<CubeData> processedCubes = new ArrayList<>();

    public ProcessCubeTickWorkerThread(CreateArmourSupervisorThread createArmourSupervisorThread, Thread initializerThread, ItemStack stackApplicant, EntityPlayer playerApplicant) {
        this.createArmourSupervisorThread = createArmourSupervisorThread;
        this.initializerThread = initializerThread;
        this.stackApplicant = stackApplicant;
        this.playerApplicant = playerApplicant;

        /**Adding the worker thread to the global map*/
        List<ProcessCubeTickWorkerThread> inMap = pctwThreads.get(stackApplicant);
        final ProcessCubeTickWorkerThread selfRefrence = this;
        if (Objects.nonNull(inMap)) {
            inMap.add(this);
        } else {
            pctwThreads.put(stackApplicant, new ArrayList<ProcessCubeTickWorkerThread>() {{ add(selfRefrence); }});
        }
    }

    /**
     *
     * @return Will return the next ProcessCubeTickWorkerThread thread with empty spaces in the process queue
     */
    public static ProcessCubeTickWorkerThread getIfNotExists(CreateArmourSupervisorThread createArmourSupervisorThread, Thread initializerThread, ItemStack stack, EntityPlayer player) {
        List<ProcessCubeTickWorkerThread> returned = pctwThreads.get(stack);
        if (!(returned.size() == 0)) {
            for (ProcessCubeTickWorkerThread worker : returned) {
                if (ProcessCubeTickSupervisorThread.workerThreadThreshhold.upperLowerDifference() > worker.occupiedRange().upperLowerDifference()) {
                    return worker;
                } else {
                    break;
                }
            }
        }
        return new ProcessCubeTickWorkerThread(createArmourSupervisorThread, initializerThread, stack, player);
    }

    public synchronized ProcessCubeTickSupervisorThread.Range<Integer> occupiedRange() {
        return new ProcessCubeTickSupervisorThread.Range<Integer>(0, Math.abs(cubesToProcess.size()-processedCubes.size()));
    }

    public synchronized ProcessCubeTickSupervisorThread.Range<Integer> unoccupiedRange() {
        return ProcessCubeTickSupervisorThread.Range.getDifference(occupiedRange(), ProcessCubeTickSupervisorThread.workerThreadThreshhold);
    }

    public synchronized CubeData[] queueCubes(CubeData... cubeApplicants) {
        timeSinceLastExecFinish = -1;
        final int initialUnoccupied = unoccupiedRange().upperLowerDifference()+1;
        for (int i = 0; i < initialUnoccupied; i++) {
            this.cubesToProcess.add(cubeApplicants[i]);
        }
        return Arrays.returnPastIndex(cubeApplicants, initialUnoccupied);
    }

    private double timeSinceLastExecFinish = 0;

    protected synchronized void exec() {
        cubesToProcess.forEach(cube -> {
//            if () {
                synchronized (initializerThread) {
                    if (armorHelper2.isActive(cube.cube, stackApplicant) && cube.cube.hasEffects(playerApplicant, stackApplicant)) {
                        cube.cube.applyEffect(playerApplicant, cube, stackApplicant);
                    }
                }
                processedCubes.add(cube);
				armorHelper2.checkAndHandleBrokenCube(createArmourSupervisorThread, playerApplicant, stackApplicant, cube);
//            }
        });
        cubesToProcess.removeAll(processedCubes);
        timeSinceLastExecFinish = System.currentTimeMillis();
    }

//    private boolean run = true;

    protected synchronized boolean kill() {
        if (cubesToProcess.size() == 0) {
//            run = false;
            synchronized (this) {
                interrupt();
            }
            ProcessCubeTickWorkerThread.pctwThreads.get(stackApplicant).remove(this);
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            if (cubesToProcess.size() == 0) {
                if ((timeSinceLastExecFinish - System.currentTimeMillis()) > (Settings.workerThreadTimeout * 1000)) {
                    if (kill()) {
                        break;
                    }
                } else {
                    synchronized (this) {
                        try {
                            wait();
                        } catch (InterruptedException e) {}
                    }
                }
            } else {
                exec();
            }
        }
    }

}
