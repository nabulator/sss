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
	public boolean visible = true;
	public float alpha = 255.0f;
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
	
	public void remove(int index)
	{
		children.remove(index);
	}
	
	public int totalChildren()
	{
		return children.size();
	}
	
	public void initStage(PApplet p)
	{
		this.p = p;
		this.parent = null;
	}
	
	public void drawChildren()
	{
		if( this.visible )
		{
			draw();
			for( int i=0; i<children.size(); i++)
				children.get(i).drawChildren();
		}
	
		
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
	
	private float parentX()
	{
		return parent != null ? parent.x : 0;
	}
	private float parentY()
	{
		return parent != null ? parent.y : 0;
	}
	
	//GETS ABSOLUTE VALUES RELATIVE TO STAGE
	protected float absoluteX()
	{
		DisplayObject d = this;
		float finalX = this.x;
		while( d.parent != null )
		{
			finalX += d.parent.x;
			d = d.parent;
		}
		return finalX;
	}
	protected float absoluteY()
	{
		DisplayObject d = this;
		float finalY = this.y;
		while( d.parent != null )
		{
			finalY += d.parent.y;
			d = d.parent;
		}
		return finalY;
	}
	
	public void rect( float x0, float y0, float width, float height )
	{
		p.rect( parentX() + x + x0, parentY() + y + y0, width, height);
	}
	
	public void circle( int x0, int y0, float radius )
	{
		p.ellipse( parentX() + x + x0, parentY() + y + y0, radius * 2, radius * 2);
	}
	
	public void textSize(float size)
	{
		p.textSize(size);
	}
	
	public void text(String text, float x0, float y0)
	{
		p.textAlign( p.CENTER );
		p.text(text, parentX() + x + x0, parentY() + y + y0);
	}
	
	public void fill( int rgb )
	{
		p.fill( rgb );
	}
	
	public void fill( int r, int g, int b )
	{
		p.fill(r, g, b, this.alpha);
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
