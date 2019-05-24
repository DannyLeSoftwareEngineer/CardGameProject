package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.interfaces.Player;

public class StatusBar extends JPanel{
	private JLabel statusBarLabel;
	
	public StatusBar()
	{
		statusBarLabel = new JLabel("No one is being dealt", SwingConstants.LEFT);
		Border border = BorderFactory.createRaisedBevelBorder();
		statusBarLabel.setBorder(border);
		setLayout(new GridLayout(1, 0));	
		add(statusBarLabel);	
	}
	
	//Updates the status bar's text depending on who is being dealt
	public void update(Player Player) {
		statusBarLabel.setText(Player.getPlayerName() + " is being dealt");
	}
	public void update() {
		statusBarLabel.setText("No one is being dealt");
	}
	public void houseUpdate() {
		statusBarLabel.setText("The house is being dealt");
	}
	
}
