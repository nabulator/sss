package basicMath;

public class QuarterCircle {
	
	public float radius, x, y, xside, yside;
	
	public QuarterCircle(float r, float x0, float y0, float xs, float ys)
	{
		radius = r;
		x = x0;
		y = y0;
		xside = xs;
		yside = ys;
	}
	
	public boolean contains(float x1, float y1)
	{
		float xdiff = x1 - x;
		float ydiff = y1 - y;
		return between(x, x1, xside) && between(y, y1, yside) && Math.sqrt( xdiff * xdiff + ydiff * ydiff) <= radius;
	}
	
	public Vector2D getNormalComponent(float x1, float y1)
	{
		return new Vector2D( x1 - x, y1 - y);
	}
	
	/*
	 * returns the horizontal x-distance between given y coordinate on the circle
	 */
	public float horizontalShit(float y1)
	{
		float ydis = this.y - y1;
		System.out.println("sqrt " + (Math.pow(radius, 2) - Math.pow(ydis, 2)));
		return (float) Math.sqrt( radius*radius - ydis * ydis);
	}
	
	private boolean between( float a, float b, float c )
	{
		return ( a >= b && b >= c ) || ( a <= b && b <= c ); 
	}

}
