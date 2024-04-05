package battleship;

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