//package wurmatron.voidrpg;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import wurmatron.voidrpg.api.cube.CubeData;
//import wurmatron.voidrpg.common.config.Settings;
//import wurmatron.voidrpg.common.utils.ArmorHelper2;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * Created by matthew on 19/11/16.
// */
//public final class ProcessCubeTickSupervisorThread extends Thread {
//
//    public static final class Range<T extends Number> {
//
//        public final T lower;
//
//        public final T upper;
//
//        public Range(T lower, T upper) {
//            this.lower = lower;
//            this.upper = upper;
//        }
//
//        public T getLowerLimit() {
//            return this.lower;
//        }
//
//        public T getUpperLimit() {
//            return this.upper;
//        }
//
//        @Override
//        public String toString() {
//            return (this.lower + " : " + this.upper);
//        }
//
//        public boolean isWithinRange(T arbitraryNumber) {
//            return Range.<T>isWithinRange(this.lower, this.upper, arbitraryNumber);
//        }
//
//        public static <T extends Number> boolean isWithinRange(T lowerLimit, T upperLimit, T arbitraryNumber) {
//            return (arbitraryNumber.doubleValue() >= lowerLimit.doubleValue() &&
//                    arbitraryNumber.doubleValue() <= upperLimit.doubleValue());
//        }
//
//        public static <T extends Number> boolean compareRanges(Range<T> first, Range<T> second) {
//            if (first.getUpperLimit() == second.getUpperLimit() && first.getLowerLimit() == second.getLowerLimit()) {
//                return true;
//            }
//            return false;
//        }
//    }
//
//    public static final ArmorHelper2 armorHelper = new ArmorHelper2();
//
//    public static final int thresholdIncrementalFactor = 256;
//
//    public static final LinkedHashMap<Integer, Range<Integer>> workerThreadThreshold = new LinkedHashMap<Integer, Range<Integer>>(){
//        {
//            for (int i = thresholdIncrementalFactor; i <= 4096; i++) {
//                int fac = i / thresholdIncrementalFactor;
//                double mod = i%Double.parseDouble(new Integer(thresholdIncrementalFactor).toString());
//                if (mod == 0d) {
//                    put(fac, new Range<Integer>(i-255, i));
//                }
//            }
//            forEach((x, y) -> System.out.println(x + " -> " + y));
//        }
//    };
//
//    private static final LinkedHashMap<EntityPlayer, ProcessCubeTickSupervisorThread> supervisorThreads =
//            new LinkedHashMap<>();
//
//    private final Thread currentThread;
//
//    private final EntityPlayer player;
//
//    private final ItemStack stack;
//
//    protected volatile ArrayList<CubeData> cubes = new ArrayList<>();
//
//    public ProcessCubeTickSupervisorThread(Thread currentThread, EntityPlayer player, ItemStack stack) {
//        this.currentThread = currentThread;
//        this.player = player;
//        this.stack = stack;
//    }
//
//    public static final ProcessCubeTickSupervisorThread getIfNotExists(Thread currentThread, EntityPlayer player,
//                                                                       ItemStack stack) {
//        if (supervisorThreads.get(player) == null) {
//            ProcessCubeTickSupervisorThread pctst = new ProcessCubeTickSupervisorThread(Thread.currentThread(), player, stack);
//            supervisorThreads.put(player, pctst);
//            return pctst;
//        } else {
//            return supervisorThreads.get(player);
//        }
//    }
//
//    public static final boolean cubesBelongToStack(ItemStack stack, CubeData... cubes) {
//        List<CubeData> data = java.util.Arrays.<CubeData>asList(armorHelper.getCubeData(stack));
//        for (CubeData cube : cubes) {
//            if (data.contains(cube)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private double timeSinceLastCall = 0;
//
//    public synchronized void queueApplyEffectOperation(ItemStack stack, CubeData... cube) {
//        this.timeSinceLastCall = System.currentTimeMillis();
//        dispatchThreads(stack, cube);
//    }
//
//    private final void dispatchThreads(ItemStack stack, CubeData... cubes) {
//        new Thread("Process Cube Tick - Worker Thread Dispatcher") {
//
//            private ItemStack stack;
//
//            private ArrayList<CubeData> cubesToProcess;
//
//            private ProcessCubeTickSupervisorThread superThread;
//
////            LinkedHashMap<Integer, CubeData> indicies;
//
//            LinkedList<ProcessCubeTickWorkerThread> workersToStart;
//
//            public final Thread initializeThread(ProcessCubeTickSupervisorThread superThread, ItemStack stack, CubeData... cubesToProcess) {
//                this.stack = stack;
//                this.cubesToProcess = new ArrayList<CubeData>(java.util.Arrays.<CubeData>asList(cubesToProcess));
//                this.superThread = superThread;
//                return this;
//            }
//
//            @Override
//            public void run() {
//                workersToStart = new LinkedList<ProcessCubeTickWorkerThread>() {
//                    {
//                        workerThreadThreshold.forEach((i, r) -> {
//                            if (cubesToProcess.size() < r.getUpperLimit()) {
//                                for (int threadNum = 0; threadNum <= i; threadNum++) {
//                                    Range<Integer> rangeAtI = workerThreadThreshold.get(i);
//                                    if (threadNum == i) {
//                                        workersToStart.add(new ProcessCubeTickWorkerThread(superThread,
//                                                new Range<Integer>(rangeAtI.getLowerLimit(),
//                                                        (rangeAtI.getUpperLimit() - cubesToProcess.size()))));
//                                    } else {
//                                        workersToStart.add(new ProcessCubeTickWorkerThread(superThread, rangeAtI));
//                                    }
//                                }
//                            }
//                        });
//                    }
//                };
//                synchronized (this) {
//                    interrupt();
//                }
//            }
//        }.initializeThread(this, stack, cubes).start();
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            if (System.currentTimeMillis() - timeSinceLastCall >= 10* Settings.supervisorThreadTimeout) {
//                synchronized (this) {
//                    interrupt();
//                    break;
//                }
//            } else {
//
//            }
//        }
//    }
//}
