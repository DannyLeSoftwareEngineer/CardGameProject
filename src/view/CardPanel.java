package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.interfaces.PlayingCard;

public class CardPanel extends JPanel{
	private JLabel cardPlayerLabel = new JLabel("Card Label", SwingConstants.CENTER);
	private CardDisplayPanel cardDisplayPanel = new CardDisplayPanel();
	private JLabel scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
	
	//Creates the three components related to the Card panel
	public CardPanel(){
		Border border = BorderFactory.createRaisedSoftBevelBorder();
		this.setBorder(border);
	      setLayout(new GridLayout(1, 0, 20, 0));
	      add(cardPlayerLabel);
	      add(scoreLabel);
	      add(cardDisplayPanel);
	}
	
	//This method removes all the labels in the panel and redraws itself based on the list of playing cards
	public void replacePanel(List<PlayingCard> List) {
		cardDisplayPanel.removeAllLabels();
		cardDisplayPanel.redraw(List);
	}
	
	public void setScoreLabel(int Score) {
		scoreLabel.setText("Score: " + Score);
	}
	
}

