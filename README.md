# 373-project
Chess

Java Application to play chess for ECE373 project

Navigate to your eclipse-workspace, and clone the repository with:
```
git clone https://github.com/muneebmahmed/373-project.git
```
Configuring name and email can be done with
```
git config user.name "First Last"
git config user.email "email@url"
```
Eclipse supports importing git repositories, as well staging, commiting, fetching, pulling etc. but
`git fetch` or `git pull` can be used to update the local repository with remote changes.

Will be compatible with files in [portable game notation](https://en.wikipedia.org/wiki/Portable_Game_Notation) (.pgn) using [algebraic notation](https://en.wikipedia.org/wiki/Algebraic_notation_(chess))

There are three packages containing different types of classes:
* [pieces](src/boardgame/pieces), containing the board, pieces, and squares that make up a chessboard;
* [data](src/boardgame/data), which contains classes that parse and store moves, board configurations, and move evaluations;
* [play](src/boardgame/play), which contains the classes necessary to play a game, such as players, a Game class, and CLI/GUI interfaces

The abstract class [Piece](src/boardgame/pieces/Piece.java) has child classes King, Queen, Rook, Bishop, Knight, and Pawn; Each class *is-a* piece

A composition relationship exists between Board, Piece, and Square. A board is made up Squares (*has-a*) and *has* Pieces, but a Piece also needs to have a reference to a Board in order to have moves. A Square has a Piece, but doesn't need a Board.
An aggregation exists between the Board and its Configurations; a Board has different configurations, but a configuration does not need a Board.
A Command represents a move. A command is parsed from a GUI or CLI, and is used by a Board, but is not part of it.
An Evaluator evaluates a computer move given a Configuration, in order to find the best move. An Evaluator *has-a* Configuration so has an aggregation.

A Player is the human or computer that is playing the game. The overall Game class has Players and a Board (aggregation).

The graphical interface will show the board in two dimensions, and will allow movement of pieces via point and click.

![Chess board](images/Chess\ Board.png)
