package battleship;
import java.util.Random;

//Makes random move each time
public class RandomAI implements BattleShipStrategy{
	
	private static Random generator;
	
	public RandomAI() {
		generator = new Random();
	}

	@Override
	public int[] desiredMove(Board b) {
		boolean[][] guessed = b.getGuessed();
		int[] returned = new int[2];
		boolean running = true;
		int x = 0;
		int y = 0;
		while(running) {
			x = generator.nextInt(10);
			y = generator.nextInt(10);
			if(!guessed[x][y]) {
				running = false;
			}
		}
		returned[0] = x;
		returned[1] = y;
		return returned;
	}
	
}
