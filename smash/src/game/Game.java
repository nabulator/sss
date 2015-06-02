package game;

import processing.core.PApplet;

public class Game {
	
	PApplet p;
	
	public Game(PApplet parent)
	{
		this.p = parent;
	}

	public void draw()
	{
		p.background(0);
	}
}
