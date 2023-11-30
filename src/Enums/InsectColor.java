package Enums;

import Exceptions.Execution;
import Exceptions.InvalidInsectColorException;

import java.io.IOException;

public enum InsectColor {
    RED,
    GREEN,
    BLUE,
    YELLOW;

    public static InsectColor toColor(String s) throws IOException, InvalidInsectColorException {
        switch (s) {
            case "Red":
                return RED;
            case "Green":
                return GREEN;
            case "Blue":
                return BLUE;
            case "Yellow":
                return YELLOW;
            default:
                throw new InvalidInsectColorException();
        }
    }
}
