#include "../include/Board.h"


Piece** Board::getBoard(){
    return board;
}

void Board::setBoard(Piece** newBoard){
    board = newBoard;
}

Piece Board::getPiece(int row, int col){
    return board[row][col];
}

void Board::setPiece(Piece newPiece){
    board[newPiece.getRow()][newPiece.getCol()] = newPiece;
}

void Board::printBoard(){ //TODO
    for(int a = 0; a < 8; a++){
        for(int b = 0; b < 8; b++){
            if(board[a][b].getType() == PAWN){
                if(board[a][b].getColor() == WHITE){
                    cout << "P ";
                }
                else{
                    cout << "p ";
                }
            }
            else{
                cout << ". ";
            }
        }
        cout << '\n';
    }
}
