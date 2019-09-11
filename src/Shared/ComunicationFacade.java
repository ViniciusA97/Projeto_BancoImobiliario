package Shared;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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

    public String reciveMessage(DatagramSocket socket) throws IOException {
       
        byte[] reciveData = new byte[1024];
        DatagramPacket packageRecive = new DatagramPacket(reciveData, reciveData.length);
        socket.receive(packageRecive);
		String sentence = new String( packageRecive.getData());
        return sentence;
    }

}