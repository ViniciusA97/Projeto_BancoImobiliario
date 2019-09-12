package Shared;

import java.io.Serializable;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import Server.Player;
import Server.Room;

public class CmdServerRoom implements Cmd{

	private ComunicationFacade comunication;
	private Room room;
	private DatagramSocket socket;
	
	public CmdServerRoom(Room r, DatagramSocket s) {
		
		this.comunication = new ComunicationFacade();
		this.room = r;
		this.socket = s;
		
	}

	public String cases(HashMap<String, Object> map) {
		
		String msg = map.get("msg").toString();
		
		
		
		switch(msg) {
			
			case "getin":
				
				InetAddress usual =(InetAddress) map.get("address");
				Player newPlayer =  new Player(usual); 
				this.room.addPlayer(newPlayer);
				break;
				
			case "setColor":
				
				InetAddress client = (InetAddress) map.get("address");
				this.room.setPlayerColor(client, map.get("usual").toString());
				
				
			case "startGame":
				
				this.room.setStatusGame(true);
				break;
				
			case "leaveRoom":
				
			case "jogar":
				
			case "status":
			
			case "vender":
				
			case "carta":
				
			case "pagar":
				
			case "biuld":
				
			case "":
			
				
		}
		
		
		
		return "";
	}
	
}
