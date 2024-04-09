package battleship;

//Represents a point on board
public class Square {
	
	int x;
	int y;
	boolean guessed;
	Ship ship;
	
	public Square(int xNew, int yNew) {
		x = xNew;
		y = yNew;
		guessed = false;
		ship = null;
	}
	
	public void place(Ship s) {
		ship = s;
	}
	
	public void guess() {
		guessed = true;
		if(ship != null) {
			ship.guess();
		}
	}
	
	//tbh i forgot which chars we were using, might need to be updated
	/*
	States:
		player == true:
			hasShip & guessed    -> ship.getID() (lower case)
			hasShip & notGuessed -> ship.getID() (upper case)
			noShip & guessed     -> 'X'
			noShip & notGuessed  -> '.'
		player == false:
			hasShip & guessed    -> 'H' 
			hasShip & notGuessed -> '.' 
			noShip & guessed     -> 'X'
			notGuessed           -> '.'
	*/
	public char state(boolean player) {
		if(player) {
			if(ship == null && guessed == true) {
				//missed
				return 'X';
			}else if(ship != null){
				if(guessed) {
					return Character.toLowerCase(ship.getID());
				}else {
					return ship.getID();
				}
			}else {
				return '.';
			}
		}else {
			if(ship == null && guessed == true) {
				//missed
				return 'X';
			}else if(ship != null && guessed == true) {
				//hit
				return 'H';
			}else {
				return '.';
			}
		}
	}
}