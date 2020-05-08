package sushigame.view;

import java.util.Comparator;
import sushigame.model.Chef;

public class LowToHighFoodSpoiledComparator implements Comparator<Chef>{
	
	@Override
	public int compare(Chef a, Chef b) {
		// We do a-b because we want largest to smallest
	
	return (int) (Math.round(a.getFoodSpoiled()*100.0)- 
			Math.round(b.getFoodSpoiled()*100));
	
	}
	

}
