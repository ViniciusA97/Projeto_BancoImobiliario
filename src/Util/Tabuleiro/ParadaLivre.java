package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;
import Util.observer.EventNotification;
import Util.observer.Observer;

public class ParadaLivre implements Casa {

	@Override
	public String getNome() {
		 return "Parada Livre";
	}

	@Override
	public void fazAcao(Comandos cmd, Jogadores j) {
		Observer o = cmd.getObserver();
		o.fireEventNotification(getNome(), new EventNotification(), j);
		
	}

	
}
