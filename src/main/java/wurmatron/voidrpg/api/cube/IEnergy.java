package wurmatron.voidrpg.api.cube;

public interface IEnergy {

		/**
			* How much energy this cube can store
			*/
		int getStorage();

		/**
			* The amount of energy this cube will produce
			*/
		int getProductionAmount();
}
