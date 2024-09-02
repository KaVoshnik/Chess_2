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
        ret[1][i].setType(PAWN);
        ret[1][i].setColor(NIGGER);
    }

    for(int i = 0; i < 8; i++){
        ret[0][i].setColor(NIGGER);
    }

    // фигуры низ
    ret[7][0].setType(ROOK);
    ret[7][1].setType(KNIGHT);
    ret[7][2].setType(BISHOP);
    ret[7][3].setType(QUEEN);
    ret[7][4].setType(KING);
    ret[7][5].setType(BISHOP);
    ret[7][6].setType(KNIGHT);
    ret[7][7].setType(ROOK);

    // пешки низ
    for(int i = 0; i < 8; i++){
        ret[6][i].setType(PAWN);
        ret[6][i].setColor(WHITE);
    }

    for(int i = 0; i < 8; i++){
        ret[0][i].setColor(WHITE);
    }

    //cout << "DDD"
}


int main(){
    Piece board[8][8];

    for(int x = 0; x < 8; x++){
        for(int y = 0; y < 8; y++){
            board[x][y] = *new Piece(WHITE, NAIN, x, y);
        }   
    }
    
    start_8_8(board);

    char out = ' ';

    for(int a = 0; a < 8; a++){
        for(int b = 0; b < 8; b++){
            
            if(board[a][b].getType() == PAWN){
                out = '1';
            }
            else if(board[a][b].getType() != PAWN){
                out = '.';
            }
            else{}


            cout << out << " ";
            
        } 
        cout << '\n';
    }
}
