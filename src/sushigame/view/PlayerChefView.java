package sushigame.view;

import comp401.sushi.*;
import comp401.sushi.Nigiri.NigiriType;
import comp401.sushi.Sashimi.SashimiType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.*;

@SuppressWarnings("serial")
public class PlayerChefView extends JPanel implements ActionListener{

	private JComboBox<Plate.Color> plateColor;
	private JComboBox <NigiriType> nigiriType;
	private JComboBox <SashimiType> sashimiType;
	private JTextField goldPrice;
	private IngredientSlider avocadoSlider;
	private IngredientSlider crabSlider;
	private IngredientSlider eelSlider;
	private IngredientSlider riceSlider;
	private IngredientSlider salmonSlider;
	private IngredientSlider seaweedSlider;
	private IngredientSlider shrimpSlider;
	private IngredientSlider tunaSlider;
	private PositionSlider positionSlider;
	private JButton _makeRoll;
	private JButton _makeSashimi;
	private JButton _makeNigiri;
	private int belt_size;
	private List<ChefViewListener> listeners;
	private Sushi _sushi;

	private ArrayList<ChefViewListener> _listeners;

	public PlayerChefView(int belt_size ) {

			this.belt_size = belt_size;
			_listeners = new ArrayList<ChefViewListener>();

	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



		setLayout(new GridLayout(0,2));

		plateColor = new JComboBox<Plate.Color>(new Plate.Color[]
				{Plate.Color.BLUE, Plate.Color.GOLD, Plate.Color.GREEN,
				Plate.Color.RED});

		nigiriType = new JComboBox<NigiriType>(new NigiriType[]
				{NigiriType.EEL, NigiriType.CRAB, NigiriType.SALMON,
						NigiriType.SHRIMP, NigiriType.TUNA});


		sashimiType = new JComboBox<SashimiType>(new SashimiType[]
				{SashimiType.EEL, SashimiType.CRAB, SashimiType.SALMON,
					SashimiType.SHRIMP, SashimiType.TUNA});
		
		
		add(new JLabel("                                              Plate Color: "));
		add(plateColor);
		
		goldPrice = new JTextField(5);
		add(new JLabel("                                              Gold Price: $"));
		add(goldPrice);
		
		positionSlider = new PositionSlider();
		add(new JLabel("                                              Position "));
		add(positionSlider);
		
		avocadoSlider = new IngredientSlider();
		add(new JLabel("                                              Avocado: "));
		add(avocadoSlider);
		
		crabSlider = new IngredientSlider();
		add(new JLabel("                                              Crab: "));
		add(crabSlider);
		
		eelSlider = new IngredientSlider();
		add(new JLabel("                                              Eel: "));
		add(eelSlider);
		
		riceSlider = new IngredientSlider();
		add(new JLabel("                                              Rice: "));
		add(riceSlider);
		
		salmonSlider = new IngredientSlider();
		add(new JLabel("                                              Salmon: "));
		add(salmonSlider);
		
		seaweedSlider = new IngredientSlider();
		add(new JLabel("                                              Seaweed: "));
		add(seaweedSlider);
		
		shrimpSlider = new IngredientSlider();
		add(new JLabel("                                              Shrimp: "));
		add(shrimpSlider);
		
		tunaSlider = new IngredientSlider();
		add(new JLabel("                                              Tuna: "));
		add(tunaSlider);
		
		_makeRoll = new JButton(" Make Roll");
		add(_makeRoll);
		_makeRoll.addActionListener(this);
		_makeRoll.setActionCommand("makeRoll");
		
		_makeNigiri = new JButton ("Make Nigiri");
		add(_makeNigiri);
		_makeNigiri.addActionListener(this);
		_makeNigiri.setActionCommand("makeNigiri");
		
		_makeSashimi = new JButton("Make Sashimi");
		add (_makeSashimi);
		_makeSashimi.addActionListener(this);
		_makeSashimi.setActionCommand("makeSashimi");
		
		add(nigiriType);
		nigiriType.setActionCommand("nigiriComboBoxChanged");
		add(sashimiType);
		sashimiType.setActionCommand("sashimiComboBoxChanged");
		
		
	}

