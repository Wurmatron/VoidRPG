package wurmatron.voidrpg;

import net.minecraft.entity.player.EntityPlayer;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.item.ItemStack;
import wurmatron.voidrpg.CreateArmourSupervisorThread.Stack;
import wurmatron.voidrpg.api.cube.CubeData;
import wurmatron.voidrpg.common.reference.NBT;

import static wurmatron.voidrpg.common.utils.ArmorHelper2.armourInstance;

/**
 * Created by matthew on 20/11/16.
 */
public class CreateArmourWorker implements Runnable {

    protected static final
        LinkedHashMap<CreateArmourSupervisorThread, CreateArmourWorker> workers
            = new LinkedHashMap<>();

    public final EntityPlayer player;

    public final Stack cubeStack;

    public final ItemStack original;

    public final String type;

//    protected LinkedList<ItemStack> returnQueue = new LinkedList<ItemStack>();

    protected ItemStack returnQueue;

    public CreateArmourWorker(CreateArmourSupervisorThread supervisor, EntityPlayer player,
                              Stack stack) {
        this.player = player;
        this.cubeStack = stack;
        this.original  = cubeStack.stack;
        this.type = stack.getItemStackType();
        CreateArmourWorker.workers.put(supervisor, this);
    }

    public static final CreateArmourWorker createIfNotExists(CreateArmourSupervisorThread supervisor,
                                                             EntityPlayer player,  Stack stack) {
        for (CreateArmourWorker caw : workers.values()) {
            if (caw.type == stack.getItemStackType()) return caw;
        }
        return new CreateArmourWorker(supervisor, player, stack);
    }

    public final synchronized ItemStack getOriginal() {
        return this.original;
    }

    public final ItemStack createCubeOnHelmet() {
        List<CubeData> activeCubes = Arrays.<CubeData>asList(armourInstance.getCubeData(cubeStack.stack));
        for (CubeData cube : this.cubeStack.cubesToRemove) {
            activeCubes.remove(cube);
        }
        this.cubeStack.cubesToRemove.clear();
        return armourInstance.createHelmet(activeCubes.toArray(new CubeData[0]));
    }

    public final ItemStack createCubeOnChestplace() {
        List<CubeData> torsoCubes = Arrays.<CubeData>asList(armourInstance.getCubeData(cubeStack.stack, NBT.BODY));
        List<CubeData> leftArmCubes = Arrays.<CubeData>asList(armourInstance.getCubeData(cubeStack.stack, NBT.LEFTARM));
        List<CubeData> rightArmCubes = Arrays.<CubeData>asList(armourInstance.getCubeData(cubeStack.stack,
                NBT.RIGHTARM));
        for (CubeData cube : this.cubeStack.cubesToRemove) {
            torsoCubes.remove(cube);
            leftArmCubes.remove(cube);
            rightArmCubes.remove(cube);
        }
        this.cubeStack.cubesToRemove.clear();
        return armourInstance.createChestplate(torsoCubes.toArray(new CubeData[0]),
                leftArmCubes.toArray(new CubeData[0]), rightArmCubes.toArray(new CubeData[0]));
    }

    public final ItemStack createCubeOnLeggings() {
        List<CubeData> leftLeg = Arrays.<CubeData>asList(armourInstance.getCubeData(cubeStack.stack, NBT.LEFTLEG));
        List<CubeData> rightLeg = Arrays.<CubeData>asList(armourInstance.getCubeData(cubeStack.stack, NBT.RIGHTLEG));
        for (CubeData cube : this.cubeStack.cubesToRemove) {
            leftLeg.remove(cube);
            rightLeg.remove(cube);
        }
        this.cubeStack.cubesToRemove.clear();
        return armourInstance.createLeggings(leftLeg.toArray(new CubeData[0]), rightLeg.toArray(new CubeData[0]));
    }

    public final ItemStack createCubeOnBoots() {
        List<CubeData> leftLeg = Arrays.<CubeData>asList(armourInstance.getCubeData(cubeStack.stack, NBT.LEFTLEG));
        List<CubeData> rightLeg = Arrays.<CubeData>asList(armourInstance.getCubeData(cubeStack.stack, NBT.RIGHTLEG));
        for (CubeData cube : this.cubeStack.cubesToRemove) {
            leftLeg.remove(cube);
            rightLeg.remove(cube);
        }
        this.cubeStack.cubesToRemove.clear();
        return armourInstance.createBoots(leftLeg.toArray(new CubeData[0]), rightLeg.toArray(new CubeData[0]));
    }

    private int numCalcs = 0;

    public synchronized void calc() {
        numCalcs++;
        //TODO Debug, remove
        System.out.println(numCalcs);
        switch(this.cubeStack.getItemStackType()) {
            case ("head"): {
                returnQueue = this.createCubeOnHelmet();
            }
            case ("chest"): {
                returnQueue = this.createCubeOnChestplace();
            }
            case ("legs"): {
                returnQueue = this.createCubeOnLeggings();
            }
            case ("feet"): {
                returnQueue = this.createCubeOnBoots();
            }
        }
    }

    protected synchronized ItemStack getReturnQueue() {
        ItemStack toReturn = returnQueue;
        this.returnQueue = null;
        Thread thisThread = Thread.currentThread();
        synchronized (thisThread) {
            thisThread.interrupt();
        }
        return returnQueue;
    }

//    protected synchronized ItemStack getQueuedReturn(String type) {
//        for (ItemStack stack : this.returnQueue) {
//            if (stack.getItem().getUnlocalizedName().substring(11) == type) {
//                return stack;
//            }
//        }
//        return null;
//    }

    protected synchronized boolean kill() {
        if (this.cubeStack.cubesToRemove.size() > 0) {
            return false;
        } else {
            synchronized (this) {
                Thread.currentThread().interrupt();
            }
        }
        return true;
    }

    @Override
    public void run() {
        while (true) {
            running();
        }
    }

    private final void running() {
        try {
            Thread.currentThread().wait();
        } catch (InterruptedException e) {
            calc();
        }
    }
}
