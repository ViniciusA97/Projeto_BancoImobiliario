package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;

public class VaPraPrisao implements Casa {



	
	
	public String getNome() {
		return "V� para a Pris�o";
	}

	@Override
	public void fazAcao(Comandos cmd, Jogadores j) {
		Prisao prisao = Prisao.getInstance();
		
		prisao.addPrisioneiro(j.getJogadorDaVez());
		j.getJogadorDaVez().setCasa(9);
		System.out.println("O jogador "+j.getJogadorDaVez().getNome()+" pegou a carta de Rev�s 'V� para a prisao' e esta detido. Na sua proxima "
				+ "rodada, ter� mais comandos disponiveis.");
		
	}
}
