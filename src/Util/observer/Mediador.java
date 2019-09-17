package Util.observer;

import Util.Tabuleiro.Terreno;
import Util.jogadores.Jogadores;


public class Mediador {
	
	private Observer observer;
	
	public Mediador(Observer o) {
		
		this.observer=o;
	}
	
	public void confereEventoMonopolio(Jogadores jogadores) {
		Terreno temp = jogadores.getJogadorDaVez().getUltimoTerreno();
		if(temp.verificaMonopolioPorCor(jogadores.getJogadorDaVez())) {
			Events evento = new EventoMonopolio(temp);
			this.observer.fireEventoMonopolio(evento,jogadores);
		}
	}
	
	public void confereEventoPreMonopolio(Jogadores jogadores) {
		Terreno temp = jogadores.getJogadorDaVez().getUltimoTerreno();
		if(temp.verificaPreMonopolioPorCor(jogadores.getJogadorDaVez())) {
			Events evento = new EventoPreMonopolio(temp);
			this.observer.fireEventoPreMonopolio(evento, jogadores);
		}
	}
	
	public void EventoPreHotel(Terreno t, Jogadores j) {
		Events evento = new EventoPreHotel(t);
		this.observer.fireEventoPreHotel(evento, j);
	}
	
	
	public void confereDoisTipoMonopolio(Jogadores jogadores) {
		confereEventoMonopolio(jogadores);
		confereEventoPreMonopolio(jogadores);
	}

}

