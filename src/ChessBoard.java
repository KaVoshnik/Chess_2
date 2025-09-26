/**
 * Класс шахматной доски
 */
public class ChessBoard {
    private final int[][] pieces = new int[8][8];

    public int getPiece(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException("Invalid position");
        } else {
            return pieces[x][y];
        }
    }
}
