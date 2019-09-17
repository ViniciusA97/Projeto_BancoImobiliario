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
