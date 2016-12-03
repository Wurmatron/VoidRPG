package wurmatron.voidrpg.common.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.CreateArmourSupervisorThread;
import wurmatron.voidrpg.GenericWrapper;
import wurmatron.voidrpg.ProcessCubeTickSupervisorThread;
import wurmatron.voidrpg.api.cube.*;
import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.Local;
import wurmatron.voidrpg.common.reference.NBT;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Objects;

public class ArmorHelper2 {

	public static final ArmorHelper2 armourInstance = new ArmorHelper2();

		public final static ModelRenderer createModelRenderer (ModelBase base, final CubeData data) {
				ModelRenderer model = new ModelRenderer(base) {
						@Override
						public void render (float scale) {
								Minecraft.getMinecraft().renderEngine.bindTexture(data.cube.getTexture());
								super.render(scale);
								Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Global.MODID, "textures/test/armor.png"));
						}
				};
				model.addBox(data.offX, data.offY, data.offZ, 1, 1, 1);
				return model;
		}

		public final ItemStack createHelmet (CubeData[] head) {
				NBTTagCompound cubes = new NBTTagCompound();
				NBTTagCompound nbt = new NBTTagCompound();
				for (int index = 0; index < head.length; index++)
						cubes.setTag(Integer.toString(index), createNBTFromCubeData(head[index]));
				nbt.setTag(NBT.CUBES, cubes);
				ItemStack stack = new ItemStack(VoidRPGItems.armorHelmet,1,0);
				stack.setTagCompound(nbt);
				return stack;
		}

		public final ItemStack createChestplate (CubeData[] body, CubeData[] leftArm, CubeData[] rightArm) {
				NBTTagCompound cubes = new NBTTagCompound();
				NBTTagCompound nbt = new NBTTagCompound();
				NBTTagCompound bodyNBT = new NBTTagCompound();
				for (int index = 0; index < body.length; index++)
						bodyNBT.setTag(Integer.toString(index), createNBTFromCubeData(body[index]));
				cubes.setTag(NBT.BODY, bodyNBT);
				NBTTagCompound leftArmNBT = new NBTTagCompound();
				for (int index = 0; index < leftArm.length; index++)
						leftArmNBT.setTag(Integer.toString(index), createNBTFromCubeData(leftArm[index]));
				cubes.setTag(NBT.LEFTARM, leftArmNBT);
				NBTTagCompound rightArmNBT = new NBTTagCompound();
				for (int index = 0; index < rightArm.length; index++)
						rightArmNBT.setTag(Integer.toString(index), createNBTFromCubeData(rightArm[index]));
				cubes.setTag(NBT.RIGHTARM, rightArmNBT);
				nbt.setTag(NBT.CUBES, cubes);
				ItemStack stack = new ItemStack(VoidRPGItems.armorChestplate,1,0);
				stack.setTagCompound(nbt);
				return stack;
		}

		private final ItemStack createLeggingsOrBoots (Item item, CubeData[] leftLeg, CubeData[] rightLeg) {
				NBTTagCompound cubes = new NBTTagCompound();
				NBTTagCompound nbt = new NBTTagCompound();
				NBTTagCompound leftLegNBT = new NBTTagCompound();
				for (int index = 0; index < leftLeg.length; index++)
						leftLegNBT.setTag(Integer.toString(index), createNBTFromCubeData(leftLeg[index]));
				cubes.setTag(NBT.LEFTLEG, leftLegNBT);
				NBTTagCompound rightLegNBT = new NBTTagCompound();
				for (int index = 0; index < rightLeg.length; index++)
						rightLegNBT.setTag(Integer.toString(index), createNBTFromCubeData(rightLeg[index]));
				cubes.setTag(NBT.RIGHTLEG, rightLegNBT);
				nbt.setTag(NBT.CUBES, cubes);
				ItemStack stack = new ItemStack(item,1,0);
				stack.setTagCompound(nbt);
				return stack;
		}

		public final ItemStack createLeggings (CubeData[] leftLeg, CubeData[] rightLeg) {
				return createLeggingsOrBoots(VoidRPGItems.armorLeggings, leftLeg, rightLeg);
		}

		public final ItemStack createBoots (CubeData[] leftLeg, CubeData[] rightLeg) {
				return createLeggingsOrBoots(VoidRPGItems.armorBoots, leftLeg, rightLeg);
		}

		private final NBTTagCompound createNBTFromCubeData (CubeData data) {
				NBTTagCompound temp = new NBTTagCompound();
				temp.setString(NBT.CUBE, data.cube.getUnlocalizedName());
				temp.setInteger(NBT.OFFSETX, data.offX);
				temp.setInteger(NBT.OFFSETY, data.offY);
				temp.setInteger(NBT.OFFSETZ, data.offZ);
				temp.setInteger(NBT.DAMAGE, data.damage);
				return temp;
		}

		private final CubeData[] getCubesFromNBT (NBTTagCompound nbt) {
				CubeData[] cubeData = new CubeData[nbt.getSize()];
				for (int size = 0; size < nbt.getSize(); size++) {
						NBTTagCompound temp = nbt.getCompoundTag(Integer.toString(size));
						cubeData[size] = new CubeData(temp.getInteger(NBT.OFFSETX), temp.getInteger(NBT.OFFSETY), temp.getInteger(NBT.OFFSETZ), CubeRegistry.INSTANCE.getCubesFromName(temp.getString(NBT.CUBE)), temp.getInteger(NBT.DAMAGE));
				}
				return cubeData;
		}

		public final CubeData[] getCubeData (ItemStack stack, String type) {
				if (stack.getUnlocalizedName().substring(11).equals("legs") || stack.getUnlocalizedName().substring(11).equals("chest") || stack.getUnlocalizedName().substring(11).equals("feet"))
						return getCubesFromNBT(stack.getTagCompound().getCompoundTag(NBT.CUBES).getCompoundTag(type));
				else if (stack.getUnlocalizedName().substring(11).equals("head"))
						return getCubesFromNBT(stack.getTagCompound().getCompoundTag(NBT.CUBES));
				return new CubeData[0];
		}

		public final CubeData[] getCubeData (ItemStack stack) {
				switch (stack.getItem().getUnlocalizedName().substring(11)) {
						case ("head"): {
								return getCubesFromNBT(stack.getTagCompound().getCompoundTag(NBT.CUBES));
						}
						case ("chest"): {
								CubeData[] body = getCubeData(stack, NBT.BODY);
								CubeData[] leftArm = getCubeData(stack, NBT.LEFTARM);
								CubeData[] rightArm = getCubeData(stack, NBT.RIGHTARM);
								CubeData[] bodyData = new CubeData[body.length + leftArm.length + rightArm.length];
								for (int b = 0; b < body.length; b++)
										bodyData[b] = body[b];
								for (int l = 0; l < leftArm.length; l++)
										bodyData[body.length + l] = leftArm[l];
								for (int r = 0; r < rightArm.length; r++)
										bodyData[body.length + leftArm.length + r] = rightArm[r];
								return bodyData;
						}
						case ("legs"): {
								CubeData[] leftLeg = getCubeData(stack, NBT.LEFTLEG);
								CubeData[] rightLeg = getCubeData(stack, NBT.RIGHTLEG);
								CubeData[] legsData = new CubeData[leftLeg.length + rightLeg.length];
								for (int l = 0; l < leftLeg.length; l++)
										legsData[l] = leftLeg[l];
								for (int r = 0; r < rightLeg.length; r++)
										legsData[leftLeg.length + r] = rightLeg[r];
								return legsData;
						}
						case ("feet"): {
								CubeData[] leftBoot = getCubeData(stack, NBT.LEFTLEG);
								CubeData[] rightBoot = getCubeData(stack, NBT.RIGHTLEG);
								CubeData[] bootsData = new CubeData[leftBoot.length + rightBoot.length];
								for (int l = 0; l < leftBoot.length; l++)
										bootsData[l] = leftBoot[l];
								for (int r = 0; r < rightBoot.length; r++)
										bootsData[leftBoot.length + r] = rightBoot[r];
								return bootsData;
						}
				}
				return new CubeData[0];
		}

		public final double getWeight (ItemStack stack) {
				double weight = 0;
				for (CubeData data : getCubeData(stack))
						weight += data.cube.getWeight();
				return weight;
		}

		public int getComplexity (ItemStack stack) {
				int complexity = 0;
				for (CubeData data : getCubeData(stack))
						complexity += data.cube.getComplexity();
				return complexity;
		}

		public ItemStack overrideData (ItemStack stack, CubeData find, CubeData replace) {
				switch (stack.getItem().getUnlocalizedName().substring(11)) {
						case ("head"): {
								CubeData[] head = getCubesFromNBT(stack.getTagCompound().getCompoundTag(NBT.CUBES));
								for (int index = 0; index < head.length; index++)
										if (head[index].equals(find))
												head[index] = replace;
								return createHelmet(head);
						}
						case ("chest"): {
								CubeData[] body = getCubeData(stack, NBT.BODY);
								for (int index = 0; index < body.length; index++)
										if (body[index].equals(find))
												body[index] = replace;
								CubeData[] leftArm = getCubeData(stack, NBT.LEFTARM);
								for (int index = 0; index < leftArm.length; index++)
										if (leftArm[index].equals(find))
												leftArm[index] = replace;
								CubeData[] rightArm = getCubeData(stack, NBT.RIGHTARM);
								for (int index = 0; index < rightArm.length; index++)
										if (rightArm[index].equals(find))
												rightArm[index] = replace;
								return createChestplate(body, leftArm, rightArm);
						}
						case ("legs"): {
								CubeData[] rightLeg = getCubeData(stack, NBT.RIGHTLEG);
								for (int index = 0; index < rightLeg.length; index++)
										if (rightLeg[index].equals(find))
												rightLeg[index] = replace;
								CubeData[] leftLeg = getCubeData(stack, NBT.LEFTLEG);
								for (int index = 0; index < leftLeg.length; index++)
										if (leftLeg[index].equals(find))
												leftLeg[index] = replace;
								return createLeggings(leftLeg, rightLeg);
						}
						case ("feet"): {
								CubeData[] leftBoots = getCubeData(stack, NBT.LEFTLEG);
								for (int index = 0; index < leftBoots.length; index++)
										if (leftBoots[index].equals(find))
												leftBoots[index] = replace;
								CubeData[] rightBoots = getCubeData(stack, NBT.RIGHTLEG);
								for (int index = 0; index < rightBoots.length; index++)
										if (rightBoots[index].equals(find))
												rightBoots[index] = replace;
								return createLeggings(leftBoots, rightBoots);
						}
				}
				return stack;
		}

		public int getPassiveEnergyDrain (CubeData[] data) {
				int passiveDrain = 0;
				for (CubeData d : data)
						if (d.cube instanceof IEnergyConsumer)
								passiveDrain += ((IEnergyConsumer) d.cube).getPassiveDrain();
				return passiveDrain;
		}

		public int getReactorEnergySupport (CubeData[] data) {
				int reactorMax = 0;
				for (CubeData d : data)
						if (d.cube instanceof IReactor)
								reactorMax += ((IReactor) d.cube).getMaxPower();
				return reactorMax;
		}

		public boolean canReactorRun (String reactorStatus) {
				return reactorStatus.equals(Local.REACTOR_LIMITED) || reactorStatus.equals(Local.REACTOR_OPERATIONAL);
		}

		private boolean canReactorRun (ItemStack stack) {
				return canReactorRun(hasValidReactor(getCubeData(stack)));
		}

		public String hasValidReactor (ItemStack stack) {
				return hasValidReactor(getCubeData(stack));
		}

		public String hasValidReactor (CubeData[] data) {
				int reactorMax = getReactorEnergySupport(data);
				int passiveDrain = getPassiveEnergyDrain(data);
				if (reactorMax == 0) return Local.REACTOR_MISSING;
				else if (reactorMax >= passiveDrain + (passiveDrain * Settings.reactorOverrage))
						return Local.REACTOR_OPERATIONAL;
				else if (reactorMax >= passiveDrain)
						return Local.REACTOR_LIMITED;
				else if (reactorMax < passiveDrain)
						return Local.REACTOR_OVERLOAD;
				return Local.REACTOR_MISSING;
		}

		public int getMaxEnergyStorage (CubeData[] data) {
				int maxEnergy = 0;
				for (CubeData d : data)
						if (d.cube instanceof IEnergyCube)
								maxEnergy += ((IEnergyCube) d.cube).getStorage();
				return maxEnergy;
		}

		public int getAmountOfCube (ICube cube, ItemStack stack) {
				int amount = 0;
				for (CubeData data : getCubeData(stack))
						if (data.cube.equals(cube))
								amount++;
				return amount;
		}

		public int getAmountOfCube (ICube cube, CubeData[] cubes) {
				int amount = 0;
				for (CubeData data : cubes)
						if (data.cube.equals(cube))
								amount++;
				return amount;
		}

		public boolean isActive (ICube cube, ItemStack stack) {
				return getAmountOfCube(cube, stack) >= cube.getMinAmount(stack.getItem(), getWeight(stack)) && canReactorRun(stack);
		}

		public boolean isActive (ICube cube, ItemStack stack, double weight) {
				return getAmountOfCube(cube, stack) >= cube.getMinAmount(stack.getItem(), weight) && canReactorRun(stack);
		}

		private static final LinkedList<Double> averageRuntime = new LinkedList<Double>() {
			@Override
			public synchronized boolean add(Double d) {
				boolean toReturn = super.add(d);
				final GenericWrapper<Double> dd = new GenericWrapper<Double>(0d, true);
				forEach(ddd -> dd.setObj(dd.getObj()+ddd));
				LogHandler.info("Average runtime: " + (dd.getObj() / size()));
				return toReturn;
			}

			@Override
			public synchronized Double get(int pos) {
				return super.get(pos);
			}
		};

		public void processCubeTick(EntityPlayer player, ItemStack stack) {
			CreateArmourSupervisorThread thread = CreateArmourSupervisorThread.getIfNotExists(Thread.currentThread(), player);
//			if (thread.getState() != Thread.State.RUNNABLE) {
			if (!thread.isAlive()) {
				thread.start();
			}
			ProcessCubeTickSupervisorThread processor = ProcessCubeTickSupervisorThread.getIfNotExists(thread, Thread.currentThread(), stack, player);
			processor.checkCubes();
//			if (thread.getState() != Thread.State.RUNNABLE) {
			if (!processor.isAlive()) {
				processor.start();
			}
//			final CubeData[] cubes = getCubeData(stack);
//			double start = System.currentTimeMillis();
//			for (CubeData data : cubes) {
//				if (isActive(data.cube, stack) && data.cube.hasEffects(player, stack)) {
//					data.cube.applyEffect(player, data, stack);
//				}
//				checkAndHandleBrokenCube(thread, player, stack, data);
//			}
//			averageRuntime.add(System.currentTimeMillis() - start);
//			LogHandler.info("Loop took: '" + (averageRuntime.get(averageRuntime.size()-1)) + "' ms to run!");
		}

		public boolean brokenCube(CubeData cube) {
			return cube.damage >= cube.cube.getDurability();
		}

	/***
	 *
	 * @param player
	 * @param stack THE ORIGINAL ITEMSTACK
	 * @return
	 */
		public static synchronized int stackIndex(EntityPlayer player, ItemStack stack) {
			for (int slot = 0; slot < 4; slot++) {
				if (player.inventory.armorInventory[slot] == stack) {
					return slot;
				}
			}
			return -1;
		}

		public void checkAndHandleBrokenCube (CreateArmourSupervisorThread thread, EntityPlayer player, ItemStack stack,
											  CubeData cube) {
			System.out.println("Checking and Handling the broken cubes...");
			abstract class WatcherThread extends Thread {
				private CreateArmourSupervisorThread cast;

				public ItemStack original;

				public EntityPlayer player;

				public WatcherThread(String name) {
					super(name);
				}

				public synchronized Thread initializeThread(
						CreateArmourSupervisorThread thread, ItemStack original, EntityPlayer player) {
					this.cast = thread;
					this.original = original;
					this.player = player;
					return this;
				}

				public LinkedHashMap<ItemStack, CubeData[]> getData() {
					return new LinkedHashMap<ItemStack, CubeData[]>() {
						CubeData[] originalQueuedToRemove = cast.getCubesToRemove(original);
						{
							put(cast.getQueuedReturn(original, originalQueuedToRemove), originalQueuedToRemove);
						}
					};
				}

				public abstract void exec(ItemStack stack, CubeData[] removedCubes);

				@Override
				public void start() {
					super.start();
					run();
				}

				@Override
				public void run() {
					while (true) {
						LinkedHashMap<ItemStack, CubeData[]> data = getData();
						if (Objects.nonNull(data.keySet().toArray()[0])) {
							exec(data.keySet().toArray(new ItemStack[0])[0], data.values().toArray(new CubeData[0][])[0]);
							synchronized (this) {
								interrupt();
							}
							break;
						} else {
							continue;
						}
					}
				}

			}
			if (brokenCube(cube)) {
				new WatcherThread("Create Helmet Operation Watcher") {
					@Override
					public void exec(ItemStack stack, CubeData[] data) {
						int originalPos = stackIndex(this.player, this.original);
						if (originalPos >= 0) {
							this.player.inventory.setInventorySlotContents(100 + originalPos, stack);
						}
					}
				}.initializeThread(thread, stack, player).start();
				thread.queueOperation(stack, cube);
			}
		}
}
