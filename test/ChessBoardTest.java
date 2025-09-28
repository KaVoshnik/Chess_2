import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {
    
    @Test
    public void testChessBoardCreation() {
        ChessBoard board = new ChessBoard();
        assertNotNull(board);
    }
    
    @Test
    public void testSetAndGetPiece() {
        ChessBoard board = new ChessBoard();
        Position pos = new Position(3, 4);
        Piece piece = new Piece(Core.PieceType.PAWN, Core.Color.WHITE, pos);
        
        board.setPiece(piece, 3, 4);
        Piece retrievedPiece = board.getPiece(3, 4);
        
        assertEquals(piece, retrievedPiece);
    }
    
    @Test
    public void testSetPieceInvalidPosition() {
        ChessBoard board = new ChessBoard();
        Position pos = new Position(0, 0);
        Piece piece = new Piece(Core.PieceType.PAWN, Core.Color.WHITE, pos);
        
        assertThrows(IllegalArgumentException.class, () -> {
            board.setPiece(piece, -1, 0);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            board.setPiece(piece, 8, 0);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            board.setPiece(piece, 0, -1);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            board.setPiece(piece, 0, 8);
        });
    }
    
    @Test
    public void testGetPieceInvalidPosition() {
        ChessBoard board = new ChessBoard();
        
        assertThrows(IllegalArgumentException.class, () -> {
            board.getPiece(-1, 0);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            board.getPiece(8, 0);
        });
    }
    
    @Test
    public void testInitializeStandard() {
        ChessBoard board = new ChessBoard();
        board.initializeStandard();
        
        // Проверяем, что черные фигуры вверху (строки 0-1)
        assertNotNull(board.getPiece(0, 0)); // Черная ладья
        assertNotNull(board.getPiece(0, 4)); // Черный король
        assertNotNull(board.getPiece(1, 0)); // Черная пешка
        
        // Проверяем, что белые фигуры внизу (строки 6-7)
        assertNotNull(board.getPiece(7, 0)); // Белая ладья
        assertNotNull(board.getPiece(7, 4)); // Белый король
        assertNotNull(board.getPiece(6, 0)); // Белая пешка
        
        // Проверяем цвета
        assertEquals(Core.Color.BLACK, board.getPiece(0, 0).getColor());
        assertEquals(Core.Color.WHITE, board.getPiece(7, 0).getColor());
        
        // Проверяем типы фигур
        assertEquals(Core.PieceType.ROOK, board.getPiece(0, 0).getType());
        assertEquals(Core.PieceType.KING, board.getPiece(0, 4).getType());
        assertEquals(Core.PieceType.PAWN, board.getPiece(1, 0).getType());
    }
    
    @Test
    public void testEmptySquares() {
        ChessBoard board = new ChessBoard();
        board.initializeStandard();
        
        // Проверяем, что средние строки пустые
        assertNull(board.getPiece(2, 0));
        assertNull(board.getPiece(3, 0));
        assertNull(board.getPiece(4, 0));
        assertNull(board.getPiece(5, 0));
    }
    
    @Test
    public void testSetNullPiece() {
        ChessBoard board = new ChessBoard();
        Position pos = new Position(0, 0);
        Piece piece = new Piece(Core.PieceType.PAWN, Core.Color.WHITE, pos);
        
        board.setPiece(piece, 0, 0);
        assertNotNull(board.getPiece(0, 0));
        
        board.setPiece(null, 0, 0);
        assertNull(board.getPiece(0, 0));
    }
}
