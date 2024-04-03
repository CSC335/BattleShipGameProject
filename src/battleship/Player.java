package battleship;

import java.util.ArrayList;

public class Player {
	private Board myBoard;
	private Board oppBoard;
	
	private final int BOARD_SIZE = 8;
	
	// ships is an arrayList flexibility (like adding ships midgame)
	private ArrayList<Ship> ships;
	
	// Player initializer
	public Player(Board myBoard, Board oppBoard) {
		//creates a new player with opposing players's board
		this.myBoard  = myBoard;
		this.oppBoard = oppBoard;
		
		ships = new ArrayList<Ship>();
	}
	
	/* 	returns true and adds ship if valid; returns false otherwise
	   	int x, y: coords of one end of ship
	   	orientation: (0, 1, 2, 3) -> (up, right, down, left) 
	  	size: # cells that the ship takes up
	*/
	public boolean ShipAdd(int x, int y, int orientation, int size) {
		int[][] shipArr = ShipInfoToArray(x, y, orientation, size);
		for (int[] point : shipArr) {
			
			// return false if outside of board bounds.
			if (point[0] < 0 || point[0] >= BOARD_SIZE || point[1] < 0 || point[1] >= BOARD_SIZE) {
				System.out.println("Player.ShipAdd() coords out of bounds");
				return false;
			}
			
			/* TODO: when board methods are made
				if (myBoard.getCell(point[0], point[1]) != EMPTY) {
					// TODO: output exception if necessary
					  System.out.println("Player.ShipAdd() coords occupied");
				    return false;
				}
				
			 */
		}
		/* TODO: when board methods are made
		
		// orientation and size params may help with graphics.
		myBoard.addShip(shipArr, orientation, size);

		*/
		return true;
	}
	
	/* 	return int[x][y] of each cell of the described ship
	 	int x, y: coords of one end of ship
	 	orientation: (0=up, 1=right, 2=down, 3=right)
	 	size: # cells that the ship takes up
	 	
	 	
	 	Ex. ShipInfoToArray(5, 5, 2, 3) returns
	 		new int[][] {{5, 5}, {5, 4}, {5, 3}}
	*/ 
	private int[][] ShipInfoToArray(int x, int y, int orientation, int size) {
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
	
	// returns true and plays move if valid; returns false otherwise
	public boolean ShipFire(int x, int y) {
		
		// return false if outside of board bounds.
		if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) {
			// TODO: output exception if necessary
			System.out.println("Player.ShipFire() coords out of bounds");
			return false;
		}
		
		/* TODO: when board methods are made
		if (oppBoard.getCell(point[0], point[1]) != SHOT_AT_BEFORE) {
			// TODO: output exception if necessary
			System.out.println("Player.ShipFire() coords already tried");
		    return false;
		}
	    */
		
		
		/* TODO: when board methods are made
		
		// orientation and size params may help with graphics.
		oppBoard.fire(int x, int y);
		*/
		
		return true;
	}
}
