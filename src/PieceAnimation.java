import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс для анимации перемещения шахматных фигур
 */
public class PieceAnimation {
    private static final int ANIMATION_DURATION = 300; // миллисекунды
    private static final int ANIMATION_STEPS = 20;
    
    private JPanel sourceSquare;
    private JPanel targetSquare;
    private JLabel animatedPiece;
    private Timer animationTimer;
    private int currentStep;
    private Point startPoint;
    private Point endPoint;
    private Runnable onComplete;
    
    public PieceAnimation(JPanel sourceSquare, JPanel targetSquare, JLabel pieceLabel, Runnable onComplete) {
        this.sourceSquare = sourceSquare;
        this.targetSquare = targetSquare;
        this.animatedPiece = new JLabel(pieceLabel.getText());
        this.animatedPiece.setFont(pieceLabel.getFont());
        this.animatedPiece.setForeground(pieceLabel.getForeground());
        this.onComplete = onComplete;
        
        // Получаем координаты относительно родительского контейнера
        this.startPoint = getSquareCenter(sourceSquare);
        this.endPoint = getSquareCenter(targetSquare);
        
        setupAnimation();
    }
    
    private void setupAnimation() {
        // Создаем таймер для анимации
        animationTimer = new Timer(ANIMATION_DURATION / ANIMATION_STEPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
            }
        });
        
        // Настраиваем анимированную фигуру
        animatedPiece.setSize(animatedPiece.getPreferredSize());
        animatedPiece.setLocation(startPoint.x - animatedPiece.getWidth() / 2, 
                                 startPoint.y - animatedPiece.getHeight() / 2);
        
        // Добавляем фигуру к родительскому контейнеру
        Container parent = sourceSquare.getParent();
        parent.add(animatedPiece);
        parent.setComponentZOrder(animatedPiece, 0); // Поверх всех остальных компонентов
        
        currentStep = 0;
    }
    
    private Point getSquareCenter(JPanel square) {
        Point squareLocation = square.getLocation();
        Dimension squareSize = square.getSize();
        
        // Получаем координаты относительно родительского контейнера
        Container parent = square.getParent();
        Point parentLocation = parent.getLocationOnScreen();
        Point squareScreenLocation = square.getLocationOnScreen();
        
        int centerX = squareScreenLocation.x - parentLocation.x + squareSize.width / 2;
        int centerY = squareScreenLocation.y - parentLocation.y + squareSize.height / 2;
        
        return new Point(centerX, centerY);
    }
    
    private void updateAnimation() {
        currentStep++;
        
        if (currentStep >= ANIMATION_STEPS) {
            // Анимация завершена
            finishAnimation();
            return;
        }
        
        // Вычисляем текущую позицию с использованием easing функции
        double progress = (double) currentStep / ANIMATION_STEPS;
        double easedProgress = easeInOutCubic(progress);
        
        int currentX = (int) (startPoint.x + (endPoint.x - startPoint.x) * easedProgress);
        int currentY = (int) (startPoint.y + (endPoint.y - startPoint.y) * easedProgress);
        
        animatedPiece.setLocation(currentX - animatedPiece.getWidth() / 2, 
                                 currentY - animatedPiece.getHeight() / 2);
        
        animatedPiece.repaint();
    }
    
    private double easeInOutCubic(double t) {
        return t < 0.5 ? 4 * t * t * t : 1 - Math.pow(-2 * t + 2, 3) / 2;
    }
    
    private void finishAnimation() {
        animationTimer.stop();
        
        // Удаляем анимированную фигуру
        Container parent = animatedPiece.getParent();
        if (parent != null) {
            parent.remove(animatedPiece);
            parent.repaint();
        }
        
        // Вызываем callback
        if (onComplete != null) {
            onComplete.run();
        }
    }
    
    public void start() {
        animationTimer.start();
    }
    
    public void stop() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
        finishAnimation();
    }
}
