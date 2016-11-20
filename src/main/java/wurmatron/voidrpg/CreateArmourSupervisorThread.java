package wurmatron.voidrpg;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.utils.LogHandler;
import wurmatron.voidrpg.common.utils.StackHelper;

import java.util.*;

import static wurmatron.voidrpg.common.utils.ArmorHelper2.armourInstance;

/**
 * Created by matthew on 20/11/16.
 */
public class CreateArmourSupervisorThread extends Thread {

    private static final LinkedHashMap<EntityPlayer, CreateArmourSupervisorThread> supervisorThreads = new LinkedHashMap<>();

    private final HashMap<CreateArmourWorker, Thread> workers = new HashMap<CreateArmourWorker, Thread>();

    private final Thread superThread;

    private final CreateArmourSupervisorThread supervisor;

    private final EntityPlayer player;

    /**Timeout int ticks*/
    public static final int timeout = 10;

    private double timeSinceLastDamaged = 0;

    protected final class Stack {

        public final ItemStack stack;

        public final List<CubeData> cubesToRemove = new LinkedList<CubeData>() {
            @Override
            public void clear() {
                super.clear();
                toRemove.remove(stack);
            }
        };

        public Stack(ItemStack stack, List<CubeData> data) {
            this.stack = stack;
            data.forEach(cube -> data.add(cube));
        }

        public void addCube(CubeData cube) {
            this.cubesToRemove.add(cube);
        }

        protected void dequeqeCube(CubeData cube) {
            this.cubesToRemove.remove(cube);
        }

        public String getItemStackType() {
            return stack.getItem().getUnlocalizedName().substring(11);
        }
    }

//    private final LinkedHashMap<ItemStack, List<CubeData>> toRemove = new LinkedHashMap<ItemStack, List<CubeData>>() {};

    private final LinkedList<Stack> toRemove = new LinkedList<Stack>();

    private CreateArmourSupervisorThread(Thread currentThread, EntityPlayer player) {
        CreateArmourSupervisorThread.supervisorThreads.put(player, this);
        this.supervisor = this;
        this.superThread = currentThread;
        this.player = player;
    }

    public static final CreateArmourSupervisorThread getIfNotExists(Thread currentThread, EntityPlayer player) {
        if (supervisorThreads.get(player) == null) {
            return new CreateArmourSupervisorThread(currentThread, player);
        } else {
            return CreateArmourSupervisorThread.supervisorThreads.get(player);
        }
    }

    private void dispatchWorkers() {
        for (int i = 0; i < toRemove.size(); i++) {
            CreateArmourWorker armourworker = new CreateArmourWorker(this, player, toRemove.get(i));
            Thread worker = new Thread(armourworker);
            this.workers.put(armourworker, worker);
            worker.start();
        }
    }

    private final Stack stackListContains(ItemStack stack) {
        for (Stack s : toRemove) {
            if (StackHelper.areArmorEqual(s.stack, stack)) return s;
        }
        return null;
    }

    public synchronized void queueOperation(ItemStack stack, CubeData... data) {
        this.timeSinceLastDamaged = System.currentTimeMillis();
        Stack s = stackListContains(stack);
        if (s != null) {
            toRemove.add(new Stack(stack, new LinkedList<CubeData>() {{
                for (CubeData brokenCube : data) {
                    add(brokenCube);
                }
            }}));
        } else {
//            s.addCube(data);
            for (CubeData brokenCube : data) {
                s.addCube(brokenCube);
            }
        }
        dispatchWorkers();
    }

    private final LinkedList<ItemStack> returnQueue = new LinkedList<ItemStack>();

    protected synchronized void queueReturn(ItemStack stack) {
        returnQueue.add(stack);
    }

    public final synchronized CubeData[] getCubesToRemove(ItemStack stack) {
        GenericWrapper<CubeData[]> toReturn = new GenericWrapper<CubeData[]>(null, true);
        workers.forEach((worker, thread) -> {
            if (worker.getOriginal() == stack) {
                toReturn.setObj(worker.cubeStack.cubesToRemove.toArray(new CubeData[0]));
            }
        });
        return toReturn.getObj();
    }

    public final synchronized ItemStack getQueuedReturn(ItemStack stack, CubeData... removedItems) {
        final GenericWrapper<Boolean> stupidLambda = new GenericWrapper<>(false, true);
        List<CubeData> newData = Arrays.<CubeData>asList(armourInstance.getCubeData(stack));
        List<CubeData> removedCubes = Arrays.<CubeData>asList(removedItems);
        newData.forEach(cube -> removedCubes.forEach(removed -> stupidLambda.setObj(cube==removed)));
        if (stupidLambda.getObj()) {
            return stack;
        }
        return null;
    }

    @Override
    public void run() {
        while (true) {
            if (toRemove.size() <= 0 && Math.round(System.currentTimeMillis() - timeSinceLastDamaged) >= 10000) {
                for (CreateArmourWorker worker : workers.keySet()) {
                    synchronized (worker) {
                        while (!worker.kill()) {
                            LogHandler.error("One of the worker threads is still processing!!");
                        }
                    }
                }
                synchronized (this) {
                    this.interrupt();
                }
            } else {
                synchronized (superThread) {
                    if (player.worldObj.getWorldTime() % 10 == 0) {
                        workers.forEach((w, t) -> {
                            w.calc();
                        });
                    }
                }
            }
        }
    }

}
