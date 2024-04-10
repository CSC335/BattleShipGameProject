package battleship;

public class BattleshipGame {
	
	private Board a, b;
	private BattleShipStrategy strategyAI;
	private Player player1, player2;
	
	public boolean isAI;
	
	public BattleshipGame(boolean isAI) {
		this.isAI = isAI;
		
		//a = new Board(!isAI);
		// a is true for testing purposes
		a = new Board(true);
		b = new Board(true);
		if (isAI) {
			// AI on left, player on right
			player1 = new Player(b, a);
			strategyAI = new RandomAI();
		} else {
			// player1 on left, player2 on right
			player1 = new Player(a, b);
			player2 = new Player(b, a);
		}
		//initialize the game
		// start the gui
		//if start then
		//make the board player 1
		//make the board player 2
		//make player + ai with their respective boards
		// start the gui
		//having a class that initializes everything makes it much easier for the different pieces to communicate between eachother
	}
	
	//we should probably have a function that allows the players to move their ships into different positions
	//at the beginning of the game and then have another function for the actual gameplay
	//im not sure what class to put these in yet so i am putting my thoughts here :)
	
	//I think we should make a barebones GUI first too to make it easier to test
	
	public Ship nextShip(boolean isPlayer1) {
		if (isPlayer1 == isAI) {
			return b.getNextShip();
		} else {
			return a.getNextShip();
		}
	}
	
	public boolean humanPlaceShip(boolean isPlayer1, int x, int y, int orientation, Ship ship) {
		if (isPlayer1) {
			return player1.ShipAdd(x, y, orientation, ship);
		} else {
			return player2.ShipAdd(x, y, orientation, ship);
		}
	}
	
	public void computerPlaceShip() {
		while (!a.addShip(strategyAI.desiredShip(a, a.getNextShip()), a.getNextShip())) {
			System.out.println("rerolling ship");
		}
		a.shipsPlaced++;
	}
	
	public boolean humanPlayMove(boolean isPlayer1, int x, int y) {
		if (isPlayer1) {
			return player1.playMove(x, y);
		} else {
			return player2.playMove(x, y);
		}
	}
	
	public void computerPlayMove() {
		int[] guess = strategyAI.desiredMove(b);
		b.guess(guess[0], guess[1]);
	}
	
	public String getBoard(boolean firstBoard) {
		Board cur = firstBoard ? a : b;
		char[][] boardChars = cur.getCharBoard();
		StringBuilder boardString = new StringBuilder();
		
		System.out.println("Board " + (firstBoard ? "1" : "2") + ": ");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				boardString.append(boardChars[i][j]).append(" ");
				System.out.print(boardChars[i][j]);
				System.out.print(" ");
			}
			boardString.append("\n");
			System.out.println();
		}
		return boardString.toString();
	}

	public boolean gameOver() {
		return a.isOver() || b.isOver();
	}
}