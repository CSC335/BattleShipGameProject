package battleship;

public class InstaSink extends Skill {

	public InstaSink(Board myBoard, Board oppBoard) {
		super(myBoard, oppBoard);
	}
	
	public void ExecuteSkill(int x, int y) {
		if (!enabled)
			return;
		// TODO Auto-generated method stub
		
		enabled = false;
	}

}
