package game;

import processing.core.PApplet;

public class Game extends DisplayObject {
	
	private Platform fd;
	private Character k1;
	
	public Game()
	{
		fd = new Platform(700, 120);
		k1 = new Character();
	}
	
	public void init()
	{
		//bg(0, 1, 1);
		this.add( fd );
		this.add( k1 );
		
		fd.x = 150;
		fd.y = 500;
	}


	public void run()
	{
		//physics
		//character collusion			
		
		if( k1.y + k1.RADIUS > fd.y ) //under neath top line
		{
			
			if(k1.x > fd.x && k1.x < fd.x + fd.r.width) //in x range
			{
				if( k1.y + k1.RADIUS  < fd.y + 10 )
				{
					k1.dy = 0;
					k1.y = fd.y - k1.RADIUS - 0.2f;
				}
				else 
					k1.dx *= -5;
			}
		}
		
		
		//side platform collision?
		
		
		if( Main.keysPressed[0] )
			k1.moveLeft();
		if( Main.keysPressed[1] )
			k1.moveRight();
		if( Main.keysPressed[2] )
			k1.attack();
			
	}
}
