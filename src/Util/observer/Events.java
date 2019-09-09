package Util.observer;

import Util.Tabuleiro.Terreno;

public abstract class Events {

	protected Terreno terreno;
	
	public Events(Terreno t) {
		terreno=t;
	}
	
	public abstract String getStringEvento();
}
