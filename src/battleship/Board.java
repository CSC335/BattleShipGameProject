package battleship;

public class Board {
	public int shipsPlaced = 0;
	final static int TOTAL_SHIPS = 5;
	
	Ships ships;
	Square[][] squareBoard;
	
	// I implemented it in a way that doesn't need guessBoard 
	// since we can get that info from squareBoard !
	// We may be able to get rid of this
	boolean[][] guessedBoard;
	
	//if player board, player true
	//if computer, false
	boolean player;
	boolean isOver;
	
	public Board(boolean p) {
		//initialize board
		ships = new Ships(this);
		squareBoard = new Square[10][10];
		guessedBoard = new boolean[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				squareBoard[i][j] = new Square(i, j);
				guessedBoard[i][j] = false;
			}
		}
		player = p;
		isOver = false;
	}
	
	public boolean guess(int x, int y) {
		//player can make a move
		if(!guessedBoard[x][y]) {
			squareBoard[x][y].guess();
			guessedBoard[x][y] = true;
			return true;
		}else {
			return false;
		}
	}
	
	// returns null if all ships are placed; 
	// otherwise, returns next ship to be placed
	public Ship getNextShip() {
		if (shipsPlaced < TOTAL_SHIPS) {
			return ships.getShips()[shipsPlaced];
		} else {
			return null;
			
		}
	}
	
	// Adds a ship to the board. To be called by player/AI
	// shipArr is in the format of { {x1, y1}, {x2, y2}, ...  }
	public boolean addShip(int[][] shipArr, Ship ship) {
		for (int[] point : shipArr) {
			
			// return false if outside of board bounds.
			if (point[0] < 0 || point[0] >= 10 || point[1] < 0 || point[1] >= 10) {
				System.out.println("Player.ShipAdd() coords out of bounds");
				return false;
			}
			
			char curSquare = getSqState(point[0], point[1], true);
			
			// return false if any ship square is occupied already
			if (curSquare != 'X' && curSquare != '.') {
				// TODO: output exception if necessary
				System.out.println("Player.ShipAdd() coords occupied");
				return false;
			}
		}
		
		for (int[] point : shipArr) {
			placeSq(point[0], point[1], ship);
		}
		return true;
		
	}
	
	public char getSqState(int x, int y, boolean isPlayer) {
		return squareBoard[x][y].state(isPlayer);
	}
	
	public void placeSq(int x, int y, Ship s) {
		squareBoard[x][y].place(s);
	}
	
	public boolean isOver() {
		//true = running, false = all ships sunk
		return isOver;
	}
	
	public void gameOver() {
		isOver = true;
	}
	
	//return char representation of board
	public char[][] getCharBoard(){
		char[][] charBoard = new char[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				charBoard[i][j] = squareBoard[i][j].state(player);
			}
		}
		return charBoard;
	}
	
	public void makeTest() {
		Ship[] shipObjs = ships.getShips();
		Ship currShip = shipObjs[0];
		for(int i = 0; i < 5; i++) {
			squareBoard[9][i].place(currShip);
		}
		currShip = shipObjs[1];
		for(int i = 3; i < 7; i++) {
			squareBoard[i][8].place(currShip);
		}
		currShip = shipObjs[2];
		for(int i = 1; i < 4; i++) {
			squareBoard[i][0].place(currShip);
		}
		currShip = shipObjs[3];
		for(int i = 3; i < 6; i++) {
			squareBoard[6][i].place(currShip);
		}
		currShip = shipObjs[4];
		for(int i = 7; i < 9; i++) {
			squareBoard[0][i].place(currShip);
		}
	}
	
	public boolean[][] getGuessed(){
		return guessedBoard;
	}
	
}
