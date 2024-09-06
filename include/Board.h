#include <iostream>
#include "Piece.h"

using std::cout;
using std::endl;

class Board
{
public:
    Board(int size){
        sx = size;
        sy = size;

        board = new Piece*[size]();
        for(int i = 0; i < size; i++){
            board[i] = new Piece[size]();
            /* DEBUG
            for(int c = 0; c < size; c++){
                cout << ". ";
            }
            cout << '\n';
            */
        }
    }

    Board(int sizeX, int sizeY){
        cout << sizeX << ' ' << sizeY << " <-- int matrix sizes on input\n"; //DEBUG

        sx = sizeX;
        sy = sizeY;

        board = new Piece*[sizeX]();
        for(int i = 0; i < sizeX; i++){
            board[i] = new Piece[sizeY]();
            /* DEBUG
            for(int c = 0; c < sizeY; c++){
                cout << ". ";
            }
            cout << '\n';
            */
        }
    }

    ~Board(){}

    Piece** getBoard();
    void setBoard(Piece** newBoard);

    void printBoard();
    //void movePiece();

    Piece getPiece(int row, int col);
    void setPiece(Piece& newPiece);

    Board set_base8x8();
    void moveFromTo(int x1, int y1, int x2, int y2);

private:
    Piece **board;
    int sx;
    int sy;
};