package battleship;
//Parent class for ai strategy
public interface BattleShipStrategy {
	public int[] desiredMove(Board b);
}
