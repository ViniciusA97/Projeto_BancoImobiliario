package Util.Tabuleiro;

import java.util.ArrayList;

import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;
import Util.jogadores.SemSaldoException;
import Util.observer.Observer;

public class FachadaTabuleiro {

	private Prisao prisao;
	private Tabuleiro tabuleiro;
	private GerenciamentoDeTerreno gerenciamentoTerreno;
	
	public FachadaTabuleiro(){
		this.prisao= new Prisao();
		this.gerenciamentoTerreno = new GerenciamentoDeTerreno();
		this.tabuleiro = new Tabuleiro(this.gerenciamentoTerreno, prisao);
		
	}

	public void tentaSairPrisao(String aux, Jogadores j,Observer o) {
		this.prisao.tentaSairPrisao(aux, j, o);
		
	}
	
	public void vaiPraPrisao(Jogadores j, Observer o) {

		prisao.vaiPraPrisao(j,o);
	}
	
	public boolean procuraPrisioneiro(Jogador j) {
		return this.prisao.procuraPrisioneiro(j);
	}
	
	public Casa getCasaTabuleiro(int v) {
		return this.tabuleiro.getCasa(v);
	}
	
	
	public void retiraJogadorDaPrisao(Jogador j) {
		this.prisao.retiraPrisioneiro(j);
	}
	
	public boolean verificaMonopolio(Jogador j) {
		return this.gerenciamentoTerreno.verificaMonopolio(j);
	}
	
	public boolean verificaAmarelo(Jogador j) {
		return this.gerenciamentoTerreno.verificaAmarelo(j);
	}
	
	public boolean verificaAzul(Jogador j) {
		return this.gerenciamentoTerreno.verificaAzul(j);
	}
	
	public boolean verificaCiano(Jogador j) {
		return this.gerenciamentoTerreno.verificaCiano(j);
	}
	
	public boolean verificaRoxo(Jogador j) {
		return this.gerenciamentoTerreno.verificaRoxo(j);
	}
	
	public boolean verificaRosa(Jogador j) {
		return this.gerenciamentoTerreno.verificaRosa(j);
	}
	
	public boolean verificaLaranja(Jogador j) {
		return this.gerenciamentoTerreno.verificaLaranja(j);
	}
	
	public boolean verificaVermelho(Jogador j) {
		return this.gerenciamentoTerreno.verificaVermelho(j);
	}
	
	public boolean verificaVerde(Jogador j) {
		return this.gerenciamentoTerreno.verificaVerde(j);
	}
	
	public ArrayList<TerrenoAmarelo> getTerrenoAmarelo(){
		return this.gerenciamentoTerreno.getListAmarelo();
	}
	
	public ArrayList<TerrenoAzul> getTerrenoAzul(){
		return this.gerenciamentoTerreno.getListAzul();
	}
	
	public ArrayList<TerrenoRoxo> getTerrenoRoxo(){
		return this.gerenciamentoTerreno.getListRoxo();
	}
	
	public ArrayList<TerrenoRosa> getTerrenoRosa(){
		return this.gerenciamentoTerreno.getListRosa();
	}
	
	public ArrayList<TerrenoVerde> getTerrenoVerde(){
		return this.gerenciamentoTerreno.getListVerde();
	}
	
	public ArrayList<TerrenoVermelho> getTerrenoVermelho(){
		return this.gerenciamentoTerreno.getListVermelho();
	}
	
	public ArrayList<TerrenoLaranja> getTerrenoLaranja(){
		return this.gerenciamentoTerreno.getListLaranja();
	}
	
	public ArrayList<TerrenoCiano> getTerrenoCiano(){
		return this.gerenciamentoTerreno.getListCiano();
	}
	
	public void compraCasa(Terreno terreno, Jogador j) {
		try {
			terreno.compraCasa(j);
		} catch (SemSaldoException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void vendeCasa(Terreno terreno) {
		terreno.vendeCasa();
	}
}
