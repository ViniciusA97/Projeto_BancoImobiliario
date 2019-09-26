package Server;

import java.net.DatagramSocket;
import java.net.SocketException;

import Shared.CmdServer;
import Shared.Salas;

public class Server {
	
	public static void main(String[] args) {
		
		System.out.println("Iniciando main servidor .... ");
		try {
			final DatagramSocket SOCKET = new DatagramSocket(4444);
		} catch (SocketException e) {
			System.out.println(e.getMessage());
		}
		Salas salas = new Salas();
		
		Room roomPadrao = new Room(0, 100);
		CmdServer cmdServer = new CmdServer();
		
		//fazer um novo communication para o servidor
		// fazer os comandos que podem ser recebidos e implementar
		
		System.out.println("Criando Pool de Threads ...");
		
		
		
	}
	
	
	
}
