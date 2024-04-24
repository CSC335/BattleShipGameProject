package battleship;

public class MoveShip extends Skill {

	public MoveShip(Board myBoard, Board oppBoard) {
		super(myBoard, oppBoard);
	}
	
	public void ExecuteSkill(Ship ship, int offset, boolean vertically) {
		if (!enabled)
			return;
		
		enabled = false;
	}
	
}
