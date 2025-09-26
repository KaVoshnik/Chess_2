/**
 * Класс, представляющий шахматную фигуру
 */
public class Piece {
    public Core.PieceType type;
    public Core.Color color;
    public Position position;

    public Piece(Core.PieceType type, Core.Color color, Position position) {
        this.type = type;
        this.color = color;
        this.position = position;
    }

    public Core.PieceType getType() {
        return type;
    }

    public Core.Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Piece{" + "type = " + type + ", color = " + color + ", position = " + position + '}';
    }
}