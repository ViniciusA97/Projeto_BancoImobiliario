package Util.observer;

import Util.jogadores.Jogadores;

import java.io.IOException;
import java.net.DatagramSocket;
import Shared.ComunicationFacade;
import Util.jogadores.*;
public class Observer {
	
	private ComunicationFacade comunication;
	private int id;
	
	public Observer(ComunicationFacade c, int index) {
		this.comunication = c;
		this.id = index;
	}
	
	public int getId() {
		return this.id;
	}
	
	
	public void fireEventoMonopolio(Events eventoMonopolio,  Jogadores j) throws IOException {
		DatagramSocket socket = new DatagramSocket(9999);
		for(Jogador i: j.getJogadores()) {
			comunication.sendMessage(eventoMonopolio.getStringEvento(j),9999, i.getAddress());
		}	
		socket.close();
	}
	
	public void fireEventoPreMonopolio(Events eventoPreMonopolio, Jogadores j) throws IOException {
		DatagramSocket socket = new DatagramSocket(9999);
		for(Jogador i: j.getJogadores()) {
			comunication.sendMessage(eventoPreMonopolio.getStringEvento(j), 9999, i.getAddress());
		}
		socket.close();
	}
	
	public void fireEventoPreHotel(Events eventoPreHotel, Jogadores j) throws IOException {
		DatagramSocket socket = new DatagramSocket(9999);
		for(Jogador i: j.getJogadores()) {
			comunication.sendMessage(eventoPreHotel.getStringEvento(j), 9999, i.getAddress());
		}
		socket.close();
	}
	
	public void fireEventNotification(String msg,EventsNotification event , Jogadores j) {
		
		try {
			DatagramSocket socket = new DatagramSocket(9999);
			event.lancaEventNotification(msg,j, comunication);
			socket.close();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage()+" -- Event get In");
		}
		
	}
	
	public ComunicationFacade getComunication() {
		return this.comunication;
	}
	
	
}
