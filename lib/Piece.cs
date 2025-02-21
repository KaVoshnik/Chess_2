using System;

namespace Piece
{
    public class piece
    {
        private string type;
        private int xpos;
        private int ypos;

        // CONSTRUCTORS

        public piece()
        {
            this.type = "";
        }
        public piece(string type, int xpos, int ypos)
        {
            this.type = type;
            this.xpos = xpos;
            this.ypos = ypos;
        }

        // GETTERS

        public string get_type(){
            return this.type;
        }
        public int get_xpos(){
            return this.xpos;
        }
        public int get_ypos(){
            return this.ypos;
        }

        // SETTERS

        public void set_type(string type){
            this.type = type;
        }
        public void set_xpos(int xpos){
            this.xpos = xpos;
        }
        public void set_ypos(int ypos){
            this.ypos = ypos;
        }


        // FUNCTIONS

        public void SetPos(int xpos, int ypos)
        {
            this.xpos = xpos;
            this.ypos = ypos;
        }
        
        public override string ToString()
        {
            return $"Type: {this.type}, X: {this.xpos}, Y: {this.ypos}";
        }
    }
}