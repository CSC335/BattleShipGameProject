package battleship;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CanvasBoard extends Application{
	
	private Canvas canvas;
	private GraphicsContext gc;
	private GridPane gp;
	
	public static void main(String[] args) {
		launch(args);
	}
		
	@Override
	public void start(Stage stage){
//		// TODO Auto-generated method stub
//		gp = new GridPane();
//		canvas = new Canvas(360, 360);
//		CanvasView canvasView = new CanvasView();
//		gc = canvas.getGraphicsContext2D();
//		Scene scene = new Scene(gp, 400, 450);
//		gc.setFill(Color.DODGERBLUE);
//		Board board = new Board(true);
//		board.makeTest();
//		ArrayList<Ship> shipsList = new ArrayList<Ship>();
//		for(int i = 0; i < 5; i++) {
//			shipsList.add(board.ships.shipObjs[i]);
//		}
//		canvasView.updateBoard(board);
//		Random rand = new Random();
//		for(int i = 0; i < 20; i++) {
//			board.guess(rand.nextInt(10), rand.nextInt(10));
//		}
////		for(int i = 0; i < 360; i+= 36) {
////			for(int j = 0; j < 360; j+= 36) {
////				gc.strokeRect(i, j, 36, 36);
////				gc.fillRect(i, j, 36, 36);
////			}
////		}
////		
////		gc.strokeRect(0, 0, 360, 360);
////		Image miss = new Image("file:Images/missTest.png", 36, 36, false, false);
////		Image ship = new Image("file:Images/cruiser3.png", 108, 36, false, false);
////		Image ship1 = new Image("file:Images/cruiser5.png", 180, 36, false, false);
////		Image ship2 = new Image("file:Images/destroyer.png", 72, 36, false, false);
////		Image ship3 = new Image("file:Images/bShip.png", 144, 36, false, false);
////		Image ship4 = new Image("file:Images/sub.png", 98, 36, false, false);
////		//gc.drawImage(miss, 0, 0);
////		gc.drawImage(ship, 0, 0);
////		gc.drawImage(ship1, 0, 72);
////		gc.drawImage(ship2, 0, 36);
////		gc.drawImage(ship3, 0, 108);
////		gc.drawImage(ship4, 0, 144);
//		canvasView.updateBoard(board);
//		gp.add(canvasView, 0, 0);
//		stage.setScene(scene);
//		stage.show();
		
	}
	
	
}
