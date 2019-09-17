package Util.observer;

import Util.jogadores.Jogadores;

public interface Listeners {
	public void eventoMonopolio(Events eventoMonopolio,Jogadores j);
	public void eventoPreMonopolio(Events EventoPreMonopolio,Jogadores j);
	public void eventoPreHotel(Events eventoPreHotel, Jogadores j);
}
