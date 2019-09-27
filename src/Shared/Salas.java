package Shared;

import java.io.IOException;
import java.util.ArrayList;

import Server.Room;
import Util.jogadores.Jogador;

public class Salas {

	private ArrayList<Room> salas;

	public Salas() {
		this.salas = new ArrayList<Room>(40);
	}
	
	public void addRoom(Room n) {
		this.salas.add(n);
	}
	
	public void addRoom(int index) {
		if(salas.size()>39) {
			System.out.println("ja deu");
		}
		Room a =new Room(index);
		System.out.println("teste");
		this.salas.add(a);
		a.start();
	}
	
	public Room getRoomNome(String nome) {
		for(Room i: this.salas) {
			if(i.getName().equals(nome)) {
				return i;
			}
		}
		return null;
	}
	public Room getRoomId(int id) {
		for(Room i: this.salas) {
			if(i.getId()== id) {
				return i;
			}
		}
		return null;
	}
	
	public int getSize() {
		return this.salas.size();                      
	}
	
	public Room addJogadorInRoomID(int id, Jogador j) throws IOException {
		Room r =  getRoomId(id);
		if(r!=null) {
			r.addPlayer(j);
			return r;
		}
		return null;
	}
	
	public Room addJogadorInRoomName(String name, Jogador j) throws IOException {
		Room r =  getRoomNome(name);
		if(r!=null) {
			r.addPlayer(j);
			return r;
		}
		return null;
	}
	
	public boolean existeRoomID(int id) {
		for(Room i: this.salas) {
			if(i.getId()==id) {
				return true;
			}
		}
		return false;
	}
	
	public boolean existeRoomName(String name) {
	
		for(Room i: this.salas) {
			if(i.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
}
