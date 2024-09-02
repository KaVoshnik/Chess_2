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

int main() {
    
    Piece piece(WHITE, PAWN, 1, 1);

    printPieceInfo(piece);

    return 0;
}