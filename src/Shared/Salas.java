package Shared;

import java.util.ArrayList;

import Server.Room;

public class Salas {

	private ArrayList<Room> salas;
	private int cont = 10;

	public Salas() {}
	
	public void addRoom(Room n) {
		this.salas.add(n);
	}
	
	public void addRoom(int index) {
		this.salas.add(new Room(index,cont++));
	}
	
	public Room procuraRoom(String nome) {
		for(Room i: this.salas) {
			if(i.getName().equals(nome)) {
				return i;
			}
		}
		return null;
	}
	
	public int getSize() {
		return this.salas.size();                      
	}
	
}
