#include <cassert>
#include "Board.h"
#include "chess_object.h"

// Test class for Board and Piece
class TestBoardAndPiece {
public:
    void testPieceConstructor() {
        Piece piece(WHITE, PAWN, 4, 3);
        assert(piece.getColor() == WHITE);
        assert(piece.getType() == PAWN);
        assert(piece.getRow() == 4);
        assert(piece.getCol() == 3);
    }

    void testBoardConstructor() {
        Board board;
        Piece** boardArray = board.getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assert(boardArray[i][j].getType() == NAIN); // All pieces should be NaN
            }
        }
    }

    void testBoardSetPiece() {
        Board board;
        Piece piece(WHITE, PAWN, 4, 3);
        board.setPiece(piece);
        Piece** boardArray = board.getBoard();
        assert(boardArray[4][3].getType() == PAWN);
    }

    void testBoardPrintBoard() {
        Board board;
        Piece piece(WHITE, PAWN, 4, 3);
        board.setPiece(piece);
        board.printBoard(); // Checking that the board is printed correctly
    }
};

int main() {
    TestBoardAndPiece test;
    test.testPieceConstructor();
    test.testBoardConstructor();
    test.testBoardSetPiece();
    test.testBoardPrintBoard();
    return 0;
}