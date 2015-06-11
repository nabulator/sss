package networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Host {
	
	private ServerSocket ss;
	private Socket client;
	
	public Host() throws IOException
	{
		final int PORT = 16002;
		ss = new ServerSocket(PORT);
		
		client = ss.accept();
		Scanner scan = new Scanner( client.getInputStream() );
		PrintWriter pw = new PrintWriter( client.getOutputStream() );
		
	}

}
