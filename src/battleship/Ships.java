package battleship;

public class Ships {
	
	Ship[] shipObjs;
	
	public Ships() {
		shipObjs = new Ship[5];
		shipObjs[0] = new Ship(5, "Carrier");
		shipObjs[1] = new Ship(4, "Battleship");
		shipObjs[2] = new Ship(3, "Cruiser");
		shipObjs[3] = new Ship(3, "Submarine");
		shipObjs[4] = new Ship(2, "Destroyer");
	}
	
	
}