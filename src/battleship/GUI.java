package battleship;

import java.io.File;
import java.net.URI;
import java.util.Random;
import java.util.Scanner;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUI extends Application {
	// need to display both boards the players and the ai board
	// the ai board wont have visible ships but needs to show where the player
	// has guessed and hit
	//

	// Create a label for the title "Battleship"
	private Label titleLabel = new Label("Battleship");

	// Create radio buttons for game mode selection
	private RadioButton randomAiRadioButton = new RadioButton("Random AI");
	private RadioButton hardAiRadioButton = new RadioButton("Hard AI");
	private RadioButton players2RadioButton = new RadioButton("2 Players");

	private static RadioButton funMusic = new RadioButton("Fun Music");
	private static RadioButton boringMusic = new RadioButton("Boring Music");

	private static boolean fun = false;
	private static boolean playerTurn = false;

	static BattleshipGame game;

	
	static ToggleGroup skillShots = new ToggleGroup();
	static RadioButton skillShot0 = new RadioButton("row");
	static RadioButton skillShot1 = new RadioButton("col");
	static RadioButton skillShot2 = new RadioButton("3x3");
	static RadioButton skillShot3 = new RadioButton("normal (default)");
	
	// static TextArea firstBoardArea = new TextArea();
	// static TextArea secondBoardArea = new TextArea();
	static int wR, wL;
	static ImageView[] waterR, waterL;
	static StackPane firstBoardStack;
	static StackPane secondBoardStack;
	static CanvasView firstBoardA = new CanvasView();
	static CanvasView secondBoardA = new CanvasView();
	static ExplosionClass expL, expR;

	private static boolean isPlayer1 = true;

	static BorderPane root, waterRpane, waterLpane;

	private static Stage primaryStage;
	private static MediaPlayer songPlayer;
	private static MediaPlayer effectsPlayer;
	
	public	static Timeline timelineR, timelineL;
	
	private static Canvas expLC, expRC;

	@Override
	public void start(Stage primaryStage) {
		GUI.primaryStage = primaryStage;

		// Set title "Battleship" style
		titleLabel.setFont(Font.font("Impact", 40));
		titleLabel.setTextFill(Color.TRANSPARENT);

		// Create a linear gradient for the text color
		titleLabel.setTextFill(Color.rgb(0, 76, 153));

		// Apply effects like drop shadow and reflection
		titleLabel.setEffect(new DropShadow());
		titleLabel.setEffect(new Reflection());

		// Create radio buttons for music selection
		ToggleGroup musicSelection = new ToggleGroup();
		funMusic.setToggleGroup(musicSelection);
		boringMusic.setToggleGroup(musicSelection);
		funMusic.setStyle("-fx-font-size: 16px;" + "-fx-text-fill: rgb( 0, 0, 102);" + "-fx-font-family: Roboto Slab");
		boringMusic
				.setStyle("-fx-font-size: 16px;" + "-fx-text-fill: rgb( 0, 0, 102);" + "-fx-font-family: Roboto Slab");

		// Create radio buttons for game mode selection
		ToggleGroup gameModeGroup = new ToggleGroup();
		randomAiRadioButton.setToggleGroup(gameModeGroup);
		hardAiRadioButton.setToggleGroup(gameModeGroup);
		players2RadioButton.setToggleGroup(gameModeGroup);
		randomAiRadioButton
				.setStyle("-fx-font-size: 16px;" + "-fx-text-fill: rgb( 0, 0, 102);" + "-fx-font-family: Roboto Slab");
		hardAiRadioButton
				.setStyle("-fx-font-size: 16px;" + "-fx-text-fill: rgb( 0, 0, 102);" + "-fx-font-family: Roboto Slab");
		players2RadioButton
				.setStyle("-fx-font-size: 16px;" + "-fx-text-fill: rgb( 0, 0, 102);" + "-fx-font-family: Roboto Slab");

		// Create a button to start the game
		Button startButton = new Button("S T A R T");
		startButton
				.setStyle("-fx-background-color: rgb(0, 51, 102); " + "-fx-text-fill: white; " + "-fx-font-size: 16px; "
						+ "-fx-pref-width: 120px; " + "-fx-pref-height: 40px;" + "-fx-font-family: Impact");
		startButton.setOnAction(event -> {
			fun = funMusic.isSelected();
			if (randomAiRadioButton.isSelected()) {
				// Start AI game
				System.out.println("Starting random AI game...");
				startGame(primaryStage, "random");
			} else if (hardAiRadioButton.isSelected()) {
				// Start AI game
				System.out.println("Starting hard AI game...");
				startGame(primaryStage, "hard");
			} else if (players2RadioButton.isSelected()) {
				// Start 2 players game
				System.out.println("Starting 2 players game...");
				startGame(primaryStage, "notAI");
			} else {
				// No game mode selected
				System.out.println("Please select a game mode.");
			}
		});

		// Create an HBox to hold the radio buttons
		HBox radioBox = new HBox(35, randomAiRadioButton, hardAiRadioButton, players2RadioButton);
		radioBox.setAlignment(Pos.CENTER);

		HBox musicBox = new HBox(35, funMusic, boringMusic);
		musicBox.setAlignment(Pos.CENTER);

		// Create a VBox to hold all elements
		VBox root = new VBox(60, titleLabel, radioBox, musicBox, startButton);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(50));

		// Set up the scene
		Scene scene = new Scene(root, 500, 400);

		primaryStage.setTitle("Battleship Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void startGame(Stage primaryStage, String whichAI) {
		game = new BattleshipGame(whichAI);

		// Determine board labels based on isAI flag
		Label leftLabel, rightLabel;
		if (whichAI.equals("random") || whichAI.equals("hard")) {
			leftLabel = new Label("AI Board");
			rightLabel = new Label("Your Board");
		} else {
			leftLabel = new Label("Player1 Board");
			rightLabel = new Label("Player2 Board");
		}

		// Set left label font and color
		leftLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
		leftLabel.setTextFill(Color.rgb(0, 51, 102));

		// Set right label font and color
		rightLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
		rightLabel.setTextFill(Color.rgb(0, 51, 102));

		// Create HBox for left and right labels
		HBox labelBox = new HBox(300, leftLabel, rightLabel);
		labelBox.setAlignment(Pos.CENTER);
		//make the stackPanes:
		firstBoardStack = new StackPane();
		waterRpane = new BorderPane();
		expRC = new Canvas(360, 360);
		GraphicsContext gr = expRC.getGraphicsContext2D();
		expR = new ExplosionClass(gr);
		firstBoardStack.getChildren().addAll(waterRpane, secondBoardA, expRC);
		water();
		
		secondBoardStack = new StackPane();
		waterLpane = new BorderPane();
		expLC = new Canvas(360, 360);
		GraphicsContext gL = expLC.getGraphicsContext2D();
		expL = new ExplosionClass(gL);
		secondBoardStack.getChildren().addAll(waterLpane, firstBoardA, expLC);
		waterL();
		
		// Create a BorderPane to hold all elements
		root = new BorderPane();
		root.setPadding(new Insets(40));
		root.setTop(labelBox);
		root.setRight(firstBoardStack);
		firstBoardA.setPlayer(false);
		root.setLeft(secondBoardStack);
		
		// set input grid in add ships mode
		setShipInputGrid();

		// Set up the scene
		Scene scene = new Scene(root, 860, 630);

		// prints both boards
		printBoards();

		// play song
		if (fun == true) {
			playAFunSong();
		} else {
			playABoringSong();
		}

		// Set the stage title and scene, then show the stage
		primaryStage.setTitle("Battleship Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private static void setShipInputGrid() {
		GridPane inputGrid = new GridPane();

		// Create input fields for x and y coordinates
		TextField xInput = new TextField();
		TextField yInput = new TextField();
		TextField orientation = new TextField();
		Ship nextShip = game.nextShip(isPlayer1);

		if (nextShip == null)
			setGuessInputGrid();

		Label shipSize = new Label("Size:" + nextShip.size());

		// Create Place button
		Button placeButton = new Button("P l a c e");
		placeButton
				.setStyle("-fx-background-color: rgb(0, 51, 102); " + "-fx-text-fill: white; " + "-fx-font-size: 14px; "
						+ "-fx-pref-width: 80px; " + "-fx-pref-height: 32px;" + "-fx-font-family: Impact");
		placeButton.setOnAction(event -> {
			// call addShip() function to update board
			addShip(xInput, yInput, orientation, shipSize);
		});

		// Create Place button
		Button randomPlaceButton = new Button("R a n d o m    P l a c e");
		randomPlaceButton
				.setStyle("-fx-background-color: rgb(0, 51, 102); " + "-fx-text-fill: white; " + "-fx-font-size: 14px; "
						+ "-fx-pref-width: 160px; " + "-fx-pref-height: 32px;" + "-fx-font-family: Impact");
		randomPlaceButton.setOnAction(event -> {
			// call addShip() function to update board
			game.getRandomPlace();
			printBoards();
			if (game.nextShip(isPlayer1) == null)
				setGuessInputGrid();
			else
				shipSize.setText("Size:" + game.nextShip(isPlayer1).size());
		

			printBoards();

			xInput.clear();
			yInput.clear();
			orientation.clear();

			// plays AI move or switches to next player
			if (game.isAI) {
				game.computerPlaceShip(true);
				printBoards();
			} else
				isPlayer1 = !isPlayer1;
			System.out.println(game.nextShip(isPlayer1));
			if (game.nextShip(isPlayer1) == null)
				setGuessInputGrid();
			else
				shipSize.setText("Size:" + game.nextShip(isPlayer1).size());
		});
		Button tutorialButton = new Button("Tutorial");
		tutorialButton
				.setStyle("-fx-background-color: rgb(0, 51, 102); " + "-fx-text-fill: white; " + "-fx-font-size: 16px; "
						+ "-fx-pref-width: 120px; " + "-fx-pref-height: 40px;" + "-fx-font-family: Impact");
		tutorialButton.setOnAction(event -> {
			TutorialScreen tutorialScreen = new TutorialScreen();
			try {
				tutorialScreen.start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		expRC.setOnMousePressed(event->{
			int x = (int) event.getX();
			int y = (int) event.getY();
			System.out.print("tried to make move ");
			System.out.print(x);
			System.out.print(" ");
			System.out.println(y);
			x = x / 36;
			y = y / 36;
			System.out.print("tried to make move ");
			System.out.print(x);
			System.out.print(" ");
			System.out.println(y);
			xInput.setText(String.valueOf(x));
			yInput.setText(String.valueOf(y));
		});
		// Create a GridPane to hold the xInput, yInput, and guessButton
		inputGrid.setHgap(10);
		inputGrid.setVgap(5);
		inputGrid.addRow(0, new Label("X:"), xInput);
		inputGrid.addRow(1, new Label("Y:"), yInput);
		inputGrid.addRow(2, new Label("rot (0,1,2,3):"), orientation);
		inputGrid.add(shipSize, 2, 0, 1, 1);
		inputGrid.add(placeButton, 2, 1, 1, 3);
		inputGrid.add(randomPlaceButton, 4, 1, 1, 3);
		inputGrid.add(tutorialButton, 4, 0, 3, 1);

		inputGrid.setAlignment(Pos.CENTER);

		root.setBottom(inputGrid);
	}

	private static void setGuessInputGrid() {
		GridPane inputGrid = new GridPane();
		// Create input fields for x and y coordinates
		TextField xInput = new TextField();
		TextField yInput = new TextField();

		// Create Guess button
		Button guessButton = new Button("G u e s s");
		guessButton
				.setStyle("-fx-background-color: rgb(0, 51, 102); " + "-fx-text-fill: white; " + "-fx-font-size: 14px; "
						+ "-fx-pref-width: 80px; " + "-fx-pref-height: 32px;" + "-fx-font-family: Impact");
		guessButton.setOnAction(event -> {
			// call userGuess() function to update board
			playMove(xInput, yInput);
		});

		// Create a GridPane to hold the xInput, yInput, and guessButton
		inputGrid.setHgap(10);
		inputGrid.setVgap(5);
		//inputGrid.addRow(0, new Label("X:"), xInput);
		//inputGrid.addRow(1, new Label("Y:"), yInput);
		//inputGrid.add(guessButton, 2, 0, 1, 2);
		
		
		skillShot0.setToggleGroup(skillShots);
		skillShot1.setToggleGroup(skillShots);
		skillShot2.setToggleGroup(skillShots);
		skillShot3.setToggleGroup(skillShots);
		
		expLC.setOnMousePressed(event->{
			int x = (int) event.getX();
			int y = (int) event.getY();
			System.out.print("tried to make move ");
			System.out.print(x);
			System.out.print(" ");
			System.out.println(y);
			x = x / 36;
			y = y / 36;
			System.out.print("tried to make move ");
			System.out.print(x);
			System.out.print(" ");
			System.out.println(y);
			if(isPlayer1) {
				// x and y are swapped
				playAttack(y, x);
			}
		});
		
		inputGrid.setAlignment(Pos.CENTER);
		inputGrid.add(skillShot0, 0, 4, 1, 1);
		inputGrid.add(skillShot1, 1, 4, 1, 1);
		inputGrid.add(skillShot2, 2, 4, 1, 1);
		inputGrid.add(skillShot3, 3, 4, 1, 1);
		root.setBottom(inputGrid);
	}

	private static void addShip(TextField xInput, TextField yInput, TextField orientation, Label shipSize) {
		int xValue, yValue, rotValue;

		// Process the guess based on xInput and yInput values
		try {
			yValue = Integer.parseInt(yInput.getText());
			xValue = Integer.parseInt(xInput.getText());
			rotValue = Integer.parseInt(orientation.getText());
		} catch (Exception e) {
			// TODO: add alert if we want one, or just keep like this
			System.out.println("x, y, or rot is not parsable to int");
			return;
		}

		// Perform the game logic for the guess
		System.out.println(
				"Player " + (isPlayer1 ? "1" : "2") + " place: (" + xValue + ", " + yValue + "," + rotValue + ")");

		// does nothing else (may put up alerts tho) if move is invalid
		if (!game.humanPlaceShip(isPlayer1, xValue, yValue, rotValue, game.nextShip(isPlayer1)))
			return;

		printBoards();

		xInput.clear();
		yInput.clear();
		orientation.clear();

		// plays AI move or switches to next player
		if (game.isAI) {
			game.computerPlaceShip(true);
		} else
			isPlayer1 = !isPlayer1;
		System.out.println(game.nextShip(isPlayer1));
		if (game.nextShip(isPlayer1) == null)
			setGuessInputGrid();
		else
			shipSize.setText("Size:" + game.nextShip(isPlayer1).size());
		
		printBoards();
	}

	
	private static void printBoards() {
		firstBoardA.updateBoard(game.getActualBoard(true));
		secondBoardA.updateBoard(game.getActualBoard(false));

		// prints each board
		game.getBoard(true);
		game.getBoard(false);
	}
	
	private static void playAttack(int xValue, int yValue) {
		// !isPlayer 1 = 2nd board if only AI, might break with 2 player
				// ExecuteSkillShot uses parameter firstBoard rather than player1
				if (skillShot0.isSelected()) {
					if (!game.ExecuteSkillShot(!isPlayer1, 0, yValue, xValue))
						return;
					skillShot0.setSelected(false);
					skillShot0.setDisable(true);
				} else if (skillShot1.isSelected()) {
					if (!game.ExecuteSkillShot(!isPlayer1, 1, yValue, xValue))
						return;
					skillShot1.setSelected(false);
					skillShot1.setDisable(true);
				} else if (skillShot2.isSelected()) {
					if (!game.ExecuteSkillShot(!isPlayer1, 2, yValue, xValue))
						return;
					skillShot2.setSelected(false);
					skillShot2.setDisable(true);
				} else {
					// does nothing else (may put up alerts tho) if move is invalid
					if (!game.humanPlayMove(isPlayer1, xValue, yValue)) {
						return;
					}
				}
				


				if (game.gameOver()) {
					try {
						startGameOver();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return;
				}

				// plays AI move or switches to next player
				if (game.isAI) {
					game.computerPlayMove();
					printBoards();
				} else
					isPlayer1 = !isPlayer1;
				
				if (game.gameOver()) {
					try {
						startGameOver();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
	}

	
	private static void playMove(TextField xInput, TextField yInput) {
		int xValue, yValue;
		// Process the guess based on xInput and yInput values
		try {
			xValue = Integer.parseInt(xInput.getText());
			yValue = Integer.parseInt(yInput.getText());
		} catch (Exception e) {
			// TODO: add alert if we want one, or just keep like this
			System.out.println("x or y is not parsable to int");
			return;
		}

		// Perform the game logic for the guess
		System.out.println("Player " + (isPlayer1 ? "1" : "2") + " guess: (" + xValue + ", " + yValue + ")");
		
		playAttack(xValue, yValue);
		

		// if game still running update the board
		printBoards();
		xInput.clear();
		yInput.clear();
	}

	// sets the game over screen
	
	public static void startGameOver() {
		StackPane gO = new StackPane();
		StackPane statTitle = new StackPane();
		StackPane statsTwo = new StackPane();
		StackPane statsThree = new StackPane();
		StackPane statsFour = new StackPane();
		StackPane statsFive = new StackPane();
		BorderPane root = new BorderPane();
		GridPane title = new GridPane();
		// Border border = new Border();
		GridPane gp = new GridPane();
		GridPane buttons = new GridPane();
		gO.setPadding(new Insets(10));
		Label gameOver = new Label("  Game Over  ");
		// in the stat label makes it so it is the
		// (p1.ss + "Ships Sunk" + p2.ss)
		int[] statsP1 = game.getStats(true);
		int[] statsP2 = game.getStats(false);

		Label stats = new Label("P1       Game Stats      P2");	 
		Label ss = new Label(String.format("%-10s", statsP1[0]) + "Ships Sunk" + String.format("%10s", statsP2[0]));
		Label gm = new Label(String.format("%-7s", statsP1[1]) + "Squares Tried" + String.format("%7s", statsP2[1]));
		Label miss = new Label(String.format("%-13s", statsP1[2]) + "Misses" + String.format("%14s", statsP2[2]));
		Label hits = new Label(String.format("%-15s", statsP1[3]) + "Hits" + String.format("%16s", statsP2[3]));

		gameOver.setFont(Font.font("Impact", 40));
		stats.setFont(Font.font("Impact", 20));
		// gameOver.setTextFill(Color.TRANSPARENT);

		// Create a linear gradient for the text color
		gameOver.setTextFill(Color.rgb(184, 16, 4));

		// Apply effects like drop shadow and reflection
		gameOver.setEffect(new InnerShadow());
		gameOver.setEffect(new Glow(0.8));

		// add the Stats
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

		// Images for title
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

		title.add(gO, 1, 0);
		gO.getChildren().add(gameOver);
		gO.setAlignment(gameOver, Pos.CENTER);
		root.setTop(title);
		root.setCenter(gp);
		root.setAlignment(gp, Pos.CENTER);
		root.setBottom(buttons);
		root.setBorder(null);
		Scene scene = new Scene(root, 400, 300);
		Stage GameOverStage = new Stage();
		GameOverStage.setScene(scene);
		GameOverStage.show();
	}

	// music player functions

	// Plays a song, boolean isSong is used to determine whether songPlayer or
	// effectsPlayer should be used
	private static void playAudio(String audio, boolean isSong) {
		System.out.println(audio);
		File file = new File(audio);
		URI uri = file.toURI();
		Media media = new Media(uri.toString());

		MediaPlayer curPlayer = new MediaPlayer(media);
		// mediaPlayer.setOnEndOfMedia(new Waiter());
		curPlayer.setAutoPlay(true);
		curPlayer.play();

		if (isSong) {
			songPlayer = curPlayer;
		} else {
			effectsPlayer = curPlayer;
		}
	}

	private static void playAFunSong() {
		String str = "Mp3s/tgw.mp3";
		playAudio(str, true);
	}

	private static void playABoringSong() {
		String str = "Mp3s/[1080p HD] Call of Duty Black Ops Multiplayer Menu Music.mp3";
		playAudio(str, true);
	}

	public static void playExplosion() {
		String str = "Mp3s/Explosion sound effect.mp3";
		playAudio(str, false);
	}
	// music player functions

	public static void makeWater() {
		Image image = new Image("File:images/water1.png");
		ImageView pic = new ImageView();
		pic.setFitWidth(360);
		pic.setFitHeight(360);
		pic.setImage(image);
		Image image2 = new Image("File:images/water2.png");
		ImageView pic2 = new ImageView();
		pic2.setFitWidth(360);
		pic2.setFitHeight(360);
		pic2.setImage(image2);
		Image image3 = new Image("File:images/water3.png");
		ImageView pic3 = new ImageView();
		pic3.setFitWidth(360);
		pic3.setFitHeight(360);
		pic3.setImage(image3);
		Image imageL = new Image("File:images/water1.png");
		ImageView picL = new ImageView();
		picL.setFitWidth(360);
		picL.setFitHeight(360);
		picL.setImage(imageL);
		Image image2L = new Image("File:images/water2.png");
		ImageView pic2L = new ImageView();
		pic2L.setFitWidth(360);
		pic2L.setFitHeight(360);
		pic2L.setImage(image2L);
		Image image3L = new Image("File:images/water3.png");
		ImageView pic3L = new ImageView();
		pic3L.setFitWidth(360);
		pic3L.setFitHeight(360);
		pic3L.setImage(image3L);
		waterR = new ImageView[5];
		waterR[0] = pic;
		waterR[1] = pic2;
		waterR[2] = pic3;
		waterR[3] = pic2;
		waterR[4] = pic;
		waterL = new ImageView[5];
		waterL[0] = picL;
		waterL[1] = pic2L;
		waterL[2] = pic3L;
		waterL[3] = pic2L;
		waterL[4] = picL;
	}
	
	public static void water() {
		makeWater();
		timelineR = new Timeline(new KeyFrame(Duration.millis(400), new waterR()));
		timelineR.setCycleCount(Animation.INDEFINITE);
		timelineR.play();
	}
	public static void waterL() {
		timelineL = new Timeline(new KeyFrame(Duration.millis(400), new waterL()));
		timelineL.setCycleCount(Animation.INDEFINITE);
		timelineL.play();
	}
	public static class waterL implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
		   	 if (wL == 4) {
					 wL = 0;
				 }
				 waterLpane.setCenter(waterL[wL]);
				 wL ++;
		}
		 
	 }
	
	 private static class waterR implements EventHandler<ActionEvent> {

			@Override
			public void handle(ActionEvent arg0) {
			   	 if (wR== 4) {
						 wR = 0;
					 }
					 waterRpane.setCenter(waterR[wR]);
					 wR ++;
			}
			 
		 }
	
	
	public static void main(String[] args) {
		launch(args);
	}

}