package Util.Tabuleiro;

import Util.base.Comandos;
import Util.jogadores.Jogadores;

public class PasseLivrePrisao implements Casa{
	public PasseLivrePrisao() {
		
	}

	public void fazAcao(Comandos cmd, Jogadores j){
		
		j.getJogadorDaVez().adereCartaLiberdade();
		System.out.println("Você ganhou o passe Livre para sair da prisão, quando tiver na cadeia poderá usar-la");
	}
	public String getNome() {
		return "Passe Livre Da Prisão";
	}
}	
