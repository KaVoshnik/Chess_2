#include "Board.h"

Board::Board()
{
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            board[i][j] = ' ';
        }
    }
    char white[] = "RNBQKBNR";
    char black[] = "rnbqkbnr";
    for (int i = 0; i < 8; i++) {
        board[0][i] = white[i];
        board[7][i] = black[i];
    }
}

Board::~Board()
{
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            board[i][j] = ' ';
        }
    }
}

void Board::printBoard()
{
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            cout << board[i][j] << " ";
        }
        cout << endl;
    }
}

void Board::movePiece()
{
    // TODO
}

int main()
{
    Board board;
    board.printBoard();
    return 0;
}