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

void Board::setPiece(Piece& newPiece){
    /* DEBUG
    cout << '\n' << newPiece.getRow() << ' ' << newPiece.getCol() << " <-- enter piece cords\n";
    cout << sx << ' ' << sy << " <-- int matrix size\n";
    cout << board[0][0].getType() << " <-- int board[0][0] type\n\n";
    */

    if(sx > newPiece.getCol() and sy > newPiece.getRow()){
        board[newPiece.getRow()][newPiece.getCol()] = newPiece;
        return;
    }
    cout << "Piece out of bounds\n";
    return;
}


Board Board::set_base8x8(){
    Board outBoard = *new Board(8);

    // БЛОК ПЕШЕК
    Piece piece(NIGGER, PAWN, 1, 0);   
    outBoard.setPiece(piece);

    for(int i = 1; i < 8; i++){
        piece.newCords(1, i);
        outBoard.setPiece(piece);
    }
    
    piece.changePiece(WHITE, PAWN, 6, 0);
    outBoard.setPiece(piece);

    for(int i = 1; i < 8; i++){
        piece.newCords(6, i);
        outBoard.setPiece(piece);
    }


    // БЛОК КОНЬКОВ
    piece.changePiece(NIGGER, KNIGHT, 0, 1);
    outBoard.setPiece(piece);
    piece.newCords(0, 6);
    outBoard.setPiece(piece);

    piece.changePiece(WHITE, KNIGHT, 7, 1);
    outBoard.setPiece(piece);
    piece.newCords(7, 6);
    outBoard.setPiece(piece);

    //БЛОК ГОРИЗОНТАЛЕК
    piece.changePiece(NIGGER, ROOK, 0, 0);
    outBoard.setPiece(piece);
    piece.newCords(0, 7);
    outBoard.setPiece(piece);

    piece.changePiece(WHITE, ROOK, 7, 0);
    outBoard.setPiece(piece);
    piece.newCords(7, 7);
    outBoard.setPiece(piece);

    //БЛОК ДИАГОНАЛЕК
    piece.changePiece(NIGGER, BISHOP, 0, 2);
    outBoard.setPiece(piece);
    piece.newCords(0, 5);
    outBoard.setPiece(piece);

    piece.changePiece(WHITE, BISHOP, 7, 2);
    outBoard.setPiece(piece);
    piece.newCords(7, 5);
    outBoard.setPiece(piece);

    //БЛОК КОРОЛЬ/КОРОЛЕВА
    piece.changePiece(NIGGER, KING, 0, 4);
    outBoard.setPiece(piece);
    piece.changePiece(NIGGER, QUEEN, 0, 3);
    outBoard.setPiece(piece);

    piece.changePiece(WHITE, KING, 7, 4);
    outBoard.setPiece(piece);
    piece.changePiece(WHITE, QUEEN, 7, 3);
    outBoard.setPiece(piece);

    return outBoard;
}

void Board::printBoard(){ 
    cout << '\n';
    for(int a = 0; a < sx; a++){
        for(int b = 0; b < sy; b++){
            if(board[a][b].getType() == PAWN){
                if(board[a][b].getColor() == WHITE){
                    cout << "P ";
                }
                else{
                    cout << "p ";
                }
            }
            else if(board[a][b].getType() == ROOK){
                if(board[a][b].getColor() == WHITE){
                    cout << "R ";
                }
                else{
                    cout << "r ";
                }
            }
            else if(board[a][b].getType() == KNIGHT){
                if(board[a][b].getColor() == WHITE){
                    cout << "N ";
                }
                else{
                    cout << "n ";
                }
            }
            else if(board[a][b].getType() == KING){
                if(board[a][b].getColor() == WHITE){
                    cout << "K ";
                }
                else{
                    cout << "k ";
                }
            }
            else if(board[a][b].getType() == QUEEN){
                if(board[a][b].getColor() == WHITE){
                    cout << "Q ";
                }
                else{
                    cout << "q ";
                }
            }
            else if(board[a][b].getType() == BISHOP){
                if(board[a][b].getColor() == WHITE){
                    cout << "B ";
                }
                else{
                    cout << "b ";
                }
            }
            else{
                cout << ". ";
            }
        }
        cout << '\n';
    }
    cout << '\n';
}
void Board::moveFromTo(int x1, int y1, int x2, int y2){
    board[x2][y2] = board[x1][y1];
    board[x2][y2].newCords(x2, y2);
}
