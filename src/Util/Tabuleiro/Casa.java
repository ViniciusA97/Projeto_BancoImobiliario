package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;

public interface Casa {
	
	public void fazAcao(Comandos cmd, Jogadores j);
	public String getNome();

}
