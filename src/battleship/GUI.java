package battleship;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

	@Override
	public void start(Stage primaryStage) {
		// Set title "Battleship" style
		titleLabel.setStyle("-fx-font-size: 24px;");

		// Create radio buttons for game mode selection
		ToggleGroup gameModeGroup = new ToggleGroup();
		aiRadioButton.setToggleGroup(gameModeGroup);
		players2RadioButton.setToggleGroup(gameModeGroup);

		// Create a button to start the game
		Button startButton = new Button("Start");
		startButton.setOnAction(event -> {
			if (aiRadioButton.isSelected()) {
				// Start AI game
				System.out.println("Starting AI game...");
				startGame(primaryStage, true);
			} else if (players2RadioButton.isSelected()) {
				// Start 2 players game
				System.out.println("Starting 2 players game...");
				startGame(primaryStage, false);
			} else {
				// No game mode selected
				System.out.println("Please select a game mode.");
			}
		});

		// Create an HBox to hold the radio buttons
		HBox radioBox = new HBox(10, aiRadioButton, players2RadioButton);
		radioBox.setAlignment(Pos.CENTER);

		// Create a VBox to hold all elements
		VBox root = new VBox(20, titleLabel, radioBox, startButton);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(50));

		// Set up the scene
		Scene scene = new Scene(root, 400, 300);

		primaryStage.setTitle("Battleship Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void startGame(Stage primaryStage, boolean isAI) {
		// Create TextFields, inputs, and button as before

		// Create TextFields for opponent and player's battlefield
		TextField opponentField = new TextField();
		opponentField.setEditable(false); // Set to read-only
		opponentField.setPrefWidth(400); 
        opponentField.setPrefHeight(400);
		TextField playerField = new TextField();
		playerField.setEditable(false); // Set to read-only
		playerField.setPrefWidth(400); // Set preferred width
		playerField.setPrefHeight(400);

		// Determine board labels based on isAI flag
        Label leftLabel, rightLabel;
        if (isAI) {
            leftLabel = new Label("AI Board");
            rightLabel = new Label("Your Board");
        } else {
            leftLabel = new Label("Player1 Board");
            rightLabel = new Label("Player2 Board");
        }

        // Create HBox for left and right labels
        HBox labelBox = new HBox(400, leftLabel, rightLabel);
        labelBox.setAlignment(Pos.CENTER);
		
		// Create input fields for x and y coordinates
		TextField xInput = new TextField();
		TextField yInput = new TextField();

		// Create Guess button
		Button guessButton = new Button("Guess");
		guessButton.setOnAction(event -> {
			// Process the guess based on xInput and yInput values
			int x = Integer.parseInt(xInput.getText());
			int y = Integer.parseInt(yInput.getText());
			// Perform the game logic for the guess
			System.out.println("Player guess: (" + x + ", " + y + ")");
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
		root.setPadding(new Insets(20));
		root.setTop(labelBox);
		root.setLeft(opponentField);
		root.setRight(playerField);
		root.setBottom(inputGrid);

		// Set up the scene
		Scene scene = new Scene(root, 860, 550);

		// Set the stage title and scene, then show the stage
		primaryStage.setTitle("Battleship Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
