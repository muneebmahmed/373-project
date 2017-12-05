# 373-project
Chess

Java Application to play chess for ECE373 project

To download the source code, you can navigate to your eclipse-workspace or another directory, and clone the repository with:
```
git clone https://github.com/muneebmahmed/373-project.git
```
Alternatively, you can download a zip of the repository as well, or just the executable .jars. However, if you download the Chess.jar executable, it MUST be run in a directory that also contains the images folder

# Running and Testing

GameRunner and CLIRunner drivers are provided to play the game. GameRunner, or its archive Chess.jar (as long as it is in a directory containing the images folder) should be used to run the game's main graphical interface. CLIRunner can be used to run a whole game from the command line.

The CLIRunner class in the [play](src/boardgame/play) package was the driver used for unit testing classes quickly from the command line, as it can quickly show whether the basic Pieces, Squares, and Board are working or not.
Over time as more components were developed, the CLIRunner was used for integration testing. As such, it still calls multiple smaller subroutines to play the game rather than creating a layer of abstraction by instantiating classes to handle them as the other drivers do.

The GUIRunner class in the play package is a deprecated driver used for originally testing components of the GUI. As such, it contains a large amount of commented out code from previous tests. Although it is capable of playing a full game of chess, the use of the GameRunner class is preferred as it has more features.

The GameRunner class in the [gui](src/boardgame/gui) package is a complete graphical interface for playing chess. It has the ability to play a full game with any number of human or computer players, scanning games from/saving to files in .pgn or .txt format at any time, rename/change players between computer/human while playing a game, replay a game move by move after it is over, and play a new game after finishing the old one. It features dual input from either a point and click interface or selection of squares based off of arrow keys, a custom console output at the bottom, and a text area with move history updated in real time. 

The CLIRunner driver is capable of running a full game of chess, with human or computer players. It supports limited file I/O with the option to scan in a game at the beginning or export a game when it is over. It also accepts two integer command line arguments, each representing if a player is a computer (1) or human (not 1). The first argument is the white player, the second is the black player. This can be run as a jar or compiled and run from the terminal:
```
java -jar Chess-CLI.jar
```
```
javac src/boardgame/*/*.java
java src/boardgame/CLIRunner 1 1
```
The above example will create a simulation of two computers playing against each other. If no command line arguments are specified then the user will be asked to decide during program execution.

Operations supported by the CLIRunner while playing a game:
* Moving a piece: The CLI supports entering moves in algebraic notation. The user can simply enter "e4" or "Qxf7" and the game will automatically determine which piece to move.
* Undoing a move: enter "u" to undo the last move
* Redoing a move: enter "redo" to redo an undone move, if possible
* Quitting: enter "quit" to quit
* Promotion: enter the symbol of a piece after an '=' (e.g. dxe8=R)
The game will automatically be checked for checkmate or draws. 

On certain systems, depending on the font, changing line 87 of the CLIRunner from '<=' to '==' can center the pieces:
```
if (j%2 == 1) { //should be <=
```

The GameRunner driver is capable of doing everything the CLIRunner can do with the addition of a graphical user interface. Additionally, files can be opened and saved at any time, ad playing a new game does not require running the executable over again. When the game is over, the user has the option of replaying the game move by move for analysis.

Moves are selected by clicking on an origin piece with the mouse, and a destination square. Moves can also be selected by using the arrow keys and enter to specify squares on the board. The board features move highlighting to show the legal moves of each piece. GameRunner also includes a helpful rules window for beginners who forget how each piece can move. Unlike the CLIRunner, GameRunner does not support command line arguments.

GameRunner can be run by either compiling from source or running or double clicking the Chess.jar executable:
```
java -jar Chess.jar
```
However, if using the Chess.jar archive, it MUST be in a directory that also includes the [images](images) folder


# Package Description

This project offers compatibility with files formatted in [portable game notation](https://en.wikipedia.org/wiki/Portable_Game_Notation) (.pgn) based on [algebraic notation](https://en.wikipedia.org/wiki/Algebraic_notation_(chess))

There are four packages containing different types of classes:
* [pieces](src/boardgame/pieces), containing the board, pieces, and squares that make up a chess board;
* [data](src/boardgame/data), which contains classes that parse and store moves, board configurations, and move evaluations;
* [play](src/boardgame/play), which contains the classes necessary to play a game, such as players, a ChessGame class, and CLI/GUI interfaces
* [gui](src/boardgame/gui), which has custom JComponents used for making our GUI, such as a Console, SquareButtons representng squares, and a start menu.

## [Pieces](src/boardgame/pieces) (package)

The abstract class [Piece](src/boardgame/pieces/Piece.java) has child classes King, Queen, Rook, Bishop, Knight, and Pawn.

The association between a Board and its Squares is a composition, as the lifetime of each is the same, and a Board always has squares/

A Board always has exactly 64 Squares, and can have many Pieces. A Square can have 0 to 1 Pieces. A piece has exactly one Square and one Board it is on.

## [Data](src/boardgame/data)

A Command represents a move, which is parsed from a UserInterface.
A Configuration represents the state of a Board at any given time. The game can undo moves by simpling loading a previous configuration.
An Evaluator evaluates a position based on its pieces, and searches evaluations to find the best move. An Evaluator is a Computer player's AI, and uses a basic algorithm with a lookahead of one move to find a move that leaves it with the most pieces after one turn.

## [Play](src/boardgame/play)

A Player is the Human or Computer that is playing the game. 
The overall ChessGame class has Players and a Board, and is a Runnable so that it can be executed on a thread separate from the Event Dispatch Thread. This allows the game to pause while the user has not selected a move, while still allowing the user to interact with the GUI in order to actually select a move. 
Creating a new thread from ChessGame is how each new game of chess is played in GameRunner. ChessGame uses Swing-specific code and was designed specifically for use with a GUI, so is not used by the CLI.

## [GUI](src/boardgame/gui)

The graphical interface shows the board in two dimensions, and allows movement of pieces via point and click or selection based off of the arrow keys. 
This package includes all of the class incorporated into the graphical interface except the main GUI representing the board, which implements the UserInterface interface in the play package. 
These classes include the custom Console output stream and its enclosing scroll pane that sit at the bottom of the graphical interface, the SquareButtons that are used for each square of the board, the start menu, the rules menu, and the GameRunner driver.

Below is a screenshot of what the board's GUI looks like:

![Chess board](https://github.com/muneebmahmed/373-project/blob/master/images/Chess%20Board%20GUI.png)
