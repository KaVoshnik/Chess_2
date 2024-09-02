#include "/home/avairon/Chess_2/chess_object.h"

    Piece();
    Piece(Color color, PieceType type, int row, int col): //Конструктор  
    //color(color), type(type), row(row), col(col) {}

    Color Piece::getColor() {
        return color;
    } //Цвет фигуры

    PieceType Piece::getType() {
        return type;
    }  //Тип фигуры

    int Piece::getRow() {
        return row;
    } 

    int Piece::getCol() {
        return col;
    } 

    void Piece::setRow(int newRow) {
        row = newRow;
    } 
    void Piece::setCol(int newCol) {
        col = newCol;
    } 
    void Piece::setColor(Color newColor){
        color = newColor;
    }
    void Piece::setType(PieceType newType){
        type = newType;
    }