	public Plate.Color getPlateColor() {
		return (Plate.Color) plateColor.getSelectedItem();
	}

	// Since we used a text field to get the price of the gold plate,
	public Double getGoldPrice() {
		if ((Double.parseDouble(goldPrice.getText()) >= 5.0 &&
				(Double.parseDouble(goldPrice.getText()) <= 10.0))) {

			return (Double.parseDouble(goldPrice.getText()));
		}

		JOptionPane.showMessageDialog(goldPrice, "Out of range. Range is between $5 -$10");
		return 0.0;
	}

	// We need getter methods to fetch the values of the sliders.
	// by 10 to return the amount in ounces.
	public double getAvocado() {
		return ((double) avocadoSlider.getValue()) / 10.0;
	}

	public double getCrab() {
		return ((double) crabSlider.getValue()) / 10.0;
	}

	public double getEel() {
		return ((double) eelSlider.getValue()) / 10.0;
	}

	public double getRice() {
		return ((double) riceSlider.getValue()) / 10.0;
	}

	public double getSalmon() {
		return ((double) salmonSlider.getValue()) / 10.0;
	}

	public double getSeaweed() {
		return ((double) seaweedSlider.getValue()) / 10.0;
	}

	public double getShrimp() {
		return ((double) shrimpSlider.getValue()) / 10.0;
	}

	public double getTuna() {
		return ((double) tunaSlider.getValue()) / 10.0;
	}

	public void registerChefListener(ChefViewListener cl) {
		_listeners.add(cl);
		
	}
	
	public Sushi getSashimi(){
		
		SashimiType sashimi =  (SashimiType)sashimiType.getSelectedItem();
		_sushi = new Sashimi(sashimi);
		
		return _sushi;	
	}
	
	public Sushi getNigiri(){
        NigiriType nigiri = (NigiriType)nigiriType.getSelectedItem();
        _sushi = new Nigiri(nigiri);
       
        return _sushi;
	}
	
	public Sushi getRoll(){
	   ArrayList<IngredientPortion> addIngredientPortion = new ArrayList<IngredientPortion>();
		
		if(getAvocado() != 0.0){
			addIngredientPortion.add(new AvocadoPortion(getAvocado()));
		}
		if(getCrab() != 0.0){
			addIngredientPortion.add(new CrabPortion(getCrab()));
		}
		if(getEel() != 0.0){
			addIngredientPortion.add(new EelPortion(getEel()));
		}
		if(getRice() != 0.0){
			addIngredientPortion.add(new RicePortion(getRice()));
		}
		if(getSalmon() != 0.0){
			addIngredientPortion.add(new SalmonPortion(getSalmon()));
		}
		if (getSeaweed() != 0.0){
			addIngredientPortion.add(new SeaweedPortion(getSeaweed()));
		}
		if (getShrimp() != 0.0){
			addIngredientPortion.add(new ShrimpPortion(getShrimp()));
		}
		if (getTuna() != 0.0){
			addIngredientPortion.add(new TunaPortion(getTuna()));
		}
		
		IngredientPortion[] ingredientList = addIngredientPortion.toArray
				(new IngredientPortion [addIngredientPortion.size()]);
		
		_sushi = new Roll("roll", ingredientList);
		
		return _sushi;
	}
	
	public void chooseColor(Sushi f){
		if(getPlateColor() == Plate.Color.RED){
			makeRedPlateRequest(f, positionSlider.getValue());
		}
		if(getPlateColor() == Plate.Color.BLUE){
			makeBluePlateRequest(f, positionSlider.getValue());
		}
		if(getPlateColor() == Plate.Color.GREEN){
			makeGreenPlateRequest(f, positionSlider.getValue());
		}
		if(getPlateColor() == Plate.Color.GOLD){
			makeGoldPlateRequest(f,positionSlider.getValue(), getGoldPrice());
		}
	}
	


	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("makeNigiri")){
			
			chooseColor(this.getNigiri());	
		}
		if (e.getActionCommand().equals("makeSashimi")){
			
			chooseColor(this.getSashimi());
		}
		if (e.getActionCommand().equals("makeRoll")){
			
			chooseColor(this.getRoll());
		}
		
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : _listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : _listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : _listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}
	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : _listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}
}

