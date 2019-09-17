package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;
import Util.jogadores.SemSaldoException;

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
	
		if(this.pague == 0) {
			System.out.println(this.nome +" .Receba "+receba );
			j.getJogadorDaVez().ganhaDinheiro(this.receba);
			System.out.println("Saldo atual de: "+j.getJogadorDaVez().getDinheiro());
		}else {
			System.out.println(this.nome +" .Pague "+pague );
			try {
				j.getJogadorDaVez().perdeDinehiro(this.pague);
			} catch (SemSaldoException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Saldo atual de: "+j.getJogadorDaVez().getDinheiro());
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
