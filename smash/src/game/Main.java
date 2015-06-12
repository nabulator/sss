package game;

import ddf.minim.*;
import processing.core.PApplet;

public class Main extends PApplet{

	private Game g;
	private CharacterSelect cs;
	
	public static boolean keysPressed[], keysPressed2[], reset, spacePressed, lockKeys;
	public static boolean MULTIPLAYER = false, HOST = false;
	public static int STAGE_HEIGHT=680, STAGE_WIDTH=960;
	public static Minim m;
	public static AudioPlayer themeSong, csSong;
	public static AudioSample startFX, swipeFX, kirbyFX, punchFX, hit1FX, hit2FX, hit3FX, screamFX, exitFX, jumpFX;
	
	public Main()
	{
		//Object[] opts = {"Local", "NetworkHost", "NetworkClient"};
		//int i = JOptionPane.showOptionDialog(null, "What kind of game?", "Super Smash Sisters", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opts, opts[0]);
		
		cs = new CharacterSelect();
		cs.initStage(this);
		g = new Game();
		g.initStage(this);
		keysPressed = new boolean[5];
		keysPressed2 = new boolean[5];
		
		m = new Minim(this);
		themeSong = m.loadFile("bass.mp3");
		csSong = m.loadFile("coffee.mp3");
		
		startFX = m.loadSample("start.wav");
		swipeFX = m.loadSample("swipe.wav");
		kirbyFX = m.loadSample("kirby.wav");
		punchFX = m.loadSample("punch.wav");
		hit1FX = m.loadSample("pnch1.wav");
		hit2FX = m.loadSample("pnch2.wav");
		hit3FX = m.loadSample("pnch3.wav");
		screamFX = m.loadSample("scream.wav");
		exitFX = m.loadSample("exit.wav");
		jumpFX = m.loadSample("jump.wav");
	}
	
	public void setup()
	{
		frameRate(30);
		size(STAGE_WIDTH, STAGE_HEIGHT);
		g.initChildren();
		cs.initChildren();
		csSong.loop();
	}
	
	private static boolean csOver;
	public void draw()
	{
		clear();
		
			try {
				if( ! csOver && ! MULTIPLAYER )
				{
					cs.runChildren();
					cs.drawChildren();
					csOver = cs.selected();
				}
				else
				{
					g.runChildren();
					g.drawChildren();
					g.k1.color = cs.colors[ cs.c1.number ];
					g.k2.color = cs.colors[ cs.c2.number ];
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
			case 'J': keysPressed2[0] = true; break;
			case 'L': keysPressed2[1] = true; break;
			case (char)222: keysPressed2[2] = true; break; //SINGLE QUOTE
			case ';': keysPressed2[3] = true; break;
			case ']': keysPressed2[4] = true; break;
			
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
			case 'G': keysPressed[2] = false; break;
			case 'F': keysPressed[3] = false; break;
			case 'Y': keysPressed[4] = false; break;
			
			case 'J': keysPressed2[0] = false; break;
			case 'L': keysPressed2[1] = false; break;
			case (char)222: keysPressed2[2] = false; break;  //has comptability issues with my linux
			case ';': keysPressed2[3] = false; break;
			case ']': keysPressed2[4] = false; break;
			
			case 112: reset = false; break;
			
			case ' ': spacePressed = false; break;
			}
		}
	}

}
