# Battleship Game Project
The following is a group project by Luckie Musngi, Mel Kinghorn, Carrie, and Alec Schmitt using scrum. 
We were given direction in that there were choices of different games we could make, and a rubric.

## Overview
This project is an implementation of the classic Battleship game, and is played 
with a working animated GUI using a javafx canvas.  

## Features
- Tutorial
- Single-player mode against a computer opponent
- Customizable grid size
- Different types of ships with varying lengths
- Random ship placement for the computer opponent
- Turn-based gameplay with hit/miss feedback
- Skillshots

## Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/battleshipgameproject.git
    ```
2. Navigate to the project directory:
    ```sh
    cd battleshipgameproject
    ```
3. Install JavaFX:
    - Download JavaFX from the official website: [JavaFX Downloads](https://gluonhq.com/products/javafx/)
    - Extract the downloaded files to a directory of your choice.
    - Set the `PATH_TO_FX` environment variable to the `lib` directory of the extracted JavaFX files.

4. Compile the project:
    ```sh
    javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -d out src/*.java
    ```

5. Run the project:
    ```sh
    java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -cp out GUI.java
    ```
