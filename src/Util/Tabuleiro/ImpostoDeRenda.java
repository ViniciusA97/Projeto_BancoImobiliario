package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;
import Util.jogadores.SemSaldoException;

public class ImpostoDeRenda implements Casa {

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "Imposto de Renda";
	}

	@Override
	public void fazAcao(Comandos cmd, Jogadores j) {

		System.out.println("Terá que pagar 200$ para o imposto de Renda");
		try {
			j.getJogadorDaVez().perdeDinehiro(200);
		} catch (SemSaldoException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Saldo Atual: "+j.getJogadorDaVez().getDinheiro() );
		
	}		

}
