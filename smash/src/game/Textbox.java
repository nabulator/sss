package game;

public class Textbox extends DisplayObject
{
	public String text;
	public int size;
	
	
	public Textbox(String s)
	{
		text = s;
		size = 32;
	}
	
	public void draw()
	{
		fill(255);
		textSize(size);
		text(text, 0, 0);
	}
}
