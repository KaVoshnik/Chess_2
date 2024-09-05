#include <iostream>

using std::cout;

enum Color {
    WHITE,
    NIGGER
};

enum PieceType {
    PAWN, //пешка
    ROOK,  // Горизонталька
    KNIGHT, // конь
    BISHOP, // диагональка
    QUEEN, //в пояснении не нуждается
    KING,
    NAIN
};

class Piece {

public:
    Piece(){
        color = WHITE;
        type = NAIN;
        row = 0;
        col = 0; 
    }

    Piece(Color newColor, PieceType newType, int newRow, int newCol){
        color = newColor;
        type = newType;
        row = newRow;
        col = newCol;
    }

    ~Piece(){}
    
    Color getColor() {
        return color;
    } //Color of the piece

    PieceType getType() {
        return type;
    }  //Type of the piece

    int getRow() {
        return row;
    } 

    int getCol() {
        return col;
    } 

    void setRow(int newRow) {
        row = newRow;
    } 

    void setCol(int newCol) {
        col = newCol;
    } 

    void setColor(Color newColor){
        color = newColor;
    }

    void setType(PieceType newType){
        type = newType;
    }

    void move(int newRow, int newCol) {
        if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) { // Add limitation for row and column
            row = newRow;
            col = newCol;
        } else {
            // Exception handling or error message
        }
    }

private:
    Color color;
    PieceType type;
    int row;
    int col;
};

class Board
{
public:
    Board(){        
        Piece **board;

        board = new Piece*[8];
        for(int i = 0; i < 8; i++){
            board[i] = new Piece[8];
        }
    }

    ~Board(){}

    Piece** getBoard(){
        return board;
    }

    void setBoard(Piece** newBoard){
        board = newBoard;
    }

    Piece getPiece(int row, int col){
        return board[row][col];
    }

    void setPiece(Piece newPiece){
        cout << newPiece.getRow() << " " << newPiece.getCol() << '\n';
        board[newPiece.getRow()][newPiece.getCol()] = newPiece; //<----FIX HERE
    }

    void printBoard(){ //TODO
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

private:
    Piece **board;
};

int main() {
    Board board = *new Board();
    Piece piece(WHITE, PAWN, 4, 3);

    board.setPiece(piece);
    cout << "3\n";
    board.printBoard();
    cout << "4\n";
    return 0;
}