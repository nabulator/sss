package game;

import java.awt.Color;

import basicMath.QuarterCircle;
import basicMath.Vector2D;

public class Character extends DisplayObject
{
	public Circle hurtBox, hitBox, shieldBox, eye;
	public int dmg, stockCount, jumpCount;
	public boolean attack, special, onGround, attackLanded;
	public int attackFrameTimer, specialFrameTimer, shieldFrameCount, jumpTimeout;
	public Color color;

	public float dx, dy;
	public int dir, attackDir;
	
	public final static float GRAVITY = 1.25f,  ddx = 2.5f, FRICTION_DAMP = 0.2f;
	public final static int RADIUS = 30, MAX_HOR_SPEED = 18, MAX_FALL_SPEED = 13;
	
	//outside references
	private Platform fd;
	private QuarterCircle rightQC, leftQC;
	
	//jabAttack specs
	private static int JAB_START_POS = 30;
	private static int SHIELD_MAX = 200;
	private static int JUMP_TIMEOUT = 10;
	
	public Character(Platform pt, Color c)
	{
		dmg = 0;
		stockCount = 4;
		dir = 1;
		hurtBox = new Circle(RADIUS);
		hurtBox.color = c;
		color = c;
		this.fd = pt;
		
		hitBox = new Circle(17);
		hitBox.color = Color.GREEN;
		hitBox.x = JAB_START_POS;
		
		shieldBox = new Circle(RADIUS + 15);
		shieldBox.color = new Color( 0xbc0f5a );
		shieldBox.alpha = 166.0f;
		shieldFrameCount = SHIELD_MAX;
		
		eye = new Circle(5);
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
		
		leftQC = new QuarterCircle( RADIUS, fd.x, fd.y, fd.x - RADIUS, fd.y - RADIUS );
		rightQC = new QuarterCircle( RADIUS, fd.x + fd.width, fd.y, fd.x + fd.width + RADIUS, fd.y - RADIUS);
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
	
	public void checkCollision()
	{
		//physics
		//character collision			
		if( this.y + this.RADIUS >= fd.y ) //under neath top line
		{
			if(this.x > fd.x && this.x < fd.x + fd.width) //in x range
			{
				if( this.y + this.RADIUS  < fd.y + this.MAX_FALL_SPEED + 20 ) //the fall speed offset is to prevent ball from going crazy
				{
					this.dy = 0;
					this.y = fd.y - this.RADIUS;
					this.onGround = true;
					this.jumpCount = 0;
				}
				else 
					this.dx *= -1;
			}
			else
				this.onGround = false;
		}
		
		//special corner case
		if( leftQC.contains( this.x , this.y ) )
		{
			this.x -= leftQC.horizontalShit( this.y ) + this.x - fd.x - 1;
			this.dx = - this.ddx;
		}
		else if ( rightQC.contains(this.x, this.y) )
		{
			this.x += rightQC.horizontalShit( this.y ) - this.x + fd.x + fd.width + 1;
			this.dx = this.ddx;
		}
		
		//side walls
		if( this.y - this.RADIUS > fd.y )
		{
			float leftSide = this.x - this.RADIUS;
			float rightSide = this.x + this.RADIUS;
			if( leftSide < fd.x + fd.width && leftSide > fd.x + fd.width/2 )
				this.dx = this.dx < this.ddx ? this.ddx : -2 * this.dx;
			else if ( rightSide > fd.x && rightSide < fd.x + fd.width/2 )
				this.dx = -3;
		}
	}
	
	public void hitOtherCharacters(Character other)
	{
		if( ! this.hitBox.isIntersecting( other.shieldBox ) )
		{
			if( this.hitBox.isIntersecting( other.hurtBox ) )
				if( ! attackLanded )
				{
					other.dmg += 4 + (int)(Math.random() * 2);
					attackLanded = true;
					//knockback
					Vector2D force = new Vector2D( this.hurtBox.absoluteX() - other.hitBox.absoluteX(), this.hurtBox.absoluteY() - other.hitBox.absoluteY());
					force = force.direction().scale( other.dmg / 5.0f );
					other.dx = - force.x;
					other.dy = Math.abs(force.y);
				}
				
		}
		
		if( this.hurtBox.isIntersecting( other.hurtBox) )
		{
			other.dx += this.dx / 10;
		}
	}
	
	public void attack()
	{
		if( attackFrameTimer == 0 && shieldBox.visible == false)
		{
			attack = true;
			attackFrameTimer = 12;
		}
		
	}
	
	public void shield( boolean isPressed )
	{
		if( isPressed && onGround && attackFrameTimer == 0 )
		{
			if( shieldFrameCount > 0 )
			{
				shieldFrameCount--;
				dx = 0;
			}
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
			dy = -17;
			onGround = false;
			jumpCount++;
			jumpTimeout = JUMP_TIMEOUT;
		}
	}
	
	public void moveLeft()
	{
		if( dx > -MAX_HOR_SPEED && !shieldBox.visible)
			dx -= ddx;
		dir = -1;
	}
	
	public void moveRight()
	{
		if( dx < MAX_HOR_SPEED && !shieldBox.visible)
			dx += ddx;
		dir = 1;
	}
	
	public void draw()
	{
		noStroke();
		hitBox.visible = attackFrameTimer > 0;
		
		if(attackFrameTimer == 0)
		{
			attackDir = dir;
			attackLanded = false;
		}
		
		if( attackFrameTimer >= 9 )
			hitBox.x += 20 * attackDir;
		else if( attackFrameTimer > 0 )
			hitBox.x -= 8*attackDir;
		else
			hitBox.x = JAB_START_POS*attackDir;
		
		
	}
	
}
