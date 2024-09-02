#include "chess_object.h"

void printColor(Color color) {
    switch (color) {
        case WHITE:
            cout << "WHITE";
            break;
        case NIGGER:
            cout << "NIGGER";
            break;
    }
}

void printPieceType(PieceType type) {
    switch (type) {
        case PAWN:
            cout << "PAWN";
            break;
        case ROOK:
            cout << "ROOK";
            break;
        case KNIGHT:
            cout << "KNIGHT";
            break;
        case BISHOP:
            cout << "BISHOP";
            break;
        case QUEEN:
            cout << "QUEEN";
            break;
        case KING:
            cout << "KING";
            break;
    }
}

void printPieceInfo(Piece piece) {
    cout << "Color: ";
    printColor(piece.getColor());
    cout << ", Type: ";
    printPieceType(piece.getType());
    cout << ", Row: " << piece.getRow();
    cout << ", Col: " << piece.getCol();
}

void movePawn(Piece piece, int newRow, int newCol) {
    PieceType type = piece.getType();
    if (type == PAWN) {
        switch (piece.getColor()) {
            case WHITE:
            // double move
                if (piece.getRow() == 1 && newRow == 3) {
                    piece.setRow(newRow);
                    piece.setCol(newCol);
                } else if (newRow == piece.getRow() + 1) {
                    // base move
                    piece.setRow(newRow);
                    piece.setCol(newCol);
                } else {
                    // No valid move
                    cout << "Not a valid move!" << endl;
                }
                break;
            case NIGGER:
            // double move
                if (piece.getRow() == 6 && newRow == 4) {
                    piece.setRow(newRow);
                    piece.setCol(newCol);
                } else if (newRow == piece.getRow() - 1) {
                    // base move
                    piece.setRow(newRow);
                    piece.setCol(newCol);
                } else {
                    // No valid move
                    cout << "Not a valid move!" << endl;
                }
                break;
        }
    }
}

int main() {
    
    Piece piece(WHITE, PAWN, 1, 1);
    movePawn(piece, 3, 1); // Double move
    movePawn(piece, 4, 1); // Base move
    movePawn(piece, 5, 1); // No valid move
    return 0;

}