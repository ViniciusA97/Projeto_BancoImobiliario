package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;

public class VaAteOInicio implements Casa{
	public VaAteOInicio() {
		
	}
	
	public void fazAcao(Comandos cmd, Jogadores j) {
		
		System.out.println(toString());
		j.getJogadorDaVez().setCasa(39);
		j.getJogadorDaVez().ganhaDinheiro(200);
	}
	
	public String toString() {
		return "Vá até o início e receba 200";
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}
}
