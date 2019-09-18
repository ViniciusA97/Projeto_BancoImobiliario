package Util.Tabuleiro;

import Util.jogadores.Jogador;

public class TerrenoAmarelo extends Terreno implements Casa,Comercial {

	public TerrenoAmarelo(int index,String nome, int preco, int aluguel, int aluguel1Casa, int aluguel2Casas, int aluguel3Casas,
			int aluguel4Casas, int aluguelHotel, int hipoteca, int precoCasa) {
			
		super( index,nome, preco, aluguel, aluguel1Casa, aluguel2Casas, aluguel3Casas, aluguel4Casas, aluguelHotel, hipoteca,
				precoCasa);
		
	}
	
	public String getCor() {
		return "Amarelo";
	}

	public boolean verificaMonopolioPorCor(Jogador j) {
		return this.gerenciador.verificaAmarelo(j);
	}

	@Override
	public boolean verificaPreMonopolioPorCor(Jogador j) {
		return gerenciador.verificaAmareloPre(j);
	}
}
