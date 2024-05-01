package battleship;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TutorialScreen extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	Button moreInfo, done;
	boolean info;
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane root = new BorderPane();
		GridPane howTo = new GridPane();
		GridPane tut = new GridPane();
		
		Label tutorial = new Label("  Tutorial  ");
		Label instructions = new Label("1. you can select what type of shot you'd like to use \n"
			 + "2. if you use \"row\" it goes across the row \n"
			 + "3. if you use \"col\" it will go down the column\n"
			 + "4. \"3x3\" fires a shot in a 3x3 square\n"
			 + "5. you can use each type of special shot only once\n"
			 + "6. click where you would like to make a guess\n"
			 + "7. sink their ships before they sink yours\n"
			 + "8. enjoy the music\n");
		tutorial.setFont(Font.font("Impact", 40));
		tutorial.setTextFill(Color.rgb(86, 147, 187));
		tutorial.setEffect(new Glow(0.8));
		tutorial.setAlignment(Pos.CENTER);
		instructions.setFont(Font.font("Impact", 15));
		GridPane buttons = new GridPane();
		moreInfo = new Button("More info");
		done = new Button("I get it");
		buttons.add(done, 0, 0);
		buttons.add(moreInfo, 1, 0);
		buttons.setAlignment(Pos.CENTER);
		Label space = new Label("  ");
		buttons.add(space, 0, 1);
		buttons.setHgap(20);
		info = false;
		moreInfo.setOnAction(event->{
			if (info == true) {
				instructions.setText("you, my friend, simply cannot be taught");
				tutorial.setFont(Font.font("Impact", 80));
			}else {
				info = true;
			instructions.setText("I will make it real simple for you \n"
					+ "1. clicking a square is a \"guess\" \n"
					 + "2. you can use the special shots or not I dont care\n"
					 + "3. If you make a hit, guess the spots nearby!\n"
					 + "4. I reccomend guessing in a checker board pattern to be efficient\n"
					 + "5. you want to sink all of their ships before they get yours \n"
					 + "6. dude idk if you havent figured it out by now\n");
				tutorial.setFont(Font.font("Impact", 60));
			}});
		done.setOnAction(event->{
			//javafx.application.Platform.exit();
			stage.close();
		});
		howTo.add(instructions, 0, 0);
		howTo.setAlignment(Pos.CENTER);
		tut.setAlignment(Pos.CENTER);
		tut.add(tutorial, 0, 0);
		root.setTop(tut);
		root.setCenter(howTo);
		root.setBottom(buttons);
		
		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
	    stage.show();
	}
}
