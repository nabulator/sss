package game;

public class Character extends DisplayObject
{
	public Circle hurtbox, hitBox;
	public int dmg;
	public boolean attack, special;
	public int attackFrameTimer, specialFrameTimer;
	public float dx, dy;
	public static float gravity = 0.2f,  ddx = 1, frictionDampener = 0.15f;
	
	public final static int RADIUS = 40, MAX_SPEED= 4;
	
	public Character()
	{
		dmg = 0;
		hurtbox = new Circle(0, 0, RADIUS);
		hitBox = new Circle(50, 0, 20);
	}
	
	public void init()
	{
		this.x = 300;
		this.y = 300;
	}
	
	public void run()
	{
		this.x += dx;
		this.y += dy;
		
		//friction
		if( dx > 0.001f || dx < -0.001f)
			dx *= (1.0f - 0.1f);
		
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
		noStroke();
		fill(0, 255, 255);
		circle(0, 0, RADIUS);
		if( attack )
		{
			fill(0, 255, 0);
			circle( , 0, 20);
		}
		
	}
	
}
