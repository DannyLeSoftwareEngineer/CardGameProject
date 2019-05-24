package view;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import controller.AddPlayerButtonActionListener;
import controller.DealButtonActionListener;
import controller.ItemChangeListener;
import model.interfaces.GameEngine;


public class ToolBar extends JToolBar {
	private JButton playerButton = new JButton("Add Player");
	private JButton dealButton = new JButton("Deal Player");
	private JComboBox playerComboBox = new JComboBox();
	private AddPlayerButtonActionListener ButtonActionListener;
	private ItemChangeListener ItemChangeListener;
	private DealButtonActionListener DealButtonActionListener;
	
	//Creates the various components needed in the tool bar such as buttons and combo boxes
	public ToolBar(GameEngine gameEngine, GameApp gameApp, ItemChangeListener ItemChangeListener, DealButtonActionListener DealButtonActionListener) {
		add(Box.createHorizontalGlue());
		playerButton.addActionListener(new AddPlayerButtonActionListener(gameEngine, gameApp, this));
		dealButton.addActionListener(DealButtonActionListener);
		playerComboBox.addItemListener(ItemChangeListener);
		dealButton.setEnabled(false);
		add(playerButton);
		add(playerComboBox);
		add(dealButton);
		add(Box.createHorizontalGlue());
	}

	public int getComboAmount() {
		return playerComboBox.getItemCount();
	}
	public void addComboItem(PlayerComboItem item) {
		playerComboBox.addItem(item);
	}
	
	public void dealButtonState(Boolean state){
		dealButton.setEnabled(state);
	}

}

