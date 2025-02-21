//using System;
//using Piece;
using Board;

namespace test_app
{
    class Program
    {
        static void Main(string[] args)
        {
            board board = new board(); // board
            
            board.create_piece("pawn", 2, 1);

            board.print_field_info(-2, -2, 8, 8);    

            board.print_field_info(0, 0, 4, 6);   
        }  
    }
}



