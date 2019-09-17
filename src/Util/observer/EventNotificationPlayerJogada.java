package Util.observer;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.ArrayList;
import Shared.ComunicationFacade;
import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;

public class EventNotificationPlayerJogada extends EventNotificationJogada{


	@Override
	public void lancaNotificationJogada(Jogadores j, DatagramSocket socket, ComunicationFacade comunication, String s)throws IOException {
		
		ArrayList<Jogador> jogadores = j.getJogadores();
		for(Jogador i: jogadores) {
			comunication.sendMessage(s, socket, i.getAddress());
		
		}
	}
}
