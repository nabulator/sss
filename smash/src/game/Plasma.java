package game;

import java.awt.Rectangle;
import java.util.Random;

import processing.core.PImage;

public class Plasma extends DisplayObject
{
	private PImage i;
	private int side;
	private static Random rand;
	
	public Plasma()
	{
		side = 513;
		rand = new Random(0);
	}
	
	public void init()
	{
		i = createImage(side, side);
		i.loadPixels();
		warp(0.0001);
		i.pixels[0] = 0xff0000;
		i.pixels[side-1] = 0xffff00;
		i.pixels[side*side-1] = 0x00FF00;
		i.pixels[side*side - side -1] = 0xFF00FF;
		populate(0.01);
		i.updatePixels();
	}
	
	public void warp( double d )
	{
		for(int j=0; j< side*side; j++)
			i.pixels[j] = (char)(j * d);
		i.updatePixels();
	}
	
	public void populate( double d )
	{
		helpPopulate(new Rectangle(0, 0, side-1, side-1), i, d);
		i.updatePixels();
	}
	
	public void rotateSeed( int j )
	{
		i.pixels[0] += j;
		i.pixels[side-1] +=j;
		i.pixels[side*side-1] += j;
		i.pixels[side*side - side -1] += j;
		rand.setSeed(0);
		populate(0.01);
		i.updatePixels();
	}
	
	private void helpPopulate(Rectangle r, PImage i, double k)
	{
		if( r.width <= 1 || r.height <= 1 )
			return;
		
		/*
		 * C0	C1
		 * 
		 *   
		 * C3	C2
		 */
		//System.out.println(r);
		int colors[] = new int[4];
		colors[0] = i.pixels[ findPixel(r.x, r.y) ];
		colors[1] = i.pixels[ findPixel(r.x+r.width, r.y) ];
		colors[2] = i.pixels[ findPixel(r.x+r.width, r.y+r.height) ];
		colors[3] = i.pixels[ findPixel(r.x, r.y+r.height) ];
		
		i.pixels[ findPixel(r.x, r.y+r.height/2) ] = (colors[0] + colors[3])/2;
		i.pixels[ findPixel(r.x+r.width/2, r.y+r.height) ] = (colors[3] + colors[2])/2;
		i.pixels[ findPixel(r.x+r.width, r.y+r.height/2) ] = (colors[2] + colors[1])/2;
		i.pixels[ findPixel(r.x+r.width/2, r.y) ] = (colors[0] + colors[1])/2;
		i.pixels[ findPixel( r.x+r.width/2, r.y+r.height/2) ] = (colors[0]+colors[1]+colors[2]+colors[3] - (int)(rand.nextGaussian() * 0xffffff * k))/4;
		
		helpPopulate( new Rectangle(r.x, r.y, r.width/2, r.height/2), i, k/2);
		helpPopulate( new Rectangle(r.x+r.width/2, r.y, r.width/2, r.height/2), i, k/2);
		helpPopulate( new Rectangle(r.x+r.width/2, r.y+r.height/2, r.width/2, r.height/2), i, k/2);
		helpPopulate( new Rectangle(r.x, r.y+r.height/2, r.width/2, r.height/2), i, k/2);
	}
	
	private int findPixel(int x, int y)
	{
		return x + y * side;
	}
	
	public void draw()
	{
		image(i);
	}
}
