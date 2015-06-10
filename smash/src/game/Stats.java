package game;

import java.awt.Color;

public class Stats extends DisplayObject
{
	private Character c;
	private Platform p;
	private Textbox damage;
	private int stocks;
	private final static int TOTALSTOCKS = 4;
	
	public Stats(Character c)
	{
		this.c = c;
		
		damage = new Textbox( "0%" );
		damage.size = 48;
		
		p = new Platform(144 , 120);
		p.alpha = 64;
		p.color = c.color;
		
		stocks = TOTALSTOCKS;
	}
	
	public void init()
	{
		this.add(p);
		this.add(damage);
		damage.x = 75;
		damage.y = 95;
		
		//MAKE SURE THIS IS THE LAST THING ADDED!!!
		
		for( int t=0; t<TOTALSTOCKS; t++ )
		{
			Circle stock = new Circle(12);
			this.add(stock);
			stock.color = c.color;
			stock.x = 25 + 30 * t;
			stock.y = 24;
		}
	}
	
	public void draw()
	{
		damage.text = c.dmg + "%";
		if( c.stockCount < stocks && stocks > 0)
		{
			this.remove( this.totalChildren() - 1 );
			stocks--;
		}
	}
}
