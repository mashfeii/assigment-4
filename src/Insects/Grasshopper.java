package Insects;

import Abstract.BoardEntity;
import Abstract.Insect;
import Classes.EntityPosition;
import Enums.Direction;
import Enums.InsectColor;

import java.util.Map;

public class Grasshopper extends Insect {
    public Grasshopper(EntityPosition position, InsectColor color) {
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

}
