package battleship;

public class ReEnable extends Skill{

	public ReEnable(Board myBoard, Board oppBoard) {
		super(myBoard, oppBoard);
	}
	
	public void ExecuteSkill(Skill skill) {
		if (!enabled)
			return;

		enabled = false;
	}

}
