package networking;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Host implements Closeable{
	
	private Socket client;
	private ServerSocket ss;
	private RemoteController rc; //YOU MUST SET THIS!!!!
	private Scanner scan;
	private PrintWriter pw;
	
	public long hostfc, clientfc;
	
	public Host() throws IOException
	{
		final int PORT = 16002;
		ss = new ServerSocket(PORT);

		client = ss.accept();
		ss.close();
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
		
		pw.println( this.hostfc );
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
			//System.out.println("Host received! hfc " + hostfc + " cfc" + clientfc);
			return true;
		}
		return false;
			
	}
	
	public void close() throws IOException
	{
		client.close();
	}

}
