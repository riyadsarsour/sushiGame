package comp401.sushi;

import javax.swing.JOptionPane;

import sushigame.model.Chef;

public class GoldPlate extends PlateImpl {

	public GoldPlate(Chef chef, Sushi s, double price) throws PlatePriceException {
		super(chef, s, check_price(price), Color.GOLD);
	}
	
	private static double check_price(double price) {
		if (price < 5.0 && price > 10) {
			//throw new IllegalArgumentException();
			JOptionPane.showInputDialog(price, "Out of range. Range is between $5 -$10");
		}
		return price;
	}
}
