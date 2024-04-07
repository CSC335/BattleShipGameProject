package battleship;

public class Ships {
	
	public Ship[] shipObjs;
	boolean allSunk;
	Board board;
	
	public Ships(Board b) {
		shipObjs = new Ship[5];
		shipObjs[0] = new Ship(5, "Carrier", 'C', this);
		shipObjs[1] = new Ship(4, "Battleship", 'B', this);
		shipObjs[2] = new Ship(3, "Cruiser", 'U', this);
		shipObjs[3] = new Ship(3, "Submarine", 'S', this);
		shipObjs[4] = new Ship(2, "Destroyer", 'D', this);
		allSunk = false;
		board = b;
	}
	
	public void update() {
		if(shipObjs[0].isSunk() && shipObjs[1].isSunk() && shipObjs[2].isSunk() && 
				shipObjs[3].isSunk() && shipObjs[4].isSunk()) {
			allSunk = true;
			board.gameOver();
		}
	}
	
	public Ship[] getShips() {
		return shipObjs;
	}
}