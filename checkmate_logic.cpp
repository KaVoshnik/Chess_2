#include "include/Board.h"

/*  
    
    DESCRYPTION

    cords: X is vertical Y is horizontal 

    FOR EXAMPLE, i take raw version pawn logic to test
    
*/



void pawn_turn(int x1, int y1, int x2, int y2, Board board){


    if (board.getPiece(x1, y1).getType() == PAWN){
        if (board.getPiece(x1, y1).getColor() == WHITE){
            if (x1 - x2 == 2 && x1 == 6 && y1 == y2){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1,y1,x2,y2);
                return;
                
            }
            if (x1 - x2 == 1 && (y1 == y2 or board.getPiece(x1 - 1, y1 + 1).getColor() == NIGGER or board.getPiece(x1 - 1, y1 - 1).getColor() == NIGGER)){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    cout << "uncurrect turn!!!!!! 1aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1, y1, x2, y2);
                return;
            }
            cout << "uncurrect turn!!!!!! white pizda\n";
            return;
        }
        else{
            if (x2 - x1 == 2 && x1 == 1 && y1 == y2){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1, y1, x2, y2);
                return;
                
            }
            if (x2 - x1 == 1 && (y1 == y2 or board.getPiece(x1 + 1, y1 + 1).getColor() == WHITE or board.getPiece(x1 + 1, y1 - 1).getColor() == WHITE)){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    cout << "uncurrect turn!!!!!! 2aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1, y1, x2, y2);
                return;
            }
            cout << "uncurrect turn!!!!!! black pizda\n " ;
            return;
        }
    }
    else {
        cout << "uncurrect turn!!!!!! ti dolbaeb\n";
        return;
    }
}

void pawn_to(int x, int y, PieceType type, Board board){
    if (board.getPiece(x, y).getType() == PAWN){
        if (board.getPiece(x, y).getColor() == WHITE){
            
        }
        else{
            

            
        }
    }
    
}

//ходы короля
void king_turn(int x1, int y1, int x2, int y2, Board board) {
    if (board.getPiece(x1, y1).getType() == KING) {
        if (board.getPiece(x1, y1).getColor() == WHITE) {
            //left top
            if (x1 - x2 == 1 && y1 - y2 == 1) {
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2) {
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1, y1, x2, y2);
                return;
            }

            //mid top
            if (x1 - x2 == 1 && y1 == y2) {
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2) {
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1, y1, x2, y2);
                return;
            }

            //mid left
            if (x1 == x2 && y1 - y2 == 1) {
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2) {
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1, y1, x2, y2);
                return;
            }

            //mid right
            if (x1 == x2 && y1 - y2 == 1) {
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2) {
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1, y1, x2, y2);
                return;
            }

            if (board.getPiece(x1, y1).getType() == KING) {
                if (board.getPiece(x1, y1).getColor() == NIGGER) {
                    //left top
                    if (x1 - x2 == 1 && y1 - y2 == 1) {
                        if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2) {
                            cout << "uncurrect turn!!!!!! aaaaaaa\n";
                            return;
                        }
                        board.moveFromTo(x1, y1, x2, y2);
                        return;
                    }

                    //mid top
                    if (x1 - x2 == 1 && y1 == y2) {
                        if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2) {
                            cout << "uncurrect turn!!!!!! aaaaaaa\n";
                            return;
                        }
                        board.moveFromTo(x1, y1, x2, y2);
                        return;
                    }

                    //mid left
                    if (x1 == x2 && y1 - y2 == 1) {
                        if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2) {
                            cout << "uncurrect turn!!!!!! aaaaaaa\n";
                            return;
                        }
                        board.moveFromTo(x1, y1, x2, y2);
                        return;
                    }

                    //mid right
                    if (x1 == x2 && y1 - y2 == 1) {
                        if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2) {
                            cout << "uncurrect turn!!!!!! aaaaaaa\n";
                            return;
                        }
                        board.moveFromTo(x1, y1, x2, y2);
                        return;
                    }
                }
            }
        }

    }
}

