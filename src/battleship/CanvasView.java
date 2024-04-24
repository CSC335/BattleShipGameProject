package battleship;
import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class CanvasView extends BorderPane{
	
	private Canvas canvas;
	private GraphicsContext gc;
	//true if 
	private boolean player;
	private Image hitImg;
	private Image missImg;
	private Image sunkImg;
	
	public CanvasView() {
		canvas = new Canvas(360, 360);
		gc = canvas.getGraphicsContext2D();
		player = true;
		hitImg = new Image("file:Images/hit5.png", 36, 36, false, false);
		missImg = new Image("file:Images/miss.png", 36, 36, false, false);
		sunkImg = new Image("file:Images/sunk.png", 36, 36, false, false);
		this.setCenter(canvas);
	}
	
	public void updateBoard(Board board) {
		gc.clearRect(0, 0, 360, 360);
		makeBackground();
		makeShips(board.placedShips);
		makeShots(board);
	}
	
	public void setPlayer(boolean update) {
		player = update;
	}
	
	public void makeBackground() {
		gc.setFill(Color.DODGERBLUE);
		for(int i = 0; i < 360; i+= 36) {
			for(int j = 0; j < 360; j+= 36) {
				gc.strokeRect(i, j, 36, 36);
				gc.fillRect(i, j, 36, 36);
			}
		}
		gc.strokeRect(0, 0, 360, 360);
	}
	
	public void makeShips(ArrayList<Ship> ships) {
		Ship curr = null;
		int minI = 0;
		int currI;
		ArrayList<Square> squares;
		Image img;
		for(int i = 0; i < ships.size(); i++) {
			curr = ships.get(i);
			if(player || curr.isSunk()) {
				squares = curr.squares();
				System.out.println(squares.toString());
				if(curr.orientation() % 2 == 0) {
					System.out.println(0);
					minI = 9;
					for(int j = 0; j < curr.size(); j++) {
						currI = squares.get(j).y;
						if(currI < minI) {
							minI = currI;
						}
					}
					img = curr.img();
					gc.drawImage(img, squares.get(0).x * 36, minI * 36);
					System.out.println("Drew img: " + curr.type + " : " + String.valueOf(squares.get(0).x)
					 + " : " + String.valueOf(minI * 36));
				}else {
					System.out.println("1");
					minI = 9;
					for(int j = 0; j < curr.size(); j++) {
						currI = squares.get(j).x;
						if(currI < minI) {
							minI = currI;
						}
					}
					img = curr.img();
					gc.drawImage(img, minI * 36, squares.get(0).y * 36);
					System.out.println("Drew img: " + curr.type + " : " + String.valueOf(minI * 36)
					 + " : " + String.valueOf(squares.get(0).y));
				}
			}
		}
	}
	
	public void makeShots(Board board) {
		Square curr;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				curr = board.getSquare(i, j);
				if(curr.guessed) {
					if(curr.ship == null) {
						gc.drawImage(missImg, i * 36, j * 36);
					}else {
						if(curr.ship.isSunk()) {
							gc.drawImage(sunkImg, i * 36, j * 36);
						}else {
							gc.drawImage(hitImg, i * 36, j * 36);
						}
					}
				}
			}
		}
	}
}
