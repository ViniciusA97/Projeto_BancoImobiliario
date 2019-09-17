package Shared;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

import Server.Player;
import Server.Room;
import Util.observer.Observer;

public class CmdServerRoom implements Cmd{

	private ComunicationFacade comunication;
	private Room room;
	private DatagramSocket socket;
	private Observer observer;
	
	public CmdServerRoom(Room r, DatagramSocket s,Observer observer) {
		
		this.comunication = new ComunicationFacade();
		this.room = r;
		this.socket = s;
		this.observer = observer;
		
	}

	public String cases(HashMap<String, Object> map) {
		
		String msg = map.get("msg").toString();
		
		
		
		switch(msg) {
			
			case "getin":
				
				InetAddress usual =(InetAddress) map.get("address");
				Player newPlayer =  new Player(usual); 
				this.room.addPlayer(newPlayer);
				
				//lan�ar observer para cada player dentro da sala
				
				break;
				
			case "setColor":
				
				InetAddress client = (InetAddress) map.get("address");
				this.room.setPlayerColor(client, map.get("usual").toString());
				//lan�ar observer para cada player debtri da sala
				
			case "startGame":
				
				this.room.setStatusGame(true);
				
				//pedir confirma��o de todos os jogadores
				
				break;
				
			case "leaveRoom":
				
				
				
			case "getOut":
				this.room.delPlayer( (InetAddress)map.get("address") );
				//lan�ar observer
				
			case "jogar":
				
				//jogar
				//lan�ar observer
				
			case "status":
				
				//lan�ar observer
			
			case "vender":
				
				//vender e lan�ar observer
				
			case "carta":
				
				// carta e lan�ar observer
				
			case "pagar":
				
				//pagar e lan�ar observer
				
			case "biuld":
				
				//biuld e lan�ar observer
				
			case "":
			
				
		}
		
		
		
		return "";
	}
	
}
