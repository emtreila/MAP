package exceptions;

public class ListException extends RuntimeException {
    public ListException() {
        super();
    }

    public ListException(String message) {
        super(message);
    }
}
