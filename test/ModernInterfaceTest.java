public class ModernInterfaceTest {
    
    public static void main(String[] args) {
        System.out.println("Тестирование современного интерфейса...");
        
        int passed = 0;
        int failed = 0;
        
        // Тест создания MainMenu
        try {
            MainMenu menu = new MainMenu();
            assert menu != null : "MainMenu должен быть создан";
            System.out.println("✓ MainMenu создается корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка создания MainMenu: " + e.getMessage());
            failed++;
        }
        
        // Тест создания ChessGame
        try {
            ChessGame game = new ChessGame();
            assert game != null : "ChessGame должен быть создан";
            System.out.println("✓ ChessGame создается корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка создания ChessGame: " + e.getMessage());
            failed++;
        }
        
        // Тест GameLogic в новом интерфейсе
        try {
            GameLogic gameLogic = new GameLogic();
            assert gameLogic != null : "GameLogic должен быть создан";
            assert gameLogic.getBoard() != null : "Доска должна быть создана";
            assert gameLogic.getCurrentPlayer() == Core.Color.WHITE : "Первым должен ходить белый";
            
            System.out.println("✓ GameLogic работает в новом интерфейсе");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка GameLogic в новом интерфейсе: " + e.getMessage());
            failed++;
        }
        
        // Тест валидного хода в новом интерфейсе
        try {
            GameLogic gameLogic = new GameLogic();
            boolean result = gameLogic.makeMove(6, 0, 5, 0); // Белая пешка
            assert result : "Валидный ход должен быть выполнен";
            assert gameLogic.getCurrentPlayer() == Core.Color.BLACK : "Ход должен поменяться";
            
            System.out.println("✓ Валидный ход работает в новом интерфейсе");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка валидного хода: " + e.getMessage());
            failed++;
        }
        
        System.out.println("\n=== Результаты тестов современного интерфейса ===");
        System.out.println("Пройдено: " + passed);
        System.out.println("Провалено: " + failed);
        System.out.println("Всего: " + (passed + failed));
        
        if (failed == 0) {
            System.out.println("🎉 Все тесты современного интерфейса прошли успешно!");
            System.out.println("✨ Новый интерфейс готов к использованию!");
        } else {
            System.out.println("⚠️  Некоторые тесты провалились");
        }
    }
}
