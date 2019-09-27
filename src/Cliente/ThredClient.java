package Cliente;

import java.util.HashMap;

public class ThredClient extends Thread {

	private ComunicationClient comunication;

	public ThredClient() {
		this.comunication=new ComunicationClient();
		System.out.println("d");
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

		HashMap<String, Object> map;
		CmdClient a = new CmdClient(comunication);
		while (true) {
			map = this.comunication.RessiveMessageEspecial();
			System.out.println(map.get("msg"));
			String sentence = (String) map.get("msg");
			System.out.println(sentence);
		}
	}

}
