package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameApp;
import view.ToolBar;
import view.PlayerComboItem;

public class AddPlayerButtonActionListener implements ActionListener {
	private GameEngine gameEngine;
	private GameApp gameApp;
	private ToolBar toolBar;
	
	public AddPlayerButtonActionListener(GameEngine gameEngine, GameApp gameApp, ToolBar toolBar) {
		this.gameEngine = gameEngine;
		this.gameApp = gameApp;
		this.toolBar = toolBar;
	}

	@Override
	//Calls for the creation of a player, if the player returned is not null then add the player name and player object to the combo box,
	//add the player to the game engine and update the summary panel.
	//If this is the first player to be added into the game, update the player user interface, as the item change listener,
	//the controller responsible for updating the player panel cannot detect a item change with only one player and therefore
	//this player panel update with have to be done manually.
	public void actionPerformed(ActionEvent event) {
		Player Player = gameApp.CreatePlayer();
		if(Player != null) {
			toolBar.addComboItem(new PlayerComboItem(Player.getPlayerName(), Player));
			gameEngine.addPlayer(Player);
			gameApp.UpdateSummaryPanel();
			if(toolBar.getComboAmount() == 1) {
				gameApp.setCurrentPlayer(Player);
				gameApp.setPlayerNameAndPointsAndBet(Player);
				gameApp.setPlayerCardandScore(Player);
				gameApp.enableBetandDealButtons();
			}
		}
	}
}
