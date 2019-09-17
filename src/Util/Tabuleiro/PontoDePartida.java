package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;

public class PontoDePartida implements Casa {
	
	public PontoDePartida() {
		
	}

	@Override
	public String getNome() {
		return "Ponto de Partida";
	}

	@Override
	public void fazAcao(Comandos cmd, Jogadores j) {
		System.out.println(getNome());
		
	}
	

}
