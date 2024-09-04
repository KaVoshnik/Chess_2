#include "Board.h"

int main(){
    Board board;

    Piece pawn1(WHITE, PAWN, 4, 3);

    board.setPiece(pawn1);
    board.printBoard();
}
