package game;

import java.awt.Color;

public class Textbox extends DisplayObject
{
	public String text;
	public int size;
	public Color color;
	
	
	public Textbox(String s)
	{
		color = Color.BLACK;
		text = s;
		size = 24;
	}
	
	public void draw()
	{
		fill( color.getRed(), color.getGreen(), color.getBlue());
		textSize(size);
		text(text, 0, 0);
	}
}
