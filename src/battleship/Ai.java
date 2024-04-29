package battleship;

import java.util.Random;

public class Ai implements BattleShipStrategy {
	// at the beginning the ai can be random
	private static Random generator = new Random();

	@Override
	public int[][] desiredShip(Board b, Ship ship) {
		int[][] cur = null;
		int ori = 0;
		while (cur == null) {
			ori = generator.nextInt();
			cur = ShipInfoToArray(generator.nextInt(10 - ship.size()), generator.nextInt(10 - ship.size()),
					ori, ship.size());
		}
		ship.setOrientation(ori);
		return cur;
	}

	@Override
	public int[] desiredMove(Board b) {
		boolean[][] guessed = b.getGuessed();
		int[] returned = new int[2];
		boolean running = true;

		int x = 0;
		int y = 0;

		int lastHitX = -1;
		int lastHitY = -1;

		// Find the last hit that can be expanded upon
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				// get already hit square letter
				char hitSquare = b.getSqState(i, j, true);
				
				// check if the square near hit square has a place to hit
				int[] availableSquare = availableHitSquare(i, j, b);
				
				// check if it is lower case letter which is hit before and it has a near by square can hit
				if (hitSquare != 'X' && hitSquare != '.' && Character.isLowerCase(hitSquare) 
						&& (availableSquare[0] != -1 && availableSquare[1] != -1)) {
					// last hit update
					lastHitX = i;
					lastHitY = j;
					
					// return next step which can hit another square
					returned[0] = availableSquare[0];
					returned[1] = availableSquare[1];
					
					return returned;
				}
			}
		}

		// default random move
		while (running) {
			x = generator.nextInt(10);
			y = generator.nextInt(10);
			if (!guessed[x][y]) {
				returned[0] = x;
				returned[1] = y;
				System.out.printf("Random: x = %d, y = %d\n", x, y);
				running = false;
				break;
			}
		}
		return returned;
	}
	
	public int[] availableHitSquare(int x, int y, Board b) {
		int[][] directions = { { 1, 0 }, { 0, 1 } };
		int[] target = new int[2];
		for (int[] dir : directions) {
			
			// Go to upper or right square
			int upperX = x + dir[0];
			int rightY = y + dir[1];
			
			 // Check bounds before accessing the board
	        if (upperX >= 0 && upperX < 10 && rightY >= 0 && rightY < 10) {
	        	
	        	// get this square letter
	        	char nextPlusSquare = b.getSqState(upperX, rightY, true);
	        	
	        	// Check if this square is possible boat place
	        	if (nextPlusSquare != 'X' && !Character.isLowerCase(nextPlusSquare)) {
	        		target[0] = upperX;
	        		target[1] = rightY;
	        		return target;
	        	}
	        }
	        
	        // Go to down or left square
	        int downX = x - dir[0];
			int leftY = y - dir[1];
			System.out.printf("x: %d, y: %d", downX, leftY);
			 // Check bounds before accessing the board
	        if (downX >= 0 && downX < 10 && leftY >= 0 && leftY < 10) {
	        	
	        	// get this square letter
	        	char nextMinusSquare = b.getSqState(downX, leftY, true);
	        	
	        	// Check if this square is possible boat place
	        	if (nextMinusSquare != 'X' && nextMinusSquare != '.' && Character.isUpperCase(nextMinusSquare)) {
	        		target[0] = downX;
	        		target[1] = leftY;
	        		return target;
	        	}
	        }
		}
		
		// if there are not any available square can hit set target (-1, -1)
		target[0] = -1;
		target[1] = -1;
		return target;
	}

}
