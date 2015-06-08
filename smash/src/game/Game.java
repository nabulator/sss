package game;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import basicMath.QuarterCircle;
import basicMath.Vector2D;
import processing.core.PApplet;

public class Game extends DisplayObject {
	
	private Platform fd;
	private Character k1;
	
	private Rectangle2D.Float rightCorner, leftCorner;
	private QuarterCircle rightQC, leftQC;
	
	public Game()
	{
		fd = new Platform(700, 120);
		k1 = new Character();

	}
	
	public void init()
	{
		this.add( fd );
		this.add( k1 );
		
		fd.x = 150;
		fd.y = 500;
		
		k1.x = 300;
		k1.y = 300;

		leftQC = new QuarterCircle(k1.RADIUS, fd.x, fd.y, fd.x - k1.RADIUS, fd.y - k1.RADIUS );
		rightQC = new QuarterCircle(k1.RADIUS, fd.x + fd.width, fd.y, fd.x + fd.width + k1.RADIUS, fd.y - k1.RADIUS);
	}

	/* (non-Javadoc)
	 * @see game.DisplayObject#run()
	 */
	public void run()
	{
		//physics
		//character collision			
		if( k1.y + k1.RADIUS >= fd.y ) //under neath top line
		{
			if(k1.x > fd.x && k1.x < fd.x + fd.width) //in x range
			{
				if( k1.y + k1.RADIUS  < fd.y + k1.MAX_FALL_SPEED ) //the fall speed offset is to prevent ball from going crazy
				{
					k1.dy = 0;
					k1.y = fd.y - k1.RADIUS;
					k1.onGround = true;
					k1.jumpCount = 0;
				}
				else 
					k1.dx *= -1;
			}
			else
				k1.onGround = false;
		}
		
		//special corner case
		if( leftQC.contains( k1.x , k1.y ) )
		{
			k1.x -= leftQC.horizontalShit( k1.y ) + k1.x - fd.x - 1;
			k1.dx = - k1.ddx;
		}
		else if ( rightQC.contains(k1.x, k1.y) )
		{
			k1.x += rightQC.horizontalShit( k1.y ) - k1.x + fd.x + fd.width + 1;
			k1.dx = k1.ddx;
		}
		
		//side wallsi
		if( k1.y - k1.RADIUS > fd.y )
		{
			float leftSide = k1.x - k1.RADIUS;
			float rightSide = k1.x + k1.RADIUS;
			if( leftSide < fd.x + fd.width && leftSide > fd.x + fd.width/2 )
				k1.dx = k1.dx < k1.ddx ? k1.ddx : -2 * k1.dx;
			else if ( rightSide > fd.x && rightSide < fd.x + fd.width/2 )
				k1.dx = -3;
		}
		
		if( Main.keysPressed[0] )
			k1.moveLeft();
		if( Main.keysPressed[1] )
			k1.moveRight();
		if( Main.keysPressed[2] )
			k1.attack();
		if( Main.keysPressed[4] )
			k1.jump();
		
		k1.shield( Main.keysPressed[3] );
		
		//FOR RESET GAME
		if( Main.reset )
		{
			k1.init();
			k1.x = 200;
			k1.y = 300;
		}
			
			
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
