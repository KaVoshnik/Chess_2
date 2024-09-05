#include "../include/Piece.h"


    Color Piece::getColor() {
        return color;
    } //Color of the piece

    PieceType Piece::getType() {
        return type;
    }  //Type of the piece

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

    void Piece::move(int newRow, int newCol) {
    if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) { // Add limitation for row and column
        row = newRow;
        col = newCol;
    } else {
        // Exception handling or error message
    }

    }

