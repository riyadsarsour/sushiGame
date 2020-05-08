package sushigame.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.SushiGameModel;

public class SushiGameView extends JPanel implements ActionListener, BeltObserver {

	private PlayerChefView player_chef_ui;
	private List<RotationRequestListener> rotation_request_listeners;
	private JLabel controllerMessages;
	private JComboBox sorter;
	ScoreboardWidget scoreboard;
	
	public SushiGameView(SushiGameModel game_model) {
		setLayout(new BorderLayout());
		
		scoreboard = new ScoreboardWidget(game_model);
		add(scoreboard, BorderLayout.WEST);
				
		player_chef_ui = new PlayerChefView(20);
		add(player_chef_ui, BorderLayout.EAST);
		
		BeltView belt_view = new BeltView(game_model.getBelt());
		
		add(belt_view, BorderLayout.CENTER);
		
		JPanel bottom_panel = new JPanel();
		bottom_panel.setLayout(new BorderLayout());
		
		JButton rotate_button = new JButton(" Rotate");
		rotate_button.setActionCommand("rotate");
		rotate_button.addActionListener(this);
		
		
//		_sorter = new JComboBox<Plate.Color>(new Plate.Color[]
//				{By Balance, Plate.Color.GOLD, Plate.Color.GREEN,
//				Plate.Color.RED});
//
//		add(new JLabel("   Sorter: "));
//		add(_sorter);
		

		bottom_panel.add(rotate_button, BorderLayout.NORTH);

		controllerMessages = new JLabel("Controller messages.");
		bottom_panel.add(controllerMessages, BorderLayout.SOUTH);
		
		add(bottom_panel, BorderLayout.SOUTH);
		
		rotation_request_listeners = new ArrayList<RotationRequestListener>();
		
		game_model.getBelt().registerBeltObserver(this);
	}
	
	public void registerPlayerChefListener(ChefViewListener cl) {
		player_chef_ui.registerChefListener(cl);
	}
	
	public void registerRotationRequestListener(RotationRequestListener rrl) {
		rotation_request_listeners.add(rrl);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("rotate")) {
			for (RotationRequestListener rrl : rotation_request_listeners) {
				rrl.handleRotationRequest();
				
				}
			}

	}
	
	public void setControllerMessage(String message) {
		controllerMessages.setText(message);
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			controllerMessages.setText("");
		}
	}
	
	public void refreshScoreboard() {
		scoreboard.refresh();
	}
}
