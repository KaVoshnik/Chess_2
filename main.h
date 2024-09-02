#ifndef class_h
#define class_h
#include <iostream>
#include <string>

using std::cout;

class Piece {

public:
    Piece(Color color, PieceType type, int row, int col): //Конструктор  
    color(color), type(type), row(row), col(col) {}
    
    getColor() const {
        return color;
    } //Цвет фигуры
    getType() const {
        return type;
    }  //Тип фигуры
    
    int getRow() const {
        return row;
    } 
    int getCol() const {
        return col;
    } 
    void setRow(int newRow)const {
        row = newRow;
    } 
    void setCol(int newCol) const {
        col = newCol;
    } 

private:
    Color color;
    PieceType type;
    int row;
    int col;
}

#endif