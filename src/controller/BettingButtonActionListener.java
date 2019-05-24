package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.BettingPanel;
import view.GameApp;

public class BettingButtonActionListener implements ActionListener{
	private GameApp gameApp;
	private BettingPanel bettingPanel;
	
	public BettingButtonActionListener(GameApp gameApp, BettingPanel bettingPanel) {
		this.gameApp = gameApp;
		this.bettingPanel = bettingPanel;
	}
	
	@Override
	//If the text in the betting panel is valid and the player has enough points to place the bet
	//then update the player panel, else display an error message
	public void actionPerformed(ActionEvent event) {
		if(gameApp.getCurrentPlayer().placeBet(bettingPanel.getPlayerBet())) {
			gameApp.setPlayerNameAndPointsAndBet(gameApp.getCurrentPlayer());
		}
		else {
			JOptionPane.showMessageDialog(gameApp, "Invalid Bet");
		}
		
	}

}
