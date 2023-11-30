package Exceptions;

public class InvalidInsectColorException extends Exception {
    @Override
    public String getMessage() {
        return "Invalid insect color ";
    }
}
