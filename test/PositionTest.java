import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    
    @Test
    public void testPositionCreation() {
        Position pos = new Position(3, 4);
        assertEquals(3, pos.getX());
        assertEquals(4, pos.getY());
    }
    
    @Test
    public void testPositionEquality() {
        Position pos1 = new Position(2, 3);
        Position pos2 = new Position(2, 3);
        Position pos3 = new Position(3, 2);
        
        assertEquals(pos1, pos2);
        assertNotEquals(pos1, pos3);
        assertEquals(pos1.hashCode(), pos2.hashCode());
    }
    
    @Test
    public void testPositionToString() {
        Position pos = new Position(5, 7);
        assertEquals("(5, 7)", pos.toString());
    }
    
    @Test
    public void testPositionWithNegativeValues() {
        Position pos = new Position(-1, -2);
        assertEquals(-1, pos.getX());
        assertEquals(-2, pos.getY());
    }
}
