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
	private Character k1, k2;
	
	private Rectangle boundaries;
	
	public Game()
	{
		fd = new Platform(700, 120);
		k1 = new Character( fd, Color.CYAN );
		k2 = new Character( fd, Color.PINK );
		
		boundaries = new Rectangle(0, 0, Main.STAGE_WIDTH, Main.STAGE_HEIGHT);
	}
	
	public void init()
	{
		this.add( fd );
		this.add( k1 );
		this.add( k2 );
		
		fd.x = 150;
		fd.y = 500;
		
		k1.x = 300;
		k1.y = 300;
		
		k2.x = 700;
		k2.y = 300;
	}

	/* (non-Javadoc)
	 * @see game.DisplayObject#run()
	 */
	public void run()
	{
		k1.checkCollision();
		k2.checkCollision();
		
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
		
		//FOR RESET GAME
		if( Main.reset )
		{
			resetCharacter(k1);
			resetCharacter(k2);
		}
		
		//KO DETECTION
		Point k1Pos = new Point((int)k1.x, (int)k1.y);
		if(!boundaries.contains(k1Pos))
		{
			resetCharacter(k1);
		}
		if( !boundaries.contains( new Point((int)k2.x, (int)k2.y)))
			resetCharacter(k2);
			
	}
	
	public static void resetCharacter(Character c)
	{
		c.init();
		c.x = 200;
		c.y = 300;
	}
	
	public void draw()
	{
		fill(81, 252, 70);
		for( int i=0; i<10; i++)
			for( int j=1 ; j<=24; j++ )
				;//rect( (float)(i * 60  * Math.cos(frameCount()/200.0f * j )) + 400, (float)(i * 60 * Math.sin(frameCount()/200.0f * j)) + 350, i * 5, i* 5);
	}
	//asdf
}
