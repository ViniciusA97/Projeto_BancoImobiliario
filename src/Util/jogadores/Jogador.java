package Util.jogadores;

import java.net.InetAddress;
import java.util.ArrayList;

import Util.Tabuleiro.Empresas;
import Util.Tabuleiro.Terreno;
import Util.observer.Events;
import Util.observer.Listeners;

public class Jogador implements Listeners{
		
	private String nome;
	private String cor;
	private String status;
	private int dinheiro;
	private int casa;
	private Fila dadosJogados;
	private boolean cartaLiberdade;
	private ArrayList<Terreno> terrenos= new ArrayList<Terreno>();
	private ArrayList<Empresas> empresas= new ArrayList<Empresas>();
	private InetAddress ip;
	private int porta;
	private boolean adm;
	
	public Jogador(String n) {
		this.nome=n;
		this.cor="";
		this.status="";
		this.dinheiro=1500;
		this.casa=39;
		this.dadosJogados = new Fila();
		cartaLiberdade=false;
		this.adm =false;
	}
	
	public Jogador() {}
	
	public void setAdm() {
		this.adm = !adm;
	}
	
	public boolean getAdm() {
		return this.adm;
	}
	
	public Jogador(InetAddress address) {
		this.ip = address;
		this.nome = this.ip.getHostName();
		this.adm=false;
	}
	
	public void setIp(InetAddress address) {
		this.ip = address;
	}
	
	public void setPort(int num) {
		this.porta = num;
	}
	
	public InetAddress getAddress() {
		return this.ip;
	}
	
	public int getPort() {
		return this.porta;
	}
	
	public void addStatus(String s) {//Adiciona uma string ao status do jogador
		this.status+=s+"\n";
	}
	
	public String getNome() {//Retorna nome
		return this.nome;
	}
	
	public String getCor() {//Retorna cor do jogador
		return this.cor;
	}
	
	public String getStatus(){//Retorna o status do jogador
		String status="";
		for(Terreno i: this.terrenos) {
			status+=i.getNome()+" do tipo "+ i.getCor() +"\n" ;
		}
		for(Empresas i: this.empresas) {
			status+= i.getNome(); 
		}
		return status;
		
	}
	
	public int ganhaDinheiro(int money) {//Adiciona dinheiro ao jogador
		this.dinheiro+=money;
		return this.dinheiro;
	}
	
	public int perdeDinehiro(int money)throws SemSaldoException {//Retira dinheiro do jogador
		if(money>this.dinheiro) {
			throw new SemSaldoException();
		}else {
			this.dinheiro-=money;
			return this.dinheiro;
		}
	}
	
	public void setCor(String cor) {//seta uma cor ao jogador
		this.cor=cor;
	}
	
	public String toString() {//retorna string com as info do jogador
		return "Nome do jogador: "+this.nome+"\nCor: "+this.cor+"\nStatus: "+this.status
				+"\nDinheiro: "+this.dinheiro;
	}
	
	public int getCasa() {//retorna a posi��o atual do jogador no tabuleiro
		return this.casa;
	}
	
	public void setCasa(int c) {//Move o jogador para uma casa espec�fica
		this.casa=c;
	}
	
	public int getDinheiro() {//retorna o dinheiro do jogador
		return this.dinheiro;
	}
	
	public void addDados(String dados) {//adiciona dados na lista de dados
		dadosJogados.enfileirar(dados);
	}
	
	public String [] getAll() {//retorna todos os dados
		return this.dadosJogados.getAll();
	}
	
	public void apagaTudoDados() {//reseta os dados
		dadosJogados = new Fila();
	}
	
	public String getUltimoDado() {//retorna apenas o �ltimo dado jogado
		String[] aux = this.dadosJogados.getAll();
		if(aux.length==3) {
			return aux[2];
		}else if(aux.length==2) {
			return aux[1];
		}else return aux[0];
	}
	
	public int qntdDados() {//retorna a quantidade de vezes jogada
		return dadosJogados.tamanho();
	}
	
	public void adereCartaLiberdade() {//adiciona a carta de passe livre da pris�o ao jogador
		this.cartaLiberdade=true;
	}
	
	public boolean getValorCarta() {//verifica se jogador tem carta de passe livre da pris�o
		return this.cartaLiberdade;
	}
	
	public void usouCarta() {//jogador utilizou sua carta e agora n�o a possui
		this.cartaLiberdade=false;
	}
	
	public void addTerreno(Terreno t) {//adiciona um terreno a lista de terrenos do  jogador
		this.terrenos.add(t);
	}
	
	public void addEmpresa(Empresas e) {//adiciona uma empresa a lista de empresas do jogador
		this.empresas.add(e); 
	}
	
	public boolean temTerreno() {//verifica se jogador tem algum terreno
		if(this.terrenos.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Terreno> getTerrenos(){//retorna todos os terrenos do jogador
		return this.terrenos;
	}
	
	public boolean verificaCasas() {//verifica se jogador tem algum terreno com casas
		for(Terreno i: terrenos) {
			if(i.getCasas()!=0) return true;
		}
		return false;
	}

	public void eventoMonopolio(Events eventoMonopolio, Jogadores j) {//Evento do observer 
		
		System.out.println(eventoMonopolio.getStringEvento(j));
	}

	public void eventoPreMonopolio(Events eventoPreMonopolio, Jogadores j) {//Evendo do observer
		System.out.println(eventoPreMonopolio.getStringEvento(j));
	}
	
	public Terreno getUltimoTerreno() {
		return this.terrenos.get(this.terrenos.size()-1);
	}

	@Override
	public void eventoPreHotel(Events eventoPreHotel, Jogadores j) {
		System.out.println(eventoPreHotel.getStringEvento(j));
	}

	public void setName(String nome) {
		this.nome = nome;
	}
	
}
