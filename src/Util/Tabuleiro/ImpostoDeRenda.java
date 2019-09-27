package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;
import Util.jogadores.SemSaldoException;
import Util.observer.EventNotification;
import Util.observer.Observer;

public class ImpostoDeRenda implements Casa {

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "Imposto de Renda";
	}

	@Override
	public void fazAcao(Comandos cmd, Jogadores j) {
		Observer o = cmd.getObserver();
		o.fireEventNotification("Terá que pagar 200$ para o imposto de Renda",new EventNotification(), j);
		try {
			j.getJogadorDaVez().perdeDinehiro(200);
		} catch (SemSaldoException e) {
			o.fireEventNotification(e.getMessage(), new EventNotification(), j);
		}
		o.fireEventNotification("Saldo Atual: "+j.getJogadorDaVez().getDinheiro(),new EventNotification(), j);
		
	}		

}
