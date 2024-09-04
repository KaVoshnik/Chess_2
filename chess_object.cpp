#include "chess_object.h"

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
    void Piece::move(int newRow, int newCol){
        col = newCol;
        row = newRow;
    }
