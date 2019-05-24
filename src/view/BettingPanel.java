package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.BettingButtonActionListener;

public class BettingPanel extends JPanel{
	private JLabel betLabel = new JLabel("Place Bet Here:" , SwingConstants.CENTER);
	private JTextField betTextField = new JTextField();
	private JButton betButton = new JButton("Place Bet >");
	
	//Creates the three components related to the betting panel
	public BettingPanel(GameApp gameApp) {
		betTextField.setPreferredSize(new Dimension(150,40));
		betButton.setPreferredSize(new Dimension(150,40));
		betButton.addActionListener(new BettingButtonActionListener(gameApp, this));
		betButton.setEnabled(false);
		Border border = BorderFactory.createRaisedSoftBevelBorder();
		this.setBorder(border);
	      setLayout(new GridLayout(1, 0, 20, 0));
	      add(betLabel);
	      add(betTextField);
	      add(betButton);
	}
	
	
	//If an invalid bet were to be placed such as a null or letters this method will return -1 to indicate an invalid bet,
	//otherwise it will pass the value of a valid bet
	public int getPlayerBet() {
		if (betTextField.getText() != null) {
			if (betTextField.getText().matches("[0-9]+"))
				return Integer.parseInt(betTextField.getText());
		}
		return -1;
	}
	
	public void betButtonState(Boolean state){
		betButton.setEnabled(state);
	}

	public void resetBettingField(){
		betTextField.setText("");
	}
	
}
