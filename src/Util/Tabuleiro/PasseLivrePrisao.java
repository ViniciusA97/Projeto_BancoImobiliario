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
		o.fireEventNotification("Você ganhou o passe Livre para sair da prisão, quando tiver na cadeia poderá usar-la", new EventNotification(), j);
		j.getJogadorDaVez().adereCartaLiberdade();
	}
	public String getNome() {
		return "Passe Livre Da Prisão";
	}
}	
