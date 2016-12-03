package wurmatron.voidrpg;

import com.sun.corba.se.impl.io.TypeMismatchException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.utils.ArmorHelper2;
import wurmatron.voidrpg.common.utils.Arrays;
import wurmatron.voidrpg.common.utils.LogHandler;
import wurmatron.voidrpg.common.utils.StackHelper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by matthew on 21/11/16.
 */
public final class ProcessCubeTickSupervisorThread extends Thread {

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

        @Override
        public boolean equals(Object o) throws TypeMismatchException {
            Range<T> range = null;
            try {
                 range = (Range<T>) o;
            } catch (ClassCastException e) {
                throw new TypeMismatchException();
            }
            return Range.areEqual(this, range);
        }

        public boolean isWithinRange(T arbitraryNumber) {
            return Range.<T>isWithinRange(this.lower, this.upper, arbitraryNumber);
        }

        //TODO Find a better way to do this...
        public T upperLowerDifference() {
            switch(upper.getClass().getSimpleName()) {
                case "Integer": return (T)(Integer)((Integer)this.upper - (Integer)this.lower);
                case "Long": return (T)(Long)((Long)this.upper - (Long)this.lower);
                case "Short": return (T)Short.valueOf(((Integer)((Short)this.upper - (Short)this.lower)).toString());
                case "Double": return (T)(Double)((Double)this.upper - (Double)this.lower);
                case "Float": return (T)(Float)((Float)this.upper - (Float)this.lower);
                default: throw new TypeMismatchException();
            }
        }

        //TODO Find a better way to do this...
        public Range<T> isGreaterThan(Range<T> supposedlyLessor) {
            boolean greaterThan = false;
            switch(upper.getClass().getSimpleName()) {
                case "Integer": greaterThan = ((Integer)this.upperLowerDifference() > (Integer)supposedlyLessor.upperLowerDifference());
                case "Long":  greaterThan = ((Long)this.upperLowerDifference() > (Long)supposedlyLessor.upperLowerDifference());
                case "Short":  greaterThan = ((Short)this.upperLowerDifference() > (Short)supposedlyLessor.upperLowerDifference());
                case "Double":  greaterThan = ((Double)this.upperLowerDifference() > (Double)supposedlyLessor.upperLowerDifference());
                case "Float":  greaterThan = ((Float)this.upperLowerDifference() > (Float)supposedlyLessor.upperLowerDifference());
            }
            return greaterThan ? this : supposedlyLessor;
        }

        public Range<T> isLessThan(Range<T> supposedlyGreater) {
            return (isGreaterThan(supposedlyGreater) == this) ? supposedlyGreater : this;
        }

        //TODO Fix...
        public static <T extends Number> boolean isWithinRange(T lowerLimit, T upperLimit, T arbitraryNumber) {
            return (arbitraryNumber.doubleValue() >= lowerLimit.doubleValue() &&
                    arbitraryNumber.doubleValue() <= upperLimit.doubleValue());
        }

        public static <T extends Number> boolean areEqual(Range<T> first, Range<T> second) {
            return first.getUpperLimit() == second.getUpperLimit() && first.getLowerLimit() == second.getLowerLimit();
//            if (first.getUpperLimit() == second.getUpperLimit() && first.getLowerLimit() == second.getLowerLimit()) {
//                return true;
//            }
//            return false;
        }

