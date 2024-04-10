package battleship;
//Parent class for ai strategy
public interface BattleShipStrategy {
	public abstract int[] desiredMove(Board b);
	
	public abstract int[][] desiredShip(Board b, Ship ship);

	/* 	return int[x][y] of each cell of the described ship
 	int x, y: coords of one end of ship
 	orientation: (0=up, 1=right, 2=down, 3=right)
 	size: # cells that the ship takes up
 	
 	
 	Ex. ShipInfoToArray(5, 5, 2, 3) returns
 		new int[][] {{5, 5}, {5, 4}, {5, 3}}
	 */ 
	 default int[][] ShipInfoToArray(int x, int y, int orientation, int size) {
		if (orientation < 0 || orientation > 4) {
			// TODO: output exception if necessary
			System.out.println("Player.ShipAdd() orientation out of bounds.");
			return null;
		}
		
		
		int[][] shipArr = new int[size][2];
		if (orientation % 2 == 0) {
			// 0 = up, 2 = down;
			
			// negate iff down
			int negateY = 1;
			if (orientation == 2)
				negateY = -1;
			
			
			for (int i = 0; i < size; i++) {
				shipArr[i] = new int[] { x, y + (i * negateY) };
			}
		} else {
			// 1 = right, 3 = left;
			
			// negate iff left
			int negateX = 1;
			if (orientation == 3)
				negateX = -1;
			
			for (int i = 0; i < size; i++) {
				shipArr[i] = new int[] { x + (i * negateX), y};
			}
		}
		return shipArr;
	}
}
