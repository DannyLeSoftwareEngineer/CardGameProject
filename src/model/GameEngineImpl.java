package model;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
	private Map<String, Player> playerMap = new HashMap<String, Player>();
	private List<GameEngineCallback> callbacksList = new ArrayList<GameEngineCallback>();
	private LinkedList<PlayingCard> listDeck = new LinkedList<PlayingCard>();


	public GameEngineImpl() {
		getShuffledDeck();
	}
	
	@Override
	//The deal player method functions by looping until the player get a score of 21 or is about to bust
	//If the player score when added with the next card is 21 or less the players the cards score will be added to the player's score and the card will be logged
	//Else if the card will bust the player the score will not be added and it breaks out of the loop
	//The player result will be stored and logged.
	public void dealPlayer(Player player, int delay) {
		int playerScore = 0;	
		while(playerScore < GameEngine.BUST_LEVEL){			
			
			delayCardDealing(delay);
			PlayingCard nextCard = checkDeckandDeal();
			
			if(playerScore + nextCard.getScore() <= GameEngine.BUST_LEVEL) {
				for(GameEngineCallback Callbacks : callbacksList) {
					Callbacks.nextCard(player, nextCard, this);
				}	
				playerScore += nextCard.getScore();
			}
			else if(playerScore + nextCard.getScore() > GameEngine.BUST_LEVEL) {
				for(GameEngineCallback Callbacks : callbacksList) {
					Callbacks.bustCard(player, nextCard, this);
				}	
				break;
			}	
		}	
		for(GameEngineCallback Callbacks : callbacksList) {
			Callbacks.result(player, playerScore, this);
		}	
		player.setResult(playerScore);
	}

	@Override
	//Processes the dealing exactly like the dealPlayer methods however it logs with different methods
	//and it needs to handle bets based on the results and reset bets
	public void dealHouse(int delay) {
		int housePoints = 0;
		while(housePoints < GameEngine.BUST_LEVEL){			
		
			delayCardDealing(delay);
			PlayingCard nextCard = checkDeckandDeal();
			
			if(housePoints + nextCard.getScore() <= GameEngine.BUST_LEVEL) {
				for(GameEngineCallback Callbacks : callbacksList) {
					Callbacks.nextHouseCard(nextCard, this);
				}
				housePoints += nextCard.getScore();
			}
			else if(housePoints + nextCard.getScore() > GameEngine.BUST_LEVEL) {
				for(GameEngineCallback Callbacks : callbacksList) {
					Callbacks.houseBustCard(nextCard, this);
				}
				break;
			}
		}
		pointsDistribution(housePoints);
		for(Player player : playerMap.values()){
			player.resetBet();
		}
		for(GameEngineCallback Callbacks : callbacksList) {
			Callbacks.houseResult(housePoints, this);
		}
	}

	
	@Override
	public void addPlayer(Player player) {
		playerMap.put(player.getPlayerId(), player);
	}

	@Override
	public Player getPlayer(String id) {
			return playerMap.get(id);
	}

	@Override
	public boolean removePlayer(Player player) {
		return playerMap.keySet().remove(player.getPlayerId());
	
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callbacksList.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return callbacksList.remove(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(playerMap.values());
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.placeBet(bet);
	}

	@Override
	//This method creates a new shuffled deck by ensuring the deck is first empty
	//Adding all 52 cards with a nested loop in a particular order and then calling the shuffle method to rearrange the cards in a random order
	public Deque<PlayingCard> getShuffledDeck() {		
		listDeck.clear();
		for(int i = 0; i < PlayingCard.Value.values().length ; i++) {
			for(int k = 0; k < PlayingCard.Suit.values().length; k++) {
				listDeck.add(new PlayingCardImpl(PlayingCard.Suit.values()[k],PlayingCard.Value.values()[i]));
			}
		}
		Collections.shuffle(listDeck);
		return listDeck;
	}
	
	//This methods uses the parameter house points and compares it to each player's result that round
	//If the house wins and therefore has a greater point score, the player loses their bet
	//If the house loses and therefore has a lower point score, the player wins their bet
	//A draws results in nothing happening
	private void pointsDistribution(int housePoints) {
		for(Player Player : playerMap.values()){
			if(Player.getResult() > housePoints){
				Player.setPoints(Player.getPoints() + Player.getBet());
			}
			else if(Player.getResult() < housePoints){
				Player.setPoints(Player.getPoints() - Player.getBet());
			}
		}
	}
	
	//This methods removes the top card of the deck and returns the card but  
	// it also first ensures that the deck is not empty, else 
	//it will create a new shuffled deck
	private PlayingCard checkDeckandDeal() {
		if(listDeck.isEmpty()) {
			getShuffledDeck();
		}
		return listDeck.pop();
	}
	
	//Delays the thread by causing it to sleep 
	private void delayCardDealing(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}