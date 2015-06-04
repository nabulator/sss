package game;

import processing.core.PApplet;

public class Main extends PApplet{

	private Game g;
	private Platform stage;
	public static boolean keysPressed[];
	
	public Main()
	{
		g = new Game();
		g.initPApplet(this);
		
		keysPressed = new boolean[4];
	}
	
	public void setup()
	{
		size(960, 680);
		g.initChildren();
		
	}
	
	public void draw()
	{
		clear();
		g.runChildren();
		g.drawChildren();
	}
	
	/**
	 * checks if key is pressed
	 * @param keyCode the keyCode of keyPressed
	 */
	public void keyPressed()
	{
		int keyCode = this.keyCode;
		switch(keyCode)
		{
			case 65: keysPressed[0] = true; break;
			case 68: keysPressed[1] = true; break;
			case 74: keysPressed[2] = true; break;
			case 72: keysPressed[3] = true; break;
		}
	}
	
	/**
	 * checks if key is released
	 * @param keyCode
	 */
	public void keyReleased()
	{
		int keyCode = this.keyCode;
		switch(keyCode)
		{
			case 65: keysPressed[0] = false; break;
			case 68: keysPressed[1] = false; break;	
			case 74: keysPressed[2] = false; break;
			case 72: keysPressed[3] = false; break;
		}
	}

}
