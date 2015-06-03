package game;

import processing.core.PApplet;

public class Main extends PApplet{

	private Game g;
	private Platform stage;
	
	public Main()
	{
		g = new Game();
		g.initPApplet(this);
	}
	
	public void setup()
	{
		size(960, 680);
		g.init();
		
	}
	
	public void draw()
	{
		clear();
		g.run();
		g.drawChildren();
	}

}
