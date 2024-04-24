package battleship;

public class Missile extends Skill {

	public Missile(Board myBoard, Board oppBoard) {
		super(myBoard, oppBoard);
	}
	
	public void ExecuteSkill(int x, int y) {
		if (!enabled)
			return;
		
		enabled = false;
	}

}
