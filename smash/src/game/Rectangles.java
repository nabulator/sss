package game;

import java.awt.Color;

public class Rectangles extends DisplayObject 
{
	public Color color = Color.BLACK;
	public int width, height;
	
	public void draw()
	{
		fill( color.getRed(), color.getGreen(), color.getBlue());
		rect(0, 0, width, height);		
	}
}
