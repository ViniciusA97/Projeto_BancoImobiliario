package Server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

import Shared.CmdServerRoom;
import Shared.ComunicationFacade;
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
	
	public Room(){
		
		this.jogadores = new Jogadores();
		this.inGame = false;
		this.comunication = new ComunicationFacade();
		this.observer = new Observer();
	
		try {
			this.socket = new DatagramSocket(4444);
			this.cmd = new CmdServerRoom(this,this.socket, this.observer);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		
		}
	}
	
	public void run() {
		
		boolean condiction = true;
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		while(condiction) {
			
			try {
				map = (HashMap<String, Object>) comunication.reciveMessage(this.socket);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			cmd.cases(map);
			
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
}
