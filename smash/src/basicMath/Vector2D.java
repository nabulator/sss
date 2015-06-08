package basicMath;

public final class Vector2D {
	
	public final float x, y;
	
	public Vector2D(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float magnitude()
	{
		return (float)Math.sqrt( x * x + y * y);
	}
	
	public Vector2D direction()
	{
		return new Vector2D( this.x / magnitude(), this.y / magnitude() );
	}
	
	public Vector2D scale(float scalar)
	{
		return new Vector2D( this.x * scalar, this.y * scalar);
	}
}
