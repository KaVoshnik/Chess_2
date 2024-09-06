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

    void Piece::newCords(int newRow, int newCol) {
        row = newRow;
        col = newCol;
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

    void Piece::operator=(Piece& other){
        type = other.type;
        color = other.color;
        col = other.col;
        row = other.row;
    }

    void Piece::changePiece(Color newColor, PieceType newType, int newRow, int newCol){
        type = newType;
        color = newColor;
        col = newCol;
        row = newRow;
    }