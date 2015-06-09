package game;

import java.awt.Color;

public class Character extends DisplayObject
{
	public Circle hurtBox, hitBox, shieldBox, eye;
	public int dmg;
	public boolean attack, special, onGround;
	public int attackFrameTimer, specialFrameTimer, shieldFrameCount;

	public float dx, dy;
	public int dir, attackDir;
	
	public final static float GRAVITY = 0.55f,  ddx = 1.5f, FRICTION_DAMP = 0.2f;
	public final static int RADIUS = 40, MAX_HOR_SPEED = 12, MAX_FALL_SPEED = 10;
	
	//jabAttack specs
	private static int JAB_START_POS = 30;
	private static int SHIELD_MAX = 200;
	private static int JUMP_TIMEOUT = 10;
	
	public Character()
	{
		dmg = 0;
		dir = 1;
		hurtBox = new Circle(RADIUS);
		hurtBox.color = Color.CYAN;
		
		hitBox = new Circle(20);
		hitBox.color = Color.GREEN;
		hitBox.x = JAB_START_POS;
		
		shieldBox = new Circle(RADIUS + 10);
		shieldBox.color = new Color( 0xbc0f5a );
		shieldBox.alpha = 166.0f;
		shieldFrameCount = SHIELD_MAX;
		
		eye = new Circle(10);
		eye.color = Color.BLACK;
	
	}
	
	public void init()
	{		
		this.dx = 0;
		this.dy = 0;
		this.onGround = false;
				
		this.add(hurtBox);
		this.add(eye);
		this.add(hitBox);
		this.add(shieldBox);
		
		eye.x = 20;
		eye.y = -10;
	}
	
	public void run()
	{
		this.x += dx;
		this.y += dy;
		
		eye.x = 20*dir;
		
		//friction
		if( dx > 0.001f || dx < -0.001f)
			dx *= (1.0f - FRICTION_DAMP);
		
		if( dy < MAX_FALL_SPEED && !onGround)
			dy+= GRAVITY;
		
		if( attackFrameTimer > 0)
		{
			attackFrameTimer--;
			attack = false;
		}
		
		if(jumpTimeout > 0)
			jumpTimeout--;
		
	}
	
	public void attack()
	{
		if( attackFrameTimer == 0 && shieldBox.visible == false)
		{
			attack = true;
			attackFrameTimer = 15;
		}
		
	}
	
	public void shield( boolean isPressed )
	{
		if( isPressed && attackFrameTimer == 0 )
		{
			if( shieldFrameCount > 0 )
				shieldFrameCount--;
			else
				;//;STUNNNED
			shieldBox.visible = true;
		}
		else
		{
			if( shieldFrameCount < SHIELD_MAX )
				shieldFrameCount++;
			shieldBox.visible = false;
		}
		
		shieldBox.radius = (RADIUS+10) * (shieldFrameCount / (float)SHIELD_MAX);
	}
	
	public void jump()
	{
		if( jumpTimeout == 0 && jumpCount <= 1 )
		{
			dy = -10;
			onGround = false;
			jumpCount++;
			jumpTimeout = JUMP_TIMEOUT;
		}
	}
	
	public void moveLeft()
	{
		if( dx > -MAX_HOR_SPEED )
			dx -= ddx;
		dir = -1;
	}
	
	public void moveRight()
	{
		if( dx < MAX_HOR_SPEED )
			dx += ddx;
		dir = 1;
	}
	
	public void draw()
	{
		noStroke();
		hitBox.visible = attackFrameTimer > 0;
		
		if(attackFrameTimer == 0)
			attackDir = dir;
		
		if( attackFrameTimer >= 12 )
			hitBox.x += 15 * attackDir;
		else if( attackFrameTimer > 0 )
			hitBox.x -= 5*attackDir;
		else
			hitBox.x = JAB_START_POS*attackDir;
		
		
	}
	
}
