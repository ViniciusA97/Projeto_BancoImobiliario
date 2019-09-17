package Shared;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import Server.Room;
import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;
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
		InetAddress client;
		
		
		switch(msg) {
			
			case "getin":
				
				Jogador newPlayer =  new Jogador();
				try {
					this.room.addPlayer(newPlayer);
				} catch (IOException e) {
					try {
						this.comunication.sendMessage(e.getMessage(), socket,newPlayer.getAddress());
					} catch (IOException e1) {
						System.out.println(e.getMessage());
					}
					System.out.println(e.getMessage());
				}
				this.observer.fireEventNotification(new EventoNotificationGetIn(), this.room.getJogadores());;
				
				break;
				
			case "setColor":
				
				client = (InetAddress) map.get("address");
				this.room.setPlayerColor(client, map.get("usual").toString());
				this.observer.fireEventNotification(new EventNotificationColorChange(), this.room.getJogadores());
				
			case "startGame":
			
				client = (InetAddress) map.get("address");
				if(this.room.getPlayer(client).getAdm()) {
					this.room.setStatusGame(true);
					this.observer.fireEventNotification(new EventNotificationStartGame(), this.room.getJogadores());
				}else {
					try {
						this.comunication.sendMessage("Apenas Jogadores intitulados como adms podem iniciar o jogo. ", socket, client);
					} catch (IOException e) {
						System.out.println(e.getMessage()+" Start Game cmd Server room");
					}	
				}
				break;
				
			case "leaveRoom":
				
				
				
				
			case "getOutGame":
				this.room.delPlayer( (InetAddress)map.get("address") );
				//lançar observer
				
			case "jogar":
				
				Jogadores jogadores = this.room.getJogadores();
				client = (InetAddress) map.get("address");
				if(client.equals(jogadores.getJogadorDaVez().getAddress())) {
					//jogar
					//lançar observer	
				}else {
					
					try {
						this.comunication.sendMessage("Não é sua vez, porfavor esperar!", socket, client);
					} catch (IOException e) {
						System.out.println(e.getMessage()+" -- jogar cmd Server Room");
					}
				}
				
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
				
			case "/h":
			
			case "chat":
			
				
		}
		
		
		
		return "";
	}
	
}
