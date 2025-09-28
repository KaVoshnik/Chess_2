/**
 * Класс для хранения информации о шахматном ходе
 */
public class Move {
    private final Position from;
    private final Position to;
    private final Piece piece;
    private final Piece capturedPiece;
    private final String notation;
    private final int moveNumber;
    
    public Move(Position from, Position to, Piece piece, Piece capturedPiece, int moveNumber) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.capturedPiece = capturedPiece;
        this.moveNumber = moveNumber;
        this.notation = generateNotation();
    }
    
    public Position getFrom() {
        return from;
    }
    
    public Position getTo() {
        return to;
    }
    
    public Piece getPiece() {
        return piece;
    }
    
    public Piece getCapturedPiece() {
        return capturedPiece;
    }
    
    public String getNotation() {
        return notation;
    }
    
    public int getMoveNumber() {
        return moveNumber;
    }
    
    private String generateNotation() {
        StringBuilder notation = new StringBuilder();
        
        // Добавляем номер хода
        notation.append(moveNumber).append(". ");
        
        // Добавляем символ фигуры
        switch (piece.getType()) {
            case KING:
                notation.append("K");
                break;
            case QUEEN:
                notation.append("Q");
                break;
            case ROOK:
                notation.append("R");
                break;
            case BISHOP:
                notation.append("B");
                break;
            case KNIGHT:
                notation.append("N");
                break;
            case PAWN:
                // Для пешки символ не добавляем
                break;
        }
        
        // Добавляем координаты
        notation.append(getSquareName(from)).append("-").append(getSquareName(to));
        
        // Если была взята фигура
        if (capturedPiece != null) {
            notation.append("x");
        }
        
        return notation.toString();
    }
    
    private String getSquareName(Position pos) {
        char file = (char) ('a' + pos.getY());
        int rank = 8 - pos.getX();
        return file + String.valueOf(rank);
    }
    
    @Override
    public String toString() {
        return notation;
    }
}
