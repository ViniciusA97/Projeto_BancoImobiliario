package Util.observer;

import Util.Tabuleiro.Terreno;
import Util.jogadores.Jogadores;

public class EventoMonopolio extends Events {
	 
	
	
	public EventoMonopolio(Terreno t) {
		super(t);
	
	}

	public String getStringEvento() {
		 Jogadores temp = Jogadores.getInstance();
		 return "Jogador "+ temp.getJogadorDaVez().getNome()+" acaba de comprar todos os terrenos da classe " + terreno.getCor()+". Em sua próxima \n"
		 		+ "jogada poderá usar o comando [construir] para adiquirir casas.";
	 }
}
