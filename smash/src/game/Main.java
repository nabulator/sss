package game;

import processing.core.PApplet;

public class Main extends PApplet{

	private Game g;
	private Platform stage;
	
	public void setup()
	{
		size(960, 680);
		g = new Game(this);
	}
	
	public void draw()
	{
		g.draw();
	}

}
