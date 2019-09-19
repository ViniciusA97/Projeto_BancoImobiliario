package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;
import Util.observer.*;
import Util.observer.Observer;

public class JogueDadosNovamente implements Casa{
	
	public JogueDadosNovamente() {
		
	}

	public void fazAcao(Comandos cmd, Jogadores j) {
		Observer o = cmd.getObserver();
		o.fireEventNotification("Jogador "+ j.getJogadorDaVez().getNome()+" tirou a carta 'Jogue os dados novamente'.", new EventNotification(), j);
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
