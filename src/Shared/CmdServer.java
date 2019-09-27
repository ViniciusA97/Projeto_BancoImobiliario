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

	public CmdServer(Salas sala, ComunicationFacadeServer s) {

		this.comunicationServer = s;
		this.sala = sala;
		this.cont = 0;
	}

	@Override
	public String cases(HashMap<String, Object> map) {

		String msg = map.get("msg").toString();
		InetAddress client = (InetAddress) map.get("address");
		System.out.println(map.get("address"));
		Jogador j;
		Room r;
		System.out.println("mensagem: " + msg);
		switch (msg.trim()) {

		case "getInID":

			try {
				System.out.println("entrou no get in id");
				int id = (int) map.get("usual");
				j = new Jogador(client);
				r = this.sala.addJogadorInRoomID(id, j);
				this.comunicationServer.sendMessage("" + r.getPort(), client);
			} catch (Exception e) {
				this.comunicationServer.sendMessage(e.getMessage(), client);
			}
			break;
		case "getInName":
			System.out.println("entrou get in name");
			String nome = (String) map.get("usual");
			j = new Jogador(client);
			try {
				r = this.sala.addJogadorInRoomName(nome, j);
				this.comunicationServer.sendMessage("" + r.getPort(), client);
			} catch (IOException e) {
				this.comunicationServer.sendMessage(e.getMessage(), client);
			}
			break;
		case "createRoom":
			System.out.println("entrou create room");
			if (cont >= 40) {

				this.comunicationServer.sendMessage("Ja há mais salas que o permitido.", client);

			} else {

				this.sala.addRoom(cont);
				this.cont++;
				System.out.println("vai entrarr");
				this.comunicationServer.sendMessage("" + cont, client);
				System.out.println("saiu");
			}
			break;
		default:
			this.comunicationServer.sendMessage("Comando não aceito.", client);

		}
		System.out.println(msg);
		return null;
	}
}
