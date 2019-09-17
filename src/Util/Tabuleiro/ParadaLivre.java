package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;

public class ParadaLivre implements Casa {

	@Override
	public String getNome() {
		 return "Parada Livre";
	}

	@Override
	public void fazAcao(Comandos cmd, Jogadores j) {
		System.out.println(getNome());
		
	}

	
}
