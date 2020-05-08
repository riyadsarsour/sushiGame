package sushigame.view;

import sushigame.model.Chef;

import java.util.Comparator;


public class HighToLowFoodSoldComparator implements Comparator<Chef> {
	
	@Override
	public int compare(Chef a, Chef b) {
		// We do b - a because we want largest to smallest
		return (int) (Math.round(b.getFoodSold()*100.0) - 
				Math.round(a.getFoodSold()*100));
	}

}
