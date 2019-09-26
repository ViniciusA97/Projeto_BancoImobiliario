package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;
import Util.observer.EventNotification;
import Util.observer.Observer;

public class PontoDePartida implements Casa {
	
	public PontoDePartida() {
		
	}

	@Override
	public String getNome() {
		return "Ponto de Partida";
	}

	@Override
	public void fazAcao(Comandos cmd, Jogadores j) {
		Observer o = cmd.getObserver();
		o.fireEventNotification(getNome(), EventNotification.getInstance(o.getId()), j);
		
	}
	

}
