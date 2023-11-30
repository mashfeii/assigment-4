package Abstract;

import Classes.EntityPosition;
import Enums.Direction;
import Enums.InsectColor;

import java.util.Map;

public abstract class Insect extends BoardEntity {
    protected InsectColor color;

    public Insect(EntityPosition position, InsectColor color) {
        this.entityPosition = position;
        this.color = color;
    }

    abstract public Direction getBestDirection(Map<String, BoardEntity> boardData, int boardSize);

    /**
     * @param dir direction of travelling
     * @param boardData current board parameters
     * @param boardSize current board size
     * @return amount of eaten food and update the board
     */
    abstract public int travelDirection(Direction dir, Map<String, BoardEntity> boardData, int boardSize);

    public InsectColor getColor() {
        return color;
    }
}
