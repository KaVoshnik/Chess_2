#include <iostream>

using std::cout;

int main(){
    char **board;

    char buff = '7';

    char *buff2;
    buff2 = new char[4];
    for(int i = 0; i < 8; i++){
        buff2[i] = buff;
    }

        board = new char*[8];
        for(int i = 0; i < 8; i++){
            board[i] = buff2;

            /* DEBUG
            for(int c = 0; c < 8; c++){
                cout << ". ";
            }
            cout << '\n';
            */
        }
    
    for(int a = 0; a < 8; a++){
        for(int b = 0; b < 4; b++){
            cout << board[a][b] << ' ';
        }
        cout << '\n';
    }



    return 0;
}