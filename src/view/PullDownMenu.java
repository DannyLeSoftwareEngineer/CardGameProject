package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PullDownMenu extends JMenuBar{
	
	//Creates the JMenuBar that allows the user to exit the program
	public PullDownMenu() {
	  JMenu fileMenu = new JMenu("File");
	  JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
	  exitItem.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.exit(0);
         }
      });
	  fileMenu.add(exitItem);
	  add(fileMenu);
	}
}
