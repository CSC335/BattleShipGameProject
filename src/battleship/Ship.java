package battleship;

public class Ship {
	
	int size;
	String type;
	int [][] squares;
	int notHit;
	boolean sunk;
	
	public Ship(int s, String t) {
		size = s;
		type = t;
		notHit = s;
		squares = new int[size][2];
		sunk = false;
	}
	
	public void guess() {
		notHit--;
		if(notHit <= 0) {
			sunk = true;
		}
	}
}

