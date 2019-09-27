package Util.base;

import java.util.ArrayList;

import Util.Tabuleiro.*;
import Util.jogadores.Jogador;

public class GeraString {

	private static ArrayList<GeraString> instance = null;
	private ArrayList<Terreno> construcoes = new ArrayList<Terreno>(40);
	private FachadaTabuleiro fachadaT;
	
	private GeraString(int index) {
		this.fachadaT= FachadaTabuleiro.getInstance(index);
	}
	
	public static GeraString getInstance(int index) {
		if(instance.get(index) == null) instance.set(index, new GeraString(index));
		return instance.get(index);
	}
	
	public String geraInicioDaJogada(Jogador j) {//gera a string das opções do jogador de acordo com sua situação no jogo

		System.out.println("Vez do jogador "+j.getNome()+" De cor ("
				+j.getCor()+").");
		if(fachadaT.procuraPrisioneiro(j)) {
			if(j.getValorCarta()) return "[pagar][status][sair][carta][jogar]";
			return "[pagar][status][sair][jogar]";
		}
		else if(fachadaT.verificaMonopolio(j)) {
			if(j.verificaCasas()) {
				return "[jogar][status][sair][construir][vender]";
			}else {
				this.construcoes=new ArrayList<Terreno>();
				return "[jogar][status][sair][construir]";
				}
		}
		return "[jogar][status][sair]";
	}
	
	public String getStringConstrucao(Jogador j) {//retorna a string com todas as propriedades que estão disponíveis para construção de casa
		int cont=0;
		String propriedades="[Digite o numero do terreno para construir]['0' para sair] \n";
		if(this.fachadaT.verificaAmarelo(j)) {
			for(TerrenoAmarelo i: this.fachadaT.getTerrenoAmarelo()) { 
				propriedades+=++cont +"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço da casa: "+ i.getPrecoCasa()+"\n";
				construcoes.add(i);
			}
		}
		if(this.fachadaT.verificaAzul(j)) {
			for(TerrenoAzul i: this.fachadaT.getTerrenoAzul()) { 
				propriedades+= ++cont +"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço da casa: "+ i.getPrecoCasa()+"\n";
				construcoes.add(i);
			}
		}
		if(this.fachadaT.verificaCiano(j)) {
			for(TerrenoCiano i: this.fachadaT.getTerrenoCiano()) { 
				propriedades+= ++cont +"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço da casa: "+ i.getPrecoCasa()+"\n";
				construcoes.add(i);
			}
		}
		if(this.fachadaT.verificaLaranja(j)) {
			for(TerrenoLaranja i: this.fachadaT.getTerrenoLaranja()) { 
				propriedades+= ++cont +"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço da casa: "+ i.getPrecoCasa()+"\n";
				construcoes.add(i);
			}
		}
		if(this.fachadaT.verificaRosa(j)) {
			for(TerrenoRosa i: this.fachadaT.getTerrenoRosa()) { 
				propriedades+= ++cont +"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço da casa: "+ i.getPrecoCasa()+"\n";
				construcoes.add(i);
			}
		}
		if(this.fachadaT.verificaRoxo(j)) {
			for(TerrenoRoxo i: this.fachadaT.getTerrenoRoxo()) { 
				propriedades+= ++cont +"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço da casa: "+ i.getPrecoCasa()+"\n";
				construcoes.add(i);
			}
		}
		if(this.fachadaT.verificaVerde(j)) {
			for(TerrenoVerde i: this.fachadaT.getTerrenoVerde()) { 
				propriedades+= ++cont +"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço da casa: "+ i.getPrecoCasa()+"\n";
				construcoes.add(i);
			}
		}
		if(this.fachadaT.verificaVermelho(j)) {
			for(TerrenoVermelho i: this.fachadaT.getTerrenoVermelho()) { 
				propriedades+= ++cont+"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço da casa: "+ i.getPrecoCasa()+"\n";
				construcoes.add(i);
			}
		}
	return propriedades;
	}
	
	public String getStringVenda(Jogador j) {//retorna string de terrenos com casa disponíveis para venda
		int cont=0;
	
		String propriedades="[Digite o numero do terreno para realizar a venda da casa]['0' para sair] \n";
		if(this.fachadaT.verificaAmarelo(j)) {
			for(TerrenoAmarelo i: this.fachadaT.getTerrenoAmarelo()) {
				if(i.getCasas() > 0) {
					propriedades += ++cont+"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço de venda da casa: "+ i.getPrecoCasa()/2+"\n";
					construcoes.add(i);
				}
			}
		}
		if(this.fachadaT.verificaAzul(j)) {
			for(TerrenoAzul i: this.fachadaT.getTerrenoAzul()) {
				if(i.getCasas() > 0) {
					propriedades += ++cont+"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço de venda da casa: "+ i.getPrecoCasa()/2+"\n";
					construcoes.add(i);
				}
			}
		}
		if(this.fachadaT.verificaCiano(j)) {
			for(TerrenoCiano i: this.fachadaT.getTerrenoCiano()) { 
				if(i.getCasas() > 0) {
					propriedades += ++cont+"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço de venda da casa: "+ i.getPrecoCasa()/2+"\n";
					construcoes.add(i);
				}
			}
		}
		if(this.fachadaT.verificaLaranja(j)) {
			for(TerrenoLaranja i: this.fachadaT.getTerrenoLaranja()) { 
				if(i.getCasas() > 0) {
					propriedades += ++cont+"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço de venda da casa: "+ i.getPrecoCasa()/2+"\n";
					construcoes.add(i);
				}
			}
		}
		if(this.fachadaT.verificaRosa(j)) {
			for(TerrenoRosa i: this.fachadaT.getTerrenoRosa()) { 
				if(i.getCasas() > 0) {
					propriedades += cont+"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço de venda da casa: "+ i.getPrecoCasa()/2+"\n";
					cont++;
					construcoes.add(i);
				}
			}
		}
		if(this.fachadaT.verificaRoxo(j)) {
			for(TerrenoRoxo i: this.fachadaT.getTerrenoRoxo()) { 
				if(i.getCasas() > 0) {
					propriedades += ++cont+"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço de venda da casa: "+ i.getPrecoCasa()/2+"\n";
					construcoes.add(i);
				}
			}
		}
		if(this.fachadaT.verificaVerde(j)) {
			for(TerrenoVerde i: this.fachadaT.getTerrenoVerde()) { 
				if(i.getCasas() > 0) {
					propriedades += ++cont+"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço de venda da casa: "+ i.getPrecoCasa()/2+"\n";
					construcoes.add(i);
				}
			}
		}
		if(this.fachadaT.verificaVermelho(j)) {
			for(TerrenoVermelho i: this.fachadaT.getTerrenoVermelho()) { 
				if(i.getCasas() > 0) {
					propriedades += ++cont+"- "+i.getNome()+" Casas: "+i.getCasas()+ "Preço de venda da casa: "+ i.getPrecoCasa()/2+"\n";
					construcoes.add(i);
				}
			}
		}
		return propriedades;
	}
	
	public Terreno getTerreno(int i) throws IndexNaoSuportadoEx{//retorna terreno específico
		return this.construcoes.get(i);
	}
}
