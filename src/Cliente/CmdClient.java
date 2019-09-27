package Cliente;

import java.net.*;
import java.util.HashMap;
import Shared.*;

public class CmdClient implements Cmd {

    private ComunicationClient comunication;

    public CmdClient(ComunicationClient c) {
        this.comunication = c;
    }

    public static void main(String[] args){
        try {
            DatagramSocket socket = new DatagramSocket(0);
            byte[] bytes = "ok".getBytes();
            InetAddress oi = InetAddress.getByName("192.168.27.106");
            DatagramPacket pacote = new DatagramPacket(bytes, bytes.length, oi, 0);
            socket.send(pacote);
            socket.close();
        } catch (Exception e) {
            System.out.println("Nao pegou!");
        }
    }

    public String cases(HashMap<String, Object> map) {
        String msg = (String) map.get("msg");
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