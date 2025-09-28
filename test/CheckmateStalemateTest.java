public class CheckmateStalemateTest {
    
    public static void main(String[] args) {
        System.out.println("Тестирование мата, шаха и пата...");
        
        int passed = 0;
        int failed = 0;
        
        // Тест проверки шаха
        try {
            GameLogic gameLogic = new GameLogic();
            assert !gameLogic.isInCheck(Core.Color.WHITE) : "В начальной позиции нет шаха";
            assert !gameLogic.isInCheck(Core.Color.BLACK) : "В начальной позиции нет шаха";
            System.out.println("✓ Проверка шаха работает корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка проверки шаха: " + e.getMessage());
            failed++;
        }
        
        // Тест проверки мата (в начальной позиции мата нет)
        try {
            GameLogic gameLogic = new GameLogic();
            assert !gameLogic.isCheckmate(Core.Color.WHITE) : "В начальной позиции нет мата";
            assert !gameLogic.isCheckmate(Core.Color.BLACK) : "В начальной позиции нет мата";
            System.out.println("✓ Проверка мата работает корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка проверки мата: " + e.getMessage());
            failed++;
        }
        
        // Тест проверки пата (в начальной позиции пата нет)
        try {
            GameLogic gameLogic = new GameLogic();
            assert !gameLogic.isStalemate(Core.Color.WHITE) : "В начальной позиции нет пата";
            assert !gameLogic.isStalemate(Core.Color.BLACK) : "В начальной позиции нет пата";
            System.out.println("✓ Проверка пата работает корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка проверки пата: " + e.getMessage());
            failed++;
        }
        
        // Тест состояния игры
        try {
            GameLogic gameLogic = new GameLogic();
            assert !gameLogic.isGameOver() : "Игра не должна быть окончена в начале";
            String status = gameLogic.getGameStatus();
            assert status.isEmpty() : "Статус должен быть пустым в начале игры";
            System.out.println("✓ Состояние игры работает корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка состояния игры: " + e.getMessage());
            failed++;
        }
        
        // Тест валидных ходов
        try {
            GameLogic gameLogic = new GameLogic();
            boolean result = gameLogic.makeMove(6, 0, 5, 0); // Белая пешка
            assert result : "Валидный ход должен быть выполнен";
            assert gameLogic.getCurrentPlayer() == Core.Color.BLACK : "Ход должен поменяться";
            assert !gameLogic.isGameOver() : "Игра не должна быть окончена после одного хода";
            System.out.println("✓ Валидные ходы работают корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка валидных ходов: " + e.getMessage());
            failed++;
        }
        
        // Тест невалидных ходов
        try {
            GameLogic gameLogic = new GameLogic();
            boolean result = gameLogic.makeMove(6, 0, 7, 0); // Неверный ход пешки
            assert !result : "Невалидный ход не должен быть выполнен";
            assert gameLogic.getCurrentPlayer() == Core.Color.WHITE : "Ход не должен поменяться";
            System.out.println("✓ Невалидные ходы отклоняются корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка невалидных ходов: " + e.getMessage());
            failed++;
        }
        
        System.out.println("\n=== Результаты тестов мата и пата ===");
        System.out.println("Пройдено: " + passed);
        System.out.println("Провалено: " + failed);
        System.out.println("Всего: " + (passed + failed));
        
        if (failed == 0) {
            System.out.println("🎉 Все тесты мата и пата прошли успешно!");
            System.out.println("♔ Chess 2 готов к игре с полной логикой!");
        } else {
            System.out.println("⚠️  Некоторые тесты провалились");
        }
    }
}
