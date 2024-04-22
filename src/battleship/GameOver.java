package battleship;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameOver extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	Button newGame, end;
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		StackPane gO = new StackPane();
		StackPane statTitle = new StackPane();
		StackPane	statsTwo = new StackPane();
		StackPane	statsThree = new StackPane();
		StackPane	statsFour = new StackPane();
		StackPane	statsFive = new StackPane();
		BorderPane root = new BorderPane();
		GridPane title = new GridPane();
		//Border border = new Border();
		GridPane gp = new GridPane();
		GridPane buttons = new GridPane();
		gO.setPadding(new Insets(10));
	    Label gameOver = new Label("  Game Over  ");
	    //in the stat label makes it so it is the 
	    //(p1.ss + "Ships Sunk" + p2.ss)
	    Label stats = new Label("P1       Game Stats      P2");
	    Label ss = new Label("Ships Sunk");
	    Label gm = new Label("Guesses Made");
	    Label miss = new Label("Misses");
	    Label hits = new Label("Hits");
	    
		gameOver.setFont(Font.font("Impact", 40));
		stats.setFont(Font.font("Impact", 20));
		//gameOver.setTextFill(Color.TRANSPARENT);

		// Create a linear gradient for the text color
		gameOver.setTextFill(Color.rgb(184, 16, 4));

		// Apply effects like drop shadow and reflection
		gameOver.setEffect(new InnerShadow());
		gameOver.setEffect(new Glow(0.8));
		
		//add the Stats
		gp.add(statTitle, 1, 0);
		gp.add(statsTwo, 1, 1);
		gp.add(statsThree, 1, 2);
		gp.add(statsFour, 1, 3);
		gp.add(statsFive, 1, 4);
		gp.setAlignment(Pos.CENTER);
		gp.setVgap(5);
		
		
		statTitle.getChildren().add(stats);
		statsTwo.getChildren().add(ss);
		statsThree.getChildren().add(gm);
		statsFour.getChildren().add(miss);
		statsFive.getChildren().add(hits);
		StackPane.setAlignment(stats, Pos.TOP_CENTER);
		StackPane.setAlignment(ss, Pos.TOP_CENTER);
		StackPane.setAlignment(gm, Pos.TOP_CENTER);
		StackPane.setAlignment(miss, Pos.TOP_CENTER);
		StackPane.setAlignment(hits, Pos.TOP_CENTER);
	
		//Images for title
		Image image = new Image("File:images/BattleShip2.png");
		ImageView pic = new ImageView();
		pic.setFitWidth(80);
		pic.setFitHeight(80);
		pic.setImage(image);
		title.add(pic, 0, 0);
		Image image2 = new Image("File:images/BattleShip4.png");
		ImageView pic2 = new ImageView();
		pic2.setFitWidth(80);
		pic2.setFitHeight(80);
		pic2.setImage(image2);
		title.add(pic2, 2, 0);
		title.setAlignment(Pos.CENTER);
		
		//buttons
		newGame = new Button("Play Again?");
		end = new Button("End game.");
		buttons.add(newGame, 0, 0);
		buttons.add(end, 1, 0);
		buttons.setVgap(20);
		buttons.setHgap(20);
		buttons.setAlignment(Pos.CENTER);
		newGame.setOnAction(event -> {
			//start a new game
		});
		end.setOnAction(event -> {
			//close
		});
		
		title.add(gO, 1, 0);
		gO.getChildren().add(gameOver);
		gO.setAlignment(gameOver, Pos.CENTER);
		root.setTop(title);
		root.setCenter(gp);
		root.setAlignment(gp, Pos.CENTER);
		root.setBottom(buttons);
		root.setBorder(null);
		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
	    stage.show();
	}
}
