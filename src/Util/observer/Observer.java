package Util.observer;

import Util.jogadores.Jogadores;

public class Observer {
	
	
	public Observer() {
		
		
	}
	
	
	public void fireEventoMonopolio(Events eventoMonopolio,  Jogadores j) {
		j.getJogadorDaVez().eventoMonopolio(eventoMonopolio,j);
		
	}
	
	public void fireEventoPreMonopolio(Events eventoPreMonopolio, Jogadores j) {
		j.getJogadorDaVez().eventoPreMonopolio(eventoPreMonopolio,j);
		
	}
	
	public void fireEventoPreHotel(Events eventoPreHotel, Jogadores j) {
		j.getJogadorDaVez().eventoPreHotel(eventoPreHotel,j);
		
	}
	
	
}
