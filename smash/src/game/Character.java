package game;

import javafx.scene.shape.Circle;

public class Character extends DisplayObject
{
	public Circle hurtbox;
	public int dmg;
	public float dx, dy;
	public static float gravity = 0.1f,  ddx = 1;
	
	public final static int RADIUS = 60, MAX_SPEED= 4;
	
	public Character()
	{
		dmg = 0;
		hurtbox = new Circle(0, 0, RADIUS);
	}
	
	public void init()
	{
		
	}
	
	public void run()
	{
		this.x += dx;
		this.y += dy;
		dy+= gravity;
	}
	
	public void moveLeft()
	{
		if( dx > -MAX_SPEED )
			dx -= ddx;
	}
	
	public void moveRight()
	{
		if( dx < MAX_SPEED )
			dx += ddx;
	}
	
	public void draw()
	{
		fill(0, 255, 255);
		circle(0, 0, RADIUS);
		fill(0, 255, 0);
		circle(50, 0, 20);
	}
	
}
