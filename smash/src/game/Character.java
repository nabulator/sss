package game;

import java.awt.Color;

public class Character extends DisplayObject
{
	public Circle hurtBox, hitBox;
	public int dmg;
	public boolean attack, special;
	public int attackFrameTimer, specialFrameTimer;
	public float dx, dy;
	public static float gravity = 0.4f,  ddx = 1, frictionDampener = 0.15f;
	
	public final static int RADIUS = 40, MAX_HOR_SPEED = 4, MAX_FALL_SPEED = 8;
	
	public Character()
	{
		dmg = 0;
		hurtBox = new Circle(RADIUS);
		hurtBox.color = Color.CYAN;
		
		hitBox = new Circle(20);
		hitBox.color = Color.GREEN;

	}
	
	public void init()
	{		
		this.add(hurtBox);
		this.add(hitBox);
		
		hitBox.x = 40;
		System.out.println( hitBox.color );
		System.out.println("addbox");
	}
	
	public void run()
	{
		this.x += dx;
		this.y += dy;
		
		//friction
		if( dx > 0.001f || dx < -0.001f)
			dx *= (1.0f - 0.1f);
		
		if( dy < MAX_FALL_SPEED)
			dy+= gravity;
		
		if( attackFrameTimer > 0)
		{
			attackFrameTimer--;
			attack = false;
		}
	}
	
	public void attack()
	{
		attack = true;
		attackFrameTimer = 30;
	}
	
	public void moveLeft()
	{
		if( dx > -MAX_HOR_SPEED )
			dx -= ddx;
	}
	
	public void moveRight()
	{
		if( dx < MAX_HOR_SPEED )
			dx += ddx;
	}
	
	public void draw()
	{
		noStroke();
		//circle(0, 0, 40);
	}
	
}
