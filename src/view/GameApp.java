package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.DealButtonActionListener;
import controller.ItemChangeListener;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class GameApp extends JFrame{

	private GameEngine gameEngine;
	private ToolBar toolBar;
	private StatusBar statusBar;
	private HousePanel housePanel;
	private SummaryPanel summaryPanel;
	private PlayerUIPanel WestPanel;
	private PullDownMenu PullDownMenu;
	private final ViewModel ViewModel;
	
	public GameApp(GameEngine gameEngine) {
		//Creates and Lays out all the subcomponents of the view of the game application
		super("Card Game");
		this.gameEngine = gameEngine;
		ViewModel = new ViewModel(this);
		gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(ViewModel));
		statusBar = new StatusBar();
		summaryPanel = new SummaryPanel();
		housePanel = new HousePanel();
		WestPanel = new PlayerUIPanel(this);
		toolBar = new ToolBar(gameEngine, this ,new ItemChangeListener(this), new DealButtonActionListener(gameEngine, this));
		PullDownMenu = new PullDownMenu();
		setJMenuBar(PullDownMenu);

		add(toolBar, BorderLayout.NORTH);
		add(statusBar, BorderLayout.SOUTH);
		add(summaryPanel, BorderLayout.EAST);
		add(housePanel, BorderLayout.CENTER);
		add(WestPanel, BorderLayout.WEST);
		
		pack();
		setMinimumSize(getSize());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void setCurrentPlayer(Player Player) {
		ViewModel.setCurrentPlayer(Player);
	}
	
	public Player getCurrentPlayer() {
		return ViewModel.getCurrentPlayer();
	}
	
	public void setPlayerNameAndPointsAndBet(Player Player) {
		WestPanel.setPlayerNameAndPointsAndBet(Player);
	}	
	
	public void setPlayerCardandScore(Player player) {
		WestPanel.setPlayerHand(ViewModel.getPlayerCards(player));
		WestPanel.setPlayerScore(ViewModel.getPlayerScore(player));
	}
	
	public void UpdateSummaryPanel() {
		summaryPanel.setSummaryLabel();
		for (Player player : gameEngine.getAllPlayers()) {
			summaryPanel.setSummaryLabel(player);
		}
	}
	
	//This method asks the users to input player information, if the player information is valid then it will return the player
	//and add the player to the various maps in the ViewModel else if the user does not input data it will return null
	public Player CreatePlayer(){
		String playerName;
		playerName = JOptionPane.showInputDialog("Player Name");
		String points;
		points = JOptionPane.showInputDialog("Points");
		if (points != null) {
			while (!points.matches("[0-9]+")) {
				points = JOptionPane.showInputDialog("Please enter a number for points");
			}
		}
		if (playerName != null && points != null) {
			Player player = new SimplePlayer(ViewModel.getPlayerID(), playerName, Integer.parseInt(points));
			ViewModel.resetPlayerState(player);
			ViewModel.putCardsDealt(player, new ArrayList<PlayingCard>());
			ViewModel.storePlayerScore(player, 0);
			return player;
		}
		return null;
		}
	
	//As the player is going to be dealt, Update the status bar, disable the deal and deal buttons and clears the player's
	//old scores and cards
	public void playerDeal(Player Player) {
		statusBar.update(Player);
		WestPanel.betButtonState(false);
		toolBar.dealButtonState(false);
		ViewModel.playerIsDealt(Player);
		ViewModel.resetPlayer(Player);
	}
	
	public boolean currentPlayerHasDealed() {
		return ViewModel.getPlayerDealState(ViewModel.getCurrentPlayer());
	}
	
	//Set the states of the deal and betting button depending on whether if the player has been dealt
	public void enableBetandDealButtons() {
		if(currentPlayerHasDealed()) {
			WestPanel.betButtonState(false);
			toolBar.dealButtonState(false);
		}
		else {
			WestPanel.betButtonState(true);
			toolBar.dealButtonState(true);
		}
	}
	
	public void resetBetField() {
		WestPanel.resetBetField();
	}
	
	//Updates subcomponents when a player is being dealt and is the selected player
	public void nextCard(List<PlayingCard> list, Integer score) {
	    WestPanel.setPlayerHand(list);
		WestPanel.setPlayerScore(score);
	}
		
	public void bustCard(List<PlayingCard> list) {
		 WestPanel.setPlayerHand(list);
	}

	public void result() {
		enableBetandDealButtons(); 
	}
	
	public void statusUpdate() {
		statusBar.update();
	}
	
	public void nextHouseCard(PlayingCard card, Integer houseScore) {
		housePanel.addCard(card);
		housePanel.setScoreLabel(houseScore);
	}

	public void houseBustCard(PlayingCard card) {
		housePanel.addCard(card);
	}
	
	//When the round ends update the current player panel, status bar, summary panel and reset the player in preparation for the next round 
	public void houseResult(Player Player) {
		setPlayerNameAndPointsAndBet(Player);
		statusBar.update();
		UpdateSummaryPanel();
		for(Player player : gameEngine.getAllPlayers()) {
			ViewModel.resetPlayerState(player);
			player.setResult(0);
		}
		enableBetandDealButtons();
	}
	
	//This method is responsible for checking whether the House should be dealt by first ensuring that all players have placed a bet and has been dealt to
	//with the exception to the edge case where a player has zero points. In which case the game will be played without them and they do not use their previous result
	//from the last round as those would have been reset to zero and will not gain points as their bet would have been reset to zero as zero as well
	//If the house is to be dealt it will return true as well as remove the JLabels from the housePanel,
	//reset the house score and update the status bar.
	public Boolean dealHouse() {
		for (Player player : gameEngine.getAllPlayers()) {
			if ((!(ViewModel.getPlayerDealState(player) && player.getBet() != 0)) && player.getPoints() != 0) {
				return false;
			}
		}
		housePanel.removeAllLabels();
		ViewModel.resetHouseScore();
		statusBar.houseUpdate(); 
		return true;
	}

}
