package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;

import javax.swing.JPanel;
import javax.swing.border.Border;

import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class PlayerUIPanel extends JPanel{
	private CardPanel cardPanel;
	private PlayerPanel upperPanel;
	private BettingPanel centerPanel;
	
	//Creates the three components related to the player user interface panel
	public PlayerUIPanel(GameApp gameApp) {
		Border border = BorderFactory.createRaisedSoftBevelBorder();
		this.setBorder(border);
		setLayout(new GridLayout(0, 1, 0, 0));
		cardPanel = new CardPanel();
		upperPanel = new PlayerPanel();
		centerPanel = new BettingPanel(gameApp);
	
		cardPanel.setPreferredSize(new Dimension(450,150));
		upperPanel.setPreferredSize(new Dimension(450,150));
		centerPanel.setPreferredSize(new Dimension(450,150));
		
		add(upperPanel);
		add(centerPanel);
		add(cardPanel);
	
	}
	
	public void setPlayerNameAndPointsAndBet(Player Player) {
		upperPanel.setPlayerName(Player);
		upperPanel.setPlayerPoints(Player);
		upperPanel.setPlayerBet(Player.getBet());
	}
	
	public void betButtonState(Boolean Boolean) {
		centerPanel.betButtonState(Boolean);
	}
	
	public void resetBetField() {
		centerPanel.resetBettingField();
	}
	
	public void setPlayerHand(List<PlayingCard> list) {
		cardPanel.replacePanel(list);
	}
	
	public void setPlayerScore(Integer score) {
		cardPanel.setScoreLabel(score);
	}

}
