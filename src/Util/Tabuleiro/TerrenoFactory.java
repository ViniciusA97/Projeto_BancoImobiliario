package Util.Tabuleiro;

public class TerrenoFactory {
	
	private GerenciamentoDeTerreno gt;
	
	public TerrenoFactory(GerenciamentoDeTerreno gerencia) {
		gt= gerencia;
	}
	
	public Casa criaTerreno (GerenciamentoDeTerreno gerencia,String cor,String nome, int preco, int aluguel, int aluguel1Casa, int aluguel2Casas, int aluguel3Casas,
			int aluguel4Casas, int aluguelHotel,int hipoteca, int precoCasa) {
		if(cor.equals("Amarelo")) {
			TerrenoAmarelo terreno=new TerrenoAmarelo(gerencia,nome,preco,aluguel,aluguel1Casa,aluguel2Casas,aluguel3Casas,aluguel4Casas,aluguelHotel,hipoteca, precoCasa);
			gt.addAmarelo(terreno);
			return terreno;
		}else if(cor.equals("Azul")) { 
			TerrenoAzul terreno = new TerrenoAzul(gerencia,nome,preco,aluguel,aluguel1Casa,aluguel2Casas,aluguel3Casas,aluguel4Casas,aluguelHotel,hipoteca, precoCasa);
			gt.addAzul(terreno);;
			return new TerrenoAzul(gerencia,nome,preco,aluguel,aluguel1Casa,aluguel2Casas,aluguel3Casas,aluguel4Casas,aluguelHotel,hipoteca, precoCasa);
		}else if(cor.equals("Ciano")) {
			TerrenoCiano terreno =new TerrenoCiano(gerencia,nome,preco,aluguel,aluguel1Casa,aluguel2Casas,aluguel3Casas,aluguel4Casas,aluguelHotel,hipoteca, precoCasa);
			gt.addCiano(terreno);;
			return  terreno;
		}else if(cor.equals("Laranja")) { 
			TerrenoLaranja terreno =new TerrenoLaranja(gerencia,nome,preco,aluguel,aluguel1Casa,aluguel2Casas,aluguel3Casas,aluguel4Casas,aluguelHotel,hipoteca, precoCasa);
			gt.addLaranja(terreno);;
			return terreno;
		}else if(cor.equals("Rosa")) { 
			TerrenoRosa terreno=new TerrenoRosa(gerencia,nome,preco,aluguel,aluguel1Casa,aluguel2Casas,aluguel3Casas,aluguel4Casas,aluguelHotel,hipoteca, precoCasa);
			gt.addRosa(terreno);;
			return terreno;
		}else if(cor.equals("Roxo")) { 
			TerrenoRoxo  terreno=new TerrenoRoxo(gerencia,nome,preco,aluguel,aluguel1Casa,aluguel2Casas,aluguel3Casas,aluguel4Casas,aluguelHotel,hipoteca, precoCasa);
			gt.addRoxo(terreno);
			return terreno;
		}else if(cor.equals("Verde")) { 
			TerrenoVerde terreno=new TerrenoVerde(gerencia,nome,preco,aluguel,aluguel1Casa,aluguel2Casas,aluguel3Casas,aluguel4Casas,aluguelHotel,hipoteca, precoCasa);
			gt.addVerde(terreno);;
			return terreno;
		}else if(cor.equals("Vermelho")) {
			TerrenoVermelho terreno=new TerrenoVermelho(gerencia,nome,preco,aluguel,aluguel1Casa,aluguel2Casas,aluguel3Casas,aluguel4Casas,aluguelHotel,hipoteca, precoCasa);
			gt.addVermelho(terreno);
			return terreno;
		}
		return null;
	}
}