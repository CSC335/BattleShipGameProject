package battleship;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Ship {
	
	int size;
	String type;
	ArrayList<Square> squares;
	int notHit;
	boolean sunk;
	char id;
	Ships master;
	int orientation;
	String file;
	String file1;
	
	public Ship(int s, String t, char ch, Ships ships, String f, String f1) {
		size = s;
		type = t;
		notHit = s;
		squares = new ArrayList<Square>();
		sunk = false;
		id = ch;
		master = ships;
		orientation = -1;
		file = f;
		file1 = f1;
	}
	
	public void guess() {
		notHit--;
		if(notHit <= 0) {
			sunk = true;
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
	
	public int orientation() {
		return orientation;
	}
	
	public void setOrientation(int newOr) {
		orientation = newOr;
	}
	
	public ArrayList<Square> squares(){
		return squares;
	}
	
	public Image img() {
		if(orientation % 2 == 0) {
			if(sunk) {
				return new Image(file + "1Sunk.png", 36, size * 36, false, false);
			}else {
				return new Image(file + "1.png", 36, size * 36, false, false);
			}
		}else {
			if(sunk) {
				return new Image(file + "Sunk.png", size * 36, 36, false, false);
			}else {
				return new Image(file + ".png", size * 36, 36, false, false);
			}
		}
	}
	
	public void addSquare(Square square) {
		squares.add(square);
	}
	
	public void clearSquares() {
		squares.clear();
	}
}

