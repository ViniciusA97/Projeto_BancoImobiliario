package Util.Tabuleiro;

import java.util.ArrayList;

import Util.base.Comandos;
import Util.jogadores.*;

public class Prisao implements Casa {

	private ArrayList<Jogador> presos;
	private static Prisao instance;
	
	private Prisao() {
		presos=new ArrayList<Jogador>();
	}
	
	public static Prisao getInstance() {
		if(instance==null) instance= new Prisao();
		return instance;
	}

	public void addPrisioneiro(Jogador j) {
		presos.add(j);
		j.apagaTudoDados();
	}
	
	public String getNome() {
		return "Pris�o, esta como visitante";
	}
	
	public boolean procuraPrisioneiro(Jogador j) {
		
		if(presos.isEmpty()) return false;
		else {
			for(Jogador i: this.presos) {
				if(i.equals(j)) return true;
				}
			return false;
		}
		
	}
	
	public void retiraPrisioneiro(Jogador j) {
		presos.remove(j);
	}
	
	public ArrayList<Jogador> getPresos(){
		return this.presos;
	}

	@Override
	public void fazAcao(Comandos cmd, Jogadores j) {
	}
	
	public void tentaSairPrisao(String aux, Jogadores j) {
		
		if(aux.charAt(0)==(aux.charAt(2))) {
			System.out.println("Jogador " + j.getJogadorDaVez().getNome()+" tirou  [" + aux.charAt(0) +","+ aux.charAt(1)+"] e saiu da pris�o."
					+ "Em seu pr�ximo turno poder� mover normalmente. Hist�rico de dados removidos." );
			j.getJogadorDaVez().apagaTudoDados();
			this.retiraPrisioneiro(j.getJogadorDaVez());
			
		}else {
			System.out.println("Jogador " + j.getJogadorDaVez().getNome()+ " tirou os dados ["+aux.charAt(0)+", "+aux.charAt(1)+", infelizmente n�o saiu da pris�o. ");
		}
		
	}
	
	public void vaiPraPrisao(Jogadores j) {
		
		String [] dados= j.getJogadorDaVez().getAll();
		System.out.println("Por ter tirado 3 vezes n�meros iguais nos dados o jogador foi jogado na pris�o.");
		System.out.println("Dados: ["+dados[0].charAt(0)+","+dados [0].charAt(1)+"] , ["+dados[1].charAt(0)+","+dados[1].charAt(1)+"] , ["+dados[2].charAt(0)+","+dados[2].charAt(1)+"]");
		System.out.println("Em suas pr�ximas jogadas ele poder� escolher pagar para sair, tentar acertar os dados iguais ou"
				+ "\nse possuir a carta de libera��o ela poder� ser usada.");
		this.addPrisioneiro(j.getJogadorDaVez());
	}


}
