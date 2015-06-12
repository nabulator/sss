package game;

import javax.swing.JOptionPane;

import processing.core.PApplet;

public class Main extends PApplet{

	private Game g;
	private Platform stage;
	public static boolean keysPressed[], keysPressed2[], reset, spacePressed, lockKeys;
	public static int STAGE_HEIGHT=680, STAGE_WIDTH=960;
	public static boolean MULTIPLAYER = false, HOST = false;
	
	public Main()
	{		
		super();
		g = new Game();
		g.initStage(this);
		
		keysPressed = new boolean[5];
		keysPressed2 = new boolean[5];
	}
	
	public void setup()
	{
		frameRate(30);
		size(STAGE_WIDTH, STAGE_HEIGHT);
		g.initChildren();
		
		if(MULTIPLAYER)
			this.noLoop();
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
		char keyCode = (char)this.keyCode;
		
		lockKeys = true;
		if( (MULTIPLAYER && HOST) || ! MULTIPLAYER )
		{
			switch(keyCode)
			{
			
			case 'A': keysPressed[0] = true; break;
			case 'D': keysPressed[1] = true; break;
			case 'G': keysPressed[2] = true; break;
			case 'F': keysPressed[3] = true; break;
			case 'Y': keysPressed[4] = true; break;
			}
		}
		
		if( (MULTIPLAYER && ! HOST) || ! MULTIPLAYER )
		{
			switch(keyCode)
			{
			case 'H': keysPressed2[0] = true; break;
			case 'N': keysPressed2[1] = true; break;
			case  '-': keysPressed2[2] = true; break; //SINGLE QUOTE
			case 'S': keysPressed2[3] = true; break;
			case '=': keysPressed2[4] = true; break;
			
			
			}

		}
		
		switch(keyCode)
		{
			case ' ': spacePressed = true; break;
			case 112: reset = true; break;
		}	
		lockKeys = false;
	}
	
	/**
	 * checks if key is released
	 * @param keyCode
	 */
	public void keyReleased()
	{
		char keyCode = (char)this.keyCode;
		if(!lockKeys)
		{
			switch(keyCode)
			{
				case 'A': keysPressed[0] = false; break;
				case 'D': keysPressed[1] = false; break;	
				case 'J': keysPressed[2] = false; break;
				case 'F': keysPressed[3] = false; break;
				case 'Y': keysPressed[4] = false; break;
		
				case 'H': keysPressed2[0] = false; break;
				case 'N': keysPressed2[1] = false; break;
				case '-': keysPressed2[2] = false; break;  //has comptability issues with my linux
				case 'S': keysPressed2[3] = false; break;
				case '=': keysPressed2[4] = false; break;
				
				case ' ': spacePressed = false; break; 
				case 112: reset = false; break;
			}
		}
		
	}

}
