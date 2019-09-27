package Cliente;

import java.net.*;
import java.util.HashMap;
import Shared.*;

public class CmdClient implements Cmd {

    private ComunicationClient comunication;

    public void comunication(ComunicationClient c) {
        this.comunication = c;
    }

    public String cases(HashMap<String, Object> map) {
        String msg = map.get("msg").toString();
        InetAddress client = (InetAddress) map.get("address");

        switch (msg.trim()) {
        case "ok":
            int newPort = Integer.parseInt(map.get("usual").toString());
            comunication.changePort(newPort);
            System.out.println("PEGOU CARAIO");
            break;

        case "userOut":
            System.out.println("O usuario saiu da sala!");
            comunication.resetPort();
        break;

        case "fail":
            System.out.println("Não foi possivel conectar na sala.");
            break;
        }

        return "";
    }
}