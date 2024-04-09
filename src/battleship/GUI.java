package battleship;

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
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

	// Create new board object
	private Board board = new Board(true);

	@Override
	public void start(Stage primaryStage) {
		// Set title "Battleship" style
		titleLabel.setFont(Font.font("Impact", 40));
		titleLabel.setTextFill(Color.TRANSPARENT);

		// Create a linear gradient for the text color
		titleLabel.setTextFill(Color.rgb(0, 76, 153));

		// Apply effects like drop shadow and reflection
		titleLabel.setEffect(new DropShadow());
		titleLabel.setEffect(new Reflection());

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
				startGame(primaryStage, board, true);
			} else if (players2RadioButton.isSelected()) {
				// Start 2 players game
				System.out.println("Starting 2 players game...");
				startGame(primaryStage, board, false);
			} else {
				// No game mode selected
				System.out.println("Please select a game mode.");
			}
		});

		// Create an HBox to hold the radio buttons
		HBox radioBox = new HBox(35, aiRadioButton, players2RadioButton);
		radioBox.setAlignment(Pos.CENTER);

		// Create a VBox to hold all elements
		VBox root = new VBox(60, titleLabel, radioBox, startButton);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(50));

		// Set up the scene
		Scene scene = new Scene(root, 400, 300);

		primaryStage.setTitle("Battleship Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void startGame(Stage primaryStage, Board board, boolean isAI) {
		// Create TextFields, inputs, and button as before

		// Create TextArea for opponent and player's battlefield
		TextArea opponentArea = new TextArea();
//		opponentArea.setAlignment(Pos.CENTER);
		opponentArea.setEditable(false); // Set to read-only
		opponentArea.setMaxWidth(360);
		opponentArea.setMaxHeight(368);
		opponentArea.setWrapText(true);
		opponentArea.setFont(Font.font("Courier New", FontWeight.BOLD, 27));

		// Simulate making board and print board content
		board.makeTest();
		printBoard(board, opponentArea);

		TextArea playerArea = new TextArea();
		playerArea.setEditable(false); // Set to read-only
		playerArea.setMaxWidth(360);
		playerArea.setMaxHeight(368);
		playerArea.setWrapText(true);

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
			updateBoard(board, opponentArea, xInput, yInput);
		});

		// Create a GridPane to hold the xInput, yInput, and guessButton
		GridPane inputGrid = new GridPane();
		inputGrid.setHgap(10);
		inputGrid.setVgap(5);
		inputGrid.addRow(0, new Label("X:"), xInput);
		inputGrid.addRow(1, new Label("Y:"), yInput);
		inputGrid.add(guessButton, 2, 0, 1, 2);
		inputGrid.setAlignment(Pos.CENTER);

		// Create a BorderPane to hold all elements
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(40));
		root.setTop(labelBox);
		root.setLeft(opponentArea);
		root.setRight(playerArea);
		root.setBottom(inputGrid);

		// Set up the scene
		Scene scene = new Scene(root, 860, 550);

		// Set the stage title and scene, then show the stage
		primaryStage.setTitle("Battleship Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private static void printBoard(Board b, TextArea textArea) {
		char[][] testB = b.getCharBoard();
		StringBuilder boardString = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				boardString.append(testB[i][j]).append(" ");
				System.out.print(testB[i][j]);
				System.out.print(" ");
			}
			boardString.append("\n");
			System.out.println();
		}
		textArea.setText(boardString.toString());
	}

	private static void updateBoard(Board board, TextArea textArea, TextField xInput, TextField yInput) {
		// Process the guess based on xInput and yInput values
		int xValue = Integer.parseInt(xInput.getText());
		int yValue = Integer.parseInt(yInput.getText());
		
		// Perform the game logic for the guess
		System.out.println("Player guess: (" + xValue + ", " + yValue + ")");
		
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
		// if game still running update the board
		if (board.getState()) {
			board.guess(xValue, yValue);
			printBoard(board, textArea);
			xInput.clear();
			yInput.clear();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
