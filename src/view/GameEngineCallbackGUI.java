package view;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
	
	private ViewModel ViewModel;
	public GameEngineCallbackGUI(ViewModel ViewModel) {
		this.ViewModel = ViewModel;
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ViewModel.nextCard(player, card);
			}
		});

	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {		
				ViewModel.bustCard(player, card);
			}
		});
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {		
				ViewModel.result();
			}
		});

	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {		
				ViewModel.nextHouseCard(card);
			}
		});
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {		
				ViewModel.houseBustCard(card);
			}
		});
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {		
				ViewModel.houseResult();
			}
		});

	}

}
