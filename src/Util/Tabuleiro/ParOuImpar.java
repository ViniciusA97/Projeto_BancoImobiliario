package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;
import Util.jogadores.SemSaldoException;
import Util.observer.EventNotification;
import Util.observer.Observer;

public class ParOuImpar implements Casa{
	public ParOuImpar() {
		
	}
	
	public void fazAcao(Comandos cmd, Jogadores j) {
		
		Observer o = cmd.getObserver();
		o.fireEventNotification(toString(), EventNotification.getInstance(o.getId()), j);
		String aux = j.getJogadorDaVez().getUltimoDado();
		int soma = Integer.parseInt(aux, aux.charAt(0)) + Integer.parseInt(aux, aux.charAt(1));
		if(soma%2 == 0) {
			j.getJogadorDaVez().ganhaDinheiro(100);
			o.fireEventNotification("Número par, ganhou 100", EventNotification.getInstance(o.getId()), j);
			
		}else {
			try {
				j.getJogadorDaVez().perdeDinehiro(100);
			} catch (SemSaldoException e) {
				o.fireEventNotification(e.getMessage(),EventNotification.getInstance(o.getId()), j);
				
			}
			o.fireEventNotification("Número ímpar, paga 100", EventNotification.getInstance(o.getId()), j);
		}
	}
	
	public String toString() {
		return "Recebe 100 se tirou o número par da soma dos dados e paga 100 caso contrário"; 
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}
}
