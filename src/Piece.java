/**
 * Класс, представляющий шахматную фигуру
 */
public class Piece {
    private final Core.PieceType type;
    private final Core.Color color;
    private final Position position;

    public Piece(Core.PieceType type, Core.Color color, Position position) {
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        } else if (position == null) {
            throw new IllegalArgumentException("position cannot be null");
        } else if (color == null) {
            throw new IllegalArgumentException("color cannot be null");
        }

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