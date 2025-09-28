import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    
    @Test
    public void testPieceCreation() {
        Position pos = new Position(1, 2);
        Piece piece = new Piece(Core.PieceType.PAWN, Core.Color.WHITE, pos);
        
        assertEquals(Core.PieceType.PAWN, piece.getType());
        assertEquals(Core.Color.WHITE, piece.getColor());
        assertEquals(pos, piece.getPosition());
    }
    
    @Test
    public void testPieceCreationWithNullType() {
        Position pos = new Position(1, 2);
        assertThrows(IllegalArgumentException.class, () -> {
            new Piece(null, Core.Color.WHITE, pos);
        });
    }
    
    @Test
    public void testPieceCreationWithNullColor() {
        Position pos = new Position(1, 2);
        assertThrows(IllegalArgumentException.class, () -> {
            new Piece(Core.PieceType.PAWN, null, pos);
        });
    }
    
    @Test
    public void testPieceCreationWithNullPosition() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Piece(Core.PieceType.PAWN, Core.Color.WHITE, null);
        });
    }
    
    @Test
    public void testPiecePositionUpdate() {
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(3, 4);
        Piece piece = new Piece(Core.PieceType.ROOK, Core.Color.BLACK, pos1);
        
        assertEquals(pos1, piece.getPosition());
        piece.setPosition(pos2);
        assertEquals(pos2, piece.getPosition());
    }
    
    @Test
    public void testPieceToString() {
        Position pos = new Position(2, 3);
        Piece piece = new Piece(Core.PieceType.QUEEN, Core.Color.WHITE, pos);
        String expected = "Piece{type = QUEEN, color = WHITE, position = (2, 3)}";
        assertEquals(expected, piece.toString());
    }
    
    @Test
    public void testAllPieceTypes() {
        Position pos = new Position(0, 0);
        
        Piece pawn = new Piece(Core.PieceType.PAWN, Core.Color.WHITE, pos);
        assertEquals(Core.PieceType.PAWN, pawn.getType());
        
        Piece rook = new Piece(Core.PieceType.ROOK, Core.Color.WHITE, pos);
        assertEquals(Core.PieceType.ROOK, rook.getType());
        
        Piece knight = new Piece(Core.PieceType.KNIGHT, Core.Color.WHITE, pos);
        assertEquals(Core.PieceType.KNIGHT, knight.getType());
        
        Piece bishop = new Piece(Core.PieceType.BISHOP, Core.Color.WHITE, pos);
        assertEquals(Core.PieceType.BISHOP, bishop.getType());
        
        Piece queen = new Piece(Core.PieceType.QUEEN, Core.Color.WHITE, pos);
        assertEquals(Core.PieceType.QUEEN, queen.getType());
        
        Piece king = new Piece(Core.PieceType.KING, Core.Color.WHITE, pos);
        assertEquals(Core.PieceType.KING, king.getType());
    }
}
