public class SimpleTestRunner {
    
    public static void main(String[] args) {
        System.out.println("Запуск тестов шахмат...");
        
        int passed = 0;
        int failed = 0;
        
        // Тесты Position
        System.out.println("\n=== Тесты Position ===");
        passed += testPositionCreation();
        passed += testPositionEquality();
        passed += testPositionToString();
        
        // Тесты Piece
        System.out.println("\n=== Тесты Piece ===");
        passed += testPieceCreation();
        passed += testPiecePositionUpdate();
        passed += testPieceToString();
        
        // Тесты ChessBoard
        System.out.println("\n=== Тесты ChessBoard ===");
        passed += testChessBoardCreation();
        passed += testSetAndGetPiece();
        passed += testInitializeStandard();
        
        // Тесты GameLogic
        System.out.println("\n=== Тесты GameLogic ===");
        passed += testGameLogicCreation();
        passed += testValidPawnMove();
        passed += testInvalidPawnMove();
        
        System.out.println("\n=== Результаты ===");
        System.out.println("Пройдено: " + passed);
        System.out.println("Провалено: " + failed);
        System.out.println("Всего: " + (passed + failed));
    }
    
    // Тесты Position
    private static int testPositionCreation() {
        try {
            Position pos = new Position(3, 4);
            assert pos.getX() == 3 : "X координата должна быть 3";
            assert pos.getY() == 4 : "Y координата должна быть 4";
            System.out.println("✓ testPositionCreation");
            return 1;
        } catch (Exception e) {
            System.out.println("✗ testPositionCreation: " + e.getMessage());
            return 0;
        }
    }
    
    private static int testPositionEquality() {
        try {
            Position pos1 = new Position(2, 3);
            Position pos2 = new Position(2, 3);
            Position pos3 = new Position(3, 2);
            
            assert pos1.equals(pos2) : "Позиции должны быть равны";
            assert !pos1.equals(pos3) : "Позиции не должны быть равны";
            assert pos1.hashCode() == pos2.hashCode() : "Хеш-коды должны быть равны";
            System.out.println("✓ testPositionEquality");
            return 1;
        } catch (Exception e) {
            System.out.println("✗ testPositionEquality: " + e.getMessage());
            return 0;
        }
    }
    
    private static int testPositionToString() {
        try {
            Position pos = new Position(5, 7);
            assert pos.toString().equals("(5, 7)") : "toString должен возвращать (5, 7)";
            System.out.println("✓ testPositionToString");
            return 1;
        } catch (Exception e) {
            System.out.println("✗ testPositionToString: " + e.getMessage());
            return 0;
        }
    }
    
    // Тесты Piece
    private static int testPieceCreation() {
        try {
            Position pos = new Position(1, 2);
            Piece piece = new Piece(Core.PieceType.PAWN, Core.Color.WHITE, pos);
            
            assert piece.getType() == Core.PieceType.PAWN : "Тип должен быть PAWN";
            assert piece.getColor() == Core.Color.WHITE : "Цвет должен быть WHITE";
            assert piece.getPosition().equals(pos) : "Позиция должна совпадать";
            System.out.println("✓ testPieceCreation");
            return 1;
        } catch (Exception e) {
            System.out.println("✗ testPieceCreation: " + e.getMessage());
            return 0;
        }
    }
    
    private static int testPiecePositionUpdate() {
        try {
            Position pos1 = new Position(1, 2);
            Position pos2 = new Position(3, 4);
            Piece piece = new Piece(Core.PieceType.ROOK, Core.Color.BLACK, pos1);
            
            assert piece.getPosition().equals(pos1) : "Начальная позиция должна совпадать";
            piece.setPosition(pos2);
            assert piece.getPosition().equals(pos2) : "Обновленная позиция должна совпадать";
            System.out.println("✓ testPiecePositionUpdate");
            return 1;
        } catch (Exception e) {
            System.out.println("✗ testPiecePositionUpdate: " + e.getMessage());
            return 0;
        }
    }
    
    private static int testPieceToString() {
        try {
            Position pos = new Position(2, 3);
            Piece piece = new Piece(Core.PieceType.QUEEN, Core.Color.WHITE, pos);
            String expected = "Piece{type = QUEEN, color = WHITE, position = (2, 3)}";
            assert piece.toString().equals(expected) : "toString должен возвращать правильную строку";
            System.out.println("✓ testPieceToString");
            return 1;
        } catch (Exception e) {
            System.out.println("✗ testPieceToString: " + e.getMessage());
            return 0;
        }
    }
    
