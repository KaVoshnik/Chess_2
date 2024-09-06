#include "include/Board.h"

/*  
    DESCRYPTION

    cords: X is vertical Y is horizontal 
*/


int main() {
    Board board = board.set_base8x8(); //init basic 8x8 board with pieces
    
    board.printBoard(); //output all board statement
    board.moveFromTo(0, 0, 2, 0); //move example
    board.printBoard();

    return 0;
}
