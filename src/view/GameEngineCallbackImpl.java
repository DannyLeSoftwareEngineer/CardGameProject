package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackImpl implements GameEngineCallback
{
   private final Logger logger = Logger.getLogger(this.getClass().getName());

   public GameEngineCallbackImpl()
   {
      logger.setLevel(Level.FINE);
   }

   @Override
   public void nextCard(Player player, PlayingCard card, GameEngine engine)
   {
      logger.log(Level.FINE,  String.format("Card Dealt to %s .. %s", player.getPlayerName(), card.toString()));
   }

   @Override
   public void result(Player player, int result, GameEngine engine)
   {
      logger.log(Level.INFO, String.format("%s, final result=%s", player.getPlayerName(), result));
   }
   
   @Override
   public void bustCard(Player player, PlayingCard card, GameEngine engine) {
	    logger.log(Level.FINE, String.format("Card Dealt to %s .. %s ... YOU BUSTED!", player.getPlayerName(), card.toString())); 
   }

   @Override
   public void nextHouseCard(PlayingCard card, GameEngine engine) {
	   logger.log(Level.FINE, String.format("Card Dealt to House .. %s", card.toString()));
   }

   @Override
   public void houseBustCard(PlayingCard card, GameEngine engine) {
	   logger.log(Level.FINE, String.format("Card Dealt to House .. %s ... HOUSE BUSTED!", card.toString()));
   }

   @Override
   //Logs both house result and final result
   //Where the string representing the players final result is constructed using the StringBuffer
   //and calling the toString method on all the players and appending the string produced
   //to the final string that is logged
   public void houseResult(int result, GameEngine engine) {
	   logger.log(Level.INFO, String.format("House, final result=%s", result));
	   
	   StringBuffer resultsString = new StringBuffer();
	   resultsString.append("Final Player Results \n");
	   for(Player player : engine.getAllPlayers()) {
		   resultsString.append(player.toString());
		   resultsString.append("\n");
	   }
	   logger.log(Level.INFO, resultsString.toString());
   }
}
