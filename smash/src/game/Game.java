package game;

import processing.core.PApplet;

public class Game extends DisplayObject {
	
	private Platform finalDestination;
	
	public Game()
	{
		finalDestination = new Platform(600, 40);
	}
	
	public void init()
	{
		//bg(0, 1, 1);
		this.add( finalDestination );
		finalDestination.x = 200;
		finalDestination.y = 300;
	}

	double q=0;
	public void run()
	{
		finalDestination.x += Math.sin(q += 0.03);
	}
}
