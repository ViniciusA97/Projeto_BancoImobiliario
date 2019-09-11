package Server;
import java.net.InetAddress;

import Util.jogadores.Jogador;


public class Player {

	private InetAddress ip;
	private int porta;
	private Jogador jogador;
	

	public Player(InetAddress ip) {
		this.ip = ip;
		this.jogador = new Jogador();
	}
	
	public String getIp() {
		return this.ip.getHostAddress();
	}
	
	public InetAddress getAddress() {
		return this.ip;
	}
	
	public void setPort(int port) {
		this.porta=port;
	}
	
	public void setJogador(Jogador j) {this.jogador = j;}
	
}
