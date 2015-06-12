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
	
	public long hostfc, clientfc;
	
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
		System.out.println("host sending..." + hostfc);
		boolean[] cc = rc.getP1Controls();
		
		pw.println( rc.frameCount );
		for(int j=0; j<cc.length; j++)
			pw.println( cc[j] );
		pw.flush();		
		
	}
	
	/**
	 * 
	 * @return true if compltede operation
	 */
	public boolean getControls()
	{
		if( scan.hasNext() )
		{
			clientfc = Long.parseLong(scan.nextLine());
			
			boolean newData[] = new boolean[5];
			for(int h=0; h<newData.length ; h++ )
				if( scan.hasNext() )
					newData[h] = Boolean.parseBoolean(scan.nextLine());
			rc.setP2Controls(newData);
			System.out.println("Host received! hfc " + hostfc + " cfc" + clientfc);
			return true;
		}
		return false;
			
	}
	

}
