package comp401.sushi;

public interface Sushi {
	
	public enum SushiType{NIGIRI, SASHIMI, ROLL}

	String getName();
	IngredientPortion[] getIngredients();
	int getCalories();
	double getCost();
	boolean getHasRice();
	boolean getHasShellfish();
	boolean getIsVegetarian();
	SushiType getSushiType();
}
