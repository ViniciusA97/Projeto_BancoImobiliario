package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;

public class VaPraPrisao implements Casa {

	private Prisao prisao;

	public VaPraPrisao(int index) {
		this.prisao = Prisao.getInstance(index);
	}
	
	public String getNome() {
		return "Vá para a Prisão";
	}

	@Override
	public void fazAcao(Comandos cmd, Jogadores j) {
		
		prisao.addPrisioneiro(j.getJogadorDaVez());
		j.getJogadorDaVez().setCasa(9);
		System.out.println("O jogador "+j.getJogadorDaVez().getNome()+" pegou a carta de Revés 'Vá para a prisao' e esta detido. Na sua proxima "
				+ "rodada, terá mais comandos disponiveis.");
		
	}
}
