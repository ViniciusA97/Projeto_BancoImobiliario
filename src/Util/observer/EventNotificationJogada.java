package Util.observer;

import java.io.IOException;
import java.net.DatagramSocket;

import Shared.ComunicationFacade;
import Util.jogadores.Jogadores;

public abstract class EventNotificationJogada {

	
	public abstract void lancaNotificationJogada(Jogadores j, DatagramSocket socket, ComunicationFacade comunication, String s)throws IOException;
}