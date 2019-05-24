package view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.PlayingCard;

public class CardDisplayPanel extends JPanel{
	private int CardNo = 0;
	
	public CardDisplayPanel() {
	this.setMinimumSize(new Dimension(150,50));
	}

	//Add card information to the panel in the form of a JLabel 
	public void addCard(PlayingCard Card) {
		CardNo += 1;
	    add(new JLabel(CardNo + ". " + Card.getValue() + " of " + Card.getSuit()));
	}
	
	//Redraws the entire panel with a list of cards when the user switches players
	public void redraw(List<PlayingCard> List) {
		for(PlayingCard card : List) {
			CardNo += 1;
		    add(new JLabel(CardNo + ". " + card.getValue() + " of " + card.getSuit()));
		}
	}
	
	//Resets card number and resets the card panel by removing all labels
	public void removeAllLabels() {
		CardNo = 0;
	    this.removeAll();
		this.revalidate();
		this.repaint();
	}
}
