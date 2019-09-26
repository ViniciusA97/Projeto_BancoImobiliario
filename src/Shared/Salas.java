package Shared;

import java.util.ArrayList;

import Server.Room;

public class Salas {

	private ArrayList<Room> salas;

	public Salas() {}
	
	public void addRoom(Room n) {
		this.salas.add(n);
	}
	
	public Room procuraRoom(String nome) {
		for(Room i: this.salas) {
			if(i.getName().equals(nome)) {
				return i;
			}
		}
		return null;
	}
	
}
