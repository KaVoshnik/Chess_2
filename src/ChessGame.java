import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ChessGame extends JFrame {
    private GameLogic gameLogic;
    private JPanel[][] squares;
    private int selectedX = -1, selectedY = -1;
    private static final int SQUARE_SIZE = 70;
    private static final Color LIGHT_SQUARE = new Color(240, 217, 181);
    private static final Color DARK_SQUARE = new Color(181, 136, 99);
    private static final Color SELECTED_COLOR = new Color(255, 215, 0, 150);
    private static final Color POSSIBLE_MOVE_COLOR = new Color(0, 255, 0, 100);
    private static final Color BACKGROUND_COLOR = new Color(45, 45, 45);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    
    public ChessGame() {
        gameLogic = new GameLogic();
        initializeGame();
    }
    
    private void initializeGame() {
        setTitle("Chess 2 - Игра");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Создаем главную панель
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        
        // Создаем доску
        JPanel boardPanel = createBoard();
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        
        // Создаем боковую панель с информацией
        JPanel sidePanel = createSidePanel();
        mainPanel.add(sidePanel, BorderLayout.EAST);
        
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    private JPanel createBoard() {
        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        squares = new JPanel[8][8];
        
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                JPanel square = new JPanel(new BorderLayout());
                square.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                square.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
                
                // Чередование цветов клеток
                if ((x + y) % 2 == 0) {
                    square.setBackground(LIGHT_SQUARE);
                } else {
                    square.setBackground(DARK_SQUARE);
                }
                
                // Добавляем обработчик кликов
                final int finalX = x;
                final int finalY = y;
                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleSquareClick(finalX, finalY);
                    }
                    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (selectedX == -1) {
                            square.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 0), 2));
                        }
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (selectedX == -1) {
                            square.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
                        }
                    }
                });
                
                squares[x][y] = square;
                boardPanel.add(square);
            }
        }
        
        updateBoard();
        return boardPanel;
    }
    
    private JPanel createSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(200, 0));
        sidePanel.setBackground(BACKGROUND_COLOR);
        sidePanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        
        // Заголовок
        JLabel titleLabel = new JLabel("♔ ИГРА ♚");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(titleLabel);
        
        sidePanel.add(Box.createVerticalStrut(20));
        
        // Информация о текущем игроке
        JLabel currentPlayerLabel = new JLabel("Ход: Белые");
        currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        currentPlayerLabel.setForeground(TEXT_COLOR);
        currentPlayerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(currentPlayerLabel);
        
        // Информация о состоянии игры
        JLabel gameStatusLabel = new JLabel("");
        gameStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gameStatusLabel.setForeground(new Color(255, 100, 100));
        gameStatusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(gameStatusLabel);
        
        sidePanel.add(Box.createVerticalStrut(20));
        
        // Кнопки управления
        JButton newGameButton = createSideButton("🔄 Новая игра", () -> {
            gameLogic = new GameLogic();
            selectedX = -1;
            selectedY = -1;
            possibleMoves.clear();
            updateBoard();
            updateGameStatus();
        });
        
        JButton backToMenuButton = createSideButton("🏠 Главное меню", () -> {
            dispose();
            SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
        });
        
        JButton exitButton = createSideButton("🚪 Выход", () -> System.exit(0));
        
        sidePanel.add(newGameButton);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(backToMenuButton);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(exitButton);
        
        sidePanel.add(Box.createVerticalStrut(20));
        
        // Панель истории ходов
        JPanel historyPanel = createHistoryPanel();
        sidePanel.add(historyPanel);
        
        sidePanel.add(Box.createVerticalStrut(20));
        
        // Информационная панель
        JPanel infoPanel = createInfoPanel();
        sidePanel.add(infoPanel);
        
        // Сохраняем ссылки на лейблы для обновления
        this.currentPlayerLabel = currentPlayerLabel;
        this.gameStatusLabel = gameStatusLabel;
        
        return sidePanel;
    }
    
    private JButton createSideButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(TEXT_COLOR);
        button.setBackground(new Color(70, 130, 180));
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 40));
        
        // Эффект при наведении
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });
        
        button.addActionListener(e -> action.run());
        
        return button;
    }
    
    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(60, 60, 60));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        
        JLabel infoTitle = new JLabel("ℹ️ Информация");
        infoTitle.setFont(new Font("Arial", Font.BOLD, 14));
        infoTitle.setForeground(TEXT_COLOR);
        infoTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(infoTitle);
        
        infoPanel.add(Box.createVerticalStrut(10));
        
        JLabel infoText = new JLabel("<html><div style='text-align: center; font-size: 11px;'>" +
            "• Кликните на фигуру для выбора<br>" +
            "• Кликните на клетку для хода<br>" +
            "• Повторный клик снимает выделение<br>" +
            "• Играйте по правилам шахмат</div></html>");
        infoText.setForeground(new Color(200, 200, 200));
        infoText.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(infoText);
        
        return infoPanel;
    }
    
    private JPanel createHistoryPanel() {
        JPanel historyPanel = new JPanel();
        historyPanel.setBackground(new Color(60, 60, 60));
        historyPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        
        JLabel historyTitle = new JLabel("📜 История ходов");
        historyTitle.setFont(new Font("Arial", Font.BOLD, 14));
        historyTitle.setForeground(TEXT_COLOR);
        historyTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        historyPanel.add(historyTitle);
        
        historyPanel.add(Box.createVerticalStrut(10));
        
        // Создаем список истории ходов
        moveHistoryModel = new DefaultListModel<>();
        moveHistoryList = new JList<>(moveHistoryModel);
        moveHistoryList.setFont(new Font("Arial", Font.PLAIN, 11));
        moveHistoryList.setForeground(new Color(200, 200, 200));
        moveHistoryList.setBackground(new Color(45, 45, 45));
        moveHistoryList.setSelectionBackground(new Color(70, 130, 180));
        moveHistoryList.setSelectionForeground(TEXT_COLOR);
        
        JScrollPane scrollPane = new JScrollPane(moveHistoryList);
        scrollPane.setPreferredSize(new Dimension(180, 120));
        scrollPane.setMaximumSize(new Dimension(180, 120));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setBackground(new Color(45, 45, 45));
        
        historyPanel.add(scrollPane);
        
        return historyPanel;
    }
    
    private JLabel currentPlayerLabel;
    private JLabel gameStatusLabel;
    private JList<String> moveHistoryList;
    private DefaultListModel<String> moveHistoryModel;
    private boolean isAnimating = false;
    private List<Position> possibleMoves = new ArrayList<>();
    
    private void handleSquareClick(int x, int y) {
        // Не позволяем делать ходы после окончания игры
        if (gameLogic.isGameOver()) {
            return;
        }
        
        if (selectedX == -1) {
            // Выбираем фигуру
            Piece piece = gameLogic.getBoard().getPiece(x, y);
            if (piece != null && piece.getColor() == gameLogic.getCurrentPlayer()) {
                selectedX = x;
                selectedY = y;
                // Получаем возможные ходы для выбранной фигуры
                possibleMoves = gameLogic.getPossibleMoves(x, y);
                updateBoard();
            }
        } else {
            // Если кликнули на ту же клетку, снимаем выделение
            if (x == selectedX && y == selectedY) {
                selectedX = -1;
                selectedY = -1;
                possibleMoves.clear();
                updateBoard();
                return;
            }
            
            // Делаем ход с анимацией
            if (isValidMove(selectedX, selectedY, x, y)) {
                animateMove(selectedX, selectedY, x, y);
            } else {
                // Неверный ход, снимаем выделение
                selectedX = -1;
                selectedY = -1;
                possibleMoves.clear();
                updateBoard();
            }
        }
    }
    
    private boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        Piece piece = gameLogic.getBoard().getPiece(fromX, fromY);
        if (piece == null || piece.getColor() != gameLogic.getCurrentPlayer()) {
            return false;
        }
        
        // Проверяем базовые правила движения
        return gameLogic.isValidMove(piece, toX, toY);
    }
    
    private boolean isPossibleMove(int x, int y) {
        for (Position pos : possibleMoves) {
            if (pos.getX() == x && pos.getY() == y) {
                return true;
            }
        }
        return false;
    }
    
    private void updateGameStatus() {
        if (currentPlayerLabel != null) {
            String playerText = gameLogic.getCurrentPlayer() == Core.Color.WHITE ? "Белые" : "Черные";
            String checkText = "";
            
            // Проверяем шах
            Core.Color opponentColor = (gameLogic.getCurrentPlayer() == Core.Color.WHITE) ? Core.Color.BLACK : Core.Color.WHITE;
            if (gameLogic.isInCheck(opponentColor)) {
                checkText = " - ШАХ!";
            }
            
            currentPlayerLabel.setText("Ход: " + playerText + checkText);
        }
        
        // Обновляем состояние игры
        if (gameStatusLabel != null) {
            String status = gameLogic.getGameStatus();
            gameStatusLabel.setText(status);
            
            // Показываем диалог при окончании игры
            if (!status.isEmpty()) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, status, "Игра окончена", JOptionPane.INFORMATION_MESSAGE);
                });
            }
        }
    }
    
    private void updateBoard() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                JPanel square = squares[x][y];
                square.removeAll();
                
                // Восстанавливаем цвет клетки
                if ((x + y) % 2 == 0) {
                    square.setBackground(LIGHT_SQUARE);
                } else {
                    square.setBackground(DARK_SQUARE);
                }
                
                // Выделяем выбранную клетку
                if (x == selectedX && y == selectedY) {
                    square.setBackground(SELECTED_COLOR);
                }
                
                // Подсвечиваем возможные ходы
                if (isPossibleMove(x, y)) {
                    square.setBackground(POSSIBLE_MOVE_COLOR);
                }
                
                // Отображаем фигуру
                Piece piece = gameLogic.getBoard().getPiece(x, y);
                if (piece != null) {
                    JLabel pieceLabel = new JLabel(getPieceSymbol(piece), JLabel.CENTER);
                    pieceLabel.setFont(new Font("Arial", Font.BOLD, 28));
                    pieceLabel.setForeground(piece.getColor() == Core.Color.WHITE ? Color.WHITE : Color.BLACK);
                    square.add(pieceLabel, BorderLayout.CENTER);
                }
                
                // Обновляем границы клетки
                updateSquareBorder(square, x, y);
                
                square.revalidate();
                square.repaint();
            }
        }
    }
    
    private void updateSquareBorder(JPanel square, int x, int y) {
        // Сбрасываем границу к стандартной
        square.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
        
        // Если клетка не выбрана и нет наведения мыши, оставляем стандартную границу
        if (selectedX != x || selectedY != y) {
            // Ничего не делаем - стандартная граница
        }
    }
    
    private String getPieceSymbol(Piece piece) {
        switch (piece.getType()) {
            case KING:
                return piece.getColor() == Core.Color.WHITE ? "♔" : "♚";
            case QUEEN:
                return piece.getColor() == Core.Color.WHITE ? "♕" : "♛";
            case ROOK:
                return piece.getColor() == Core.Color.WHITE ? "♖" : "♜";
            case BISHOP:
                return piece.getColor() == Core.Color.WHITE ? "♗" : "♝";
            case KNIGHT:
                return piece.getColor() == Core.Color.WHITE ? "♘" : "♞";
            case PAWN:
                return piece.getColor() == Core.Color.WHITE ? "♙" : "♟";
            default:
                return "";
        }
    }
    
    private void animateMove(int fromX, int fromY, int toX, int toY) {
        if (isAnimating) {
            return; // Не позволяем новую анимацию во время текущей
        }
        
        Piece piece = gameLogic.getBoard().getPiece(fromX, fromY);
        if (piece == null) {
            return;
        }
        
        // Создаем лейбл фигуры для анимации
        JLabel pieceLabel = new JLabel(getPieceSymbol(piece), JLabel.CENTER);
        pieceLabel.setFont(new Font("Arial", Font.BOLD, 28));
        pieceLabel.setForeground(piece.getColor() == Core.Color.WHITE ? Color.WHITE : Color.BLACK);
        
        // Создаем анимацию
        PieceAnimation animation = new PieceAnimation(
            squares[fromX][fromY], 
            squares[toX][toY], 
            pieceLabel,
            () -> {
                // Callback после завершения анимации
                isAnimating = false;
                executeMove(fromX, fromY, toX, toY);
            }
        );
        
        isAnimating = true;
        animation.start();
    }
    
    private void executeMove(int fromX, int fromY, int toX, int toY) {
        // Выполняем ход
        if (gameLogic.makeMove(fromX, fromY, toX, toY)) {
            selectedX = -1;
            selectedY = -1;
            possibleMoves.clear();
            updateBoard();
            updateGameStatus();
            updateMoveHistory();
        } else {
            // Если ход не удался, снимаем выделение
            selectedX = -1;
            selectedY = -1;
            possibleMoves.clear();
            updateBoard();
        }
    }
    
    private void updateMoveHistory() {
        if (moveHistoryModel != null) {
            Move lastMove = gameLogic.getLastMove();
            if (lastMove != null) {
                moveHistoryModel.addElement(lastMove.toString());
                // Автоматически прокручиваем к последнему ходу
                moveHistoryList.ensureIndexIsVisible(moveHistoryModel.getSize() - 1);
            }
        }
    }
}
