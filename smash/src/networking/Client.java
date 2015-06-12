package networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private RemoteController rc;
	private Scanner scan;
	private PrintWriter pw;
	
	public Client() throws UnknownHostException, IOException
	{
		String ip = "192.168.1.15";//"10.5.100.93";
		int port = 16002;
		
		Socket s = new Socket(ip, port);
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
		
		pw.print( rc.frameCount );
		for(int j=0; j<cc.length; j++)
			pw.print( cc[j] );
		pw.flush();
	}
	
	public void getControls()
	{
		long theirCount;
		if( scan.hasNext() )
			theirCount = scan.nextLong();
		
		boolean newData[] = new boolean[5];
		for(int h=0; h<newData.length ; h++ )
			if( scan.hasNext() )
				newData[h] = scan.nextBoolean();
		rc.setP1Controls(newData);
	}
}
