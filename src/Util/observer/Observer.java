package Util.observer;

import Util.jogadores.Jogadores;

import java.io.IOException;
import java.net.DatagramSocket;
import Shared.ComunicationFacade;
import Util.jogadores.*;
public class Observer {
	
	private ComunicationFacade comunication;
	private DatagramSocket socket;
	private int id;
	
	public Observer(ComunicationFacade c, DatagramSocket s, int index) {
		this.comunication = c;
		this.socket = s;
		this.id = index;
	}
	
	public int getId() {
		return this.id;
	}
	
	
	public void fireEventoMonopolio(Events eventoMonopolio,  Jogadores j) throws IOException {
		for(Jogador i: j.getJogadores()) {
			comunication.sendMessage(eventoMonopolio.getStringEvento(j),this.socket, i.getAddress());
		}	
	}
	
	public void fireEventoPreMonopolio(Events eventoPreMonopolio, Jogadores j) throws IOException {
		for(Jogador i: j.getJogadores()) {
			comunication.sendMessage(eventoPreMonopolio.getStringEvento(j), this.socket, i.getAddress());
		}
	}
	
	public void fireEventoPreHotel(Events eventoPreHotel, Jogadores j) throws IOException {
		for(Jogador i: j.getJogadores()) {
			comunication.sendMessage(eventoPreHotel.getStringEvento(j), this.socket, i.getAddress());
		}
	}
	
	public void fireEventNotification(String msg,EventsNotification event , Jogadores j) {
		try {
			event.lancaEventNotification(msg,j, this.socket, comunication);
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage()+" -- Event get In");
		}
	}
	
	public ComunicationFacade getComunication() {
		return this.comunication;
	}
	
	
}
