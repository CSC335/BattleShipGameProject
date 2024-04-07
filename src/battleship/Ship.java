package battleship;

public class Ship {
	
	int size;
	String type;
	int [][] squares;
	int notHit;
	boolean sunk;
	char id;
	Ships master;
	
	public Ship(int s, String t, char ch, Ships ships) {
		size = s;
		type = t;
		notHit = s;
		squares = new int[size][2];
		sunk = false;
		id = ch;
		master = ships;
	}
	
	public void guess() {
		notHit--;
		if(notHit <= 0) {
			sunk = true;
			master.update();
		}
	}
	
	public boolean isSunk() {
		return sunk;
	}
	
	public char getID() {
		return id;
	}
	
	public int size() {
		return size;
	}
}

