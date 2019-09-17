package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;
import Util.jogadores.SemSaldoException;

public class LucrosOuDividendo implements Casa{
	@Override
	public String getNome() {
		
		return "Lucros ou Dividendos";
	}

	@Override
	public void fazAcao(Comandos cmd, Jogadores j) {
		
		j.getJogadorDaVez().ganhaDinheiro(200);
		System.out.println("Jogador "+ j.getJogadorDaVez().getNome()+ " Ganhou 200.");
		System.out.println("Saldo Atual: "+j.getJogadorDaVez().getDinheiro());
		
	}

	
	public void geraEfeitoThrows(Jogador j) throws SemSaldoException {	
	}
	
}
