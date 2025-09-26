public class ChessBoard {
    private final Piece[][] pieces = new Piece[8][8];

    private void validatePosition(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException("Invalid position");
        }
    }

    public void setPiece(Piece piece, int x, int y) {
        validatePosition(x, y);
        pieces[x][y] = piece;
    }

    public Piece getPiece(int x, int y) {
        validatePosition(x, y);
        return pieces[x][y];
    }

    public void initializeStandard() {
        Core.PieceType[] backRankTypes = {
                Core.PieceType.ROOK, Core.PieceType.KNIGHT,
                Core.PieceType.BISHOP, Core.PieceType.QUEEN,
                Core.PieceType.KING, Core.PieceType.BISHOP,
                Core.PieceType.KNIGHT, Core.PieceType.ROOK
        };

        for (int y = 0; y < 8; y++) {
            Core.PieceType type = backRankTypes[y];
            Position pos = new Position(0, y);
            Piece piece = new Piece(type, Core.Color.WHITE, pos);
            setPiece(piece, 0, y);
        }

        for (int y = 0; y < 8; y++) {
            Position pos = new Position(1, y);
            Piece pawn = new Piece(Core.PieceType.PAWN, Core.Color.WHITE, pos);
            setPiece(pawn, 1, y);
        }

        for (int y = 0; y < 8; y++) {
            Core.PieceType type = backRankTypes[y];
            Position pos = new Position(7, y);
            Piece piece = new Piece(type, Core.Color.BLACK, pos);
            setPiece(piece, 7, y);
        }

        for (int y = 0; y < 8; y++) {
            Position pos = new Position(6, y);
            Piece pawn = new Piece(Core.PieceType.PAWN, Core.Color.BLACK, pos);
            setPiece(pawn, 6, y);
        }
    }
}
