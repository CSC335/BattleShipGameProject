package battleship;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Graphicsprac extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	ImageView[] images, images2;
	Image[] imageA;
	Image missImg;
	GraphicsContext gc;
	StackPane stack;
	 private int tic = 0;
	 private int i = 0;
	 BorderPane root, image;
	 Timeline timeline;
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stack = new StackPane();
		root = new BorderPane();
		image = new BorderPane();
		Canvas canvas = new Canvas(360, 360);
		gc = canvas.getGraphicsContext2D();
		missImg = new Image("file:Images/miss.png", 36, 36, false, false);
		makeImages();
		makeImages2();
		Button start = new Button("Start Exp");
			start.setOnAction(event -> {
				Timeline timeline = new Timeline(
				    new KeyFrame(
				        Duration.millis(400), new AnimateStarter())
				);
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
				});
				
			Button startWater = new Button("Start water");
			startWater.setOnAction(event -> {
				Timeline timeline2 = new Timeline();
				timeline2.setCycleCount(4);
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
						   new AnimateStarter2()));
				
				timeline2.play();
				});
			Button addShip = new Button("ship");
			addShip.setOnAction(event->{
				gc.drawImage(missImg, 150, 150);
			});
			stack.getChildren().addAll(image, canvas);
		root.setCenter(stack);
		root.setTop(start);
		root.setBottom(startWater);
		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
	    stage.show();
	}
	
	Runnable Animate = new Runnable() {
	    public void run() {
	    	nextFrame();
	    }
	};
	
	private void nextFrame() {
		image.setCenter(null);
   	 if (i == 4) {
			 
		 }
		 image.setCenter(images[i]);
		 i ++;
	}
	private void nextFrame2() {
		image.setCenter(null);
   	 if (i == 5) {
			 i = 0;
		 }
		 image.setCenter(images2[i]);
		 i ++;
	}
	
	private	void makeImages() {
		Image image = new Image("File:Explosion/explosion6.png");
		ImageView pic = new ImageView();
		pic.setFitWidth(250);
		pic.setFitHeight(250);
		pic.setImage(image);
		Image image2 = new Image("File:Explosion/explosion3.png");
		ImageView pic2 = new ImageView();
		pic2.setFitWidth(250);
		pic2.setFitHeight(250);
		pic2.setImage(image2);
		Image image3 = new Image("File:Explosion/explosion4.png");
		ImageView pic3 = new ImageView();
		pic3.setFitWidth(250);
		pic3.setFitHeight(250);
		pic3.setImage(image3);
		Image image4 = new Image("File:Explosion/explosion5.png");
		ImageView pic4 = new ImageView();
		pic4.setFitWidth(250);
		pic4.setFitHeight(250);
		pic4.setImage(image4);
		images = new ImageView[4];
		images[0] = pic;
		images[1] = pic2;
		images[2] = pic3;
		images[3] = pic4;
		imageA = new Image[4];
		imageA[0] = image;
		imageA[1] = image2;
		imageA[2] = image3;
		imageA[3] = image4;
	}
	private	void makeImages2() {
		Image image = new Image("File:Explosion/water1.png");
		ImageView pic = new ImageView();
		pic.setFitWidth(250);
		pic.setFitHeight(250);
		pic.setImage(image);
		Image image2 = new Image("File:Explosion/water2.png");
		ImageView pic2 = new ImageView();
		pic2.setFitWidth(250);
		pic2.setFitHeight(250);
		pic2.setImage(image2);
		Image image3 = new Image("File:Explosion/water3.png");
		ImageView pic3 = new ImageView();
		pic3.setFitWidth(250);
		pic3.setFitHeight(250);
		pic3.setImage(image3);
		images2 = new ImageView[5];
		images2[0] = pic;
		images2[1] = pic2;
		images2[2] = pic3;
		images2[3] = pic2;
		images2[4] = pic;
	}
	
	 private class AnimateStarter implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			nextFrame();
		}
		 
	 }
	 private class AnimateStarter2 implements EventHandler<ActionEvent> {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 if (i == 4) {
					 i = 0;
				 }
				 gc.drawImage(imageA[i], 250, 250);
				 i ++;
			}
			
			 
		 }
}
