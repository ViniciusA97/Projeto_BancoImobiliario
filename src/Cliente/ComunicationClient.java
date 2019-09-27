package Cliente;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.net.*;

public class ComunicationClient {

	private DatagramSocket socket;
	private InetAddress ip;
	private int port;

	public ComunicationClient(String ipef) {
		try {
			InetAddress ip = InetAddress.getByName("hostname");
			this.socket = new DatagramSocket();
			this.port = 4444;
								
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void sendMessage(String message) {

		byte[] bytes = message.getBytes();
		DatagramPacket pacote = new DatagramPacket(bytes, bytes.length, ip, this.port);

		try {
			socket.send(pacote);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public HashMap<String, Object> RessiveMessage() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		byte[] reciveData = new byte[1024];
		DatagramPacket packageRecive = new DatagramPacket(reciveData, reciveData.length);
		
		while(true) {
					try {
		
			socket.receive(packageRecive);
			String sentence = new String(packageRecive.getData(), 0 , packageRecive.getLength());
			map.put("address", packageRecive.getAddress());
			map.put("msg", sentence);
			if (sentence.contains("/")) {

				String[] aux = sentence.split("/");
				map.put("usual", aux[1]);
				map.put("msg", aux[0]);

			}
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return map;

	}
		}


	public void changePort(int p) {
		this.port = p;

	}

	public void resetPort() {
		this.port = 4444;
	}
}
