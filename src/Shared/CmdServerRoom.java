package Shared;

import java.net.DatagramSocket;

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

	public String cases(String opcoes) {
		
		
		
		switch(opcoes) {
			
			case "getin":
				
				Player newPlayer =  new Player(this.comunication.getLast()); 
				this.room.addPlayer(newPlayer);
				break;
				
			case "setName":
				
				
				
			case "setColor":
				
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
