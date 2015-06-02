package game;

import processing.core.PApplet;

public abstract class DisplayObject {
	
	public PApplet p;
	public int x, y;
	
	public DisplayObject(PApplet p)
	{
		this.p = p;
	}
	
	public void draw()
	{
		
	}

}
