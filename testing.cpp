#include "chess_object.h"
#include "chess_object.cpp"



int main(){
    //FFFFFFFFFFFFFFFFFFFFFFFFFFFF

    Piece **board;

    board = new Piece*[8];
    for(int i = 0; i < 8; i++){
        board[i] = new Piece[8];
    }

    //FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF

    board[4][4].move(4, 4);
    board[4][4].setColor(WHITE);
    board[4][4].setType(PAWN);


    for(int a = 0; a < 8; a++){
        for(int b = 0; b < 8; b++){
            if(board[a][b].getType() == PAWN){
                cout << "X ";
            }
            else{
                cout << ". ";
            }
        }
        cout << '\n';
    }
}
