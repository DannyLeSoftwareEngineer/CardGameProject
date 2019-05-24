package model;

import model.interfaces.Player;

public class SimplePlayer implements Player {
	private String playerName;
	private String playerId;
	private int points;
	private int result;
	private int bet;
	
	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = initialPoints;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String getPlayerId() {
		return playerId;
	}

	@Override
	//Places a bet but ensures the bet is legal by fulfilling the condition that the bet cannot be less than zero
	//or the players points cannot be lower than their bet
	public boolean placeBet(int bet) {
		if(bet < 0 || points < bet) {
			return false;
		}
		else {
			this.bet = bet;
			return true;
			}
	}

	@Override
	public int getBet() {
		return bet;
	}

	@Override
	public void resetBet() {
		this.bet = 0;
	}

	@Override
	public int getResult() {
		return result;
	}

	@Override
	public void setResult(int result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return String.format("Player: id=%s, name:%s, points=%s", playerId, playerName, points);
	}

}
