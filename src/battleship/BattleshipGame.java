package battleship;

import java.util.ArrayList;
import java.util.Random;

public class BattleshipGame {
	
	private Board a, b;
	private BattleShipStrategy strategyAI;
	private Player player1;
	
	private Client client;
	
	public BattleshipGame(String whichAI) {
		a = new Board(false);
		b = new Board(true);
		
		if (whichAI == "random") {
			// AI on left, player on right
			player1 = new Player(b, a);
			strategyAI = new RandomAI();
		}else if (whichAI == "hard") {
			// AI on left, player on right
			player1 = new Player(b, a);
			strategyAI = new HardAI();
		} else {
			// player1 on left, player2 on right
			player1 = new Player(b, a);
			client = new Client("localhost", 4000);
			strategyAI = new Server(4000);
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
		if (isPlayer1) {
			return b.getNextShip();
		} else {
			return a.getNextShip();
		}
	}
	
	public boolean executeSkillShot(boolean fromFirstBoard, int skillIndex, int x, int y) {		
		Board curBoard = b;
		Board oppBoard = a;
		if (fromFirstBoard) {
			curBoard = a;
			oppBoard = b;
		}
		
		int[][] shots = curBoard.getSkillShot(skillIndex, x, y);
		if (shots == null)
			return false;
		
		for (var coord : shots) {
			oppBoard.guess(coord[0], coord[1]);
		}
		
		return true;
	}
	
	public boolean humanPlaceShip(int x, int y, int orientation, Ship ship) {
		if (client != null)
			client.sendShipPlacement(x, y, orientation, orientation);
		
		return player1.ShipAdd(x, y, orientation, ship);
	}
	
	public ArrayList<Ship> getShips() {
		ArrayList<Ship> ships = new ArrayList<Ship>();
		for(int i = 0; i < 5; i++) {
			ships.add(a.ships.shipObjs[i]);
		}
		return ships;
	}
	
	public void computerPlaceShip(boolean board1) {
		Board a = board1 ? this.a : b;
		while (!a.addShip(strategyAI.desiredShip(a, a.getNextShip()), a.getNextShip())) {
			System.out.println("rerolling ship");
		}
		a.shipsPlaced++;
	}
	
	public boolean humanPlayMove(int x, int y) {
		if (client != null)
			client.sendMove(x, y);
		
		// playMove checks is move is valid before playing
		return player1.playMove(x, y);
		//if(playing) {
			//GUI.expL.setCoords(x * 36, y * 36);
		//}
	}
	
	public void computerPlayMove() {
		// guess will play move; this assumes move is valid
		int next = a.nextSkillShot();
		int[] guess = strategyAI.desiredMove(b);
		
		System.out.println(next);
		
		if (next == -1) {
			b.guess(guess[0], guess[1]);
			return;
		}
		
		executeSkillShot(true, next, guess[0], guess[1]);
	}
	
	public Board getActualBoard(boolean firstBoard) {
		if(firstBoard) {
			return a;
		}else {
			return b;
		}
	}
	
	public String getBoard(boolean isFirstBoard) {
		Board cur = isFirstBoard ? a : b;
		char[][] boardChars = cur.getCharBoard();
		StringBuilder boardString = new StringBuilder();
		
		System.out.println("Board " + (isFirstBoard ? "1" : "2") + ": ");
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
	
	public void getRandomPlace() {
		while (!b.addShip(new RandomAI().desiredShip(b, b.getNextShip()), b.getNextShip())) {
			System.out.println("rerolling ship");
		}
		b.shipsPlaced++;
	}
}