//воде нихуя не доделано сам непомню че тут было (вроде проверка на может ли походить король)
bool king_turn_check(int x1, int y1, int x2, int y2, Board board,){

    if (board.getPiece(x1, y1).getType() == KING){
        if (board.getPiece(x1, y1).getColor() == WHITE) {
            //left top
            if (x1 - x2 == 1 && y1 - y2 == 1) {
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2) {
                    isCheckMate

                }
                return true;
            }

            //mid top
            if (x1 - x2 == 1 && y1 == y2) {
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2) {
                    return false;
                }
                return true;
            }

            //mid left
            if (x1 == x2 && y1 - y2 == 1) {
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2) {
                    return false;
                }
                return true;
            }

            //mid right
            if (x1 == x2 && y1 - y2 == 1) {
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2) {
                    return false;
                }
                return true;
            }
        }

        if (board.getPiece(x1, y1).getColor() == NIGGER){
            //left top
            if (x1 - x2 == 1 && y1 - y2 == 1){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    return false;
                }
                return true; 
            }

            //mid top
            if (x1 - x2 == 1 && y1 == y2){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    return false;
                }
                return true;
            }

            //mid left
            if (x1 == x2 && y1 - y2 == 1){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    return false;
                }
                return true;
            }

            //mid right
            if (x1 == x2 && y1 - y2 == 1){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    return false;
                }
                return true;
            }
        }
    }
}


//проверка на шах
bool isCheckMate(Color color, Board board){
    for(int i = 0, i < board.sx, i++){
        for(int j = 0, j < board.sy, j++){
            if(board.getPiece(i, j).getType() != NAIN){
                //change kingx and kingy to king X coordinates and king Y coordinates
                switch(board.getPiece(i, j).getType()){
                    case(PAWN):
                        //white
                        if(pawn_turn(i, j, kingx, kingy, board) && get.Piece(kingx, kingy).getColor() == NIGGER && get.Piece(i,j).getColor() == WHITE){
                            return true;
                        }
                        //black
                        if (pawn_turn(i, j, kingx, kingy, board) && get.Piece(kingx, kingy).getColor() == WHITE && get.Piece(i, j).getColor() == NIGGER) {
                            return true;
                        }
                    case(ROOK)
                        //white
                        if (rook_turn(i, j, kingx, kingy, board) && get.Piece(kingx, kingy).getColor() == NIGGER && get.Piece(i, j).getColor() == WHITE) {
                            return true;
                        }
                        //black
                        if (rook_turn(i, j, kingx, kingy, board) && get.Piece(kingx, kingy).getColor() == WHITE && get.Piece(i, j).getColor() == NIGGER) {
                            return true;
                        }
                    case(KNIGHT)
                        //white
                        if (knight_turn(i, j, kingx, kingy, board) && get.Piece(kingx, kingy).getColor() == NIGGER && get.Piece(i, j).getColor() == WHITE) {
                            return true;
                        }
                        //black
                        if (knight_turn(i, j, kingx, kingy, board) && get.Piece(kingx, kingy).getColor() == WHITE && get.Piece(i, j).getColor() == NIGGER) {
                            return true;
                        }
                    case(BISHOP)
                        //white
                        if (bishop_turn(i, j, kingx, kingy, board) && get.Piece(kingx, kingy).getColor() == NIGGER && get.Piece(i, j).getColor() == WHITE) {
                            return true;
                        }
                        //black
                        if (bishop_turn(i, j, kingx, kingy, board) && get.Piece(kingx, kingy).getColor() == WHITE && get.Piece(i, j).getColor() == NIGGER) {
                            return true;
                        }
                    case(QUEEN)
                        //white
                        if (queen_turn(i, j, kingx, kingy, board) && get.Piece(kingx, kingy).getColor() == NIGGER && get.Piece(i, j).getColor() == WHITE) {
                            return true;
                        }
                        //black
                        if (queen_turn(i, j, kingx, kingy, board) && get.Piece(kingx, kingy).getColor() == WHITE && get.Piece(i, j).getColor() == NIGGER) {
                            return true;
                        }
                    break;
                }
            }
        }
    }
}

//проверка на шах и мат недоделано нихуя
bool isStaleMate(Color color, Board board) {
    for (int i = 0, i < board.sx, i++) {
        for (int j = 0, j < board.sy, j++) {
            if (board.getPiece(i, j).getType() == KING) {
                //проверка на то является ли клетка поля под атакой
                //mid top
                if (i + 1 == /*in_stalemate*/ ||||) {
                    
                }
                //left top 
                //left mid
                //left bottom
                //mid bottom
                //right top
                //right mid
                //right bottom

            }
        }
    }
}
}

int main() {
    Board board = board.set_base8x8(); //init basic 8x8 board with pieces
    //Piece piece(WHITE, PAWN, 2, 1);
    //board.setPiece(piece);
    
    board.printBoard(); //output all board statement
    pawn_turn(1, 1, 3, 1, board);

    board.printBoard();


    if (isCheckMate == true)
    {
        //you must protect or move your king
    }

    if (isStaleMate == true)
    {
        //... WINS
    }

    return 0;
}

