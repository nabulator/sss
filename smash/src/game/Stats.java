package game;

public class Stats extends DisplayObject
{
	private Character c;
	private Platform p;
	public Textbox damage;
	
	public Stats(Character c)
	{
		this.c = c;
		
		damage = new Textbox(String.valueOf(c.dmg));
		p = new Platform(60 ,60);
	}
	
	public void init()
	{
		this.add(damage);
		this.add(p);
		//damage.x = 10;
		//damage.y = 20;
	}
	
	public void draw()
	{

	}
}
