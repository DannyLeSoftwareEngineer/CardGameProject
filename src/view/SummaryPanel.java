package view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.interfaces.Player;

public class SummaryPanel extends JPanel{
	
	private JLabel summaryLabel = new JLabel("", SwingConstants.LEFT);

	public SummaryPanel() {
		summaryLabel.setVerticalAlignment(SwingConstants.TOP);
		summaryLabel.setPreferredSize(new Dimension(300,500));
		Border border = BorderFactory.createRaisedSoftBevelBorder();
		summaryLabel.setBorder(border);
		add(summaryLabel);
	}
	
	public void setSummaryLabel(Player Player){
		summaryLabel.setText("<html>" + summaryLabel.getText() + "<br>" + Player.toString() + "<html>");
	}

	public void setSummaryLabel(){
		summaryLabel.setText("");
	}

}
