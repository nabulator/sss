package networking;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import game.Main;

import javax.swing.JOptionPane;

public class NetworkMain {

	public static void main(String[] args) {
	
		Object[] opts = {"Local", "NetworkHost", "NetworkClient"};
		
		int v = JOptionPane.showOptionDialog(null, "What kind of game?", "Super Smash Sisters", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opts, opts[0]);
		switch(v)
		{
			case 2:
				//host
				break;
			case 3:
				//client 
				break;
			case 1:
			default:
				//local
		}
		
		final EmbededGame eg = new EmbededGame();
		eg.setSize(Main.STAGE_WIDTH, Main.STAGE_HEIGHT+32);
		eg.setVisible(true);
		eg.setResizable(false);
	
		eg.addWindowListener( new WindowAdapter()
			{
				public void windowClosing( WindowEvent e)
				{
					eg.m.exit();
					//System.out.println("Exit Game");
				}
			}
		);
		

	}

}
