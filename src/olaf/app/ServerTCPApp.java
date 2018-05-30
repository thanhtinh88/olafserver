package olaf.app;

import olaf.model.*;

public class ServerTCPApp {

	public static void main(String[] args) {
		ServerManager server = new ServerManager(5555);
		Thread t = new Thread(server);
		t.start();
	}
}
