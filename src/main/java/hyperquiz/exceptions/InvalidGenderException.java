package hyperquiz.exceptions;

public class InvalidGenderException extends RuntimeException {
    public InvalidGenderException() {
        super();
    }

    public InvalidGenderException(String message) {
        super(message);
    }

    public InvalidGenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidGenderException(Throwable cause) {
        super(cause);
    }
}
