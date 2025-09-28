import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {
    
    @Test
    public void testGameLogicCreation() {
        GameLogic gameLogic = new GameLogic();
        assertNotNull(gameLogic.getBoard());
        assertEquals(Core.Color.WHITE, gameLogic.getCurrentPlayer());
        assertFalse(gameLogic.isGameOver());
    }
    
    @Test
    public void testValidPawnMove() {
        GameLogic gameLogic = new GameLogic();
        
        // Тестируем ход белой пешки вперед
        assertTrue(gameLogic.makeMove(6, 0, 5, 0)); // Белая пешка с e2 на e3
        assertEquals(Core.Color.BLACK, gameLogic.getCurrentPlayer());
        
        // Тестируем ход черной пешки вперед
        assertTrue(gameLogic.makeMove(1, 0, 2, 0)); // Черная пешка с a7 на a6
        assertEquals(Core.Color.WHITE, gameLogic.getCurrentPlayer());
    }
    
    @Test
    public void testInvalidPawnMove() {
        GameLogic gameLogic = new GameLogic();
        
        // Неверный ход - пешка не может ходить назад
        assertFalse(gameLogic.makeMove(6, 0, 7, 0));
        assertEquals(Core.Color.WHITE, gameLogic.getCurrentPlayer()); // Ход не должен поменяться
        
        // Неверный ход - пешка не может ходить по диагонали без взятия
        assertFalse(gameLogic.makeMove(6, 0, 5, 1));
    }
    
    @Test
    public void testValidKnightMove() {
        GameLogic gameLogic = new GameLogic();
        
        // Тестируем ход коня
        assertTrue(gameLogic.makeMove(7, 1, 5, 2)); // Конь с b1 на c3
        assertEquals(Core.Color.BLACK, gameLogic.getCurrentPlayer());
    }
    
    @Test
    public void testInvalidKnightMove() {
        GameLogic gameLogic = new GameLogic();
        
        // Неверный ход коня
        assertFalse(gameLogic.makeMove(7, 1, 5, 1)); // Конь не может ходить прямо
    }
    
    @Test
    public void testValidRookMove() {
        GameLogic gameLogic = new GameLogic();
        
        // Сначала делаем ход пешкой, чтобы освободить путь ладье
        gameLogic.makeMove(6, 0, 5, 0);
        gameLogic.makeMove(1, 0, 2, 0);
        
        // Теперь ладья может ходить
        assertTrue(gameLogic.makeMove(7, 0, 6, 0)); // Ладья с a1 на a2
    }
    
    @Test
    public void testInvalidRookMove() {
        GameLogic gameLogic = new GameLogic();
        
        // Ладья не может ходить по диагонали
        assertFalse(gameLogic.makeMove(7, 0, 6, 1));
        
        // Ладья не может перепрыгивать через фигуры
        assertFalse(gameLogic.makeMove(7, 0, 5, 0));
    }
    
    @Test
    public void testValidBishopMove() {
        GameLogic gameLogic = new GameLogic();
        
        // Сначала делаем ход пешкой, чтобы освободить путь слону
        gameLogic.makeMove(6, 2, 5, 2);
        gameLogic.makeMove(1, 2, 2, 2);
        
        // Теперь слон может ходить
        assertTrue(gameLogic.makeMove(7, 2, 5, 0)); // Слон с c1 на a3
    }
    
    @Test
    public void testValidQueenMove() {
        GameLogic gameLogic = new GameLogic();
        
        // Сначала делаем ход пешкой
        gameLogic.makeMove(6, 3, 5, 3);
        gameLogic.makeMove(1, 3, 2, 3);
        
        // Теперь ферзь может ходить
        assertTrue(gameLogic.makeMove(7, 3, 5, 3)); // Ферзь с d1 на d3
    }
    
    @Test
    public void testValidKingMove() {
        GameLogic gameLogic = new GameLogic();
        
        // Сначала делаем ход пешкой
        gameLogic.makeMove(6, 4, 5, 4);
        gameLogic.makeMove(1, 4, 2, 4);
        
        // Теперь король может ходить
        assertTrue(gameLogic.makeMove(7, 4, 6, 4)); // Король с e1 на e2
    }
    
    @Test
    public void testMoveOwnPiece() {
        GameLogic gameLogic = new GameLogic();
        
        // Нельзя ходить фигурой противника
        assertFalse(gameLogic.makeMove(1, 0, 2, 0)); // Пытаемся ходить черной пешкой, когда ход белых
        assertEquals(Core.Color.WHITE, gameLogic.getCurrentPlayer());
    }
    
    @Test
    public void testMoveToOwnPiece() {
        GameLogic gameLogic = new GameLogic();
        
        // Нельзя ходить на свою фигуру
        assertFalse(gameLogic.makeMove(6, 0, 7, 0)); // Белая пешка пытается встать на белую ладью
    }
    
    @Test
    public void testCapturePiece() {
        GameLogic gameLogic = new GameLogic();
        
        // Делаем несколько ходов, чтобы создать возможность взятия
        gameLogic.makeMove(6, 4, 4, 4); // Белая пешка e2-e4
        gameLogic.makeMove(1, 3, 3, 3); // Черная пешка d7-d5
        
        // Теперь белая пешка может взять черную
        assertTrue(gameLogic.makeMove(4, 4, 3, 3)); // Взятие пешкой
        assertEquals(Core.Color.BLACK, gameLogic.getCurrentPlayer());
    }
    
    @Test
    public void testOutOfBoundsMove() {
        GameLogic gameLogic = new GameLogic();
        
        // Нельзя ходить за пределы доски
        assertFalse(gameLogic.makeMove(6, 0, -1, 0));
        assertFalse(gameLogic.makeMove(6, 0, 8, 0));
        assertFalse(gameLogic.makeMove(6, 0, 6, -1));
        assertFalse(gameLogic.makeMove(6, 0, 6, 8));
    }
    
    @Test
    public void testEmptySquareMove() {
        GameLogic gameLogic = new GameLogic();
        
        // Нельзя ходить с пустой клетки
        assertFalse(gameLogic.makeMove(2, 0, 3, 0)); // Пустая клетка
    }
    
    @Test
    public void testIsInCheck() {
        GameLogic gameLogic = new GameLogic();
        
        // В начальной позиции нет шаха
        assertFalse(gameLogic.isInCheck(Core.Color.WHITE));
        assertFalse(gameLogic.isInCheck(Core.Color.BLACK));
    }
}
