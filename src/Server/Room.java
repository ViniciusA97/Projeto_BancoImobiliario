package Server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

import Shared.CmdServerRoom;
import Shared.ComunicationFacade;
import Util.jogadores.Jogadores;
import Util.observer.Observer;

public class Room extends Thread {

	
	private ArrayList<Player> players;
	private String status;
	private ComunicationFacade comunication;
	private CmdServerRoom cmd;
	private DatagramSocket socket;
	private boolean inGame;
	private Observer observer;
	private Jogadores jogadores;
	
	public Room(){
		
		this.jogadores = new Jogadores();
		this.inGame = false;
		this.players = new ArrayList<Player>(8);
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
	
	public void addPlayer(Player newPlayer) {
		
		if(this.players.size()>=8) {
			try {
				this.comunication.sendMessage("Total de jogadores atingidos. Procure outra Room", socket, newPlayer.getAddress());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}	
		}
		this.players.add(newPlayer);
	}
	
	public void setStatusGame(boolean bool) {
		this.inGame = bool;
	}

	public void setPlayerColor(InetAddress player, String cor) {
		
		for(Player i: this.players) {
			if(i.getAddress().equals(player)) {
				i.setCor(cor);
			}
		}
	}
	
	public void delPlayer(InetAddress player) {
		
		for(Player i: this.players) {
			if(i.getAddress().equals(player)) {
				this.players.remove(i);
			}
		}	
	}
	
}
