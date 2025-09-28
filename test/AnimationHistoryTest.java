public class AnimationHistoryTest {
    
    public static void main(String[] args) {
        System.out.println("Тестирование анимации и истории ходов...");
        
        int passed = 0;
        int failed = 0;
        
        // Тест создания Move
        try {
            Position from = new Position(6, 0);
            Position to = new Position(5, 0);
            Piece piece = new Piece(Core.PieceType.PAWN, Core.Color.WHITE, from);
            Move move = new Move(from, to, piece, null, 1);
            
            assert move.getFrom().equals(from) : "Позиция 'от' должна совпадать";
            assert move.getTo().equals(to) : "Позиция 'до' должна совпадать";
            assert move.getPiece().equals(piece) : "Фигура должна совпадать";
            assert move.getMoveNumber() == 1 : "Номер хода должен быть 1";
            assert !move.getNotation().isEmpty() : "Нотация не должна быть пустой";
            
            System.out.println("✓ Создание Move работает корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка создания Move: " + e.getMessage());
            failed++;
        }
        
        // Тест истории ходов в GameLogic
        try {
            GameLogic gameLogic = new GameLogic();
            assert gameLogic.getMoveHistory().isEmpty() : "История должна быть пустой в начале";
            assert gameLogic.getMoveCount() == 0 : "Счетчик ходов должен быть 0";
            assert gameLogic.getLastMove() == null : "Последний ход должен быть null";
            
            System.out.println("✓ Инициализация истории ходов работает корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка инициализации истории: " + e.getMessage());
            failed++;
        }
        
        // Тест сохранения хода в историю
        try {
            GameLogic gameLogic = new GameLogic();
            boolean result = gameLogic.makeMove(6, 0, 5, 0); // Белая пешка
            
            assert result : "Ход должен быть выполнен";
            assert gameLogic.getMoveCount() == 1 : "Счетчик ходов должен быть 1";
            assert !gameLogic.getMoveHistory().isEmpty() : "История не должна быть пустой";
            assert gameLogic.getLastMove() != null : "Последний ход не должен быть null";
            
            Move lastMove = gameLogic.getLastMove();
            assert lastMove.getMoveNumber() == 1 : "Номер последнего хода должен быть 1";
            assert lastMove.getPiece().getType() == Core.PieceType.PAWN : "Тип фигуры должен быть PAWN";
            
            System.out.println("✓ Сохранение хода в историю работает корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка сохранения хода: " + e.getMessage());
            failed++;
        }
        
        // Тест нескольких ходов
        try {
            GameLogic gameLogic = new GameLogic();
            
            // Делаем несколько ходов
            gameLogic.makeMove(6, 0, 5, 0); // Белая пешка
            gameLogic.makeMove(1, 0, 2, 0); // Черная пешка
            gameLogic.makeMove(7, 1, 5, 2); // Белый конь
            
            assert gameLogic.getMoveCount() == 3 : "Счетчик ходов должен быть 3";
            assert gameLogic.getMoveHistory().size() == 3 : "Размер истории должен быть 3";
            
            Move lastMove = gameLogic.getLastMove();
            assert lastMove.getMoveNumber() == 3 : "Номер последнего хода должен быть 3";
            assert lastMove.getPiece().getType() == Core.PieceType.KNIGHT : "Тип последней фигуры должен быть KNIGHT";
            
            System.out.println("✓ Несколько ходов сохраняются корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка нескольких ходов: " + e.getMessage());
            failed++;
        }
        
        // Тест нотации ходов
        try {
            GameLogic gameLogic = new GameLogic();
            gameLogic.makeMove(6, 0, 5, 0); // Белая пешка a2-a3
            
            Move move = gameLogic.getLastMove();
            String notation = move.getNotation();
            assert notation.contains("a2") : "Нотация должна содержать a2";
            assert notation.contains("a3") : "Нотация должна содержать a3";
            assert notation.contains("1.") : "Нотация должна содержать номер хода";
            
            System.out.println("✓ Нотация ходов работает корректно");
            passed++;
        } catch (Exception e) {
            System.out.println("✗ Ошибка нотации ходов: " + e.getMessage());
            failed++;
        }
        
        System.out.println("\n=== Результаты тестов анимации и истории ===");
        System.out.println("Пройдено: " + passed);
        System.out.println("Провалено: " + failed);
        System.out.println("Всего: " + (passed + failed));
        
        if (failed == 0) {
            System.out.println("🎉 Все тесты анимации и истории прошли успешно!");
            System.out.println("✨ Chess 2 с анимацией и историей ходов готов!");
        } else {
            System.out.println("⚠️  Некоторые тесты провалились");
        }
    }
}
