package game;

import java.awt.Color;

public class Cursor extends DisplayObject
{
	public int number;
	public Textbox text;
	public int timeout;
	public boolean selected;
	public boolean controller[];
	
	public Cursor(String t, Color c, boolean[] cont)
	{
		number = 0;
		text = new Textbox(t);
		text.color = c;
		text.size = 30;
		controller = cont;
	}
	
	public void init()
	{
		this.add(text);
	}
	
	public void run()
	{
		if(timeout > 0)
			timeout--;
		
		if(timeout == 0 && ! selected )
		{
			timeout = 5;
			if( controller[0] )
				cursorLeft();
			else if( controller[1] )
				cursorRight();
			else if( controller[2] )
			{
				Main.kirbyFX.trigger();
				selected = true;
				text.color = Color.GRAY;
			}
			else
				timeout = 0;
		}
	}
	
	public void cursorLeft()
	{
		Main.swipeFX.trigger();
		number--;
		if(number < 0)
			number = 3;
	}
	
	public void cursorRight()
	{
		Main.swipeFX.trigger();
		number++;
		if(number > 3)
			number = 0;
	}
	
}
