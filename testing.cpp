#include "include/Board.h"

/*  
    DESCRYPTION

    cords: X is vertical Y is horizontal 
*/

void pawn_turn(int x1, int y1, int x2, int y2, Board board){


    if (board.getPiece(x1, y1).getType() == PAWN){
        if (board.getPiece(x1, y1).getColor() == WHITE){
            if (x1 - x2 == 2 and x1 == 6 and y1 == y2){
                
                board.moveFromTo(x1,y1,x2,y2);
                return;
                
            }
            if (x1 - x2 == 1 and y1 == y2){
                board.moveFromTo(x1, y1, x2, y2);
                return;
            }
            cout << "uncurrect turn!!!!!! white pizda\n";
            return;
        }
        else{
            if (x2 - x1 == 2 and x1 == 1 and y1 == y2){
                
                board.moveFromTo(x1, y1, x2, y2);
                return;
                
            }
            if (x2 - x1 == 1 and y1 == y2){
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
int main() {
    Board board = board.set_base8x8(); //init basic 8x8 board with pieces
    
    board.printBoard(); //output all board statement BLYAAAAAAAAAAAAAAAAAAAAAAAAAT
    pawn_turn(1, 1, 2, 1, board);
    board.printBoard();

    return 0;
}
