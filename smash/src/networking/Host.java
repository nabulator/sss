package networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Host {
	

	private Socket client;
	private RemoteController rc; //YOU MUST SET THIS!!!!
	private Scanner scan;
	private PrintWriter pw;
	
	public Host() throws IOException
	{
		final int PORT = 16002;
		ServerSocket ss = new ServerSocket(PORT);
		
		client = ss.accept();
		scan = new Scanner( client.getInputStream() );
		pw = new PrintWriter( client.getOutputStream() );
	}
	
	public void setRC(RemoteController rm)
	{
		rc =rm;
	}
	
	public void sendControls()
	{
		boolean[] cc = rc.getP1Controls();
		
		pw.print( rc.frameCount );
		for(int j=0; j<cc.length; j++)
			pw.print( cc[j] );
		pw.flush();
	}
	
	public void getControls()
	{
		long theirCount = scan.nextLong();
		boolean newData[] = new boolean[5];
		for(int h=0; h<newData.length ; h++ )
			newData[h] = scan.nextBoolean();
		rc.setP2Controls(newData);
	}
	

}
