import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessGUI extends JFrame {
    private GameLogic gameLogic;
    private JPanel[][] squares;
    private int selectedX = -1, selectedY = -1;
    private static final int SQUARE_SIZE = 60;
    private static final Color LIGHT_SQUARE = new Color(240, 217, 181);
    private static final Color DARK_SQUARE = new Color(181, 136, 99);
    private static final Color SELECTED_COLOR = new Color(255, 255, 0, 100);
    
    public ChessGUI() {
        gameLogic = new GameLogic();
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Шахматы");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Создаем меню
        createMenuBar();
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Создаем доску
        JPanel boardPanel = createBoard();
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        
        // Панель информации
        JPanel infoPanel = createInfoPanel();
        mainPanel.add(infoPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // Меню "Игра"
        JMenu gameMenu = new JMenu("Игра");
        
        JMenuItem newGameItem = new JMenuItem("Новая игра");
        newGameItem.addActionListener(e -> startNewGame());
        gameMenu.add(newGameItem);
        
        gameMenu.addSeparator();
        
        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.addActionListener(e -> System.exit(0));
        gameMenu.add(exitItem);
        
        menuBar.add(gameMenu);
        
        // Меню "Справка"
        JMenu helpMenu = new JMenu("Справка");
        
        JMenuItem aboutItem = new JMenuItem("О программе");
        aboutItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutItem);
        
        JMenuItem rulesItem = new JMenuItem("Правила игры");
        rulesItem.addActionListener(e -> showRulesDialog());
        helpMenu.add(rulesItem);
        
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
    }
    
    private void startNewGame() {
        gameLogic = new GameLogic();
        selectedX = -1;
        selectedY = -1;
        updateBoard();
        if (currentPlayerLabel != null) {
            currentPlayerLabel.setText("Ход: Белые");
        }
    }
    
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this,
            "Шахматы на Java\n\n" +
            "Версия: 1.0\n" +
            "Разработчик: AI Assistant\n\n" +
            "Простая реализация шахматной игры\n" +
            "с графическим интерфейсом.",
            "О программе",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showRulesDialog() {
        JOptionPane.showMessageDialog(this,
            "Правила игры в шахматы:\n\n" +
            "• Цель игры - поставить мат королю противника\n" +
            "• Фигуры ходят по своим правилам\n" +
            "• Нельзя ходить под шах\n" +
            "• Игроки ходят по очереди\n\n" +
            "Управление:\n" +
            "• Кликните на фигуру для выбора\n" +
            "• Кликните на клетку для хода\n" +
            "• Используйте меню для новой игры",
            "Правила игры",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private JPanel createBoard() {
        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        squares = new JPanel[8][8];
        
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                JPanel square = new JPanel(new BorderLayout());
                square.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
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
                });
                
                squares[x][y] = square;
                boardPanel.add(square);
            }
        }
        
        updateBoard();
        return boardPanel;
    }
    
    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new FlowLayout());
        
        JLabel currentPlayerLabel = new JLabel("Ход: Белые");
        currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(currentPlayerLabel);
        
        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(e -> startNewGame());
        infoPanel.add(newGameButton);
        
        // Сохраняем ссылку на лейбл для обновления
        this.currentPlayerLabel = currentPlayerLabel;
        
        return infoPanel;
    }
    
    private JLabel currentPlayerLabel;
    
    private void handleSquareClick(int x, int y) {
        if (selectedX == -1) {
            // Выбираем фигуру
            Piece piece = gameLogic.getBoard().getPiece(x, y);
            if (piece != null && piece.getColor() == gameLogic.getCurrentPlayer()) {
                selectedX = x;
                selectedY = y;
                updateBoard();
            }
        } else {
            // Делаем ход
            if (gameLogic.makeMove(selectedX, selectedY, x, y)) {
                selectedX = -1;
                selectedY = -1;
                updateBoard();
                updateCurrentPlayerLabel();
            } else {
                // Неверный ход, снимаем выделение
                selectedX = -1;
                selectedY = -1;
                updateBoard();
            }
        }
    }
    
    private void updateCurrentPlayerLabel() {
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
                
                // Отображаем фигуру
                Piece piece = gameLogic.getBoard().getPiece(x, y);
                if (piece != null) {
                    JLabel pieceLabel = new JLabel(getPieceSymbol(piece), JLabel.CENTER);
                    pieceLabel.setFont(new Font("Arial", Font.BOLD, 24));
                    pieceLabel.setForeground(piece.getColor() == Core.Color.WHITE ? Color.WHITE : Color.BLACK);
                    square.add(pieceLabel, BorderLayout.CENTER);
                }
                
                square.repaint();
            }
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
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChessGUI().setVisible(true);
        });
    }
}
