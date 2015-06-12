package game;

import java.awt.Color;

public class CharacterSelect extends DisplayObject
{
	public int cursorNum;
	public Textbox cursor;
	public Character[] select;
	public Color selectedColor;
	public int selectTimeout;
	
	public void init()
	{
		cursorNum = 0;
		cursor = new Textbox("SELECT");
		this.add(cursor);
		cursor.color = Color.RED;
		cursor.y = Main.STAGE_HEIGHT/2;
		
		Platform stage = new Platform(0, 0);
		
		select = new Character[4];
		select[0] = new Character(stage, Color.PINK);
		select[1] = new Character(stage, Color.CYAN);
		select[2] = new Character(stage, Color.WHITE);
		select[3] = new Character(stage, Color.GREEN);
		
		for(int i = 0; i < 4; i++)
		{
			this.add(select[i]);
			select[i].x = (Main.STAGE_WIDTH/4) * i + 80;
			select[i].y = Main.STAGE_HEIGHT / 4 - Character.RADIUS;
			select[i].shieldBox.visible = false;
		}
		
	}
	
	public void run()
	{
		for(int i = 0; i < 4; i++)
			select[i].dy = 0;
		
		cursor.x = (Main.STAGE_WIDTH/4) * cursorNum + 80;
		
		if(selectTimeout > 0)
			selectTimeout--;
		
		if(selectTimeout == 0 && selectedColor == null)
		{
			selectTimeout = 15;
			if( Main.keysPressed[0] )
				cursorLeft();
			else if( Main.keysPressed[1] )
				cursorRight();
			else if( Main.keysPressed[2] )
			{
				selectedColor = select[cursorNum].color;
				cursor.color = Color.BLUE;
			}
			else
				selectTimeout = 0;
		}
	}
	
	public void cursorLeft()
	{
		cursorNum--;
		if(cursorNum < 0)
			cursorNum = 3;
	}
	
	public void cursorRight()
	{
		cursorNum++;
		if(cursorNum > 3)
			cursorNum = 0;
	}
	
}
