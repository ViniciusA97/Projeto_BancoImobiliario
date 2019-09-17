package Util.observer;

import Util.jogadores.Jogadores;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import Shared.ComunicationFacade;
import Util.jogadores.*;
public class Observer {
	
	private ComunicationFacade comunication;
	
	public Observer() {
		this.comunication = new ComunicationFacade();
		
	}
	
	
	public void fireEventoMonopolio(Events eventoMonopolio,  Jogadores j, DatagramSocket s) throws IOException {
		for(Jogador i: j.getJogadores()) {
			comunication.sendMessage(eventoMonopolio.getStringEvento(j), s, i.getAddress());
		}
		
	}
	
	public void fireEventoPreMonopolio(Events eventoPreMonopolio, Jogadores j, DatagramSocket s) throws IOException {
		for(Jogador i: j.getJogadores()) {
			comunication.sendMessage(eventoPreMonopolio.getStringEvento(j), s, i.getAddress());
		}
	}
	
	public void fireEventoPreHotel(Events eventoPreHotel, Jogadores j, DatagramSocket s) throws IOException {
		for(Jogador i: j.getJogadores()) {
			comunication.sendMessage(eventoPreHotel.getStringEvento(j), s, i.getAddress());
		}
	}
	
	public void fireEventGetIn(EventsNotification event , Jogadores j) {
		
	}
	
	
}
