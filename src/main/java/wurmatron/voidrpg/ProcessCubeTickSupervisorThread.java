package wurmatron.voidrpg;

import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.api.cube.CubeData;

import java.util.LinkedList;

/**
 * Created by matthew on 19/11/16.
 */
public class ProcessCubeTickSupervisorThread implements Runnable {

    private static final LinkedList<ProcessCubeTickSupervisorThread> threads = new LinkedList<ProcessCubeTickSupervisorThread>() {
        @Override
        public boolean add(ProcessCubeTickSupervisorThread thread) {

            return false;
        }
    };

    private final ProcessCubeTickWorkerThread workers;

    public final String armourType;

    public final CubeData[] cubes;

    private ProcessCubeTickSupervisorThread(Thread originalThread, String stackType, ItemStack stack) {
        this.armourType = stackType;

    }

    public static ProcessCubeTickSupervisorThread getThreadIfNotExists(Thread original, ItemStack stack) {
        String armour = stack.getItem().getUnlocalizedName().substring(11);
        for (ProcessCubeTickSupervisorThread t : ProcessCubeTickSupervisorThread.threads) {
            if (t.armourType == armour) {
                return t;
            }
        }
        return new ProcessCubeTickSupervisorThread(original, armour, stack);
    }

    public synchronized void applyEffectsIfExists() {

    }

    @Override
    public void run() {

    }
}
