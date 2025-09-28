import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private static final Color BACKGROUND_COLOR = new Color(45, 45, 45);
    private static final Color BUTTON_COLOR = new Color(70, 130, 180);
    private static final Color BUTTON_HOVER_COLOR = new Color(100, 149, 237);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    
    public MainMenu() {
        initializeMainMenu();
    }
    
    private void initializeMainMenu() {
        setTitle("Chess 2 - Главное меню");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        // Устанавливаем темную тему
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        // Создаем главную панель
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        // Заголовок
        JLabel titleLabel = createTitleLabel();
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Панель кнопок
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        // Информационная панель
        JPanel infoPanel = createInfoPanel();
        mainPanel.add(infoPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("♔ CHESS 2 ♚");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        return titleLabel;
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0, 15));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Кнопка "Новая игра"
        JButton newGameButton = createStyledButton("🎮 Новая игра", () -> {
            dispose();
            SwingUtilities.invokeLater(() -> new ChessGame().setVisible(true));
        });
        
        // Кнопка "Правила"
        JButton rulesButton = createStyledButton("📖 Правила игры", this::showRulesDialog);
        
        // Кнопка "О программе"
        JButton aboutButton = createStyledButton("ℹ️ О программе", this::showAboutDialog);
        
        // Кнопка "Выход"
        JButton exitButton = createStyledButton("🚪 Выход", () -> System.exit(0));
        
        buttonPanel.add(newGameButton);
        buttonPanel.add(rulesButton);
        buttonPanel.add(aboutButton);
        buttonPanel.add(exitButton);
        
        return buttonPanel;
    }
    
    private JButton createStyledButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(TEXT_COLOR);
        button.setBackground(BUTTON_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Эффект при наведении
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_COLOR);
            }
        });
        
        button.addActionListener(e -> action.run());
        
        return button;
    }
    
    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new FlowLayout());
        infoPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel infoLabel = new JLabel("Версия 0.1 | Современный интерфейс");
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        infoLabel.setForeground(new Color(200, 200, 200));
        
        infoPanel.add(infoLabel);
        return infoPanel;
    }
    
    private void showRulesDialog() {
        String rules = """
            <html>
            <body style='font-family: Arial; font-size: 12px;'>
            <h2>Правила игры в шахматы:</h2>
            <p><b>Цель игры:</b> Поставить мат королю противника</p>
            
            <h3>Движение фигур:</h3>
            <ul>
            <li><b>Пешка:</b> На 1 клетку вперед, первый ход - на 2 клетки</li>
            <li><b>Ладья:</b> По горизонтали и вертикали</li>
            <li><b>Конь:</b> Буквой "Г" (2+1 клетки)</li>
            <li><b>Слон:</b> По диагонали</li>
            <li><b>Ферзь:</b> Во всех направлениях</li>
            <li><b>Король:</b> На 1 клетку в любом направлении</li>
            </ul>
            
            <h3>Управление:</h3>
            <ul>
            <li>Кликните на фигуру для выбора</li>
            <li>Кликните на клетку для хода</li>
            <li>Повторный клик снимает выделение</li>
            </ul>
            </body>
            </html>
            """;
        
        JOptionPane.showMessageDialog(this, rules, "Правила игры", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showAboutDialog() {
        String about = """
            <html>
            <body style='font-family: Arial; font-size: 12px;'>
            <h2>♔ Chess 2 ♚</h2>
            <p><b>Версия:</b> 0.1</p>
            <p><b>Разработчик:</b> AI Assistant</p>
            <p><b>Технологии:</b> Java Swing</p>
            
            <p>Современная реализация классической шахматной игры<br>
            с красивым интерфейсом и полным набором функций.</p>
            
            <p><b>Особенности:</b></p>
            <ul>
            <li>Современный дизайн</li>
            <li>Проверка правильности ходов</li>
            <li>Обнаружение шаха</li>
            <li>Полное покрытие тестами</li>
            </ul>
            </body>
            </html>
            """;
        
        JOptionPane.showMessageDialog(this, about, "О программе", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}
