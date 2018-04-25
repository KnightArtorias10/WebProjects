package WebServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class clientConnection extends Thread {

	public void run(ServerSocket server) {
		try {
			Socket client = server.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
