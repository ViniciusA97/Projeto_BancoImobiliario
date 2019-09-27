package Cliente;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(System.in);
		
		ComunicationClient comunication = new ComunicationClient();
		CmdClient client = new CmdClient(comunication);
		ThredClient a = new ThredClient();
		System.out.println("c");
		a.start();
		
		HashMap<String, Object> map;
		while(true) {
		
			String msg = in.nextLine();
			comunication.sendMessage(msg);
			map = comunication.RessiveMessage();
			System.out.println(map.get("msg"));
			client.cases(map);
		}
	}

	// mandar solicitações do client -- Olhar o communicationFacade(metodos
	// sendMessage)
}
