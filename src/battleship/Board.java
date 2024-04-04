package battleship;

public class Board {

	Ships ships;
	Square[][] squareBoard;
	//if player board, player true
	//if computer, false
	boolean player;
	
	public Board(boolean p) {
		//initialize board
		ships = new Ships();
		squareBoard = new Square[10][10];
		player = p;
	}
	
	public void guess(int x, int y) {
		//player can make a move
		squareBoard[x][y].guess();
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
	
}
