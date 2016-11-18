package wurmatron.voidrpg.api.cube;

public interface IEnergyConsumer {

		/**
		 * Amount of energy to consume per action preformed
		 */
		int getAmountPerAction();

		/**
		 * How much energy is required to keep the cube operational
		 */
		int getPassiveDrain();
}
