package game;

public enum Keys {
	
	LEFT('A', 'A'),
	RIGHT('D', 'E'),
	ATTACK('G', 'I'),
	SHIELD('F', 'U'),
	JUMP('Y', 'F');
	
	private final char dvorak;
	private final char qwerty;
	//private final int type;
	
	Keys(char d, char q)
	{
		this.dvorak = d;
		this.qwerty = q;
	}

	//public static final char[] qwerty = {'A', 'D', 'G', 'F', 'Y', 'J', 'L', (char)222, ';', ']'};
	//public static final char[] dvorak = {'A', 'E', 'I', 'U', 'F', 'H', 'N', '-', 'S', '='};

}
