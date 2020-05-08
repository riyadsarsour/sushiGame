package sushigame.view;

import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;

public	class IngredientSlider extends JSlider {

		// Since sliders only take integer values, we store the values of the tick marks
		// from 0 to 10 and we will just divide  later  retrieving the values.
		IngredientSlider() {
			super(0, 15);

			// we only put at 0.0 oz, 0.5 oz, and 1.0 oz.
			Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();

			labels.put(0, new JLabel("0.0"));
			labels.put(7, new JLabel("0.5"));
			labels.put(15, new JLabel("1.5"));

			setLabelTable(labels);
			setPaintLabels(true);
			setPaintTicks(true);
			setMajorTickSpacing(1);
			setSnapToTicks(true);
			
			
		}
}
