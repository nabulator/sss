package game;

import java.awt.Rectangle;

import basicMath.QuarterCircle;
import processing.core.PApplet;

public class Platform extends DisplayObject {
	
	public int width, height;
	
	public Platform(int w, int h)
	{
		height = h;
		width = w;
	}
	
	public void draw()
	{
		fill(72, 15, 179);
		rect( 0, 0, width, height);
	}
	
	public boolean contains(float x0, float y0)
	{
		System.out.println( this.x + " " + width );
		return x0 >= x && x0 <= x + width && y0 >= y && y0 <= y + height;
	}
}
