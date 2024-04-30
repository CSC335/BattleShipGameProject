package battleship;

public class Ships {
	
	public Ship[] shipObjs;
	boolean allSunk;
	Board board;
	
	public Ships(Board b) {
		shipObjs = new Ship[5];
		shipObjs[0] = new Ship(5, "Carrier", 'C', this, "file:Images/cruiser5.png", "file:Images/cruiser51.png");
		shipObjs[1] = new Ship(4, "Battleship", 'B', this, "file:Images/bShip.png", "file:Images/bShip1.png");
		shipObjs[2] = new Ship(3, "Cruiser", 'U', this, "file:Images/cruiser3.png", "file:Images/cruiser31.png");
		shipObjs[3] = new Ship(3, "Submarine", 'S', this, "file:Images/sub.png", "file:Images/sub1.png");
		shipObjs[4] = new Ship(2, "Destroyer", 'D', this, "file:Images/destroyer.png", "file:Images/destroyer1.png");
		allSunk = false;
		board = b;
	}
	
	public int getSunkShips() {
		int count = 0;
		for (Ship ship : shipObjs) {
			if (ship.isSunk())
				count++;
		}
		return count;
	}
	
	public Ship[] getShips() {
		return shipObjs;
	}
}