package game;

import java.awt.Color;

public class CharacterSelect extends DisplayObject
{
	public Character[] select;
	public Color[] colors = {Color.PINK, Color.CYAN, Color.WHITE, Color.GREEN};
	public Color selectedColor, selectedColor2;
	public Cursor c1, c2;
	public Textbox crap;
	
	public CharacterSelect()
	{	
		Platform stage = new Platform(0, 0);
		
		select = new Character[4];
		select[0] = new Character(stage, Color.PINK);
		select[1] = new Character(stage, Color.CYAN);
		select[2] = new Character(stage, Color.WHITE);
		select[3] = new Character(stage, Color.GREEN);
		
		crap = new Textbox("SELECT YOUR CHARACTER!");
		crap.size = 70;
	}
	
	
	public void init()
	{
		//these need the Main reference and must be inited here
		c1 = new Cursor("Player 1", Color.RED, Main.keysPressed);
		c2 = new Cursor("Player 2", Color.BLUE, Main.keysPressed2);
		
		this.add(c1);
		this.add(c2);
		c1.y = Main.STAGE_HEIGHT/2;
		c2.y = Main.STAGE_HEIGHT/2 + 70;
	
		for(int i = 0; i < 4; i++)
		{
			this.add(select[i]);
			select[i].x = (Main.STAGE_WIDTH/4.5f) * i + 150;
			select[i].y = Main.STAGE_HEIGHT / 3 - Character.RADIUS;
			select[i].shieldBox.visible = false;
		}
		
		this.add(crap);
		this.y = 600;
		this.x = Main.STAGE_WIDTH / 2;
		
	}
	
	public void run()
	{
		for(int i = 0; i < 4; i++)
			select[i].dy = 0;
		
		
		c1.x = (Main.STAGE_WIDTH/4.5f) * c1.number+ 150;
		c2.x = (Main.STAGE_WIDTH/4.5f) * c2.number + 150;

	}
	
	public boolean selected() throws InterruptedException
	{
		if(c1.selected && c2.selected)
		{
			Thread.sleep(1000);
			this.removeAll();
			return true;
		}
		else
			return false;
	}

}
