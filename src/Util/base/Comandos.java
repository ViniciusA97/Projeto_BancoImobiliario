package Util.base;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import Shared.ComunicationFacade;
import Util.Tabuleiro.*;
import Util.jogadores.Jogadores;
import Util.observer.EventNotification;
import Util.observer.Mediador;
import Util.observer.Observer;

public class Comandos {


	private FachadaComunicacao comunicacao= FachadaComunicacao.getInstance();
	private Jogadores jogadores;
	private GeraString geraString;
	private FachadaTabuleiro fachadaT;
	private Observer observer;
	private Mediador mediador ;
	private ComunicationFacade out;
	private DatagramSocket socket;

	
	
	public Comandos(Observer obs,int index, DatagramSocket s, Jogadores j) {
		this.jogadores = j;
		this.mediador = new Mediador(obs);
		this.geraString = GeraString.getInstance(index);
		this.fachadaT = FachadaTabuleiro.getInstance(index);
		this.observer = obs;
		this.out = new ComunicationFacade();
	}
	
	
	public Jogadores getJogadores() {
		return this.jogadores;
	}
	
	public Observer getObserver() {
		return this.observer;
	}
	
	public void comandoSair() {//sai do jogo
		String comando=comunicacao.inputString("Você deseja mesmo sair?[s] [n]");
		if(comando.equals("s")) {
			jogadores.retiraJogador(jogadores.getJogadorDaVez());
			jogadores.passaVez();
		}
	}
	
	public void comandoStatus() {//mostra status do jogador da vez
		this.observer.fireEventNotification(this.jogadores.getJogadorDaVez().toString(),new EventNotification(), jogadores);
	}
	
	public void comandoConstruir() {//leva usuário para as opções de construção de casas em terrenos, se possível
		int numEscolhido =0;
		if(fachadaT.verificaMonopolio(jogadores.getJogadorDaVez())) {
			
			this.observer.fireEventNotification(jogadores.getJogadorDaVez()+" tem um saldo de " +jogadores.getJogadorDaVez().getDinheiro(), new EventNotification(), jogadores);
			this.observer.fireEventNotification(geraString.getStringConstrucao(jogadores.getJogadorDaVez()), new EventNotification(), jogadores);
			boolean cond =true;
			HashMap<String, Object> map;			
			while(cond) {
			
				try {
					map =(HashMap<String, Object>) this.out.reciveMessage(socket);
					if(map.get("address").equals(jogadores.getJogadorDaVez().getAddress())) {
						numEscolhido = (int) map.get("usual");
						cond = false;
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());	
				}	
			}
			
			if(numEscolhido<=0) return;
			try {
				fachadaT.compraCasa(geraString.getTerreno(numEscolhido-1), jogadores.getJogadorDaVez());
				if(geraString.getTerreno(numEscolhido-1).getCasas() == 4) {
					mediador.EventoPreHotel(geraString.getTerreno(numEscolhido-1),jogadores);
				}
			} catch (IndexNaoSuportadoEx e) {
				System.out.println(e.getMessage());
			}
			this.observer.fireEventNotification("Casa construida com sucesso!", new EventNotification(), jogadores);
		}
	}
	
	public void comandoVender() {//leva usuário para opções de venda de casas em terrenos, se possível
		if(jogadores.getJogadorDaVez().temTerreno()) {
			int contador = 0;
			for(Terreno t : jogadores.getJogadorDaVez().getTerrenos()) {
				if(t.getCasas() > 0) {
					contador += 1;
					break;
				}
			}
			int temp=0;
			if(contador == 1) {
				
				boolean cond =true;
				HashMap<String, Object> map;			
				while(cond) {
				
					try {
						map =(HashMap<String, Object>) this.out.reciveMessage(socket);
						if(map.get("address").equals(jogadores.getJogadorDaVez().getAddress())) {
							temp = (int) map.get("usual");
							cond = false;
						}
					} catch (IOException e) {
						System.out.println(e.getMessage());	
					}	
				}
				if(temp > 0) {
					Terreno terreno;
					try {
						terreno = geraString.getTerreno(temp-1);
						fachadaT.vendeCasa(terreno);
						this.observer.fireEventNotification("Casa vendida com sucesso", new EventNotification(), jogadores);
					}catch (IndexNaoSuportadoEx e) {
						this.observer.fireEventNotification(e.getMessage(), new EventNotification(), jogadores);
					}
				}else {
					this.observer.fireEventNotification("Opção invalida!!", new EventNotification(), jogadores);
				}
			}
		}
	}
	
	public void comandoJogar() {//jogador fazz jogada
		
		Dados dado= Dados.getInstance();
		int [] dadosJogados= dado.jogaDados();
		String aux = Integer.toString(dadosJogados[0])+" "+Integer.toString(dadosJogados[1]);
		if(dado.verificaHistoricoDeDados(jogadores.getJogadorDaVez())) {
			fachadaT.vaiPraPrisao(jogadores);
			jogadores.getJogadorDaVez().setCasa(9);
			jogadores.passaVez();
			return ;
		}
		if(fachadaT.procuraPrisioneiro(jogadores.getJogadorDaVez())) {
			fachadaT.tentaSairPrisao(aux, jogadores);
		}
		else if(jogadores.getJogadorDaVez().getCasa()+dadosJogados[0]+dadosJogados[1]>39) {
			
			jogadores.getJogadorDaVez().setCasa(jogadores.getJogadorDaVez().getCasa()+dadosJogados[0]+dadosJogados[1]-40);
			this.observer.fireEventNotification(
					"Jogador "+ jogadores.getJogadorDaVez().getNome()+ " tirou ["+dadosJogados[0]+" "+dadosJogados[1]
					+"] e caiu na casa: "+fachadaT.getCasaTabuleiro(jogadores.getJogadorDaVez().getCasa()).getNome(),
					new EventNotification(), jogadores);
			
		}else{
			jogadores.getJogadorDaVez().setCasa(jogadores.getJogadorDaVez().getCasa()+dadosJogados[0]+dadosJogados[1]);
			this.observer.fireEventNotification(
					"Jogador "+ jogadores.getJogadorDaVez().getNome()+ " tirou ["+dadosJogados[0]+" "+dadosJogados[1]+"] e caiu na casa: "+
					fachadaT.getCasaTabuleiro(jogadores.getJogadorDaVez().getCasa()).getNome(),
					new EventNotification(), jogadores);	
			}

		Casa casaAtual = fachadaT.getCasaTabuleiro(jogadores.getJogadorDaVez().getCasa());
		casaAtual.fazAcao(this,jogadores);
		jogadores.passaVez();
	}
		
	public void comandoPagar() {//faz pagamento para usuário preso no prisão
		this.observer.fireEventNotification("Pagamento conluído. Proxima rodada poderá se mover normalmente.", new EventNotification(), jogadores);
		fachadaT.retiraJogadorDaPrisao(jogadores.getJogadorDaVez());
		jogadores.passaVez();
	}
	
	public void comandoCarta() {//utiliza carta de passe livre da prisão
		if(jogadores.getJogadorDaVez().getValorCarta()) {
			this.observer.fireEventNotification("O jogador "+ jogadores.getJogadorDaVez().getNome()+ " usou a carta do Passe livre. Ela voltou para o monte de Sorte Ou Revés"
					, new EventNotification(), jogadores);
		}else {
			this.observer.fireEventNotification("Você não possui a carta do Passe livre.", new EventNotification(), jogadores);
		}
		jogadores.passaVez();
	}
	
}
