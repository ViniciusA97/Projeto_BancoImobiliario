package Util.Tabuleiro;

import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;
import Util.jogadores.SemSaldoException;
import Util.observer.Observer;

public interface Comercial {

	public void compra(Jogadores j, Observer o)throws SemSaldoException;
	public int getPreco();
	public Jogador getProprietario();
	public void  pagaAluguel(Jogador recebe, Jogador paga, Observer o, Jogadores j) throws SemSaldoException;
}
