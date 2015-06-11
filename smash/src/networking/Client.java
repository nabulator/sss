package networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private Socket s;
	
	public Client() throws UnknownHostException, IOException
	{
		String ip = "10.5.100.93";
		int port = 16002;
		
		Socket s = new Socket(ip, port);
		Scanner scan = new Scanner( s.getInputStream() );
		PrintWriter pw = new PrintWriter( s.getOutputStream() );
		
		System.out.println("connected! ");
	}
}
