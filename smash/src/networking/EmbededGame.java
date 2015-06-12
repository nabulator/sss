package networking;

import java.awt.BorderLayout;
import java.awt.Frame;

import game.Main;

import javax.swing.JOptionPane;

public class EmbededGame extends Frame{

	public Main m;
	
	/*
	 * THE REAL GAME!
	 */
	public EmbededGame(String ss)
	{
		super("SMASH BALLS| Not a smash ripoff | v 0.2 | " + ss);
		this.setLayout(new BorderLayout());
		m = new Main();
		this.add(m, BorderLayout.CENTER);
		
		m.init();
	}
	

}
