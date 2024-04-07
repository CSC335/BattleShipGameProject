package battleship;
import java.util.*;
//Doesn't do anything important, just lets me print random stuff out
//-Alec
public class TesterIgnore {

	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Board board = new Board(true);
//		board.makeTest();
//		char[][] testB = board.getCharBoard();
//		for(int i = 0; i < 10; i++) {
//			for(int j = 0; j < 10; j++) {
//				System.out.print(testB[i][j]);
//				System.out.print(" ");
//			}
//			System.out.println();
//		}
//		printBoard(board);
//		Scanner sc = new Scanner(System.in);
//		int x;
//		int y;
//		while(board.getState()) {
//			
//			System.out.println("Make guess, input x then y");
//			x = sc.nextInt();
//			y = sc.nextInt();
//			board.guess(x, y);
//			printBoard(board);
//			
//		}
//		sc.close();
//		Board AIboard = new Board(false);
//		AIboard.makeTest();
//		char[][] testAIB = AIboard.getCharBoard();
//		for(int i = 0; i < 10; i++) {
//			for(int j = 0; j < 10; j++) {
//				System.out.print(testAIB[i][j]);
//				System.out.print(" ");
//			}
//			System.out.println();
//		}
//		Random rand = new Random();
//		System.out.println(rand.nextInt(10));
		ComputerPlayer cpl = new ComputerPlayer(new RandomAI());
		cpl.makeBoard();
		printBoard(cpl.board);
	}
	
	public static void printBoard(Board b) {
		char[][] testB = b.getCharBoard();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.print(testB[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

}
