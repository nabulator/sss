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
		this.add( fd );
		this.add( k1 );
		
		fd.x = 150;
		fd.y = 500;
		
		k1.x = 300;
		k1.y = 300;
		System.out.println("add k1");
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
					k1.y = fd.y - k1.RADIUS;
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
	
	public void draw()
	{
		fill(123, 63, 233);
		for( int i=0; i<10; i++)
			for( int j=1 ; j<=24; j++ )
				;//rect( (float)(i * 60  * Math.cos(frameCount()/200.0f * j )) + 400, (float)(i * 60 * Math.sin(frameCount()/200.0f * j)) + 350, i * 5, i* 5);
	}
}
