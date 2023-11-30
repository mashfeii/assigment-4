package Insects;

import Abstract.BoardEntity;
import Abstract.Insect;
import Classes.EntityPosition;
import Enums.Direction;
import Enums.InsectColor;
import Interfaces.DiagonalMoving;

import java.util.Map;

public class Spider extends Insect implements DiagonalMoving {
    public Spider(EntityPosition position, InsectColor color) {
        super(position, color);
    }

    @Override
    public Direction getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {
        return null;
    }

    @Override
    public int travelDirection(Direction dir, Map<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }

    @Override
    public int getDiagonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition, Map<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }

    @Override
    public int travelDiagonally(Direction dir, EntityPosition entityPosition, InsectColor color, Map<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }
}
