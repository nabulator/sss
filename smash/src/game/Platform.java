package game;

import processing.core.PApplet;

public class Platform extends DisplayObject {
	
	public int height, width;
	
	public Platform(PApplet p)
	{
		super(p);
	}
	
	public void draw()
	{
		p.color(200);
		p.rect(10, 20, 20, 20);
		
	}
}
