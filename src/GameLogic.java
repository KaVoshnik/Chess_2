import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private ChessBoard board;
    private Core.Color currentPlayer;
    private boolean gameOver;
    
    public GameLogic() {
        this.board = new ChessBoard();
        this.board.initializeStandard();
        this.currentPlayer = Core.Color.WHITE;
        this.gameOver = false;
    }
    
    public ChessBoard getBoard() {
        return board;
    }
    
    public Core.Color getCurrentPlayer() {
        return currentPlayer;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public String getGameStatus() {
        if (gameOver) {
            Core.Color opponentColor = (currentPlayer == Core.Color.WHITE) ? Core.Color.BLACK : Core.Color.WHITE;
            if (isCheckmate(currentPlayer)) {
                return "МАТ! Победили " + (opponentColor == Core.Color.WHITE ? "Белые" : "Черные");
            } else if (isStalemate(currentPlayer)) {
                return "ПАТ! Ничья";
            }
        }
        return "";
    }
    
    public boolean isCheckmate(Core.Color color) {
        if (!isInCheck(color)) {
            return false;
        }
        
        // Проверяем, есть ли хотя бы один валидный ход
        return !hasValidMoves(color);
    }
    
    public boolean isStalemate(Core.Color color) {
        if (isInCheck(color)) {
            return false;
        }
        
        // Проверяем, есть ли хотя бы один валидный ход
        return !hasValidMoves(color);
    }
    
    private boolean hasValidMoves(Core.Color color) {
        for (int fromX = 0; fromX < 8; fromX++) {
            for (int fromY = 0; fromY < 8; fromY++) {
                Piece piece = board.getPiece(fromX, fromY);
                if (piece != null && piece.getColor() == color) {
                    for (int toX = 0; toX < 8; toX++) {
                        for (int toY = 0; toY < 8; toY++) {
                            if (isValidMove(piece, toX, toY)) {
                                // Проверяем, не ставит ли этот ход короля под шах
                                if (wouldMoveBeValid(fromX, fromY, toX, toY)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean wouldMoveBeValid(int fromX, int fromY, int toX, int toY) {
        Piece piece = board.getPiece(fromX, fromY);
        if (piece == null) {
            return false;
        }
        
        // Сохраняем текущее состояние
        Piece capturedPiece = board.getPiece(toX, toY);
        board.setPiece(null, fromX, fromY);
        piece.setPosition(new Position(toX, toY));
        board.setPiece(piece, toX, toY);
        
        // Проверяем, не поставили ли мы себя под шах
        boolean inCheck = isInCheck(piece.getColor());
        
        // Восстанавливаем состояние
        board.setPiece(piece, fromX, fromY);
        piece.setPosition(new Position(fromX, fromY));
        board.setPiece(capturedPiece, toX, toY);
        
        return !inCheck;
    }
    
    public boolean isInCheck(Core.Color color) {
        // Находим короля указанного цвета
        Position kingPos = findKing(color);
        if (kingPos == null) {
            return false;
        }
        
        // Проверяем, атакует ли какая-либо фигура противника короля
        Core.Color opponentColor = (color == Core.Color.WHITE) ? Core.Color.BLACK : Core.Color.WHITE;
        
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = board.getPiece(x, y);
                if (piece != null && piece.getColor() == opponentColor) {
                    if (isValidMoveForPieceType(piece, kingPos.getX(), kingPos.getY())) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private Position findKing(Core.Color color) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = board.getPiece(x, y);
                if (piece != null && piece.getType() == Core.PieceType.KING && piece.getColor() == color) {
                    return piece.getPosition();
                }
            }
        }
        return null;
    }
    
    public boolean makeMove(int fromX, int fromY, int toX, int toY) {
        Piece piece = board.getPiece(fromX, fromY);
        if (piece == null || piece.getColor() != currentPlayer) {
            return false;
        }
        
        if (!isValidMove(piece, toX, toY)) {
            return false;
        }
        
        // Выполняем ход
        Piece capturedPiece = board.getPiece(toX, toY);
        board.setPiece(null, fromX, fromY);
        piece.setPosition(new Position(toX, toY));
        board.setPiece(piece, toX, toY);
        
        // Проверяем, не поставили ли мы себя под шах
        Core.Color playerWhoMoved = currentPlayer;
        if (isInCheck(playerWhoMoved)) {
            // Отменяем ход
            board.setPiece(piece, fromX, fromY);
            piece.setPosition(new Position(fromX, fromY));
            board.setPiece(capturedPiece, toX, toY);
            return false;
        }
        
        // Меняем игрока
        currentPlayer = (currentPlayer == Core.Color.WHITE) ? Core.Color.BLACK : Core.Color.WHITE;
        
        // Проверяем мат и пат для нового игрока
        if (isCheckmate(currentPlayer)) {
            gameOver = true;
        } else if (isStalemate(currentPlayer)) {
            gameOver = true;
        }
        
        return true;
    }
    
    private boolean isValidMove(Piece piece, int toX, int toY) {
        // Проверяем, что целевая позиция в пределах доски
        if (toX < 0 || toX > 7 || toY < 0 || toY > 7) {
            return false;
        }
        
        // Проверяем, что не ходим на свою фигуру
        Piece targetPiece = board.getPiece(toX, toY);
        if (targetPiece != null && targetPiece.getColor() == piece.getColor()) {
            return false;
        }
        
        // Проверяем правила движения для каждого типа фигуры
        return isValidMoveForPieceType(piece, toX, toY);
    }
    
    private boolean isValidMoveForPieceType(Piece piece, int toX, int toY) {
        int fromX = piece.getPosition().getX();
        int fromY = piece.getPosition().getY();
        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);
        
        switch (piece.getType()) {
            case PAWN:
                return isValidPawnMove(piece, toX, toY);
            case ROOK:
                return isValidRookMove(fromX, fromY, toX, toY);
            case KNIGHT:
                return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
            case BISHOP:
                return deltaX == deltaY && isPathClear(fromX, fromY, toX, toY);
            case QUEEN:
                return (deltaX == deltaY || deltaX == 0 || deltaY == 0) && isPathClear(fromX, fromY, toX, toY);
            case KING:
                return deltaX <= 1 && deltaY <= 1;
            default:
                return false;
        }
    }
    
    private boolean isValidPawnMove(Piece piece, int toX, int toY) {
        int fromX = piece.getPosition().getX();
        int fromY = piece.getPosition().getY();
        int direction = (piece.getColor() == Core.Color.WHITE) ? -1 : 1;
        
        // Обычный ход вперед
        if (toX == fromX + direction && toY == fromY && board.getPiece(toX, toY) == null) {
            return true;
        }
        
        // Первый ход пешки (на 2 клетки)
        int startRow = (piece.getColor() == Core.Color.WHITE) ? 6 : 1;
        if (fromX == startRow && toX == fromX + 2 * direction && toY == fromY && board.getPiece(toX, toY) == null) {
            return true;
        }
        
        // Взятие по диагонали
        if (toX == fromX + direction && Math.abs(toY - fromY) == 1 && board.getPiece(toX, toY) != null) {
            return true;
        }
        
        return false;
    }
    
    private boolean isValidRookMove(int fromX, int fromY, int toX, int toY) {
        return (fromX == toX || fromY == toY) && isPathClear(fromX, fromY, toX, toY);
    }
    
    private boolean isPathClear(int fromX, int fromY, int toX, int toY) {
        int deltaX = Integer.compare(toX, fromX);
        int deltaY = Integer.compare(toY, fromY);
        
        int currentX = fromX + deltaX;
        int currentY = fromY + deltaY;
        
        while (currentX != toX || currentY != toY) {
            if (board.getPiece(currentX, currentY) != null) {
                return false;
            }
            currentX += deltaX;
            currentY += deltaY;
        }
        
        return true;
    }
}
