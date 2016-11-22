package wurmatron.voidrpg;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.utils.ArmorHelper2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by matthew on 21/11/16.
 */
public final class PCTST extends Thread {

    public static final class Range<T extends Number> {

        public final T lower;

        public final T upper;

        public Range(T lower, T upper) {
            this.lower = lower;
            this.upper = upper;
        }

        public T getLowerLimit() {
            return this.lower;
        }

        public T getUpperLimit() {
            return this.upper;
        }

        @Override
        public String toString() {
            return (this.lower + " : " + this.upper);
        }

        public boolean isWithinRange(T arbitraryNumber) {
            return ProcessCubeTickSupervisorThread.Range.<T>isWithinRange(this.lower, this.upper, arbitraryNumber);
        }

        public static <T extends Number> boolean isWithinRange(T lowerLimit, T upperLimit, T arbitraryNumber) {
            return (arbitraryNumber.doubleValue() >= lowerLimit.doubleValue() &&
                    arbitraryNumber.doubleValue() <= upperLimit.doubleValue());
        }

        public static <T extends Number> boolean compareRanges(ProcessCubeTickSupervisorThread.Range<T> first, ProcessCubeTickSupervisorThread.Range<T> second) {
            if (first.getUpperLimit() == second.getUpperLimit() && first.getLowerLimit() == second.getLowerLimit()) {
                return true;
            }
            return false;
        }
    }

    public static final int thresholdIncrementalFactor = 256;

    public static final LinkedHashMap<Integer, ProcessCubeTickSupervisorThread.Range<Integer>> workerThreadThreshold = new LinkedHashMap<Integer, ProcessCubeTickSupervisorThread.Range<Integer>>(){
        {
            for (int i = thresholdIncrementalFactor; i <= 4096; i++) {
                int fac = i / thresholdIncrementalFactor;
                double mod = i%Double.parseDouble(new Integer(thresholdIncrementalFactor).toString());
                if (mod == 0d) {
                    put(fac, new ProcessCubeTickSupervisorThread.Range<Integer>(i-255, i));
                }
            }
            forEach((x, y) -> System.out.println(x + " -> " + y));
        }
    };

    /**BASED SOLLY ON THE CUBE DATA RETRIEVED FROM THE ITEM STACK!
     * DOES NOT WORK FOR NON-CUBE ITEMSTACKS!!!
     */
    protected static final class EdgyItemStackComparator {

        public static final int STANDARD_DEVIATION = 5;

        /**Breeder method to stacksAreTheSame(ItemStack, ItemStack, int)
         *
         * @param first
         * @param second
         * @return
         */
        public static boolean stacksAreTheSame(ItemStack first, ItemStack second) {
            return stacksAreTheSame(first, second, STANDARD_DEVIATION);
        }

        /**MULTITHREADED FOR PERFORMANCE!
         *
         * @param first
         * @param second
         * @param comparisonThreshold an integer value, representing a percentage, between 0 and 100
         * @return
         */
        public static boolean stacksAreTheSame(ItemStack first, ItemStack second, int comparisonThreshold) {
//            final class GenericCubeWorker extends Thread {
//
//                final int fullTruthSum;
//                int truthSumResult = 0;
//
//                protected final List<CubeData>[] data = (ArrayList<CubeData>[])Array.newInstance(new ArrayList<CubeData>().getClass(), 2);
//
//                public GenericCubeWorker(String name, List<CubeData> first, List<CubeData> second) {
//                    super(name);
//                    this.fullTruthSum = Math.max(first.size(), second.size());
//                    data[0] = first;
//                    data[1] = second;
//                }
//
//                public synchronized int getTruthSum() {
//                    return Math.round(this.fullTruthSum / this.truthSumResult);
//                }
//
//                @Override
//                public void run() {
//                    data[1].forEach(x -> {
//                        if (data[0].contains(x)) {
//                            truthSumResult += 1;
//                        }
//                    });
//                    synchronized (this) {
//                        try {
//                            wait();
//                        } catch (InterruptedException e) {
//                            interrupt();
//                        }
//                    }
//                }
//            }
//            new Thread("") {
//                protected volatile List<CubeData> firstCubeData;
//                protected volatile List<CubeData> secondCubeData;
//                protected int numThreads;
//                protected volatile List<GenericCubeWorker> workers = new ArrayList<GenericCubeWorker>();
//                {
//                    firstCubeData = Arrays.<CubeData>asList(armorHelper.getCubeData(first));
//                    secondCubeData = Arrays.<CubeData>asList(armorHelper.getCubeData(second));
//                    float higher = Math.max(firstCubeData.size(), secondCubeData.size();
//                    numThreads = (int)Math.nextDown(higher / thresholdIncrementalFactor);
//                    int remainder = (int)Math.nextDown(higher%thresholdIncrementalFactor);
//                    for (int i = 1; i < ((remainder == 0) ? (numThreads) : (numThreads+1)); i++) {
//                        if (i == (numThreads+1)) {
//                            int lastIndex = (i-1)*thresholdIncrementalFactor;
//                            firstCubeData.subList(lastIndex, (lastIndex-1+remainder)).forEach(x ->
//                                    workers.add(x));
//                        } else {
//                            int begin = (i-1)*thresholdIncrementalFactor;
//                            int end = (i*thresholdIncrementalFactor)-1;
//                            firstCubeData.subList(begin, end).forEach(x -> );
//                        }
//                    }
//                }
//
//                public Thread startThread() {
//                    super.start();
//                    return this;
//                }
//
//                @Override
//                public void run() {
//                    interrupt();
//                }
//            }.startThread().;
            return false;
        }
    }

    /**Avoid the need for volatile or synchronized access to a variable over multiple threads...*/
    public static final ArmorHelper2 armorHelper = new ArmorHelper2();

    public static LinkedHashMap<ItemStack, PCTST> pctsThreads = new LinkedHashMap<>();

    private final ItemStack stackApplicant;

    private final EntityPlayer playerApplicant;

    public PCTST(ItemStack stack, EntityPlayer applicatant) {
        super();
        this.stackApplicant = stack;
        this.playerApplicant = applicatant;
    }

    public PCTST getIfNotExists(ItemStack stack) {

    }

    public synchronized void queueOperation(CubeData... cubesToApply) {

    }


}
