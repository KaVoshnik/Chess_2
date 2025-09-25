class Core {
    public enum Color{
        WHITE,
        BLACK
    }

    public enum PieceType{
        PAWN,
        KNIGHT,
        BISHOP,
        ROOK,
        QUEEN,
        KING
    }

    /**
     * Класс, представляющий позицию на доске
     */
    public class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    /**
     * Класс, представляющий шахматную фигуру
     */
    public class Piece {
        public PieceType type;
        public Color color;
        public Position position;

        public Piece(PieceType type, Color color, Position position) {
            this.type = type;
            this.color = color;
            this.position = position;
        }
    
        public PieceType getType() {
            return type;
        }

        public Color getColor() {
            return color;
        }

        public Position getPosition() { 
            return position; 
        }

        @Override
        public String toString() {
            return "Piece{" + "type = " + type + ", color = " + color + ", position = " + position + '}';
        }
    }

    public static void main(String[] args) {
        Core core = new Core();
        Position pos = core.new Position(0, 1);
        Piece piece = core.new Piece(PieceType.PAWN, Color.WHITE, pos);
        System.out.println(piece);
    }
}