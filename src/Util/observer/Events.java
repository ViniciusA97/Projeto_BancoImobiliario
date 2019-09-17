package Util.observer;

import Util.Tabuleiro.Terreno;
import Util.jogadores.Jogadores;

public abstract class Events {

	protected Terreno terreno;
	
	public Events(Terreno t) {
		terreno=t;
	}
	
	public abstract String getStringEvento(Jogadores temp);
}
