package Exceptions;

public class EmptyContainerException extends Exception{
    public EmptyContainerException() {
        super();
    }

    public EmptyContainerException(String message) {
        super(message);
    }
}
