package game;

import java.awt.Rectangle;
import java.util.Random;

import processing.core.PImage;

public class Plasma extends DisplayObject
{
	private PImage i;
	private int[][] a, morph;
	private int side;
	public double popK, morphK;
	private static Random rand;
	
	public Plasma()
	{
		side = 513;
		popK = 4.01;
		a = new int[side][side];
		morph = new int[side][side];
		rand = new Random(0);
	}
	
	public void init()
	{
		i = createImage(side, side);
		i.loadPixels();
		a[0][0] = 0xad5440;
		a[side-1][0] = 0xefff90;
		a[side-1][side-1] = 0x40bf10;
		a[0][side-1]= 0xefaa3b;
		populate(popK);
		i.updatePixels();
		//rotateSeed(1);
		
		for(int p=0;p<side;p++) for( int q=0;q<side;q++ ) morph[p][q]= rand.nextInt(0xFFFFFF) - 0x777777;
	}
	
	public void warp( double d )
	{
		for(int j=0; j< side; j++)
			for(int k=0; k<side; k++)
				a[j][k] = (int)(j * k * d);
		System.out.println(a[232][51]);
	}
	
	public void rotateSeed( int p )
	{
		if( p > side )
			throw new IllegalArgumentException("rotate out of bounds");
		
		a[0][0] = a[0][p];
		a[side-1][0] = a[side-1-p][0];
		a[side-1][side-1] = a[side-1-p][side-1];
		a[0][side-1] = a[0][side-1-p];
		populate(popK);
		System.out.println(a[56][63]);
	}
	
	public void offsetSeed( int j )
	{
		a[0][0] = (a[0][0] + j) ;
		a[side-1][0] = (a[side-1][0] + j) ;
		a[side-1][side-1] = (a[side-1][side-1] + j) ;
		a[0][side-1] = (a[0][side-1] + j) ;
		populate(popK);
	}
	
	public void populate( double d )
	{
		rand.setSeed(0);
		helpPopulate(new Rectangle(0, 0, side-1, side-1), i, d);
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
		colors[0] = a[ r.x ][ r.y ];
		colors[1] = a[ r.x+r.width][ r.y ];
		colors[2] = a[ r.x+r.width][ r.y+r.height ];
		colors[3] = a[ r.x][ r.y+r.height ];
		
		a[ r.x ][ r.y+r.height/2 ] = (colors[0] + colors[3])/2;
		a[ r.x+r.width/2 ][ r.y+r.height ] = (colors[3] + colors[2])/2;
		a[ r.x+r.width ][ r.y+r.height/2 ] = (colors[2] + colors[1])/2;
		a[ r.x+r.width/2 ][ r.y ] = (colors[0] + colors[1])/2;
		a[  r.x+r.width/2 ][ r.y+r.height/2 ] = bound(0, (colors[0]+colors[1]+colors[2]+colors[3] + (int)(rand.nextGaussian() * 0xffffff + morph[r.x][r.y] * morphK))/4, 0xffffff);
		
		helpPopulate( new Rectangle(r.x, r.y, r.width/2, r.height/2), i, k/2);
		helpPopulate( new Rectangle(r.x+r.width/2, r.y, r.width/2, r.height/2), i, k/2);
		helpPopulate( new Rectangle(r.x+r.width/2, r.y+r.height/2, r.width/2, r.height/2), i, k/2);
		helpPopulate( new Rectangle(r.x, r.y+r.height/2, r.width/2, r.height/2), i, k/2);
	}
	
	public void setMorph(double d)
	{
		morphK = d;
	}
		
	public void draw()
	{
		i.loadPixels();
		for(int g=0; g<side; g++)
			for(int j=0; j<side; j++)
				i.pixels[g+j*side] = this.lerp(0xDFE7CF, 0x1269BB, bound(0, a[g][j], 0xFFFFFF) / (float) 0xFFFFFF);
		i.updatePixels();
		image(i);
	}
	
	private int bound(int a, int b, int c)
	{
		if( b < a )
			return a;
		else if( b > c )
			return c;
		else
			return b;
	}

}
