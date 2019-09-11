package Server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import Shared.CmdServerRoom;
import Shared.ComunicationFacade;

public class Room extends Thread {

	
	private ArrayList<Player> players;
	private String status;
	private ComunicationFacade comunication;
	private CmdServerRoom cmd;
	private DatagramSocket socket;
	private boolean inGame;
	
	
	public Room(){
		
		this.inGame = false;
		this.players = new ArrayList<Player>(8);
		this.comunication = new ComunicationFacade();
	
		try {
			this.socket = new DatagramSocket(4444);
			this.cmd = new CmdServerRoom(this,this.socket);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		
		}
	}
	
	public void run() {
		
		boolean condiction = true;
		String msg = "";
		
		while(condiction) {
			
			try {
				msg = comunication.reciveMessage(this.socket);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			cmd.cases(msg);
			
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
	
}
