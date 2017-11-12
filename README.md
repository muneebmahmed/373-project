# 373-project
Chess

Java Application to play chess for ECE373 project

Navigate to your eclipse-workspace, and clone the repository with:
```
git clone https://github.com/muneebmahmed/373-project.git
```
Configuring name and email can be done with
```
git config user.name "FirstName LastName"
git config user.email "name@domain"
```
Eclipse supports importing git repositories, as well staging, commiting, fetching, pulling etc. but
`git fetch` or `git pull` can also be used from the command line to update the local repository with remote changes.

# Running and Testing

A CLIRunner driver is provided. This is the driver that was used for testing classes as well as using them together for a game of chess. It only uses has a command line interface, but a GUIRunner driver will be added once enough progress is made on the GUI.

The CLIRunner driver is capable of running a game of chess, with human or computer players. It also accepts two integer command line arguments, each representing if a player is a computer (1) or human (not 1). The first argument is the white player, the second is the black player. This can be compiled and run from the terminal:
```
javac src/boardgame/*/*.java
java src/boardgame/CLIRunner 1 1
```
The above example will create a simulation of two computers playing against each other. If no command line arguments are specified then the user will be asked to decide during program execution.

Operations supported by the CLIRunner while playing a game:
* Moving a piece: enter the origin square (file then rank, such as "e2"). Afterwards, enter the destination square.
* Undoing a move: enter "u" to undo the last move
* Quitting: enter "quit" to quit
* Promotion: enter the name (capital case) of the piece when prompted
The game will automatically be checked for checkmate or draws. 

If not using Eclipse on Mac, change line 87 of the CLIRunner from '==' to '<=':
```
if (j%2 == 1) { //should be <=
```


# Package Description

This project will be compatible with files formatted in [portable game notation](https://en.wikipedia.org/wiki/Portable_Game_Notation) (.pgn) based on [algebraic notation](https://en.wikipedia.org/wiki/Algebraic_notation_(chess))

There are three packages containing different types of classes:
* [pieces](src/boardgame/pieces), containing the board, pieces, and squares that make up a chessboard;
* [data](src/boardgame/data), which contains classes that parse and store moves, board configurations, and move evaluations;
* [play](src/boardgame/play), which contains the classes necessary to play a game, such as players, a Game class, and CLI/GUI interfaces

## [Pieces](src/boardgame/pieces) (package)

The abstract class [Piece](src/boardgame/pieces/Piece.java) has child classes King, Queen, Rook, Bishop, Knight, and Pawn

The association between a Board and its Squares is a composition, as the lifetime of each is the same, and a Board always has squares

A Board always has exactly 64 Squares, and can have many Pieces. A Square can have 0 to 1 Pieces. A piece has exactly one Square and one Board it is on.

## [Data](src/boardgame/data)

A Command represents a move, which is parsed from a UserInterface.
A Configuration represents the state of a Board at any given time
An Evaluator evaluates a computer move given a Configuration, in order to find the best move.
Currently an Evaluator is only capable of finding a mate in 1, or returning a random legal move.

## [Play](src/boardgame/play)

A Player is the Human or Computer that is playing the game. The overall ChessGame class has Players and a Board.

The graphical interface will show the board in two dimensions, and will allow movement of pieces via point and click.

The board should look like:

![Chess board](https://github.com/muneebmahmed/373-project/blob/master/images/Chess%20Board%20w%20pieces.png)
