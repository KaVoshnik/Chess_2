#include <iostream>

using std::cout;
using std::endl;

class Board
{
public:
    Board();
    ~Board();
    void printBoard();
    void movePiece();
private:
    char board[8][8];
};