        public static <T extends Number> Range<T> getDifference(Range<T> first, Range<T> second) {
//            Range<T> toReturn = null;
//            switch(first.upper.getClass().getSimpleName()) {
//                case "Integer": toReturn = new Range<T>((T)((Integer)Math.abs((Integer)first.lower - (Integer)second.lower)), (T)((Integer)Math.abs((Integer)first.lower - (Integer)second.lower)));
//                case "Long":  toReturn = new Range<T>((T)((Long)Math.abs((Long)first.lower - (Long)second.lower)), (T)((Long)Math.abs((Long)first.lower - (Long)second.lower)));
//                case "Short":  toReturn = new Range<T>((T)(Short.parseShort(((Integer)Math.abs((Short)first.lower - (Short)second.lower)).toString())), (T)((Short)Math.abs((Short)first.lower - (Short)second.lower)));
//                case "Double":  greaterThan = ((Double)this.upperLowerDifference() > (Double)supposedlyLessor.upperLowerDifference());
//                case "Float":  greaterThan = ((Float)this.upperLowerDifference() > (Float)supposedlyLessor.upperLowerDifference());
//            }
            return new Range<T>((T)new Integer(Math.abs(first.lower.byteValue() - second.lower.byteValue())),
                    (T)new Integer(Math.abs(first.upper.byteValue() - second.upper.byteValue())));
        }

//        public static <T extends Number> boolean greaterThan(Range<T> first, Range<T> second) {
//            if (first.upperLowerDifference() > second.upperLowerDifference()) {
//
//            }
//        }
    }

    public static final int thresholdIncrementalFactor = Settings.thresholdIncrementalFactor;

    public static final Range<Integer> workerThreadThreshhold = new Range<Integer>(0, (4096/thresholdIncrementalFactor)-1);

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

    public static LinkedHashMap<Thread, ProcessCubeTickSupervisorThread> pctsThreads = new LinkedHashMap<>();

    private final ItemStack stackApplicant;

    private final EntityPlayer playerApplicant;

    private final CreateArmourSupervisorThread cast;

    private final Thread initializerThread;

    private boolean checkCubes = false;

    public ProcessCubeTickSupervisorThread(CreateArmourSupervisorThread cast, Thread initializer, ItemStack stack, EntityPlayer applicant) {
        super();
        this.cast = cast;
        this.initializerThread = initializer;
        this.stackApplicant = stack;
        this.playerApplicant = applicant;
        pctsThreads.put(initializer, this);
    }

    public static ProcessCubeTickSupervisorThread getIfNotExists(CreateArmourSupervisorThread cast, Thread initialThread, ItemStack stack, EntityPlayer player) {
        for (Thread initializer : pctsThreads.keySet()) {
            ProcessCubeTickSupervisorThread respective = pctsThreads.get(initializer);
            //TODO Test Wurm's 'areItemsEqual' method... if it doesn't work, finish the one in the 'EdgyStackComparator' class
            if (StackHelper.areItemsEqual(stack, respective.stackApplicant) && respective.playerApplicant == player) return respective;
        }
        return new ProcessCubeTickSupervisorThread(cast, initialThread, stack, player);
    }

    private double lastCallTime = 0;

    public synchronized void checkCubes() {
        lastCallTime = System.currentTimeMillis();
        this.checkCubes = true;
    }

    @Override
    public void start() {
        System.out.println(this.getState());
        super.start();
        run();
    }


    @Override
    public void run() {
        LogHandler.info("ProcessCubeTickSupervisor: " + this.getClass() + ": RUN CALLED");
        boolean run = true;
        while (run) {
            if (checkCubes) {
                CubeData[] overflow = armorHelper.getCubeData(stackApplicant);
//                System.out.println(overflow.length);
//                System.out.println(Arrays.getFirstPopulated(overflow));
                boolean cont = false;
                do {
                    if (Objects.isNull(overflow)) break;
                    cont = false;
                    ProcessCubeTickWorkerThread returned = ProcessCubeTickWorkerThread.getIfNotExists(this.cast, this.initializerThread, this.stackApplicant, this.playerApplicant);
                    overflow = returned.queueCubes(overflow);
//                    Arrays.removeAll(overflow, overflow);
                    returned.start();
                    if (Objects.nonNull(overflow)) {
                        if (overflow.length > 0) {
                            cont = true;
                        }
                    }
                } while (cont);
                checkCubes = false;
            } else {
                if ((System.currentTimeMillis() - lastCallTime) >= (Settings.supervisorThreadTimeout*1000)) {
                    boolean workersLeft = false;
                    for (ItemStack stack : ProcessCubeTickWorkerThread.pctwThreads.keySet()) {
                        List<ProcessCubeTickWorkerThread> stackWorkers = ProcessCubeTickWorkerThread.pctwThreads.get(stack);
                        if (stackWorkers.size() > 0) {
                            for (ProcessCubeTickWorkerThread worker : stackWorkers) {
                                if (!worker.kill()) {
                                    workersLeft = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!workersLeft) {
                        synchronized (this) {
                            interrupt();
                            pctsThreads.remove(initializerThread, this);
                            break;
                        }
                    }
                }
            }
        }
    }

}
