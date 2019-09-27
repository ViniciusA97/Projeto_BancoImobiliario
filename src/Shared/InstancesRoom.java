package Shared;

import Server.Room;
import Util.Tabuleiro.FachadaTabuleiro;
import Util.Tabuleiro.GerenciamentoDeTerreno;
import Util.Tabuleiro.Prisao;
import Util.Tabuleiro.Tabuleiro;
import Util.base.GeraString;

public class InstancesRoom {

	private Room room;
	private GerenciamentoDeTerreno gerencia;
	private FachadaTabuleiro fachadaT;
	private Tabuleiro tabuleiro;
	private GeraString geraString;
	private Prisao prisao;
	public static InstancesRoom instance;
	private ComunicationFacade comunication;
	
	public InstancesRoom(Room r) {
		instance = this;
		this.room= r;
		this.prisao = new Prisao();
		this.geraString = new GeraString();
		this.gerencia = new GerenciamentoDeTerreno();
		this.fachadaT = new FachadaTabuleiro();
		this.tabuleiro = new Tabuleiro(this.gerencia, this.prisao);
		this.comunication = new ComunicationFacade(4444);
	}
	
	public static InstancesRoom getInstance() {
		return instance;
	}

	public GeraString getGeraString() {
		return this.geraString;
	}
	
	public GerenciamentoDeTerreno getGerencia() {
		return this.gerencia;
	}
	
	public FachadaTabuleiro getFachadaTabuleiro() {
		return this.fachadaT;
	}
	
	public Tabuleiro getTabuleiro() {
		return this.tabuleiro;
	}
	
	public Room getRoom() {
		return this.room;
	}
	
	public ComunicationFacade getComunication() {
		return this.comunication;
	}
	
	public void setComunication(ComunicationFacade c) {
		this.comunication=c;
	}
}
