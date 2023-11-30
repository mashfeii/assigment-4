package Interfaces;

import Abstract.BoardEntity;
import Classes.EntityPosition;
import Enums.Direction;
import Enums.InsectColor;

import java.util.Map;

public interface DiagonalMoving {
    public int getDiagonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition, Map<String, BoardEntity> boardData, int boardSize);

    /**
     * @param dir
     * @param entityPosition
     * @param color
     * @param boardData
     * @param boardSize
     * @return amount of eaten food and update the board
     */
    public int travelDiagonally(Direction dir, EntityPosition entityPosition, InsectColor color, Map<String, BoardEntity> boardData, int boardSize);
}
