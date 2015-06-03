package game;

import java.util.ArrayList;
import processing.core.PApplet;

/**
 * Can be useful
 * @author 167504
 */
public abstract class DisplayObject {
	
	private PApplet p;
	public float x, y; //Center point! Not upper left corner!!!
	public ArrayList<DisplayObject> children = new ArrayList<DisplayObject>();
	
	//pseudo initializer
	public void add(DisplayObject d)
	{
		d.p = p;
		this.x = 0;
		this.y = 0;
		children.add(d);
	}
	
	public void initPApplet(PApplet p)
	{
		this.p = p;
	}
	
	public void drawChildren()
	{
		//System.out.println("draw + sz " + children.size() );
		
		for( int i=0; i<children.size(); i++)
			children.get(i).drawChildren();
		
		draw();
	
	}
	
	public void draw()
	{
		
	}
	/*
	 * The following methods shoul go in a separate graphics class if desire to preserve
	 * encapsulation and UML diagrams but I'M TOO LAZY
	 * The "graphis" class is really just a wrapper for papplet drawing functionas.
	 * However, you can now draw relative to your x, y position.
	 * This makes sprite movement easier...
	 */
	
	public void rect( int x0, int y0, int width, int height )
	{
		p.rect( x0 + x, y0 + y, width, height);
	}
	
	public void circle( int x0, int y0, int radius )
	{
		p.ellipse( x+x0, y+y0, radius, radius);
	}
	
	public void fill( int rgb )
	{
		p.fill( rgb );
	}
	
	public void fill( int r, int g, int b )
	{
		p.fill(r, g, b);
	}
	
	public void bg( int r, int g, int b )
	{
		p.background(r, g, b);
	}
	
}
