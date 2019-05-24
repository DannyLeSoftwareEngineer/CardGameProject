package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard{
	private Suit suit;
	private Value value;
	
	public PlayingCardImpl(Suit Suit, Value Value) {
	this.suit= Suit;
	this.value= Value;
	}
	
	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public Value getValue() {
		return value;
	}

	@Override
	//Returns the score of the card based on the position of the value in its enum declaration
	//where the score of Ace to Nine is their position in the enum plus one. eg Ace's ordinal is 0 while its score is 1
	//else if the value of their position in the enum is 9 or above its score is 10 such as Ten, Jack, Queen and King.  
	public int getScore() {
		return (value.ordinal() < 9) ? (value.ordinal() + 1) : 10;
	}

	@Override
	   public String toString() {
	return String.format("Suit: %s, Value: %s, Score: %d", suit, value, getScore());
	}
	
	@Override
	public boolean equals(PlayingCard card) {
		if (card == null) {
			return false;}
		if (suit == card.getSuit() && value == card.getValue()) {
			return true;
			}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PlayingCard))
			return false;
		PlayingCard other = (PlayingCard) obj;
		return equals(other);
	}
}
