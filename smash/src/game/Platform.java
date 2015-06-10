package game;

import java.awt.Color;
import java.awt.Rectangle;

import basicMath.QuarterCircle;
import processing.core.PApplet;

public class Platform extends DisplayObject {
	
	public int width, height;
	public Color color;
	
	public Platform(int w, int h)
	{
		height = h;
		width = w;
		color = new Color(75, 15, 179);
	}
	
	public void draw()
	{
		fill(color.getRed(), color.getGreen(), color.getBlue());
		rect( 0, 0, width, height);
	}
	
	public boolean contains(float x0, float y0)
	{
		System.out.println( this.x + " " + width );
		return x0 >= x && x0 <= x + width && y0 >= y && y0 <= y + height;
	}
}
