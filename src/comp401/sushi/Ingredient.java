package comp401.sushi;

public interface Ingredient {

	String getName();
	double getCaloriesPerDollar();
	int getCaloriesPerOunce();
	double getPricePerOunce();
	boolean equals(comp401.sushi.Ingredient other);
	boolean getIsVegetarian();
	boolean getIsRice();
	boolean getIsShellfish();
}
