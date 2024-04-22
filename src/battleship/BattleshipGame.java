package battleship;

public class BattleshipGame {
	
	private Board a, b;
	private BattleShipStrategy strategyAI;
	private Player player1, player2;
	
	public boolean isAI;
	
	public int b1Hits = 0, b1Misses = 0;
	public int b2Hits = 0, b2Misses = 0;
	
	public BattleshipGame(boolean isAI) {
		this.isAI = isAI;
		
		// set a to new Board(true)
		a = new Board(!isAI);
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
	
	public int[] getStats(boolean firstBoard) {
		// if firstBoard, then get stats of the user of the first board
		// (which is the board on the left). 
		// However, stats are stored on other board, therefore curBoard is opposite
		Board curBoard = firstBoard ? b : a;
		
		// returns { shipsSunk, guessesMade, misses, hits }
		int hits = curBoard.getHits();
		int misses = curBoard.getMisses();
		
		return new int[] { curBoard.getSunkShips(), (hits + misses), misses, hits };
	}
	
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
		boolean playing;
		if (isPlayer1) {
			// playMove checks is move is valid before playing
			playing = player1.playMove(x, y);
		} else {
			playing = player2.playMove(x, y);
		}
		
		return playing;
	}
	
	public void computerPlayMove() {
		// guess will play move; this assumes move is valid
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