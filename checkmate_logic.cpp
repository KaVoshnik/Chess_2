#include "include/Board.h"

/*  
    DESCRYPTION

    cords: X is vertical Y is horizontal 

    FOR EXAMPLE, i take raw version pawn logic to test
*/



void pawn_turn(int x1, int y1, int x2, int y2, Board board){


    if (board.getPiece(x1, y1).getType() == PAWN){
        if (board.getPiece(x1, y1).getColor() == WHITE){
            if (x1 - x2 == 2 and x1 == 6 and y1 == y2){
                if (board.getPiece(x2, y2).getType() != NAIN and y1 == y2){
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1,y1,x2,y2);
                return;
                
            }
            if (x1 - x2 == 1 and (y1 == y2 or board.getPiece(x1 - 1, y1 + 1).getColor() == NIGGER or board.getPiece(x1 - 1, y1 - 1).getColor() == NIGGER)){
                if (board.getPiece(x2, y2).getType() != NAIN and y1 == y2){
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
            if (x2 - x1 == 2 and x1 == 1 and y1 == y2){
                if (board.getPiece(x2, y2).getType() != NAIN and y1 == y2){
                    cout << "uncurrect turn!!!!!! aaaaaaa\n";
                    return;
                }
                board.moveFromTo(x1, y1, x2, y2);
                return;
                
            }
            if (x2 - x1 == 1 and (y1 == y2 or board.getPiece(x1 + 1, y1 + 1).getColor() == WHITE or board.getPiece(x1 + 1, y1 - 1).getColor() == WHITE)){
                if (board.getPiece(x2, y2).getType() != NAIN and y1 == y2){
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

int main() {
    Board board = board.set_base8x8(); //init basic 8x8 board with pieces
    //Piece piece(WHITE, PAWN, 2, 1);
    //board.setPiece(piece);
    
    board.printBoard(); //output all board statement BLYAAAAAAAAAAAAAAAAAAAAAAAAAT
    pawn_turn(1, 1, 3, 1, board);

    board.printBoard();

    return 0;
}
//pizda