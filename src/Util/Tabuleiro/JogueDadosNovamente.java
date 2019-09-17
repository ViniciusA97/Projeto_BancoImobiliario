package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;

public class JogueDadosNovamente implements Casa{
	
	public JogueDadosNovamente() {
		
	}

	public void fazAcao(Comandos cmd, Jogadores j) {
		
		System.out.println("Jogador "+ j.getJogadorDaVez().getNome()+" tirou a carta 'Jogue os dados novamente'.");
		cmd.comandoJogar();
	}
	
	public String toString() {
		return "Jogue os dados novamente";
	}

	@Override
	public String getNome() {
		return toString();
	}
}
