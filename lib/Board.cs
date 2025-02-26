using Piece;

namespace Board
{
    public class board
    {
        private int[] start_pos = new int[2];
        private List<piece> content = new List<piece>();

        public board(){
            start_pos[0] = 0;
            start_pos[1] = 0;
        }

        public board(int[] StartPos){
            start_pos[0] = StartPos[0];
            start_pos[1] = StartPos[1];
        }

        public int[] get_start_pos(){
            return start_pos;
        }

        public List<piece> get_content(){
            return content;
        }
        public void set_content(List<piece> Content){
            content = Content;
        }
        public void set_start_pos(int[] StartPos){
            start_pos[0] = StartPos[0];
            start_pos[1] = StartPos[1];
        }

        public void add_content(piece Content)
        {
            content.Add(Content);
        }

        public piece? get_piece_by_cords(int x, int y)
        {
             return content.Find(p => p.get_xpos() == x && p.get_ypos() == y);
        }

        private int? search_id_by_cords(int x, int y){
            for(int i = 0; i < content.Length; i++){
                if(content[i].get_xpos() == x && content[i].get_ypos() == y){
                    return i;
                }
            }
            return null;
        }
        public bool remove_by_cords(int x, int y)
        {
            int index = content.FindIndex(p => p.get_xpos() == x && p.get_ypos() == y);

            if (index == -1)
            {
                return false;
            }

            content.RemoveAt(index);
            return true;
        }

        public void print_field(int start_x, int start_y, int size_x, int size_y){
            /*
            y
            2
            1 
            0 1 2 x
            */
            for(int i = start_x + size_y - 1; i >= start_x; i--){
                for(int j = start_y; j < start_y + size_x; j++){
                    piece? piece = get_piece_by_cords(j, i);

                    if(piece != null){
                        switch(piece.get_type()){
                        case("pawn"):
                            Console.Write("p ");
                            break;
                        default:
                            Console.Write("E ");
                            break;
                        }
                    }
                    else{
                        Console.Write(". ");
                    }
                }
                Console.Write("\n");
            }
            Console.Write("\n");
        }

        public void print_field_info(int start_x, int start_y, int size_x, int size_y){
            Console.WriteLine("\nXstart: " + start_x + " Ystart: " + start_y + " Xends: " + (start_x + size_x - 1) + " Yends: " + (start_y + size_y - 1));
            print_field(start_x, start_y, size_x, size_y);
        }

        public void create_piece(string type, int x, int y){
            add_content(new piece(type, x, y));
        }
    }
}