#include <iostream>
#include "Piece.h"

using std::cout;
using std::endl;

class Board
{
public:
    Board(){
        Piece **board;

        board = new Piece*[8];
        for(int i = 0; i < 8; i++){
            board[i] = new Piece[8];
        }
    }

    ~Board(){}

    Piece** getBoard();
    void setBoard(Piece** newBoard);

    void printBoard();
    //void movePiece();

    Piece getPiece(int row, int col);
    void setPiece(Piece newPiece);

private:
    Piece **board;
};