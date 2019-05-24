package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import model.interfaces.Player;
import view.PlayerComboItem;
import view.GameApp;

public class ItemChangeListener implements ItemListener {
	private GameApp gameApp;
	public ItemChangeListener(GameApp gameApp) {
		this.gameApp = gameApp;
	}
	
	@Override
	// When there is a change in item selected in the JCombobox then the selected player
	//is displayed their details in the player user interface panel
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			Player currentPlayer = ((PlayerComboItem) ((JComboBox<PlayerComboItem>) event.getSource()).getSelectedItem()).getValue();
			gameApp.setCurrentPlayer(currentPlayer);
			gameApp.setPlayerNameAndPointsAndBet(currentPlayer);
			gameApp.setPlayerCardandScore(currentPlayer);
			gameApp.enableBetandDealButtons();
			gameApp.resetBetField();
		}

	}
}
