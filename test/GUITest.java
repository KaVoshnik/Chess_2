public class GUITest {
    
    public static void main(String[] args) {
        System.out.println("Тестирование GUI компонентов...");
        
        int passed = 0;
        int failed = 0;
        
        // Тест создания GameLogic
        try {
            GameLogic gameLogic = new GameLogic();
            assert gameLogic != null : "GameLogic должен быть создан";
            assert gameLogic.getBoard() != null : "Доска должна быть создана";
            System.out.println("✓ GameLogic создается корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка создания GameLogic: " + e.getMessage());
            failed++;
        }
        
        // Тест инициализации доски
        try {
            GameLogic gameLogic = new GameLogic();
            ChessBoard board = gameLogic.getBoard();
            
            // Проверяем, что фигуры на своих местах
            assert board.getPiece(0, 0) != null : "Черная ладья должна быть на (0,0)";
            assert board.getPiece(7, 0) != null : "Белая ладья должна быть на (7,0)";
            assert board.getPiece(0, 4) != null : "Черный король должен быть на (0,4)";
            assert board.getPiece(7, 4) != null : "Белый король должен быть на (7,4)";
            
            System.out.println("✓ Доска инициализируется корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка инициализации доски: " + e.getMessage());
            failed++;
        }
        
        // Тест валидного хода
        try {
            GameLogic gameLogic = new GameLogic();
            boolean result = gameLogic.makeMove(6, 0, 5, 0); // Белая пешка
            assert result : "Валидный ход должен быть выполнен";
            assert gameLogic.getCurrentPlayer() == Core.Color.BLACK : "Ход должен поменяться";
            
            System.out.println("✓ Валидный ход выполняется корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка выполнения хода: " + e.getMessage());
            failed++;
        }
        
        // Тест невалидного хода
        try {
            GameLogic gameLogic = new GameLogic();
            boolean result = gameLogic.makeMove(6, 0, 7, 0); // Неверный ход пешки
            assert !result : "Невалидный ход не должен быть выполнен";
            assert gameLogic.getCurrentPlayer() == Core.Color.WHITE : "Ход не должен поменяться";
            
            System.out.println("✓ Невалидный ход отклоняется корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка обработки невалидного хода: " + e.getMessage());
            failed++;
        }
        
        System.out.println("\n=== Результаты GUI тестов ===");
        System.out.println("Пройдено: " + passed);
        System.out.println("Провалено: " + failed);
        System.out.println("Всего: " + (passed + failed));
        
        if (failed == 0) {
            System.out.println("🎉 Все тесты GUI прошли успешно!");
        } else {
            System.out.println("⚠️  Некоторые тесты GUI провалились");
        }
    }
}
