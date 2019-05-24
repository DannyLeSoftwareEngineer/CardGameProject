package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class ViewModel {
	private int ID = 0;
	private int houseScore = 0;
	private Player currentPlayer;
	private GameApp gameApp;
	
	//Each map contains information about the player which pertains to the view. In order the information stored are
	//a list of cards they current have in their hand in order to correctly redraw a cardDisplayPanel
	//a integer to keep track of the score which can not be derived from a players hand without recalculation as I wanted the score without the bust card's score
	//a boolean to keep track of whether a player has been dealt to
	private Map<Player, List<PlayingCard>> playerCardsDealt = new HashMap<Player, List<PlayingCard>>();
	private Map<Player, Integer> playerScoreMap = new HashMap<Player, Integer>();
	private Map<Player, Boolean> playerDealMap = new HashMap<Player, Boolean>();

	public void playerIsDealt(Player Player) {
		playerDealMap.put(Player, true);
	}
	public Boolean getPlayerDealState(Player Player) {
		return playerDealMap.get(Player);
	}
	
	public void resetPlayerState(Player Player) {
		playerDealMap.put(Player, false);
	}
	
	public ViewModel(GameApp gameApp) {
		this.gameApp = gameApp;
	}

	//Auto-generates and returns a player different player ID 
	public String getPlayerID(){
		ID += 1;
		return Integer.toString(ID);
	}
	
	public List<PlayingCard> getPlayerCards(Player Player){
		return playerCardsDealt.get(Player);
	}
	
	public Integer getPlayerScore(Player Player){
		return playerScoreMap.get(Player);
	}
	
	public void putCardsDealt(Player Player, ArrayList<PlayingCard> list){
		playerCardsDealt.put(Player, list);
	}
	
	//Before a player is re-dealt a player's hand and score are cleared
	public void resetPlayer(Player Player){
		playerScoreMap.put(Player, 0);
		playerCardsDealt.get(Player).clear();
	}
	
	public void storePlayerScore(Player Player, Integer Score){
		playerScoreMap.put(Player, Score);
	}
		
	public void setCurrentPlayer(Player Player) {
		currentPlayer = Player;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	//When a player is dealt a card's score value is added to the player's score and
	//add the card to the player card list. If the current player being dealt is the 
	//player selected, update the cardDisplayPanel
	public void nextCard(Player player, PlayingCard card) {
	    int score = playerScoreMap.get(player);
	    playerScoreMap.put(player, score += card.getScore());
	    playerCardsDealt.get(player).add(card);
		if(currentPlayer.equals(player)) {
		gameApp.nextCard(playerCardsDealt.get(player),playerScoreMap.get(player));
		}
	}
	
	//Provides the same function as nextCard() but does not add the card's score to the 
	//player's score
	public void bustCard(Player player, PlayingCard card) {
		playerCardsDealt.get(player).add(card);
		if(currentPlayer.equals(player)) {
		gameApp.bustCard(playerCardsDealt.get(player));
		}
	}
	
	public void result() {
		gameApp.result();
	}
	
	//Add the card's score to the house score and calls the view to update the house panel
	public void nextHouseCard(PlayingCard card) {
		houseScore += card.getScore();
		gameApp.nextHouseCard(card, houseScore);
	}

	//Add the card's score to the house score 
	public void houseBustCard(PlayingCard card) {
		gameApp.houseBustCard(card);
	}
	
	public void houseResult() {
		gameApp.houseResult(currentPlayer);
	}
	
	public void resetHouseScore() {
		houseScore = 0;
	}
	
}
