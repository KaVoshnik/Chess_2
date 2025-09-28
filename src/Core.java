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
        ChessGUI gui = new ChessGUI();
        gui.setVisible(true);
    }
}