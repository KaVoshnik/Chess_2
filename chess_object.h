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
    PAWN, //пешка
    ROOK,  // Горизонталька
    KNIGHT, // конь
    BISHOP, // диагональка
    QUEEN, //в пояснении не нуждается
    KING,
    NAN
};

class Piece {

public:
    Piece(Color color, PieceType type, int row, int col): //Конструктор  
    color(color), type(type), row(row), col(col) {}
    
    Color getColor() {
        return color;
    } //Цвет фигуры

    PieceType getType() {
        return type;
    }  //Тип фигуры

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
}

#endif