package Shared;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import Server.Room;
import Util.Tabuleiro.FachadaTabuleiro;
import Util.base.Comandos;
import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;
import Util.observer.*;

public class CmdServerRoom implements Cmd{

	private ComunicationFacade comunication;
	private Room room;
	private DatagramSocket socket;
	private Observer observer;
	private Comandos comandos;
	private FachadaTabuleiro fachadaT;
	
	public CmdServerRoom(Room r, DatagramSocket s,Observer observer , ComunicationFacade c) {
		
		this.comunication = c;
		this.room = r;
		this.socket = s;
		this.observer = observer;
		this.comandos = new Comandos(this.observer,this.room.getNum(),this.socket,this.room.getJogadores(), c);
		this.fachadaT = FachadaTabuleiro.getInstance(this.room.getNum());
		
	}

	public String cases(HashMap<String, Object> map) {
		
		String msg = map.get("msg").toString();
		InetAddress client = (InetAddress) map.get("address");
		Jogadores jogadores;
		
		switch(msg) {
			
			case "getin":
				Jogador newPlayer =  new Jogador(client);
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
				this.observer.fireEventNotification("Jogador "+this.room.getPlayer(client).getNome()
						+" entrou no jogo",new EventoNotificationGetIn(), this.room.getJogadores());;
				
				break;
				
			case "setColor":
				
				if(confereCor(this.room.getJogadores(),(String)map.get("usual"))) {
					this.room.setPlayerColor(client, map.get("usual").toString());
				this.observer.fireEventNotification("Jogador "+this.room.getPlayer(client).getNome() 
						+" mudou a cor para "+this.room.getPlayer(client).getCor(),new EventNotificationChanges(),
						this.room.getJogadores());
				}else {
					
					try {
						this.comunication.sendMessage("Ja existe jogador usando essa cor", socket, client);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
					
				}
				
				break;
				
			case "setName":
				
				if(confereNome(this.room.getJogadores(),(String)map.get("usual"))) {
						String nomeAntigo = this.room.getPlayer(client).getNome();
				this.room.getPlayer(client).setName((String)map.get("usual"));
				this.observer.fireEventNotification("Jogador "+nomeAntigo+" mudou o nome para "+this.room.getPlayer(client).getNome()
						, new EventNotificationChanges(), this.room.getJogadores());
			
				}else {
					
					try {
						this.comunication.sendMessage("Algum jogador ja possui esse nome.", socket, client);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				}
				
			
			case "startGame":
			
				if(this.room.getPlayer(client).getAdm()) {
					this.room.setStatusGame(true);
					this.observer.fireEventNotification("O jogo irá começar.",new EventNotificationStartGame(), this.room.getJogadores());
				}else {
					try {
						this.comunication.sendMessage("Apenas Jogadores intitulados como adms podem iniciar o jogo. ", socket, client);
					} catch (IOException e) {
						System.out.println(e.getMessage()+" Start Game cmd Server room");
					}	
				}
				break;
				
			case "setNameRoom":
				
				if(this.room.getPlayer(client).getAdm()) {
					String name = (String) map.get("usual");
					this.room.setName(name);
					this.observer.fireEventNotification("O nome da sala foi mudado para "+ name,new EventNotificationStartGame(), this.room.getJogadores());
				}else {
					try {
						this.comunication.sendMessage("Apenas Jogadores intitulados como adms podem iniciar o jogo. ", socket, client);
					} catch (IOException e) {
						System.out.println(e.getMessage()+" Não trocado o nome da sala");
					}	
				}
				break;
				
			case "leaveRoom":
				
				jogadores = this.room.getJogadores();
				
				for(Jogador i: jogadores.getJogadores()) {
					if(i.getAddress().equals(client)) {
						this.room.delPlayer(client);
					}
				}
				
				this.observer.fireEventNotification("1", new EventNotification(), jogadores);
				
				
			case "getOutGame":
				this.comandos.comandoSair();
				
			case "jogar":
				
				jogadores = this.room.getJogadores();

				if(client.equals(jogadores.getJogadorDaVez().getAddress())) {
					this.comandos.comandoJogar();
				
				}else {
					this.observer.fireEventNotification("Não é sua vez", new EventNotification(), this.room.getJogadores());
				}
				
			case "status":
				
				jogadores = this.room.getJogadores();
				if(client.equals(jogadores.getJogadorDaVez().getAddress())) {
					this.comandos.comandoStatus();
				
				}else {
					this.observer.fireEventNotification("Não é sua vez", new EventNotification(), this.room.getJogadores());
				}
			
			case "vender":
				
				jogadores = this.room.getJogadores();
				if(client.equals(jogadores.getJogadorDaVez().getAddress())) {
					this.comandos.comandoVender();
				
				}else {
					this.observer.fireEventNotification("Não é sua vez", new EventNotification(), this.room.getJogadores());
				}
				
			case "carta":
				
				jogadores = this.room.getJogadores();
				
				if(this.fachadaT.procuraPrisioneiro(jogadores.getJogadorDaVez())) {
					if(client.equals(jogadores.getJogadorDaVez().getAddress())) {
						this.comandos.comandoCarta();
				
					}else {
						this.observer.fireEventNotification("Não é sua vez", new EventNotification(), this.room.getJogadores());
					}
				}
				
			case "pagar":
				
				jogadores = this.room.getJogadores();
				
				if(this.fachadaT.procuraPrisioneiro(jogadores.getJogadorDaVez())) {
					if(client.equals(jogadores.getJogadorDaVez().getAddress())) {
						this.comandos.comandoPagar();
				
					}else {
						this.observer.fireEventNotification("Não é sua vez", new EventNotification(), this.room.getJogadores());
					}
				}
				
				
			case "biuld":
				
				jogadores = this.room.getJogadores();
				if(client.equals(jogadores.getJogadorDaVez().getAddress())) {
					this.comandos.comandoConstruir();
				
				}else {
					this.observer.fireEventNotification("Não é sua vez", new EventNotification(), this.room.getJogadores());
				}
				//biuld e lançar observer
				
			case "/h":
			
			case "chat":
			
				
		}
		
		
		
		return "";
	}
	
	public static boolean confereNome(Jogadores j, String nome) {
		
		for(Jogador i: j.getJogadores()) {
			if(i.getNome().equals(nome)) return false;
		}
		return true;
	}
	
	public static boolean confereCor(Jogadores j, String cor) {
	
		for(Jogador i: j.getJogadores()) {
			if(i.getCor().equals(cor)) return false;
		}
		return true;
		
	}
	
}
