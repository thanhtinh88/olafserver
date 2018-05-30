package olaf.model;

import java.io.IOException;
import java.net.*;

public class ServerManager implements Runnable {
	private ServerSocket serverSocket;
	boolean running = false;
	
	public ServerManager(int port) {
		try {
			serverSocket = new ServerSocket(port);
			running = true;			
			System.out.println("Server start at port: " + port);
			System.out.println(serverSocket.getInetAddress().getHostAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void run() {
		while (running) {			
			try {
				Socket socket = serverSocket.accept();
				ServerThread st = new ServerThread(socket);
				Thread t = new Thread(st);
				t.start();
				System.out.println("New client connected");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
