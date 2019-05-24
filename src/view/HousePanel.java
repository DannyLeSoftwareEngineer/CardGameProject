package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.interfaces.PlayingCard;

public class HousePanel extends JPanel{
	private JLabel houseLabel = new JLabel("House Panel", SwingConstants.CENTER);
	private CardDisplayPanel cardDisplayPanel = new CardDisplayPanel();
	private JLabel scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
	
	//Creates the three components related to the house panel
	public HousePanel(){
		cardDisplayPanel.setPreferredSize(new Dimension(150,350));
		Border border = BorderFactory.createRaisedSoftBevelBorder();
		this.setBorder(border);
		houseLabel.setBorder(border);
		cardDisplayPanel.setBorder(border);
		scoreLabel.setBorder(border);
	      setLayout(new GridLayout(1, 0, 0, 0)); 
	      add(houseLabel);
	      add(scoreLabel);
	      add(cardDisplayPanel);
	}
	
	public void addCard(PlayingCard card) {
		cardDisplayPanel.addCard(card);
	}
	
	public void removeAllLabels() {
		cardDisplayPanel.removeAllLabels();
	}
	
	public void setScoreLabel(int houseScore) {
		scoreLabel.setText("Score: " + houseScore);
	}
}

