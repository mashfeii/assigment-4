package Insects;

import Abstract.BoardEntity;
import Abstract.Insect;
import Classes.EntityPosition;
import Enums.Direction;
import Enums.InsectColor;
import Interfaces.OrthogonalMoving;

import java.util.Map;

public class Butterfly extends Insect implements OrthogonalMoving {
    public Butterfly(EntityPosition position, InsectColor color) {
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
    public int getOrthogonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition, Map<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }

    @Override
    public int travelOrthogonally(Direction dir, EntityPosition entityPosition, InsectColor color, Map<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }


}
