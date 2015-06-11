package networking;

import game.Main;

public class RemoteController {
	
	private static Main m;
	public static long frameCount;
	public RemoteController(Main m)
	{
		this.m = m;
	}
	
	public void setP1Controls( boolean[] newControls )
	{
		if( newControls.length != 5)
			throw new IllegalArgumentException("Controls are wrong size!");		
		m.keysPressed = newControls;
	}
	
	public void setP2Controls( boolean[] newControls )
	{
		if( newControls.length != 5)
			throw new IllegalArgumentException("Controls are wrong size!");		
		m.keysPressed2 = newControls;
	}
	
	public boolean[] getP1Controls()
	{
		return m.keysPressed;
	}
	
	public boolean[] getP2Controls()
	{
		return m.keysPressed2;
	}
	
	
}
