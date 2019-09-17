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
				
				//lançar observer para cada player dentro da sala
				
				break;
				
			case "setColor":
				
				InetAddress client = (InetAddress) map.get("address");
				this.room.setPlayerColor(client, map.get("usual").toString());
				//lançar observer para cada player debtri da sala
				
			case "startGame":
				
				this.room.setStatusGame(true);
				
				//pedir confirmação de todos os jogadores
				
				break;
				
			case "leaveRoom":
				
				
				
			case "getOut":
				this.room.delPlayer( (InetAddress)map.get("address") );
				//lançar observer
				
			case "jogar":
				
				//jogar
				//lançar observer
				
			case "status":
				
				//lançar observer
			
			case "vender":
				
				//vender e lançar observer
				
			case "carta":
				
				// carta e lançar observer
				
			case "pagar":
				
				//pagar e lançar observer
				
			case "biuld":
				
				//biuld e lançar observer
				
			case "":
			
				
		}
		
		
		
		return "";
	}
	
}
