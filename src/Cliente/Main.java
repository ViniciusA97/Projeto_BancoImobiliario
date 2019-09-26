package Cliente;

public class Main {

	
	
	
	// perguntar qual endereço e porta do servidor
	// criar uma classe servidor para guardar IP e Port (metodos get e set) e o essa classe deve ser um Facade (1 instancia basta)  -- instancia da propria classe static como atributo da classe , construtor private , metodo statico getInstance() que inicia o atributo instance se não estiver iniciado e retorna o mesmo
	// tentar fazer a comunicação -- DatagramSocket = new DatagramSocket(port);
	// tratar excessão se der merda
	//criar thread para receber e printar na tela o  q ta sendo recebido -- olhar a classe Room 
	//fazer uma classe de comunicação que pode se comunicar com o objeto servidor para usar o IP e a porta -- olhar o communicationFacade( metodo ReciveMessage)
	// mandar solicitações do client -- Olhar o communicationFacade(metodos sendMessage)
}
