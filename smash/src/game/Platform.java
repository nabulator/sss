package game;

import java.awt.Rectangle;

import processing.core.PApplet;

public class Platform extends DisplayObject {
	
	public Rectangle r = new Rectangle();
	
	public Platform(int w, int h)
	{
		r.height = h;
		r.width= w;
	}
	
	public void draw()
	{
		fill(255, 25, 123);
		rect( 0, 0, r.width, r.height);
		
	}
	
	public boolean contains(float x0, float y0)
	{
		return r.contains( x + x0, y + y0);
	}
}
