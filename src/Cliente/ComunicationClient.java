package Cliente;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.net.*;

public class ComunicationClient {

	private InetAddress IPAddress;
	private int port;

	public ComunicationClient() {

	}

	public void sendMessage(String message) throws Exception {

		InetAddress IPAddress = InetAddress.getByName("192.168.27.105");
		DatagramSocket socket = new DatagramSocket(4444);
		byte[] a = message.getBytes();
		DatagramPacket bbbb = new DatagramPacket(a, a.length, IPAddress, 4444);
		socket.send(bbbb);
		socket.close();
	}

	public HashMap<String, Object> RessiveMessage() {
		System.out.println("ressiveMsg client");
		DatagramSocket socket;
		try {
			socket = new DatagramSocket(4444);
			HashMap<String, Object> map = new HashMap<String, Object>();
			byte[] reciveData = new byte[1024];
			DatagramPacket packageRecive = new DatagramPacket(reciveData, reciveData.length);
			socket.receive(packageRecive);
			System.out.println("k");
			String sentence = new String(packageRecive.getData(), 0, packageRecive.getLength());
			map.put("address", packageRecive.getAddress());
			map.put("msg", sentence);
			if (sentence.contains("/")) {

				String[] aux = sentence.split("/");
				map.put("usual", aux[1]);
				map.put("msg", aux[0]);

			}
			return map;
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		System.out.println("aaaaa");
		return null;
	}

	public void changePort(int p) {
		this.port = p;

	}

	public void resetPort() {
		this.port = 4444;
	}
}
