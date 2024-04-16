package battleship;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameOver extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane gO = new BorderPane();
		gO.setPadding(new Insets(40));
	    Label gameOver = new Label("  Game Over  ");
		gameOver.setFont(Font.font("Impact", 40));
		gameOver.setTextFill(Color.TRANSPARENT);

		// Create a linear gradient for the text color
		gameOver.setTextFill(Color.rgb(184, 16, 4));

		// Apply effects like drop shadow and reflection
		gameOver.setEffect(new InnerShadow());
		gameOver.setEffect(new Glow(0.8));
	
	
		gO.setTop(gameOver);
		Scene scene = new Scene(gO, 400, 300);
		stage.setScene(scene);
	    stage.show();
	}
}
