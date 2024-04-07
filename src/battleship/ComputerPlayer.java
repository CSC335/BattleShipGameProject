package battleship;
import java.util.Random;

//Opponent class
public class ComputerPlayer {
	
	public Board board;
	public BattleShipStrategy myStrat;
	
	public ComputerPlayer(BattleShipStrategy strat) {
		board = new Board(false);
		myStrat = strat;
	}
	
	//Places ships entirely randomly
	public void makeBoard() {
		Random rand = new Random();
		boolean[][] placed = new boolean[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				placed[i][j] = false;
			}
		}
		Ship[] ships = board.ships.shipObjs;
		Ship currShip = ships[0];
		int dir = rand.nextInt(2);
		int startX = rand.nextInt(6);
		int startY = rand.nextInt(6);
		if(dir == 0) {
			for(int i = startX; i < startX + 5; i++) {
				board.placeSq(i, startY, currShip);
				placed[i][startY] = true;
			}
		}else {
			for(int i = startY; i < startY + 5; i++) {
				board.placeSq(startX, i, currShip);
				placed[startX][i] = true;
			}
		}
		boolean finding;
		boolean placeable;
		for(int shipI = 1; shipI < 5; shipI++) {
			finding = true;
			currShip = ships[shipI];
			while (finding) {
				startX = rand.nextInt(10-currShip.size() + 1);
				startY = rand.nextInt(10-currShip.size() + 1);
				dir = rand.nextInt(2);
				placeable = true;
				if(dir == 0) {
					for(int i = startX; i < startX + currShip.size(); i++) {
						if(placed[i][startY] == true) {
							placeable = false;
						}
					}
				}else {
					for(int i = startY; i < startY + currShip.size(); i++) {
						if(placed[startX][i] == true) {
							placeable = false;
						}
					}
				}
				if(placeable) {
					finding = false;
				}
			}
			if(dir == 0) {
				for(int i = startX; i < startX + currShip.size(); i++) {
					board.placeSq(i, startY, currShip);
					placed[i][startY] = true;
				}
			}else {
				for(int i = startY; i < startY + currShip.size(); i++) {
					board.placeSq(startX, i, currShip);
					placed[startX][i] = true;
				}
			}
		}
	}
	
	//Returns x and y of next move based on strategy
	public int[] makeNextMove() {
		int[] next = myStrat.desiredMove(board);
		board.guess(next[0], next[1]);
		return next;
	}
}
