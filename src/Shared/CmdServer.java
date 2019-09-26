package Shared;

import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.HashMap;

import Server.Room;
import Util.Tabuleiro.FachadaTabuleiro;
import Util.base.Comandos;
import Util.observer.Observer;

public class CmdServer implements Cmd {

	private ComunicationFacade comunication;
	private ArrayList<Room> room;
	private DatagramSocket socket;
	private Observer observer;
	private Comandos comandos;
	private FachadaTabuleiro fachadaT;
	
	
	
	@Override
	public String cases(HashMap<String, Object> opcoes) {
		
		
		
		
		
		return null;
	}

}
