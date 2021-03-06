package networking;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Closeable{

	private RemoteController rc;
	private Scanner scan;
	private PrintWriter pw;
	private Socket s;
	
	public long hostfc, clientfc;
	
	public Client() throws UnknownHostException, IOException
	{
		String ip = "192.168.1.15";//"10.5.100.93";
		int port = 16002;
		
		s = new Socket(ip, port);
		scan = new Scanner( s.getInputStream() );
		pw = new PrintWriter( s.getOutputStream() );
		
		System.out.println("connected! ");
	}
	
	public void setRC(RemoteController rm)
	{
		rc =rm;
	}
	
	public void sendControls()
	{
		boolean[] cc = rc.getP2Controls();
		
		pw.println( this.clientfc );
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
			hostfc = Long.parseLong(scan.nextLine());

			boolean newData[] = new boolean[5];
			for(int h=0; h<newData.length ; h++ )
				if( scan.hasNext() )
					newData[h] = Boolean.parseBoolean(scan.nextLine());
			rc.setP1Controls(newData);
			//System.out.println("client received!" + hostfc + " cfc" + clientfc);
			return true;
		}
		//System.out.println("client failed to get");
		return false;
			
	}
	
	public void close() throws IOException
	{
		s.close();
	}
}
