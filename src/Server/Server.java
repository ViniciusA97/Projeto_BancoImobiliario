package Server;

import java.util.HashMap;
import Shared.CmdServer;
import Shared.ComunicationFacadeServer;
import Shared.Salas;

public class Server {
	
	public static void main(String[] args) {
		
		System.out.println("Iniciando main servidor .... ");
		
		Salas salas = new Salas();
		CmdServer cmdServer = new CmdServer(salas);
		ComunicationFacadeServer comunication = ComunicationFacadeServer.getInstance();
			
		while(true) {
				
			comunication.reciveMessage();
			
		}
		
		
	}
	
	
}
