package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;
import Util.jogadores.SemSaldoException;
import Util.observer.EventNotification;
import Util.observer.Observer;

public class CartasGenericas implements Casa{
	private String nome;
	private int pague;
	private int receba;
	
	public CartasGenericas(String nome, int pague, int receba) {
		this.nome = nome;
		this.pague = pague;
		this.receba = receba;
	}
	
	public void fazAcao(Comandos cmd,Jogadores j){//Faz ação de cartas de efeito genérico de Sorte e Revés
		Observer observer = cmd.getObserver();
		if(this.pague == 0) {
			observer.fireEventNotification(this.nome +" .Receba "+receba, new EventNotification(), cmd.getJogadores());
			j.getJogadorDaVez().ganhaDinheiro(this.receba);
			observer.fireEventNotification("Saldo atual de: "+j.getJogadorDaVez().getDinheiro(), new EventNotification(), cmd.getJogadores());

		}else {
			observer.fireEventNotification(this.nome +" .Pague "+pague, new EventNotification(), cmd.getJogadores());

			try {
				j.getJogadorDaVez().perdeDinehiro(this.pague);
				observer.fireEventNotification("Saldo atual de: "+j.getJogadorDaVez().getDinheiro(),new EventNotification(), cmd.getJogadores());
						
			} catch (SemSaldoException e) {
				observer.fireEventNotification(e.getMessage(), new EventNotification(), cmd.getJogadores());
			}
		}
	}
	
	public String toString() {//Retorna o efeito da carta
		return this.nome+": "+
				"\nPague: "+this.pague+
				"\nReceba: "+this.receba;
	}

	@Override
	public String getNome() {//retorna o nome da carta
		// TODO Auto-generated method stub
		return null;
	}
}
