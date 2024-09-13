# Chess_2
Project for rofle.

# Chess Object

This file contains the definition of the `Piece` class, which represents a chess piece.

## Constructor

The `Piece` class has a constructor that takes four arguments:

- `color`: the color of the piece (either `Color::White` or `Color::Black`)
- `type`: the type of the piece (e.g. `PieceType::King`, `PieceType::Queen`, etc.)
- `row`: the row of the piece (0-7)
- `col`: the column of the piece (0-7)

## Methods

The `Piece` class has several methods to get and set the color, type, row, and column of the piece.

- `getColor()`: returns the color of the piece
- `getType()`: returns the type of the piece
- `getRow()`: returns the row of the piece
- `getCol()`: returns the column of the piece
- `setRow(int newRow)`: sets the row of the piece
- `setCol(int newCol)`: sets the column of the piece
- `setColor(Color newColor)`: sets the color of the piece
- `setType(PieceType newType)`: sets the type of the piece
- `move(int newRow, int newCol)`: moves the piece to a new row and column

## Notes

- The constructor does not perform input data validation.
- The `move` function does not perform validation on the new position.
- The destructor `~Piece()` is declared but not implemented.
- There is no error checking when accessing class members.


# Chess ID

This file contains the implementation of the `ChessID` class, which represents a unique identifier for a chess piece.

## Methods

The `ChessID` class has several methods to get and set the row, column, and color of the piece.

- `getRow()`: returns the row of the piece
- `getCol()`: returns the column of the piece
- `getColor()`: returns the color of the piece
- `setRow(int newRow)`: sets the row of the piece
- `setCol(int newCol)`: sets the column of the piece
- `setColor(Color newColor)`: sets the color of the piece

## Notes

- There is no input data validation.
- There is no error checking when accessing class members.

## Suggestions for improvement

- Add input data validation.
- Add error checking when accessing class members.
- Consider using constant class members if they do not change after initialization.
- Consider using exceptions for error handling instead of returning garbage values.# Chess Board

This file contains the implementation of the `Board` class, which represents a chess board.

## Constructor

The `Board` class has a constructor that takes no arguments.

## Methods

The `Board` class has several methods to manipulate the board:

- `initBoard()`: initializes the board with the starting position of the pieces
- `printBoard()`: prints the current state of the board
- `movePiece(int fromRow, int fromCol, int toRow, int toCol)`: moves a piece from one position to another
- `getPiece(int row, int col)`: returns the piece at a given position
- `setPiece(int row, int col, Piece* piece)`: sets a piece at a given position
- `isCheck()`: checks if the king is in check
- `isCheckmate()`: checks if the king is in checkmate
- `isStalemate()`: checks if the game is a stalemate

## Notes

- The constructor does not perform input data validation.
- The `movePiece` function does not perform validation on the move.
- There is no error checking when accessing class members.

## Suggestions for improvement

- Add input data validation in the constructor.
- Implement the destructor `~Board()` if necessary.
- Add error checking when accessing class members.
- Consider using constant class members if they do not change after initialization.
- Consider using exceptions for error handling instead of returning garbage values.


# Chess Board

This file contains the implementation of the `Board` class, which represents a chess board.

## Constructor

The `Board` class has a constructor that takes no arguments.

## Methods

The `Board` class has several methods to manipulate the board:

- `initBoard()`: initializes the board with the starting position of the pieces
- `printBoard()`: prints the current state of the board
- `movePiece(int fromRow, int fromCol, int toRow, int toCol)`: moves a piece from one position to another
- `getPiece(int row, int col)`: returns the piece at a given position
- `setPiece(int row, int col, Piece* piece)`: sets a piece at a given position
- `isCheck()`: checks if the king is in check
- `isCheckmate()`: checks if the king is in checkmate
- `isStalemate()`: checks if the game is a stalemate

## Notes

- The constructor does not perform input data validation.
- The `movePiece` function does not perform validation on the move.
- There is no error checking when accessing class members.

## Suggestions for improvement

- Add input data validation in the constructor.
- Implement the destructor `~Board()` if necessary.
- Add error checking when accessing class members.
- Consider using constant class members if they do not change after initialization.
- Consider using exceptions for error handling instead of returning garbage values.
