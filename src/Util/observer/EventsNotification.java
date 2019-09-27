package Util.observer;

import java.io.IOException;
import java.net.DatagramSocket;
import Shared.ComunicationFacade;
import Util.jogadores.Jogadores;

public abstract class EventsNotification {

	
	
	public abstract void lancaEventNotification(String msg,Jogadores j, ComunicationFacade comunication) throws IOException;

}