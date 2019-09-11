package Server;

import Shared.CmdServer;
import Shared.ComunicationFacade;

public class Room extends Thread {

	
	private Player [] players;
	private String status;
	private ComunicationFacade comunication;
	private CmdServer cmd;
	
	public Room() {
		
		this.players = new Player [6];
		this.comunication = new ComunicationFacade();
		this.cmd = new CmdServer();
	}
	
	public void run() {
		
		boolean condiction = true;
		
		while(condiction) {
			
			
			
			
		}
		
		
		
		
		
	}
	
}
