package battleship;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Player {
	private Board myBoard;
	private Board oppBoard;

	// board is [0, 1, ..., 8, 9]
	private final int BOARD_SIZE = 10;

	// ships is an arrayList for flexibility (like adding ships midgame)
	public ArrayList<Ship> ships;

	// Player initializer
	public Player(Board myBoard, Board oppBoard) {
		// creates a new player with opposing players's board
		this.myBoard = myBoard;
		this.oppBoard = oppBoard;

		// TODO: this currently has no use
		ships = new ArrayList<Ship>();
	}

	/*
	 * return int[x][y] of each cell of the described ship int x, y: coords of one
	 * end of ship orientation: (0=up, 1=right, 2=down, 3=right) size: # cells that
	 * the ship takes up
	 * 
	 * 
	 * Ex. ShipInfoToArray(5, 5, 2, 3) returns new int[][] {{5, 5}, {5, 4}, {5, 3}}
	 */
	private int[][] ShipInfoToArray(int y, int x, int orientation, int size) {
		if (orientation < 0 || orientation > 4) {
			// TODO: output exception if necessary
			System.out.println("Player.ShipAdd() orientation out of bounds: " + orientation);
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
				shipArr[i] = new int[] { x + (i * negateX), y };
			}
		}
		return shipArr;
	}

	/*
	 * returns true and adds ship if valid; returns false otherwise int x, y: coords
	 * of one end of ship orientation: (0, 1, 2, 3) -> (up, right, down, left) size:
	 * # cells that the ship takes up
	 */
	public boolean ShipAdd(int x, int y, int orientation, Ship ship) {
		System.out.println(orientation);
		ship.setOrientation(orientation);
		// Ex. ShipInfoToArray(5, 5, 2, 3) returns
		// new int[][] {{5, 5}, {5, 4}, {5, 3}}
		int[][] shipArr = ShipInfoToArray(y, x, orientation, ship.size());
		
		if (myBoard.addShip(shipArr, ship)) {
			myBoard.shipsPlaced++;
			return true;
		}
		return false;
	}

	// returns true and plays move if valid; returns false otherwise
	public boolean playMove(int yValue, int xValue) {

		// make sure guess is within bounds
		if (xValue >= 10 || xValue < 0) {
			if (yValue >= 10 || yValue < 0) {
				// alert both values invalid
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("Invalid Guess");
				alert.setContentText("Both X and Y coordinates are invalid");
				alert.showAndWait();
				return false;
			} else {
				// alert x invalid
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("Invalid Guess");
				alert.setContentText("X coordinate is invalid");
				alert.showAndWait();
				return false;
			}
		} else if (yValue >= 10 || yValue < 0) {
			// alert y invalid
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Invalid Guess");
			alert.setContentText("Y coordinate is invalid");
			alert.showAndWait();
			return false;
		}

		// return false if coords have already been guessed
		if (oppBoard.getSqState(xValue, yValue, false) != '.') {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Invalid Guess");
			alert.setContentText("This location has already been guessed");
			alert.showAndWait();
			return false;
		}

		oppBoard.guess(xValue, yValue);

		return true;
	}
}
