package game;

import processing.core.PApplet;

public class Game extends DisplayObject {
	
	private Platform fd;
	private Character k1;
	
	public Game()
	{
		fd = new Platform(900, 40);
		k1 = new Character();
	}
	
	public void init()
	{
		//bg(0, 1, 1);
		this.add( fd );
		this.add( k1 );
		
		fd.x = 00;
		fd.y = 600;
	}


	public void run()
	{
		//physics
		//bouncing
		if( k1.y + k1.RADIUS > fd.y )
		{
			k1.dy = 0;
			k1.y = fd.y - k1.RADIUS - 1;
		}
		
		if( Main.keysPressed[0] )
			k1.moveLeft();
		if( Main.keysPressed[1] )
			k1.moveRight();
			
	}
}
