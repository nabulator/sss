package networking;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import game.Main;

import javax.swing.JOptionPane;

public class NetworkMain {

	public static final int TARGET_MS = 1000 / 30;
	private static EmbededGame eg;
	
	public static void main(String[] args) throws InterruptedException, IOException {
	
		Object[] opts = {"Local", "NetworkHost (P1)", "NetworkClient (P2)"};
		RemoteController rc;
		
		int v = JOptionPane.showOptionDialog(null, "What kind of game?", "Super Smash Sisters", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opts, opts[0]);
		switch(v)
		{
			case 1: //host
				Host host = new Host();
				eg = initEG( opts[v].toString() );
				eg.m.MULTIPLAYER = true;
				eg.m.HOST = true;
				rc = new RemoteController( eg.m );
				host.setRC(rc);

				boolean gotData = false;
				while(true)
				{
					eg.m.redraw();
					host.hostfc++;
					host.sendControls();
					while(gotData)
						gotData = host.getControls();
					System.out.print("Client Controls: ");
					//printCont( rc.getP1Controls() );
					printCont( rc.getP2Controls() );
					Thread.sleep(TARGET_MS);
				}
			case 2: //client 
				Client client = new Client();
				eg = initEG( opts[v].toString() );
				eg.m.MULTIPLAYER = true;
				eg.m.HOST = false;
				rc = new RemoteController( eg.m );
				client.setRC(rc);
				
				boolean done = false;
				while(true)
				{
					while(done)
						done = client.getControls();
					client.sendControls();
					eg.m.redraw();
					client.clientfc++;
					System.out.print("Host Controls: ");
					printCont( rc.getP1Controls() );
					Thread.sleep(TARGET_MS);
				}
			case 0:
			default:
				eg = initEG( opts[v].toString() );
				//local
				
		}
		

		
	}
	
	private final static EmbededGame initEG(String title)
	{
		//init game
		final EmbededGame eg = new EmbededGame( title );
		eg.setSize(Main.STAGE_WIDTH, Main.STAGE_HEIGHT+32);
		eg.setVisible(true);
		eg.setResizable(false);
	
		eg.addWindowListener( new WindowAdapter()
			{
				public void windowClosing( WindowEvent e)
				{
					eg.m.exit();
					//System.out.println("Exit Game");
				}
			}
		);
		
		return eg;
	}
	
	private static String[] names = {"left", "right", "attack", "shield", "jump"};
	private static void printCont( boolean[] b )
	{
		for(int i=0 ; i<b.length ; i++)
			System.out.print( names[i] + ": " + (b[i] == true? "1" : "0") + "\t");
		System.out.println();
	}

}
