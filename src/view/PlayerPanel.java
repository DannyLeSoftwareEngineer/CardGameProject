package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.interfaces.Player;

public class PlayerPanel extends JPanel{
	private JLabel playerName = new JLabel("Player Name", SwingConstants.CENTER);
	private JLabel playerPoints = new JLabel("Player Points", SwingConstants.CENTER);
	private JLabel playerBet= new JLabel("Player Bet", SwingConstants.CENTER);
	
	//Creates the three components related to the player panel
	public PlayerPanel() {
		Border border = BorderFactory.createRaisedSoftBevelBorder();
		this.setBorder(border);
	      setLayout(new GridLayout(1, 0, 20, 0));
	      add(playerName);
	      add(playerPoints);
	      add(playerBet);
	}
	
	public void setPlayerName(Player player) {
		this.playerName.setText("Player Name: " + player.getPlayerName());
	}
	
	public void setPlayerPoints(Player player) {
		this.playerPoints.setText("Player Points: " + player.getPoints());
	}

	public void setPlayerBet(int Bet) {
		this.playerBet.setText("Player Bet: " + Bet);
	}
}
