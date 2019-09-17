package Util.observer;

import Util.Tabuleiro.Terreno;
import Util.jogadores.Jogadores;

public class EventoPreHotel extends Events{

	public EventoPreHotel(Terreno t) {
		super(t);
	}

	@Override
	public String getStringEvento(Jogadores temp) {
		return "Voc� est� quase comprando um hotel para o terreno "+terreno.getNome()+"."+
				"\nQuando compro-lo, seu terreno valer� uma fortuna!";
	}
	
}
