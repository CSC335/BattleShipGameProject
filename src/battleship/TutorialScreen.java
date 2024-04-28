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
		Label instructions = new Label("1. The ammo listed is the amount of 'Special shots' \n"
			 + "2. A special shot works ...\n"
			 + "3. have fun\n"
			 + "4. be nice\n"
			 + "5. guess their ships before they get yours\n"
			 + "6. Enjoy the music\n");
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
				instructions.setText("I give up on teaching you");
				tutorial.setFont(Font.font("Impact", 80));
			}else {
				info = true;
			instructions.setText("I will make it real simple for you \n"
					+ "1. The ammo listed is the amount of 'Special shots' \n"
					 + "2. A special shot works ...\n"
					 + "3. You put in the coordinates of where you think the enemy ship is\n"
					 + "4. if its a hit guess the nearby spots to sink a ship\n"
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
