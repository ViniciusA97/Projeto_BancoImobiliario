package Shared;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;

public class ComunicationFacade {

    private final int PORT;
    private static ArrayList<ComunicationFacade> instances = new ArrayList<ComunicationFacade>(40);
    
    private  ComunicationFacade(int port){
    
    	this.PORT = port;
    	
    } 
    
    public static ComunicationFacade getInstance(int index , int port) {
    	if(instances.get(index)==null) {
    		instances.set(index, new ComunicationFacade(port));
    		
    	}
    	return instances.get(index);
    		
    	
    }

    public void sendMessage(String message , DatagramSocket socket, Jogadores j) throws IOException{

        byte[] bytes = message.getBytes();  
        
        for(Jogador i :j.getJogadores()) {
        	
        	DatagramPacket pacote = new DatagramPacket(bytes, bytes.length, i.getAddress(), this.PORT);
        	socket.send(pacote);        
        }
    }

    public Map<String, Object> reciveMessage() throws IOException {
       
    	DatagramSocket socket = new DatagramSocket(PORT);
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
        socket.close();
        return mapComunication;
    }
    public void sendMessage(String message , DatagramSocket socket, InetAddress  adress) throws IOException{

        byte[] bytes = message.getBytes();  
      	DatagramPacket pacote = new DatagramPacket(bytes, bytes.length, adress, this.PORT);
      	socket.send(pacote);        
        socket.close();
        
    }
    
    public int getPort(int index) {
    	return instances.get(index).PORT;
    }
  

}