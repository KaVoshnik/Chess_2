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

    public static void main(String[] args) {
        Core core = new Core();
        Position pos = core.new Position(0, 0);
        System.out.println(pos);
    }
}