package game;

import processing.core.PApplet;

/**
 * Can be useful
 * @author 167504
 */
public abstract class DisplayObject {
	
	public PApplet p;
	public int x, y;
	
	public DisplayObject(PApplet p)
	{
		this.p = p;
	}
	
	public void draw()
	{
		
	}

}
