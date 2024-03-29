package Shared;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;

import Server.Room;
import Util.jogadores.Jogador;


public class CmdServer implements Cmd {

	private ComunicationFacadeServer comunicationServer;
	private Salas sala;
	private int cont;
	
	public CmdServer( Salas sala) {
		
		this.comunicationServer = ComunicationFacadeServer.getInstance();
		this.sala = sala;
		this.cont = 0;
	}
	
	
	@Override
	public String cases(HashMap<String, Object> map) {
		
		String msg = map.get("msg").toString();
		InetAddress client = (InetAddress) map.get("address");
		Jogador j;
		Room r;
		
		switch (msg) {
		
		case "getInID":
			
			try {
				int id = (int) map.get("usual");
				j= new Jogador(client);
				r=this.sala.addJogadorInRoomID(id, j);	
				this.comunicationServer.sendMessage(""+r.getPort(), client);
			}catch(Exception e) {
				this.comunicationServer.sendMessage(e.getMessage(), client);
			}
			
		case "getInName":
			String nome= (String) map.get("usual");
			j = new Jogador(client);
			try {
				r =this.sala.addJogadorInRoomName(nome, j);
				this.comunicationServer.sendMessage(""+r.getPort(), client);
			} catch (IOException e) {
				this.comunicationServer.sendMessage(e.getMessage(), client);
			}
		
		case "createRoom":
			
			if(cont>=40) {
				
				this.comunicationServer.sendMessage("Ja h� mais salas que o permitido.", client);
				this.comunicationServer.sendMessage("Por favor escolher outra sala.", client);
				
			}else {
				
				this.sala.addRoom(cont);
				this.cont++;				
				this.comunicationServer.sendMessage(""+cont, client);
				
			}
		}	
		return null;
	}
}
