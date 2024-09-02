#ifndef chess_object_h
#define chess_object_h
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
    Piece();
    Piece(Color color, PieceType type, int row, int col): //Конструктор  
    color(color), type(type), row(row), col(col) {}
    
    Color getColor();
    PieceType getType();
    int getRow() ;
    int getCol() ;
    void setRow(int newRow);
    void setCol(int newCol);
    void setColor(Color newColor);
    void setType(PieceType newType);


private:
    Color color;
    PieceType type;
    int row;
    int col;
};

#endif