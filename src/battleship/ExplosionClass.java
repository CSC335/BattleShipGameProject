package battleship;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ExplosionClass {
	int i = 0;
	int x, y;
	GraphicsContext gc;
	Image[] images;
	
	//To Use:
	//set coords of where the 36x36 square it should be
	//then call playExplosion
	
	public ExplosionClass(GraphicsContext g) {
		gc = g;
		x = 0;
		y = 0;
		Image image = new Image("File:Explosion/explosion6.png");
		Image image2 = new Image("File:Explosion/explosion3.png");
		Image image3 = new Image("File:Explosion/explosion4.png");
		Image image4 = new Image("File:Explosion/explosion5.png");
		images = new Image[4];
		images[0] = image;
		images[1] = image2;
		images[2] = image3;
		images[3] = image4;
	}
	
	public void setCoords(int newx, int newy) {
		x = newx;
		y=newy;
	}
	
	public void playExplosion() {
		Timeline timeline2 = new Timeline();
		timeline2.setCycleCount(4);
		timeline2.getKeyFrames().add(new KeyFrame(Duration.millis(400),new AnimateStarter()));
		timeline2.play();
	}
	
	 private class AnimateStarter implements EventHandler<ActionEvent> {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 if (i == 4) {
					 i = 0;
				 }
				 gc.clearRect(0, 0, 360, 360);
				 gc.drawImage(images[i], x, y);
				 i ++;
			}
			
			 
		 }
	
	
}
