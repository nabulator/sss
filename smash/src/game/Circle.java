package game;

import java.awt.Color;

public class Circle extends DisplayObject {
	
	public int radius;
	public Color color;
	
	public Circle(int r0)
	{
		radius = r0;
	}
	
	public void draw()
	{
		fill( color.getRed(), color.getGreen(), color.getBlue());
		circle(0, 0, radius);
	}
}
