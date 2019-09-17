package Shared;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class ComunicationFacade {

    private final int PORT = 4444;
    
    public  ComunicationFacade(){} 

    public void sendMessage(String message , DatagramSocket socket, InetAddress [] adress) throws IOException{

        byte[] bytes = message.getBytes();  
        
        for(InetAddress i : adress) {
        	
        	DatagramPacket pacote = new DatagramPacket(bytes, bytes.length, i, this.PORT);
        	socket.send(pacote);        
        
        }
    }

    public Map<String, Object> reciveMessage(DatagramSocket socket) throws IOException {
       
    	Map<String, Object> mapComunication = new HashMap<String, Object>();
        byte[] reciveData = new byte[1024];
        DatagramPacket packageRecive = new DatagramPacket(reciveData, reciveData.length);
        socket.receive(packageRecive);
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
    public void sendMessage(String message , DatagramSocket socket, InetAddress  adress) throws IOException{

        byte[] bytes = message.getBytes();  
      	DatagramPacket pacote = new DatagramPacket(bytes, bytes.length, adress, this.PORT);
      	socket.send(pacote);        
        
        
    }

}