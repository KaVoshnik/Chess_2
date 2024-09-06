#include "include/Board.h"

int main() {
    Board board = board.set_base8x8(); //init basic 8x8 board with pieces
    
    board.printBoard(); //output all board statement
    board.moveFromTo(0, 0, 2, 0);
    board.printBoard();

    return 0;
}
