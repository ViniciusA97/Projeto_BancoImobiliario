package Util.Tabuleiro;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.HashMap;

import Shared.ComunicationFacade;
import Util.base.Comandos;
import Util.base.FachadaComunicacao;
import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;
import Util.jogadores.SemSaldoException;
import Util.observer.EventNotification;
import Util.observer.Mediador;
import Util.observer.Observer;

public abstract class Terreno implements Comercial, Casa{
	private String nome;
	private int preco;
	private int aluguel;
	private int aluguel1Casa;
	private int aluguel2Casas;
	private int aluguel3Casas;
	private int aluguel4Casas;
	private int aluguelHotel;
	private int casas;
	private int hipoteca;
	private int precoCasa;
	private Jogador proprietario;
	protected GerenciamentoDeTerreno gerenciador;
	
	public Terreno(int index,String nome, int preco, int aluguel, int aluguel1Casa, int aluguel2Casas, int aluguel3Casas, int aluguel4Casas, int aluguelHotel,
			int hipoteca, int precoCasa) {
		this.gerenciador = GerenciamentoDeTerreno.getInstance(index);
		this.nome = nome;
		this.preco = preco;
		this.aluguel = aluguel;
		this.aluguel1Casa = aluguel1Casa;
		this.aluguel2Casas = aluguel2Casas;
		this.aluguel3Casas = aluguel3Casas;
		this.aluguel4Casas = aluguel4Casas;
		this.aluguelHotel = aluguelHotel;
		this.casas = 0;
		this.hipoteca = hipoteca;
		this.precoCasa = precoCasa;
		this.proprietario=null;
		this.gerenciador = GerenciamentoDeTerreno.getInstance(index);
	}
	
	public void compraCasa(Jogador j) throws SemSaldoException {//adiciona uma casa ao terreno
		if(this.casas == 0) {
			j.perdeDinehiro(precoCasa);
			this.aluguel = this.aluguel1Casa;
			this.casas += 1;
		}else if(this.casas == 1) {
			j.perdeDinehiro(precoCasa);
			this.aluguel = this.aluguel2Casas;
			this.casas += 1; 
		}else if(this.casas == 2) {
			j.perdeDinehiro(precoCasa);
			this.aluguel = this.aluguel3Casas;
			this.casas +=1;
		}else if(this.casas == 3) {
			j.perdeDinehiro(precoCasa);
			this.aluguel = this.aluguel4Casas;
			this.casas += 1;
		}else if(this.casas == 4) {
			j.perdeDinehiro(precoCasa);
			this.aluguel = this.aluguelHotel;
			this.casas += 1;
		}
	}
	
	public void vendeCasa() {//vende uma casa do terreno
		this.casas -= 1;
		this.proprietario.ganhaDinheiro(this.precoCasa/2);
	}
	
	public void pagaAluguel(Jogador recebe, Jogador paga,Observer o, Jogadores j) throws SemSaldoException {//paga aluguel da propriedade
		paga.perdeDinehiro(this.aluguel);
		recebe.ganhaDinheiro(this.aluguel);
		o.fireEventNotification("Jogador "+paga.getNome()+" caiu no terreno do jogador "+recebe.getNome()+" e irá pagar o aluguel.",EventNotification.getInstance(o.getId()), j);
		o.fireEventNotification("Pagamento de "+ aluguel +" efetuado com Sucesso", EventNotification.getInstance(o.getId()), j);
		o.fireEventNotification("Saldo atual do jogador "+recebe.getNome()+" é: "+ recebe.getDinheiro(),EventNotification.getInstance(o.getId()), j);
		o.fireEventNotification("Saldo atual do jogador "+paga.getNome()+" é : "+paga.getDinheiro(),EventNotification.getInstance(o.getId()), j);
		
	}
	
	public int getHipoteca() {//retorna hipoteca do terreno
		return this.hipoteca;
	}
	
	public Jogador getProprietario() {//retorn proprietário do terreno
		return this.proprietario;
	}
	public int getPreco() {//retorna preço do terreno
		return this.preco;
	}
	
	public String toString() {//retorna info do terreno
		return this.nome+": "+
					"\nPreço: "+this.preco+
					"\nAluguel: "+this.aluguel+
					"\nCasas: "+this.casas+
					"\nHipoteca: "+this.hipoteca+
					"\nProprietário: "+this.proprietario;
	}

	
	public String getNome() {//retorna nome do terreno
		return this.nome;
	}
	public void compra(Jogadores j,Observer o) throws SemSaldoException  {//faz ação de compra do terreno
		if(j.getJogadorDaVez().getDinheiro()>=this.preco) {
			j.getJogadorDaVez().perdeDinehiro(preco);
			this.proprietario = j.getJogadorDaVez();
			j.getJogadorDaVez().addTerreno(this);
			o.fireEventNotification("Compra feita com Sucesso!",EventNotification.getInstance(o.getId()), j);
			o.fireEventNotification("Saldo atual: "+j.getJogadorDaVez().getDinheiro(), EventNotification.getInstance(o.getId()), j);
		}
		else throw new SemSaldoException();
	
		}
	
	public int getCasas() {//retorna quantidade de casas do terreno
		return this.casas;
	}
	
	public String getCor() {
		return null;
	}
	
	public int getPrecoCasa() {//retorna preço de uma casa no terreno
		return this.precoCasa;
	}
	public void fazAcao(Comandos cmd, Jogadores j) {//faz a ação referente a Terreno
		Observer o = cmd.getObserver();
		FachadaComunicacao fachada= FachadaComunicacao.getInstance();
		if(proprietario==null) {
			if(j.getJogadorDaVez().getDinheiro()>=preco) {
				
				o.fireEventNotification("O titulo do terreno está disponível por: $"+preco +" do tipo "+ this.getCor(), EventNotification.getInstance(o.getId()), j);
				o.fireEventNotification(j.getJogadorDaVez().getNome()+" você possui $"+j.getJogadorDaVez().getDinheiro(),EventNotification.getInstance(o.getId()), j);
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
					o.fireEventNotification(e1.getMessage(), EventNotification.getInstance(o.getId()), j);
				}
    			String compra=fachada.inputString("Deseja comprar? [Sim] [Nao]");
    			if(compra.equals("sim") || compra.equals("Sim")|| compra.equals("s")) {
    				try {
    					compra(j,o);
    					if(verificaMonopolioPorCor(j.getJogadorDaVez()) || verificaPreMonopolioPorCor(j.getJogadorDaVez())) {
    						Mediador mediadorObserver = new Mediador(o);
    						mediadorObserver.confereDoisTipoMonopolio(j);
    					}
    				} catch (SemSaldoException e) {
    					o.fireEventNotification(e.getMessage(),EventNotification.getInstance(o.getId()), j);
    				}
    			}
			}
		}
		else if(!proprietario.equals(j.getJogadorDaVez())) {
    		try{
    			pagaAluguel(proprietario,j.getJogadorDaVez(),o , j);
    		}
    		catch (SemSaldoException e) {
    			o.fireEventNotification(e.getMessage(), EventNotification.getInstance(o.getId()), j);
    		}
		}
	}
	
	public abstract boolean verificaMonopolioPorCor(Jogador j);
	public abstract boolean verificaPreMonopolioPorCor(Jogador j);
}
	
