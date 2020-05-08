package sushigame.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel gameModel;
	private JLabel display;
	private boolean byBalance; 
	private boolean byFoodSold;
	private boolean byFoodSpoiled;
	
	public ScoreboardWidget(SushiGameModel gm) {
		gameModel = gm;
		gameModel.getBelt().registerBeltObserver(this);
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
		setLayout(new BorderLayout());
		add(display, BorderLayout.CENTER);
		display.setText(makeScoreboardHTML());
		
		byBalance = false;
		byFoodSold = true;
		byFoodSpoiled = true;
		
		JPanel bottom_panel = new JPanel();
		bottom_panel.setLayout(new BorderLayout());
				
		JButton balance_button = new JButton(" Sort Chef by Balance");
		balance_button.setActionCommand("sortBalance");
		balance_button.addActionListener(this);
		bottom_panel.add(balance_button, BorderLayout.EAST);
		
		JButton food_sold_button = new JButton("Sort Chefs by Food Sold");
		food_sold_button.setActionCommand("sortFoodSold");
		food_sold_button.addActionListener(this);
		bottom_panel.add(food_sold_button, BorderLayout.CENTER);
		
		JButton food_spoiled_button = new JButton("Sort Chefs by Food Spoiled");
		food_spoiled_button.setActionCommand("sortFoodSpoiled");
		food_spoiled_button.addActionListener(this);
		bottom_panel.add(food_spoiled_button, BorderLayout.WEST);
		
		;
		add(bottom_panel, BorderLayout.NORTH);
	}

	private String makeScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";
		
		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= gameModel.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = gameModel.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}

		
		if (byBalance){		
			Arrays.sort(chefs, new HighToLowBalanceComparator());
			
			for (Chef c : chefs) {
			sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
			}
		}
			
		if (byFoodSold){
			Arrays.sort(chefs, new HighToLowFoodSoldComparator());
			for (Chef c : chefs) {
			sb_html += c.getName() +  " (" + Math.round(c.getFoodSold()*100.0)/100.0 + ") <br>";
			}
		  
		}
			
		if (byFoodSpoiled){
			Arrays.sort(chefs, new LowToHighFoodSpoiledComparator());
			for (Chef c : chefs) {
			sb_html += c.getName() + " (" + Math.round(c.getFoodSpoiled()*100.0)/100.0 + ") <br>";
				}
			
		}
		
		return sb_html;

	}

	public void refresh() {
		display.setText(makeScoreboardHTML());		
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		
			if (ae.getActionCommand().equals("sortBalance")) {
				byBalance = true;
				byFoodSold = false;
				byFoodSpoiled = false;
					   }
		
			if (ae.getActionCommand().equals("sortFoodSold")) {
				byBalance = false;
				byFoodSold = true;
				byFoodSpoiled = false;
						}
			if (ae.getActionCommand().equals("sortFoodSpoiled")){
				byBalance = false;
				byFoodSold = false;
				byFoodSpoiled = true;
					   }
			refresh();
	}

}
