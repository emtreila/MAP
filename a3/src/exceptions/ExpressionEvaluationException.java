package exceptions;

public class ExpressionEvaluationException extends RuntimeException {
    public ExpressionEvaluationException() {
        super();
    }
    public ExpressionEvaluationException(String message) {
        super(message);
    }
}
