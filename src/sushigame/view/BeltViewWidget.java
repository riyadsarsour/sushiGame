package sushigame.view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import comp401.sushi.*;

public class BeltViewWidget extends JPanel implements ActionListener{
	
	private JLabel chef;
	private JLabel chefInfo;
	private JLabel _color;
	private JLabel colorInfo;
	private JLabel sushiType;
	private JLabel sushiInfo;
	private JLabel age;
	private JLabel ageInfo;
	private JLabel _Ingredients;
	private JButton rollIngredients;
	
	private String _ingredientList;
	

	public BeltViewWidget(){
		
		setLayout(new GridLayout(2,5));
		
		chef = new JLabel("Chef");
		chef.setOpaque(true);
		chefInfo = new JLabel("Chef Information");
		chefInfo.setOpaque(true);
		
		_color = new JLabel("Color");
		_color.setOpaque(true);		
		colorInfo = new JLabel("Color Information");
		colorInfo.setOpaque(true);
		
		sushiInfo = new JLabel("Sushi Information");
		sushiInfo.setOpaque(true);		
		sushiType = new JLabel("Sushi Type");
		sushiType.setOpaque(true);
		
		age = new JLabel("Age");
		age.setOpaque(true);		
		ageInfo = new JLabel("Age Information");
		ageInfo.setOpaque(true);
		
		_Ingredients = new JLabel("Ingredients");
		_Ingredients.setOpaque(true);		
		rollIngredients = new JButton("Roll Ingredients");
		rollIngredients.setOpaque(true);
		rollIngredients.addActionListener(this);
		
		
		add(chef);
		add(_color);
		add(sushiType);
		add(age);
		add(_Ingredients);
		
		add(chefInfo);	
		add(colorInfo);	
		add(sushiInfo);
		add(ageInfo);	
		add(rollIngredients);
	}
		
		
	public void update(Plate p, int plateAge){
		
		if (p == null){
			
			chefInfo.setText("");
			colorInfo.setText("");
			sushiInfo.setText("");
			ageInfo.setText("");
			_Ingredients.setText("");
			rollIngredients.setActionCommand("");
			colorInfo.setBackground(Color.GRAY);
						
		}else{
		
		age.setText("Age");
		ageInfo.setText("" + plateAge);
		
		chef.setText("Chef");
		chefInfo.setText(p.getChef().getName());
		
		_color.setText("Color");
		
		switch (p.getColor()) {
		case RED:
			colorInfo.setText("RED");
			colorInfo.setBackground(Color.RED); break;
		case GREEN:
			colorInfo.setText("GREEN");
			colorInfo.setBackground(Color.GREEN); break;
		case BLUE:
			colorInfo.setText("BLUE");
			colorInfo.setBackground(Color.BLUE); break;
		case GOLD:
			colorInfo.setText("GOLD");
			colorInfo.setBackground(Color.YELLOW); break;
		
		}
		
				
		sushiType.setText("Sushi Type");
	
		
		
		switch (p.getContents().getSushiType()){
		case NIGIRI:
			sushiInfo.setText("Nigiri");
			rollIngredients.setText(p.getContents().getName());
			rollIngredients.setActionCommand("");
			break;
			
		case SASHIMI: 
			sushiInfo.setText("Nigiri");
			rollIngredients.setText(p.getContents().getName());
			rollIngredients.setActionCommand("");
			break;
			
		case ROLL: 
			sushiInfo.setText("Roll");
			rollIngredients.setText(p.getContents().getName());
			rollIngredients.setActionCommand("rollIngredients");
			
			break;
		
		}
		
		
		JButton roll_ingredients_button = new JButton("Roll Ingredients ");
		roll_ingredients_button.setActionCommand("rollIngredients");
		roll_ingredients_button.addActionListener(this);
		
		IngredientPortion[] ingList = p.getContents().getIngredients();
		_ingredientList = "";
		for(int i = 0; i < p.getContents().getIngredients().length; i ++){
			
			String oneIngredient = ingList[i].getName();
			
			_ingredientList += oneIngredient + ": " + ingList[i].getAmount() + "    \n";
			
					
				}
			}
	
		}
	
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand().equals("rollIngredients")){
		
				JFrame frame = new JFrame("Ingredient List");		    
		        JLabel IngredientLabel = new JLabel();
		        IngredientLabel.setText(_ingredientList  + '\n');
		        
		        frame.getContentPane().add(IngredientLabel, BorderLayout.CENTER);
		        frame.pack();
		        frame.setVisible(true);
			}
			
			
			
		}
	
}
