package Util.Tabuleiro;

import java.util.ArrayList;

import Util.base.Comandos;
import Util.jogadores.*;
import Util.observer.EventNotification;
import Util.observer.Observer;

public class Prisao implements Casa {

	private ArrayList<Jogador> presos;
	private static ArrayList<Prisao> instance;
	
	private Prisao() {
		presos=new ArrayList<Jogador>();
	}
	
	public static Prisao getInstance(int index) {
		if(instance.get(index)==null) instance.set(index, new Prisao());
		return instance.get(index);
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
	
	public void tentaSairPrisao(String aux, Jogadores j, Observer o) {
		
		if(aux.charAt(0)==(aux.charAt(2))) {
			o.fireEventNotification("Jogador " + j.getJogadorDaVez().getNome()+" tirou  [" + aux.charAt(0) +","+ aux.charAt(1)+"] e saiu da pris�o."
					+ "Em seu pr�ximo turno poder� mover normalmente. Hist�rico de dados removidos.", new EventNotification(), j);
			j.getJogadorDaVez().apagaTudoDados();
			this.retiraPrisioneiro(j.getJogadorDaVez());
			
		}else {
			o.fireEventNotification("Jogador " + j.getJogadorDaVez().getNome()+ " tirou os dados ["+aux.charAt(0)+", "+aux.charAt(1)+", infelizmente n�o saiu da pris�o. ", new EventNotification(), j);			
		}
		
	}
	
	public void vaiPraPrisao(Jogadores j, Observer o) {
		
		String [] dados= j.getJogadorDaVez().getAll();
		o.fireEventNotification("Por ter tirado 3 vezes n�meros iguais nos dados o jogador foi jogado na pris�o.", new EventNotification(), j);
		o.fireEventNotification("Dados: ["+dados[0].charAt(0)+","+dados [0].charAt(1)+"] , ["+dados[1].charAt(0)+","+dados[1].charAt(1)+"] , ["+dados[2].charAt(0)+","+dados[2].charAt(1)+"]", new EventNotification(), j);
		o.fireEventNotification("Em suas pr�ximas jogadas ele poder� escolher pagar para sair, tentar acertar os dados iguais ou"
				+ "\nse possuir a carta de libera��o ela poder� ser usada.", new EventNotification(), j);
		this.addPrisioneiro(j.getJogadorDaVez());
	}


}
