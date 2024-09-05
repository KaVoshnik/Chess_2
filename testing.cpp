#include <cassert>
#include "Board.h"


int main() {
    Board board = *new Board();
    Piece piece(WHITE, PAWN, 4, 3);
    board.setPiece(piece);
    board.printBoard();
    return 0;
}