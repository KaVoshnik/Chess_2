#ifndef chess_object_h
#define chess_object_h
#include <iostream>
#include <string>

using std::cout;

enum Color {
    WHITE,
    NIGGER
};

enum PieceType {
    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING,
    NAAN
};

class Piece {

public:
    Piece(Color color, PieceType type, int row, int col):
    color(color), type(type), row(row), col(col) {}
    
    Color getColor() {
        return color;
    }

    PieceType getType() {
        return type;
    }

    int getRow() const {
        return row;
    } 

    int getCol() const {
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


private:
    Color color;
    PieceType type;
    int row;
    int col;
};
#endif