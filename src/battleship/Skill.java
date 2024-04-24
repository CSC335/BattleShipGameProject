package battleship;

public abstract class Skill {
	private boolean enabled;
	
	public Skill() {
		this.enabled = true;
	}
	
	public void setEnabled(boolean newEnabled) {
		newEnabled = this.enabled;
	}
	
	public boolean isEnabeled() {
		return enabled;
	}
	
	public abstract void ExecuteSkill();
}
