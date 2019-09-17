package Util.observer;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.ArrayList;

import Shared.ComunicationFacade;
import Util.jogadores.Jogador;
import Util.jogadores.Jogadores;

public class EventoNotificationGetIn  extends EventsNotification{

	@Override
	public void lancaEventNotification(Jogadores j, DatagramSocket socket, ComunicationFacade comunication)throws IOException {
		
		ArrayList<Jogador> jogadores = j.getJogadores();
		for(Jogador i: jogadores) {
			comunication.sendMessage("Jogador "+ i.getNome()+" entrou na lista de jogadores para jogar.", socket, i.getAddress());
		}
	}
}
