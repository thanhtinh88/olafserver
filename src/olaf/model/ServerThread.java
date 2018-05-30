package olaf.model;

import java.io.*;
import java.net.*;
import java.time.Instant;
import java.util.Date;
import java.util.StringTokenizer;

import olaf.utils.CSVUtils;

public class ServerThread implements Runnable {
	private Socket socket;
	private boolean running;
	private String fileName = "dhtdata.csv"; 
	
	public ServerThread(Socket socket) {
		this.socket = socket;
		running = true;
	}

	@Override
	public void run() {
		BufferedInputStream in = null;
		try {
			 InputStream is = socket.getInputStream();
			 in = new BufferedInputStream(is);			
		} catch (IOException e) {
			e.printStackTrace();
			running = false;
			return;
		}
		
		while(running) {
			try {
				int len = in.available();
				if(len > 0) {
					byte[] data = new byte[len];					
					in.read(data);
					StringTokenizer st = new StringTokenizer(new String(data),"-");
					DHTData dht = new DHTData();
					if (st.countTokens() == 2) {
						dht.setTemp(Float.parseFloat(st.nextToken()));
						dht.setHumd(Float.parseFloat(st.nextToken()));
						dht.setTimeStamp(Instant.now().getEpochSecond());
					}
					saveCSV(dht);
					System.out.println(String.valueOf(dht.getTemp()));
					System.out.println(String.valueOf(dht.getHumd()));
					System.out.println(String.valueOf(new Date(dht.getTimeStamp())));
				}
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	private void saveCSV(DHTData data) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			CSVUtils.writeLine(file, data.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
