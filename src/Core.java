class Core {
    public static enum Color {
        WHITE,
        BLACK
    }

    public static enum PieceType {
        PAWN,
        KNIGHT,
        BISHOP,
        ROOK,
        QUEEN,
        KING
    }

    public static void main(String[] args) {
        Position pos = new Position(0, 1);
        Piece piece = new Piece(PieceType.PAWN, Color.WHITE, pos);
        System.out.println(piece);
    }
}