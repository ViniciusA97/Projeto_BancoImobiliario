package Server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

import Shared.CmdServerRoom;
import Shared.ComunicationFacade;
import Util.base.GeraString;
import Util.jogadores.Jogador;
import Util.jogadores.JogadorJaExisteException;
import Util.jogadores.Jogadores;
import Util.observer.Observer;

public class Room extends Thread {

	private ComunicationFacade comunication;
	private CmdServerRoom cmd;
	private DatagramSocket socket;
	private boolean inGame;
	private Observer observer;
	private Jogadores jogadores;
	private GeraString geraString;
	private int port;
	private int num;
	
	public Room(int port){
		this.num = port;
		this.port = port;
		this.geraString = GeraString.getInstance(num);
		this.jogadores = new Jogadores();
		this.inGame = false;
		this.comunication =  ComunicationFacade.getInstance(this.num , port);
	
		try {
			this.socket = new DatagramSocket(port);
			this.observer = new Observer(this.comunication, this.socket,port);
			this.cmd= new CmdServerRoom(this, this.socket , this.observer , this.comunication);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	public void run() {
		
		boolean condiction = true;
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		while(condiction) {
			
			try {
				map = (HashMap<String, Object>) comunication.reciveMessage();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			cmd.cases(map);

			while(this.inGame) {
				// Inicio do jogo
				try {
					comunication.sendMessage(geraString.geraInicioDaJogada(this.jogadores.getJogadorDaVez()), socket, this.jogadores);
					while(true) {
						map = (HashMap<String, Object>) comunication.reciveMessage();
						this.cmd.cases(map);
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
					
				
				
				
				
				
			}
		}
		
	}
	
	public void addPlayer(Jogador j) throws IOException {
		
		if(this.jogadores.getJogadores().size()==0) {
			j.setAdm();
		}
		try {
			this.jogadores.cadastraJogador(j);
		} catch (JogadorJaExisteException e) {
			this.comunication.sendMessage(e.getMessage(), socket, j.getAddress());
		};
	}
	
	public void setStatusGame(boolean bool) {
		this.inGame = bool;
	}

	public void setPlayerColor(InetAddress ip, String cor) {
		
		for(Jogador i: this.jogadores.getJogadores()) {
			if(i.getAddress().equals(ip)) {
				i.setCor(cor);
			}
		}
	}
	
	public void delPlayer(InetAddress player) {
		
		for(Jogador i: this.jogadores.getJogadores()) {
			if(i.getAddress().equals(player)) {
				this.jogadores.retiraJogador(i);;
			}
		}	
	}
	public Jogadores getJogadores() {
		return this.jogadores;
	}
	
	public Jogador getPlayer(InetAddress ip) {
		for(Jogador i: this.jogadores.getJogadores()) {
			if(i.getAddress().equals(ip)) return i;
		}
		return null;
	}
	
	public void setGameOn() {
		this.inGame = true;
	}
	
	public void setGameOff(){
		this.inGame = false;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public int getPort() {return this.port;}
}
