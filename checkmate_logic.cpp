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

bool if_mat(Color color, Board board){
    cout << "defeat\n";
    return false;

}

void pawn_to(int x, int y, PieceType type, Board board){
    if (board.getPiece(x, y).getType() == PAWN){
        if (board.getPiece(x, y).getColor() == WHITE){
            
        }
        else{
            

            
        }
    }
    
}

void king_turn(int x1, int y1, int x2, int y2, Board board){

    if (board.getPiece(x1, y1).getType() == KING){
        if (board.getPiece(x1, y1).getColor() == WHITE){
            //left top
            if (x1 - x2 == 1 && y1 - y2 == 1){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1,y1,x2,y2);
                return;   
            }

            //mid top
                if (x1 - x2 == 1 && y1 == y2){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1,y1,x2,y2);
                return;
                }

            //mid left
                if (x1 == x2 && y1 - y2 == 1){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1,y1,x2,y2);
                return; 
                }

            //mid right
                if (x1 == x2 && y1 - y2 == 1){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1,y1,x2,y2);
                return;
                }

            if (board.getPiece(x1, y1).getType() == KING){
                if (board.getPiece(x1, y1).getColor() == NIGGER){
            //left top
                if (x1 - x2 == 1 && y1 - y2 == 1){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1,y1,x2,y2);
                return;   
                }

            //mid top
                if (x1 - x2 == 1 && y1 == y2){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1,y1,x2,y2);
                return;
                }

            //mid left
                if (x1 == x2 && y1 - y2 == 1){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1,y1,x2,y2);
                return; 
                }

            //mid right
                if (x1 == x2 && y1 - y2 == 1){
                if (board.getPiece(x2, y2).getType() != NAIN && y1 == y2){
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1,y1,x2,y2);
                return;
                }

        }
    }
}


bool king_turn_check(int x1, int y1, int x2, int y2, Board board,){

    if (board.getPiece(x1, y1).getType() == KING){
        if (board.getPiece(x1, y1).getColor() == WHITE){
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

            if (board.getPiece(x1, y1).getType() == KING){
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


bool isCheckMate(Color color, Board board){
    for(int i = 0, i < board.sx, i++){
        for(int a = 0, a < board.sy, a++){
            if(board.getPiece(i, a).getType() != NAIN){
                switch(board.getPiece(i, a).getType()){
                    case(PAWN):
                        if(pawn_turn(i, a, kingx, kingy, board)){
                            return true;
                        }
                    break;
                }
            }
        }
    }
}


int main() {
    Board board = board.set_base8x8(); //init basic 8x8 board with pieces
    //Piece piece(WHITE, PAWN, 2, 1);
    //board.setPiece(piece);
    
    board.printBoard(); //output all board statement BLYAAAAAAAAAAAAAAAAAAAAAAAAAT
    pawn_turn(1, 1, 3, 1, board);

    board.printBoard();

    return 0;
}

