package client;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameApp;
import view.GameEngineCallbackImpl;

public class GameApplication {

	public static void main(String[] args) {
		final GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		SwingUtilities.invokeLater(() -> new GameApp(gameEngine));
	}

}
