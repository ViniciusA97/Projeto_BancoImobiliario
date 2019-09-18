package Util.observer;

import java.io.IOException;
import java.net.DatagramSocket;

import Shared.ComunicationFacade;
import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;

public class EventNotificationRodada extends EventsNotification {

	@Override
	public void lancaEventNotification(String msg, Jogadores j, DatagramSocket socket, ComunicationFacade comunication)
			throws IOException {
		// TODO Auto-generated method stub
		for(Jogador i : j.getJogadores()) {
			comunication.sendMessage(msg, socket, i.getAddress());
		}
	}

}
