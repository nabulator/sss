package game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import basicMath.QuarterCircle;
import basicMath.Vector2D;
import processing.core.PApplet;

public class Game extends DisplayObject {
	
	private Platform fd;
	private Stats k1Stats, k2Stats;
	private Character k1, k2;	
	private Rectangle boundaries;
	private boolean gameOver = false;
	private int spaceCount;
	
	public final static int P1_INITIAL_X = 300, P2_INITIAL_X = 700;
	
	public void init()
	{
		fd = new Platform(700, 120);
		k1 = new Character( fd, Color.CYAN );
		k2 = new Character( fd, Color.PINK );
		
		k1Stats = new Stats(k1);
		k2Stats = new Stats(k2);
		
		boundaries = new Rectangle(0, 0, Main.STAGE_WIDTH, Main.STAGE_HEIGHT);
		this.add( fd );
		this.add( k1 );
		this.add( k2 );
		this.add(k1Stats);
		this.add(k2Stats);	
		
		fd.x = ( Main.STAGE_WIDTH - fd.width )/ 2.0f;
		fd.y = 500;
		
		k1.x = P1_INITIAL_X;
		k1.y = 300;

		k2.x = P2_INITIAL_X;
		k2.y = 300;
		
		int statsPadding = 140;
		k1Stats.x = 0 + statsPadding;
		k1Stats.y = 530;
		k2Stats.x = Main.STAGE_WIDTH - statsPadding - 140;
		k2Stats.y = 530;
		
		gameOver = false;
		spaceCount = 0;
	}

	/* (non-Javadoc)
	 * @see game.DisplayObject#run()
	 */
	public void run()
	{		
		k1.checkCollision();
		k2.checkCollision();
		
		k1.hitOtherCharacters(k2);
		k2.hitOtherCharacters(k1);
		
		if(!gameOver)
		{
			if( Main.keysPressed[0] )
				k1.moveLeft();
			if( Main.keysPressed[1] )
				k1.moveRight();
			if( Main.keysPressed[2] )
				k1.attack();
			if( Main.keysPressed[4] )
				k1.jump();
			
			if( Main.keysPressed2[0] )
				k2.moveLeft();
			if( Main.keysPressed2[1] )
				k2.moveRight();
			if( Main.keysPressed2[2] )
				k2.attack();
			if( Main.keysPressed2[4] )
				k2.jump();
			
			k1.shield( Main.keysPressed[3] );
			k2.shield( Main.keysPressed2[3] );
		}
		
		//FOR RESET GAME
		if( Main.reset )
		{
			resetCharacter(k1, P1_INITIAL_X);
			resetCharacter(k2, P2_INITIAL_X);
		}
		
		//KO DETECTION
		if(!gameOver)
		{
			Point k1Pos = new Point((int)k1.x, (int)k1.y);
			if(!boundaries.contains(k1Pos))
			{
				k1.stockCount--;
				resetCharacter(k1, P1_INITIAL_X);
				if(k1.stockCount <= 0)
				{ 
					k1.visible = false;
					gameOver(2);
				}
					
			}
			if( !boundaries.contains( new Point((int)k2.x, (int)k2.y)))
			{
				k2.stockCount--;
				resetCharacter(k2, P2_INITIAL_X);
				if(k2.stockCount <= 0)
				{
					k2.visible = false;
					gameOver(1);
				}
			}
		}
		if(gameOver)
		{
			Textbox replay = new Textbox("Press space to play again");
			this.add(replay);
			replay.x = center.x;
			replay.y = center.y + 30;
			
			if(Main.spacePressed)
				spaceCount++;
			
			/**
			 * FIX THIS
			 */
			if(spaceCount > 0)
			{
				this.children.clear();
				
				this.init();
				this.initChildren();
			}
		}
	}
	
	public static void resetCharacter(Character c, int initialX)
	{
		c.init();
		c.x = initialX;
		c.y = 300;
		c.dmg = 0;
	}
	
	public void gameOver(int winner)
	{
		gameOver = true;
		Rectangles rect = new Rectangles();
		rect.width = Main.STAGE_WIDTH;
		rect.height = Main.STAGE_HEIGHT;
		rect.alpha = 100;
		
		this.add(rect);
		
		Textbox win = new Textbox("Winner! Player " + winner);
		win.size = 50;
		this.add(win);
		
		win.x = center.x;
		win.y = center.y;
	}
	
	static final Point center = new Point(Main.STAGE_WIDTH/2, Main.STAGE_HEIGHT/2);
	static float tk;
	public void draw()
	{
		fill(81, 252, 70);
		for( int i=0; i<10; i++)
			for( int j=1 ; j<=24; j++ )
			{
				Color c1 = new Color(j, j*9, j*5);
				Color c2 = new Color(j*8, (int)(j * 0.1), j*7);
				fill( lerp(c1.getRGB(), c2.getRGB(), (float)Math.sin( tk+=0.000001f)));
				rect( (float)(i * 60  * Math.cos(frameCount()/200.0f * j )) + center.x, (float)(i * 60 * Math.sin(frameCount()/200.0f * j)) + center.y, i * 5, i* 5);
			}
	}
}
