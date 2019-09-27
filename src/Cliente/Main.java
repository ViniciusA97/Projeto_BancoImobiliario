package Cliente;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(System.in);
		

		ComunicationClient comunication = new ComunicationClient();
		ThredClient a = new ThredClient();
		System.out.println("c");
		//a.run();
		
		
		while(true) {
			String msg = in.nextLine();
			System.out.println("b");
			comunication.sendMessage(msg);
		}
	}

	// mandar solicitações do client -- Olhar o communicationFacade(metodos
	// sendMessage)
}
