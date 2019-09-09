package Util.Tabuleiro;

import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;
import Util.jogadores.SemSaldoException;

public class LucrosOuDividendo implements Casa{
	@Override
	public String getNome() {
		
		return "Lucros ou Dividendos";
	}

	@Override
	public void fazAcao() {
		Jogadores temp= Jogadores.getInstance();
		temp.getJogadorDaVez().ganhaDinheiro(200);
		System.out.println("Jogador "+ temp.getJogadorDaVez().getNome()+ " Ganhou 200.");
		System.out.println("Saldo Atual: "+temp.getJogadorDaVez().getDinheiro());
		
	}

	
	public void geraEfeitoThrows(Jogador j) throws SemSaldoException {	
	}
	
}
