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
        ChessBoard board = new ChessBoard();
        board.initializeStandard();
        System.out.println(board.getPiece(0, 0));
    }
}