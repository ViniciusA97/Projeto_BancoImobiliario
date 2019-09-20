package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;
import Util.observer.EventNotification;
import Util.observer.Observer;

public class PasseLivrePrisao implements Casa{
	public PasseLivrePrisao() {
		
	}

	public void fazAcao(Comandos cmd, Jogadores j){
		
		Observer o = cmd.getObserver();
		o.fireEventNotification("Voc� ganhou o passe Livre para sair da pris�o, quando tiver na cadeia poder� usar-la", new EventNotification(), j);
		j.getJogadorDaVez().adereCartaLiberdade();
	}
	public String getNome() {
		return "Passe Livre Da Pris�o";
	}
}	
