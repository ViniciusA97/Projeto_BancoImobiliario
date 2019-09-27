package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;
import Util.jogadores.SemSaldoException;
import Util.observer.EventNotification;
import Util.observer.Observer;

public class LucrosOuDividendo implements Casa{
	@Override
	public String getNome() {
		
		return "Lucros ou Dividendos";
	}

	@Override
	public void fazAcao(Comandos cmd, Jogadores j) {
		Observer o = cmd.getObserver();
		j.getJogadorDaVez().ganhaDinheiro(200);
		o.fireEventNotification("Jogador "+ j.getJogadorDaVez().getNome()+ " Ganhou 200.", new EventNotification(), j);
		o.fireEventNotification("Saldo Atual: "+j.getJogadorDaVez().getDinheiro(),new EventNotification(), j);
		
	}

	
	public void geraEfeitoThrows(Jogador j) throws SemSaldoException {	
	}
	
}
