package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;

public class VaParaPrisaoSR implements Casa{
	public VaParaPrisaoSR() {
		
	}
	
	public void geraEfeito(Jogador j) {
}
	
	public String toString() {
		return "V� para a pris�o";
	}

	@Override
	public void fazAcao(Comandos cmd, Jogadores j) {
		Prisao prisao = Prisao.getInstance();
		prisao.addPrisioneiro(j.getJogadorDaVez());
		j.getJogadorDaVez().setCasa(9);
		System.out.println("O jogador "+j.getJogadorDaVez().getNome()+" pegou a carta de Rev�s 'Va para a prisao' e esta detido. Na sua proxima "
				+ "rodada, ter� mais comandos disponiveis.");
		
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}
}
