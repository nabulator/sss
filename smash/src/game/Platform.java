package game;

import processing.core.PApplet;

public class Platform extends DisplayObject {
	
	public int height, width;
	
	public Platform(int w, int h)
	{
		height = h;
		width = w;
	}
	
	public void draw()
	{
		fill(255, 25, 123);
		rect( 0, 0, width, height);
	}

}
