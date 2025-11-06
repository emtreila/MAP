package exceptions;

public class DictionaryException extends RuntimeException {
    public DictionaryException() {
        super();
    }

    public DictionaryException(String message) {
        super(message);
    }
}
