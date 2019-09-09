package Util.observer;

import Util.Tabuleiro.Terreno;

public class EventoPreMonopolio extends Events {

	
	
	public EventoPreMonopolio(Terreno t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	public String getStringEvento() {
		return "O monopólio do terreno do tipo "+terreno.getCor()+" está quase completo!. Continue comprando. "
				+ "\nQuando completar o tipo poderá construir casas.";
	}


	
	

}
