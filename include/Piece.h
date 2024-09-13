#ifndef Piece_h
#define Piece_h
#include <iostream>


using std::cout;

enum Color {
    WHITE,
    NIGGER //niga
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
    
    Color getColor();
    PieceType getType();
    int getRow();
    int getCol();
    
    void setRow(int newRow);
    void setCol(int newCol);
    void setColor(Color newColor);
    void setType(PieceType newType);

    void operator=(Piece& other);
    void changePiece(Color newColor, PieceType newType, int newRow, int newCol);
    void newCords(int newRow, int newCol);



private:
    Color color;
    PieceType type;
    int row;
    int col;
};

#endif