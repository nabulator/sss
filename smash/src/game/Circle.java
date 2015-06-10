package game;

import java.awt.Color;

public class Circle extends DisplayObject {
	
	public float radius;
	public Color color;
	
	public Circle(float r0)
	{
		radius = r0;
	}
	
	public void draw()
	{
		fill( color.getRed(), color.getGreen(), color.getBlue());
		circle(0, 0, radius);		
	}
	
	/**
	 * includes tangent points
	 * @param other
	 * @return false if invisible....
	 */
	public boolean isIntersecting( Circle other )
	{
		if( other.visible == false || this.visible == false)
			return false;
		
		float changeX = this.parentX() + this.x- other.parentX() - other.x;
		float changeY = this.parentY() + this.y- other.parentY() - other.y;
		
		return Math.sqrt( changeX * changeX + changeY * changeY ) <= this.radius + other.radius;
	}
}


