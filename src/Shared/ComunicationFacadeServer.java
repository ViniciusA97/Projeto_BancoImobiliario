package Shared;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class ComunicationFacadeServer {

	private static ComunicationFacadeServer instance;

	private ComunicationFacadeServer() {

	}

	public void sendMessage(String message, InetAddress ip) {
		DatagramSocket socket;
		try {
			socket = new DatagramSocket(4444);
			byte[] bytes = message.getBytes();
			DatagramPacket pacote = new DatagramPacket(bytes, bytes.length, ip, 4444);

			socket.send(pacote);
			socket.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public HashMap<String, Object> reciveMessage() {
		
		DatagramSocket socket;
		try {
			
			socket = new DatagramSocket(4444);
			System.out.println("out");
			HashMap<String, Object> mapComunication = new HashMap<String, Object>();
			byte[] reciveData = new byte[1024];
			DatagramPacket packageRecive = new DatagramPacket(reciveData, reciveData.length);
			socket.receive(packageRecive);
			String sentence = new String(packageRecive.getData());
			mapComunication.put("address", packageRecive.getAddress());
			mapComunication.put("msg", sentence);

			if (sentence.contains("/")) {

				String[] aux = sentence.split("/");
				mapComunication.put("usual", aux[1]);
				mapComunication.put("msg", aux[0]);

			}
			socket.close();
			return mapComunication;
		}catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		return null;
	}

	public static ComunicationFacadeServer getInstance() {
		if (instance == null)
			instance = new ComunicationFacadeServer();
		return instance;
	}

}
