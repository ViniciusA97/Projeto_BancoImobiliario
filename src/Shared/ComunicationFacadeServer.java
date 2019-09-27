package Shared;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

public class ComunicationFacadeServer {

	private DatagramSocket socket;
	private static ComunicationFacadeServer instance;
	
	private ComunicationFacadeServer() {
		try {
			this.socket = new DatagramSocket(4444);
		} catch (SocketException e) {
			System.out.println(e.getMessage());
		}
	}
	
	 public void sendMessage(String message , InetAddress ip){

	        byte[] bytes = message.getBytes();  
	        DatagramPacket pacote = new DatagramPacket(bytes, bytes.length, ip, 4444);
	        try {
				socket.send(pacote);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}        
	 }
	 
	 public HashMap<String, Object> reciveMessage(){
		 
		 HashMap<String, Object> mapComunication = new HashMap<String, Object>();
	     byte[] reciveData = new byte[1024];
	     DatagramPacket packageRecive = new DatagramPacket(reciveData, reciveData.length);
	     
	     while(true) {
	    	   try {
			socket.receive(packageRecive);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	     String sentence = new String( packageRecive.getData());
	     mapComunication.put("address",packageRecive.getAddress() );
	     mapComunication.put("msg",sentence);
	     if(sentence.contains("/")) {
	        	
	        String [] aux = sentence.split("/");
	        mapComunication.put("usual", aux[1]);
	        mapComunication.put("msg", aux[0]);
	        	
	      }
	      return mapComunication;
	 }
	     }
	   
	 
	 public static ComunicationFacadeServer getInstance() {
		 if(instance == null)instance = new ComunicationFacadeServer();
		 return instance;
	 }
	
}
