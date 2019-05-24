package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import view.GameApp;

public class DealButtonActionListener implements ActionListener{
	private GameEngine gameEngine;
	private GameApp gameApp;
	
	public DealButtonActionListener(GameEngine gameEngine, GameApp gameApp) {
		this.gameEngine = gameEngine;
		this.gameApp = gameApp;
	}

	@Override
	//If the player has no bet or has already been dealt, display an appropriate error message
	//Otherwise the player is dealt and after the player is dealt, check if the condition for dealing the
	//house is met and deal the house if it is met
	public void actionPerformed(ActionEvent e) {
		if(!gameApp.currentPlayerHasDealed()) {
			if(gameApp.getCurrentPlayer().getBet() != 0) {
			gameApp.playerDeal(gameApp.getCurrentPlayer());
			new Thread() {
				@Override
				public void run() {
					gameEngine.dealPlayer(gameApp.getCurrentPlayer(), 1000);
					gameApp.statusUpdate();
					if(gameApp.dealHouse()) {
						gameEngine.dealHouse(1000);
					}
				}
			}.start();
			}
			else {
				JOptionPane.showMessageDialog(gameApp, "Player has to place bet");
			}
			
		}
		else {
			JOptionPane.showMessageDialog(gameApp, "Player has already been dealt");
		}
		
		
	}

}
