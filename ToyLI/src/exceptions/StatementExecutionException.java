package exceptions;

public class StatementExecutionException extends RuntimeException {
    public StatementExecutionException() {
        super();
    }

    public StatementExecutionException(String message) {
        super(message);
    }
}
