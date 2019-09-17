package Shared;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

import Server.Player;
import Server.Room;
import Util.jogadores.Jogador;
import Util.observer.EventNotificationJogada;
import Util.observer.*;

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
				Jogador newPlayer =  new Jogador();
			try {
				this.room.addPlayer(newPlayer);
			} catch (IOException e) {
				try {
					this.comunication.sendMessage(e.getMessage(), socket,newPlayer.getAddress());
				} catch (IOException e1) {
					
					System.out.println(e.getMessage());
				}
				e.printStackTrace();
			}
				EventoNotificationGetIn e = new EventoNotificationGetIn();
				this.observer.fireEventGetIn(e, this.room.getJogadores());;
				
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
