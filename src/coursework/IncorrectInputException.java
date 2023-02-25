package coursework;

public class IncorrectInputException extends RuntimeException{

    private final String argument;

    public IncorrectInputException(String argument) {
        super();
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return "Ввод не выполнен или введено некорректное значение!\n";
    }
}