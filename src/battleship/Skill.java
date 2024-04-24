package battleship;

public abstract class Skill {
	protected boolean enabled;
	protected Board myBoard, oppBoard;
	
	
	public Skill(Board myBoard, Board oppBoard) {
		this.myBoard = myBoard;
		this.oppBoard = oppBoard;
		this.enabled = true;
	}
	
	public void setEnabled(boolean newEnabled) {
		newEnabled = this.enabled;
	}
	
	public boolean isEnabeled() {
		return enabled;
	}
	
	private Ship getShipFromCoords(int x, int y, Board board) {
		Ship curShip = null;
		
		
		return curShip;
	}
}
