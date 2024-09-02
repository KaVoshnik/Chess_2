#include "chess_object.h"

void start_8_8(Piece ret[8][8]){

// фигуры вверх
    ret[0][0].setType(ROOK);
    ret[0][1].setType(KNIGHT);
    ret[0][2].setType(BISHOP);
    ret[0][3].setType(QUEEN);
    ret[0][4].setType(KING);
    ret[0][5].setType(BISHOP);
    ret[0][6].setType(KNIGHT);
    ret[0][7].setType(ROOK);

    for(int i = 0; i < 8; i++){
        ret[0][i].setColor(NIGGER);
    }
    // фигуры низ
    ret[7][0] = '4';
    ret[7][1] = '3';
    ret[7][2] = '2';
    ret[7][3] = '5';
    ret[7][4] = '6';
    ret[7][5] = '2';
    ret[7][6] = '3';
    ret[7][7] = '4';
    // пешки низ
    ret[6][0] = '1';
    ret[6][1] = '1';
    ret[6][2] = '1';
    ret[6][3] = '1';
    ret[6][4] = '1';
    ret[6][5] = '1';
    ret[6][6] = '1';
    ret[6][7] = '1';

    // пешки вверх
    ret[1][0] = 'p';
    ret[1][1] = 'p';
    ret[1][2] = 'p';
    ret[1][3] = 'p';
    ret[1][4] = 'p';
    ret[1][5] = 'p';
    ret[1][6] = 'p';
    ret[1][7] = 'p';
}


int main(){

    char board[8][8];


    start_8_8(board);

    for(int a = 0; a < 8; a++){
        for(int b = 0; b < 8; b++){
            cout << board[a][b] << " ";
        } 
        cout << '\n';
    }
}
