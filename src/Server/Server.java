package Server;

import java.util.HashMap;
import Shared.CmdServer;
import Shared.ComunicationFacadeServer;
import Shared.Salas;

public class Server {
	
	public static void main(String[] args) {
		
		System.out.println("Iniciando main servidor .... ");
		
		Salas salas = new Salas();
		ComunicationFacadeServer comunication = new ComunicationFacadeServer();
		CmdServer cmdServer = new CmdServer(salas,comunication);
		
		HashMap<String, Object> map;
		while(true) {
			System.out.println("a");
			map = comunication.reciveMessage();
			System.out.println(map.get("msg"));
			cmdServer.cases(map);
		}
		
		
	}
	
	
}
