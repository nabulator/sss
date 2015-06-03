package game;

import processing.core.PApplet;

public class Platform extends DisplayObject {
	
	public int height, width;
	
	public Platform(int h, int w)
	{
		height = h;
		width = w;
	}
	
	public void draw()
	{
		fill(255, 25, 123);
		rect( 0, 0, height, width);
	}
}
