package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;

public class VaParaPrisaoSR implements Casa{
	
	private Prisao prisao;
	
	public VaParaPrisaoSR(int index) {
		this.prisao = Prisao.getInstance(index);
	}
	
	public void geraEfeito(Jogador j) {
}
	
	public String toString() {
		return "Vá para a prisão";
	}

	@Override
	public void fazAcao(Comandos cmd, Jogadores j) {
		prisao.addPrisioneiro(j.getJogadorDaVez());
		j.getJogadorDaVez().setCasa(9);
		System.out.println("O jogador "+j.getJogadorDaVez().getNome()+" pegou a carta de Revés 'Va para a prisao' e esta detido. Na sua proxima "
				+ "rodada, terá mais comandos disponiveis.");
		
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}
}
