package Util.observer;

import java.io.IOException;

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
			try {
				this.observer.fireEventoMonopolio(evento,jogadores);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void confereEventoPreMonopolio(Jogadores jogadores) {
		Terreno temp = jogadores.getJogadorDaVez().getUltimoTerreno();
		if(temp.verificaPreMonopolioPorCor(jogadores.getJogadorDaVez())) {
			Events evento = new EventoPreMonopolio(temp);
			try {
				this.observer.fireEventoPreMonopolio(evento, jogadores);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void EventoPreHotel(Terreno t, Jogadores j) {
		Events evento = new EventoPreHotel(t);
		try {
			this.observer.fireEventoPreHotel(evento, j);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	
	public void confereDoisTipoMonopolio(Jogadores jogadores) {
		confereEventoMonopolio(jogadores);
		confereEventoPreMonopolio(jogadores);
	}

}

