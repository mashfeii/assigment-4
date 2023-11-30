package Classes;

import Abstract.BoardEntity;
import Abstract.Insect;
import Enums.Direction;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<EntityPosition, BoardEntity> boardData = new HashMap<>();
    private int size;

    public Board(int size) {
        this.size = size;
    }

    public void addEntity(BoardEntity entity) {
        this.boardData.put(entity.getEntityPosition(), entity);
    }

    public BoardEntity getEntity(EntityPosition position) {
        return this.boardData.get(position);
    }

    /**
     * @param insect chosen insect on the board
     * @return direction which this insect will choose
     */
    public Direction getDirection(Insect insect) {
        return null;
    }

    /**
     * @param insect chosen insect on the board
     * @return amount of food it collects
     */
    public int getDirectionSum(Insect insect) {
        Direction chosenDirection = this.getDirection(insect);
        return 0;
    }

    public Map<EntityPosition, BoardEntity> getBoardData() {
        return boardData;
    }

    public int getSize() {
        return size;
    }
}
