package game;


import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Can be useful
 * @author 167504
 */

public abstract class DisplayObject {
	
	private PApplet p;
	private DisplayObject parent; //NULL if STAGE
	public float x, y; //Center point! Not upper left corner!!!
	public ArrayList<DisplayObject> children = new ArrayList<DisplayObject>();
	
	//pseudo initializer
	public void add(DisplayObject d)
	{
		d.p = p;
		d.x = 0;
		d.y = 0;
		d.parent = this;
		children.add(d);
	}
	
	public void initStage(PApplet p)
	{
		this.p = p;
		this.parent = null;
	}
	
	public void drawChildren()
	{
		draw();
		for( int i=0; i<children.size(); i++)
			children.get(i).drawChildren();
		
	}
	
	public void initChildren()
	{
		init();
		for( int i=0; i<children.size(); i++)
			children.get(i).initChildren();
	}
	
	public void runChildren()
	{
		run();
		for( int i=0; i<children.size(); i++)
			children.get(i).runChildren();
		
	}
	
	public void init()
	{
		
	}
	
	public void run()
	{
		
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
	
	private float parentsX()
	{
		return parent != null ? parent.x : 0;
	}
	private float parentY()
	{
		return parent != null ? parent.y : 0;
	}
	
	public void rect( float x0, float y0, float width, float height )
	{
		p.rect( parentsX() + x + x0, parentY() + y + y0, width, height);
	}
	
	public void circle( int x0, int y0, int radius )
	{
		p.ellipse( parentsX() + x + x0, parentY() + y + y0, radius * 2, radius * 2);
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
	
	public void noStroke()
	{
		p.noStroke();
	}
	
	public int frameCount()
	{
		return p.frameCount;
	}
	
}
