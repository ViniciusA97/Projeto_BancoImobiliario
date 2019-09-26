package Util.Tabuleiro;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.HashMap;
import Shared.ComunicationFacade;
import Util.base.Comandos;
import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;
import Util.jogadores.SemSaldoException;
import Util.observer.EventNotification;
import Util.observer.Observer;

public class Empresas implements Casa,Comercial{
	private String nome;
	private int preco;
	private int hipoteca;
	private int multiplicador;
	private Jogador proprietario;
	
	public Empresas(String nome, int preco, int hipoteca, int multiplicador) {
		this.nome = nome;
		this.preco = preco;
		this.hipoteca = hipoteca;
		this.multiplicador = multiplicador;
		this.proprietario=null;
	}
	
	public void compra(Jogadores j, Observer o) throws SemSaldoException  {//compra a empresa
		if(j.getJogadorDaVez().getDinheiro()>=this.preco) {
			j.getJogadorDaVez().perdeDinehiro(preco);
			this.proprietario = j.getJogadorDaVez();
			j.getJogadorDaVez().addEmpresa(this);
			o.fireEventNotification("Compra com Sucesso", EventNotification.getInstance(o.getId()), j);
			o.fireEventNotification("Saldo atual do jogador "+j.getJogadorDaVez().getNome()+ " : "+j.getJogadorDaVez().getDinheiro(),EventNotification.getInstance(o.getId()), j);
			System.out.println("Saldo atual: "+j.getJogadorDaVez().getDinheiro());}
		else throw new SemSaldoException();
	
		}
	
	
	public String toString() {//retorna as informações da empresa
		return this.nome+": "+
				"\nPreço: "+this.preco+
				"\nHipoteca: "+this.hipoteca+
				"\nProprietário: "+this.proprietario;
	}

	public String getNome() {//retorna nome da empresa
		return this.nome;
	}

	public int getPreco() {//retorna preço da empresa
	 return this.preco;
	}

	public Jogador getProprietario() {//retorna proprietário da empresa
		return this.proprietario;
	}
	
	public void pagaAluguel(Jogador recebe, Jogador paga, Observer o,Jogadores j) throws SemSaldoException {//paga aluguel para empresa
		
		if(recebe.equals(paga)) return ;
		String aux= paga.getUltimoDado();
		String[] a=aux.split(" ");
		int dadosSomados= Integer.parseInt(a[0])+Integer.parseInt(a[1]);
		paga.perdeDinehiro(dadosSomados*multiplicador);
		recebe.ganhaDinheiro(dadosSomados*multiplicador);
		o.fireEventNotification("Jogador "+paga.getNome() +" caiu na empresa de "+recebe.getNome()+ " e pagou " +dadosSomados*multiplicador, EventNotification.getInstance(o.getId()), j);
		o.fireEventNotification("Saldo atual do jogador "+recebe.getNome()+" é: "+ recebe.getDinheiro(), EventNotification.getInstance(o.getId()), j);
		o.fireEventNotification("Saldo atual do jogador "+paga.getNome()+" é : "+paga.getDinheiro(), EventNotification.getInstance(o.getId()), j);
	}
	
	public void fazAcao(Comandos cmd, Jogadores j) {//faz ação referente a Empresas
		Observer o = cmd.getObserver();
		if(proprietario==null) {
			if(j.getJogadorDaVez().getDinheiro()>=preco) {
				o.fireEventNotification("O titulo do terreno está disponível por: $"+preco, EventNotification.getInstance(o.getId()), cmd.getJogadores());
				o.fireEventNotification(j.getJogadorDaVez().getNome()+" você possui $"+j.getJogadorDaVez().getDinheiro(),EventNotification.getInstance(o.getId()), cmd.getJogadores());
				boolean cond = true;
				HashMap<String,Object> map;
				ComunicationFacade comunication = o.getComunication();
				try {
					DatagramSocket socket = new DatagramSocket();
					comunication.sendMessage("Deseja comprar? [Sim] [Nao]", socket, j.getJogadorDaVez().getAddress());
					while(cond) {
						map =(HashMap<String, Object>) comunication.reciveMessage(socket);
						if(map.get("address").equals(j.getJogadorDaVez().getAddress())) {
							String aux = (String) map.get("msg");
							if(aux.equals("sim")||aux.equals("s")||aux.equals("Sim")) {
			    				compra(j,o);
			    				cond=false;
							}
						}
					}
				} catch (IOException | SemSaldoException e1) {
					System.out.println(e1.getMessage());
				}
			}
		}
		else {
    		try{
    			pagaAluguel(proprietario,j.getJogadorDaVez(),o,j);
    		}
    		catch (SemSaldoException e) {
    			System.out.println(e.getMessage());
    		}
		}
	}
		
}

	

