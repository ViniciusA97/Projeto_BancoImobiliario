package Cliente;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		System.out.println("Qual o endere�o do servidor?");
		Scanner in = new Scanner(System.in);
		String ip = in.nextLine();

		ComunicationClient comunication = new ComunicationClient(ip);
		ThredClient a = new ThredClient(comunication);
		a.run();
		
		while(true) {
			
			String msg = "createRoom";
			System.out.println(msg);
			comunication.sendMessage(msg);
			
		}
	}

	// mandar solicitações do client -- Olhar o communicationFacade(metodos
	// sendMessage)
}
