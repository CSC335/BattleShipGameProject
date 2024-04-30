package battleship;

public class SkillShot {
	private boolean enabled;
	
	private int[][] shotOffsets;
	
	// shots go row, column, and 3x3
	public SkillShot(int[][] shotOffsets) {
		this.enabled = true;
		
		this.shotOffsets = shotOffsets;
	}
	
	public int[][] Execute(int x, int y) {
		if (!enabled)
			return null;
		int[][] coords = shotOffsets.clone();
		for (var coord : coords) {
			coord[0] += x;
			coord[1] += y;
		}
		return coords;
	}
	
	public boolean isEnabeled() {
		return enabled;
	}
}
