package coursework;

public class IncorrectArgumentException extends RuntimeException{

    public IncorrectArgumentException(String argument) {
        super(argument);
    }
}