package battleship;

public class Board {

	Ships ships;
	Square[][] squareBoard;
	boolean[][] guessedBoard;
	//if player board, player true
	//if computer, false
	boolean player;
	boolean gameState;
	
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
		gameState = true;
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
	
	public void placeSq(int x, int y, Ship s) {
		squareBoard[x][y].place(s);
	}
	
	public boolean getState() {
		//true = running, false = all ships sunk
		return gameState;
	}
	
	public void gameOver() {
		gameState = false;
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
