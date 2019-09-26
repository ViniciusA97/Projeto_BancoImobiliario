package Cliente;

import java.util.HashMap;

public class ThredClient implements Runnable {

	private ComunicationClient comunication;

	public ThredClient(ComunicationClient c) {
		this.comunication=c;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

		HashMap<String, Object> map;

		while (true) {
			map = this.comunication.RessiveMessage();
			System.out.println(map.get("msg"));
			String sentence = (String) map.get("msg");
			if (sentence.contains("/")) {
				String usual = (String) map.get("usual");
				int porta = Integer.parseInt(usual);
				this.comunication.changePort(porta);
				if (porta == 4444) {
					System.out.println("Conectado com o servidor inicial. //hs Para comandos. ");
				} else {

					System.out.println("" + "conectado com a sala de jogo. //hr Para comandos.");
				}
			}
		}
	}

}
