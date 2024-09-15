#include "./src/Board.h"

int main(){
    Board board = board.set_base8x8();
    board.printBoard();

    board.resizeBoard(10);
    board.printBoard();

    return 0;
}