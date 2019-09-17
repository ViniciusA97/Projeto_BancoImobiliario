package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;
import Util.jogadores.SemSaldoException;

public class DiaDoCasamento implements Casa{
	
	public DiaDoCasamento() {
	}
	
	public void fazAcao(Comandos cmd, Jogadores j) {
		System.out.println(toString());
		for(Jogador i :j.getJogadores()) {
			if(!i.equals(j.getJogadorDaVez())) {
				try {
					i.perdeDinehiro(50);
				} catch (SemSaldoException e) {
					System.out.println(e.getMessage());
				}
				j.getJogadorDaVez().ganhaDinheiro(50);
			}
		}
	}
	
	public String toString() {
		return "O seu dia do casamento chegou, receba os presentes"+
				"\nCada jogador paga 50 ao noivo/ noiva";
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}
}