    // Тесты ChessBoard
    private static int testChessBoardCreation() {
        try {
            ChessBoard board = new ChessBoard();
            assert board != null : "Доска должна быть создана";
            System.out.println("✓ testChessBoardCreation");
            return 1;
        } catch (Exception e) {
            System.out.println("✗ testChessBoardCreation: " + e.getMessage());
            return 0;
        }
    }
    
    private static int testSetAndGetPiece() {
        try {
            ChessBoard board = new ChessBoard();
            Position pos = new Position(3, 4);
            Piece piece = new Piece(Core.PieceType.PAWN, Core.Color.WHITE, pos);
            
            board.setPiece(piece, 3, 4);
            Piece retrievedPiece = board.getPiece(3, 4);
            
            assert retrievedPiece.equals(piece) : "Полученная фигура должна совпадать с установленной";
            System.out.println("✓ testSetAndGetPiece");
            return 1;
        } catch (Exception e) {
            System.out.println("✗ testSetAndGetPiece: " + e.getMessage());
            return 0;
        }
    }
    
    private static int testInitializeStandard() {
        try {
            ChessBoard board = new ChessBoard();
            board.initializeStandard();
            
            // Проверяем, что черные фигуры вверху (строки 0-1)
            assert board.getPiece(0, 0) != null : "Черная ладья должна быть на (0,0)";
            assert board.getPiece(0, 4) != null : "Черный король должен быть на (0,4)";
            assert board.getPiece(1, 0) != null : "Черная пешка должна быть на (1,0)";
            
            // Проверяем, что белые фигуры внизу (строки 6-7)
            assert board.getPiece(7, 0) != null : "Белая ладья должна быть на (7,0)";
            assert board.getPiece(7, 4) != null : "Белый король должен быть на (7,4)";
            assert board.getPiece(6, 0) != null : "Белая пешка должна быть на (6,0)";
            
            // Проверяем цвета
            assert board.getPiece(0, 0).getColor() == Core.Color.BLACK : "Фигура на (0,0) должна быть черной";
            assert board.getPiece(7, 0).getColor() == Core.Color.WHITE : "Фигура на (7,0) должна быть белой";
            
            System.out.println("✓ testInitializeStandard");
            return 1;
        } catch (Exception e) {
            System.out.println("✗ testInitializeStandard: " + e.getMessage());
            return 0;
        }
    }
    
    // Тесты GameLogic
    private static int testGameLogicCreation() {
        try {
            GameLogic gameLogic = new GameLogic();
            assert gameLogic.getBoard() != null : "Доска должна быть создана";
            assert gameLogic.getCurrentPlayer() == Core.Color.WHITE : "Первым должен ходить белый";
            assert !gameLogic.isGameOver() : "Игра не должна быть окончена";
            System.out.println("✓ testGameLogicCreation");
            return 1;
        } catch (Exception e) {
            System.out.println("✗ testGameLogicCreation: " + e.getMessage());
            return 0;
        }
    }
    
    private static int testValidPawnMove() {
        try {
            GameLogic gameLogic = new GameLogic();
            
            // Тестируем ход белой пешки вперед
            boolean result = gameLogic.makeMove(6, 0, 5, 0);
            assert result : "Ход белой пешки должен быть валидным";
            assert gameLogic.getCurrentPlayer() == Core.Color.BLACK : "После хода белых должен ходить черный";
            
            System.out.println("✓ testValidPawnMove");
            return 1;
        } catch (Exception e) {
            System.out.println("✗ testValidPawnMove: " + e.getMessage());
            return 0;
        }
    }
    
    private static int testInvalidPawnMove() {
        try {
            GameLogic gameLogic = new GameLogic();
            
            // Неверный ход - пешка не может ходить назад
            boolean result = gameLogic.makeMove(6, 0, 7, 0);
            assert !result : "Ход пешки назад должен быть невалидным";
            assert gameLogic.getCurrentPlayer() == Core.Color.WHITE : "Ход не должен поменяться";
            
            System.out.println("✓ testInvalidPawnMove");
            return 1;
        } catch (Exception e) {
            System.out.println("✗ testInvalidPawnMove: " + e.getMessage());
            return 0;
        }
    }
}
