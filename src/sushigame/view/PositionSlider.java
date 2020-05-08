package sushigame.view;

import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;

public	class PositionSlider extends JSlider {

		// Since sliders only take integer values, we store the values of the tick marks
		PositionSlider() {
			super(0, 20);

			// The hashtable is used to store the labels for the slider ticks, which
			Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();

			labels.put(0, new JLabel("1"));
			labels.put(20, new JLabel("20"));
			

			setLabelTable(labels);
			setPaintLabels(true);
			setPaintTicks(true);
			setMajorTickSpacing(1);
			setSnapToTicks(true);
			
			
		}
}
