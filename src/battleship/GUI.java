package battleship;

import java.io.File;
import java.net.URI;
import java.util.Scanner;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class GUI extends Application {
	// need to display both boards the players and the ai board
	// the ai board wont have visible ships but needs to show where the player
	// has guessed and hit
	//

	// Create a label for the title "Battleship"
	private Label titleLabel = new Label("Battleship");

	// Create radio buttons for game mode selection
	private RadioButton aiRadioButton = new RadioButton("AI");
	private RadioButton players2RadioButton = new RadioButton("2 Players");
	
	private static RadioButton funMusic = new RadioButton("Fun Music");
	private static RadioButton boringMusic = new RadioButton("Boring Music");
	
	private static boolean fun = false;


	static BattleshipGame game;

	static TextArea firstBoardArea = new TextArea();
	static TextArea secondBoardArea = new TextArea();
	
	private static boolean isPlayer1 = true;
	
	static BorderPane root;
	
	private static Stage primaryStage;
	private MediaPlayer mediaPlayer;
	
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
		
		//Create radio buttons for music selection
		ToggleGroup musicSelection = new ToggleGroup();
		funMusic.setToggleGroup(musicSelection);
		boringMusic.setToggleGroup(musicSelection);
		funMusic.setStyle("-fx-font-size: 16px;" + "-fx-text-fill: rgb( 0, 0, 102);" + "-fx-font-family: Roboto Slab");
		boringMusic.setStyle("-fx-font-size: 16px;" + "-fx-text-fill: rgb( 0, 0, 102);" + "-fx-font-family: Roboto Slab");


		// Create radio buttons for game mode selection
		ToggleGroup gameModeGroup = new ToggleGroup();
		aiRadioButton.setToggleGroup(gameModeGroup);
		players2RadioButton.setToggleGroup(gameModeGroup);
		aiRadioButton
				.setStyle("-fx-font-size: 16px;" + "-fx-text-fill: rgb( 0, 0, 102);" + "-fx-font-family: Roboto Slab");
		players2RadioButton
				.setStyle("-fx-font-size: 16px;" + "-fx-text-fill: rgb( 0, 0, 102);" + "-fx-font-family: Roboto Slab");

		// Create a button to start the game
		Button startButton = new Button("S T A R T");
		startButton
				.setStyle("-fx-background-color: rgb(0, 51, 102); " + "-fx-text-fill: white; " + "-fx-font-size: 16px; "
						+ "-fx-pref-width: 120px; " + "-fx-pref-height: 40px;" + "-fx-font-family: Impact");
		startButton.setOnAction(event -> {
			if (aiRadioButton.isSelected()) {
				// Start AI game
				System.out.println("Starting AI game...");
				if (funMusic.isSelected()) {
					fun = true;
				}else {
					fun = false;
				}
				startGame(primaryStage, true);
			} else if (players2RadioButton.isSelected()) {
				// Start 2 players game
				System.out.println("Starting 2 players game...");
				if (funMusic.isSelected()) {
					fun = true;
				}else {
					fun = false;
				}
				startGame(primaryStage, false);
			} else {
				// No game mode selected
				System.out.println("Please select a game mode.");
			}
		});

		// Create an HBox to hold the radio buttons
		HBox radioBox = new HBox(35, aiRadioButton, players2RadioButton);
		radioBox.setAlignment(Pos.CENTER);
		
		HBox musicBox = new HBox(35, funMusic, boringMusic);
		musicBox.setAlignment(Pos.CENTER);

		// Create a VBox to hold all elements
		VBox root = new VBox(60, titleLabel, radioBox,musicBox, startButton);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(50));

		// Set up the scene
		Scene scene = new Scene(root, 400, 300);

		primaryStage.setTitle("Battleship Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void startGame(Stage primaryStage, boolean isAI) {
		game = new BattleshipGame(isAI);

//		opponentArea.setAlignment(Pos.CENTER);
		firstBoardArea.setEditable(false); // Set to read-only
		firstBoardArea.setMaxWidth(360);
		firstBoardArea.setMaxHeight(368);
		//secondBoardArea.setWrapText(true);
		firstBoardArea.setFont(Font.font("Courier New", FontWeight.BOLD, 27));

		secondBoardArea.setEditable(false); // Set to read-only
		secondBoardArea.setMaxWidth(360);
		secondBoardArea.setMaxHeight(368);
		//firstBoardArea.setWrapText(true);
		secondBoardArea.setFont(Font.font("Courier New", FontWeight.BOLD, 27));

		// Determine board labels based on isAI flag
		Label leftLabel, rightLabel;
		if (isAI) {
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
		
		
		// Create a BorderPane to hold all elements
		root = new BorderPane();
		root.setPadding(new Insets(40));
		root.setTop(labelBox);
		root.setLeft(firstBoardArea);
		root.setRight(secondBoardArea);

		// set input grid in addships mode
		setInputGrid(false);
		// Set up the scene
		Scene scene = new Scene(root, 860, 550);
		
		// prints both boards
		printBoard(true);
		printBoard(false);
		
		//play song
		if (fun == true) {
			playAFunSong();
		}else {
			playABoringSong();
		}
		
		// Set the stage title and scene, then show the stage
		primaryStage.setTitle("Battleship Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private static void setInputGrid(boolean fireMode) {
		if (fireMode) {
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
			inputGrid.addRow(0, new Label("X:"), xInput);
			inputGrid.addRow(1, new Label("Y:"), yInput);
			inputGrid.add(guessButton, 2, 0, 1, 2);
			
			// ------------------------debug code-----------------------
			// TODO: delete this when it is no longer necessary
			Button debugButton = new Button("Debug: end game now");
			debugButton.setOnAction(event -> {try {
				startGameOver();
			} catch (Exception e) {
				e.printStackTrace();
			}});
			inputGrid.add(debugButton, 3, 0, 1, 1);
			// ------------------------debug code-----------------------
			
			inputGrid.setAlignment(Pos.CENTER);
			
			root.setBottom(inputGrid);
		} else {
			// TODO: I'm bad at writing UI :(
			// TODO: the bad UI code is all in this else statement
			
			GridPane inputGrid = new GridPane();
			
			// Create input fields for x and y coordinates
			TextField xInput = new TextField();
			TextField yInput = new TextField();
			TextField orientation = new TextField();
			Ship nextShip = game.nextShip(isPlayer1);
			
			if (nextShip == null)
				setInputGrid(true);
			
			Label shipSize = new Label("Size:" + nextShip.size());
			
			
			// Create Guess button
			Button guessButton = new Button("P l a c e");
			guessButton
					.setStyle("-fx-background-color: rgb(0, 51, 102); " + "-fx-text-fill: white; " + "-fx-font-size: 14px; "
							+ "-fx-pref-width: 80px; " + "-fx-pref-height: 32px;" + "-fx-font-family: Impact");
			guessButton.setOnAction(event -> {
				// call userGuess() function to update board
				addShip(xInput, yInput, orientation, shipSize);
			});
			
			
			
			
			// Create a GridPane to hold the xInput, yInput, and guessButton
			inputGrid.setHgap(10);
			inputGrid.setVgap(5);
			inputGrid.addRow(0, new Label("X:"), xInput);
			inputGrid.addRow(1, new Label("Y:"), yInput);
			inputGrid.addRow(2, new Label("rot (0,1,2,3):"), orientation);
			inputGrid.add(shipSize, 2, 0, 1, 1);
			inputGrid.add(guessButton, 2, 1, 1, 3);
			
			// ------------------------debug code-----------------------
			// TODO: delete this when it is no longer necessary
			Button debugButton = new Button("Debug: end game now");
			debugButton.setOnAction(event -> {try {
				startGameOver();
			} catch (Exception e) {
				e.printStackTrace();
			}});
			inputGrid.add(debugButton, 3, 0, 1, 1);
			// ------------------------debug code-----------------------
			
			inputGrid.setAlignment(Pos.CENTER);
			
			root.setBottom(inputGrid);
		}
	}
	
	private static void addShip(TextField xInput, TextField yInput, TextField orientation, Label shipSize) {
		int xValue, yValue, rotValue;
		// Process the guess based on xInput and yInput values
		try {
			xValue = Integer.parseInt(xInput.getText());
			yValue = Integer.parseInt(yInput.getText());
			rotValue = Integer.parseInt(orientation.getText());
		} catch (Exception e) {
			// TODO: add alert if we want one, or just keep like this
			System.out.println("x, y, or rot is not parsable to int");
			return;
		}
		
		// Perform the game logic for the guess
		System.out.println("Player " + (isPlayer1 ? "1" : "2") + " place: (" + xValue + ", " + yValue + "," + rotValue + ")");
		
		//  does nothing else (may put up alerts tho) if move is invalid
		if (!game.humanPlaceShip(isPlayer1, xValue, yValue, rotValue, game.nextShip(isPlayer1)))
			return;
		
		printBoard(false);
		printBoard(true);
		
		xInput.clear();
		yInput.clear();
		orientation.clear();
		
		// plays AI move or switches to next player
		if (game.isAI) {
			game.computerPlaceShip();
			printBoard(true);
		}
		else
			isPlayer1 = !isPlayer1;
		System.out.println(game.nextShip(isPlayer1));
		if (game.nextShip(isPlayer1) == null)
			setInputGrid(true);
		else
			shipSize.setText("Size:" + game.nextShip(isPlayer1).size());
	}
	

	private static void printBoard(boolean firstBoard) {
		if (firstBoard) {
			firstBoardArea.setText(game.getBoard(firstBoard));
		} else {
			secondBoardArea.setText(game.getBoard(firstBoard));
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
		
		if (game.gameOver()) {
			try {
				startGameOver();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//  does nothing else (may put up alerts tho) if move is invalid
		if (!game.humanPlayMove(isPlayer1, xValue, yValue))
			return;
		
		/*
		 Luckie here! I put these in player
		 * 
		//make sure guess is within bounds
		if (xValue >= 10 || xValue < 0) {
			if (yValue >= 10 || yValue < 0) {
				//alert both values invalid
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("Invalid Guess");
				alert.setContentText("Both X and Y coordinates are invalid");
				alert.showAndWait();
			}else {
			//alert x invalid	
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Invalid Guess");
			alert.setContentText("X coordinate is invalid");
			alert.showAndWait();
			}}else if (yValue >= 10 || yValue < 0) {
			//alert y invalid 
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("Invalid Guess");
				alert.setContentText("Y coordinate is invalid");
				alert.showAndWait();
		}
		
		//make sure guess hasn't already been guessed
		if (board.guessedBoard[xValue][yValue]) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Invalid Guess");
			alert.setContentText("This location has already been guessed");
			alert.showAndWait();
		}
		*/
		
		// if game still running update the board
		printBoard(isPlayer1);
		xInput.clear();
		yInput.clear();
		
		// plays AI move or switches to next player
		if (game.isAI) {
			game.computerPlayMove();
			printBoard(false);
		}
		else
			isPlayer1 = !isPlayer1;
	}
	
	public static void startGameOver() throws Exception {
		Button newGame, end;
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
	    int[] statsP1 = game.getStats(true);
	    int[] statsP2 = game.getStats(false);
	    
	    Label stats = new Label("P1       Game Stats      P2");
	    Label ss = new Label(String.format("%-10s", statsP1[0]) + "Ships Sunk"  + String.format("%10s", statsP2[0]));
	    Label gm = new Label(String.format("%-7s", statsP1[1]) + "Guesses Made" + String.format("%7s", statsP2[1]));
	    Label miss = new Label(String.format("%-13s", statsP1[2]) + "Hisses" + String.format("%14s", statsP2[2]));
	    Label hits = new Label(String.format("%-15s", statsP1[3]) + "Hits" + String.format("%16s", statsP2[3]));
	    
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
		primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private static void playAFunSong() {

		String str = "Mp3s/tgw.mp3";
		System.out.println(str);
		File file = new File(str);
		URI uri = file.toURI();
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		//mediaPlayer.setOnEndOfMedia(new Waiter());
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();
  
	}
	
	private static void playABoringSong() {

		String str = "Mp3s/[1080p HD] Call of Duty Black Ops Multiplayer Menu Music.mp3";
		System.out.println(str);
		File file = new File(str);
		URI uri = file.toURI();
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		//mediaPlayer.setOnEndOfMedia(new Waiter());
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();

	}
	
	public static void playExplosion() {

		String str = "Mp3s/Explosion sound effect.mp3";
		System.out.println(str);
		File file = new File(str);
		URI uri = file.toURI();
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		//mediaPlayer.setOnEndOfMedia(new Waiter());
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();

	}
	
	
}
